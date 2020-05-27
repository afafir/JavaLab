package coHelp.service;

import coHelp.dto.user.ConsumerDto;
import coHelp.dto.user.UserDto;
import coHelp.dto.user.VolunteerDto;
import coHelp.model.document.Avatar;
import coHelp.model.task.Task;
import coHelp.model.user.Consumer;
import coHelp.model.user.Role;
import coHelp.model.user.User;
import coHelp.model.user.Volunteer;
import coHelp.repository.AvatarRepository;
import coHelp.repository.TaskRepository;
import coHelp.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.beans.Transient;
import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    DocumentService documentService;

    @Autowired
    AvatarRepository avatarRepository;

    @Override
    public UserDto getProfile(Long id) {
        Optional<User> optionalUser = userRepository.find(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getRole().equals(Role.VOLUNTEER)) {
                return VolunteerDto.builder()
                        .id(user.getId())
                        .tasks((((Volunteer) user).getTasks()))
                        .address(user.getAddress())
                        .email(user.getEmail())
                        .name(user.getName())
                        .surname(user.getEmail())
                        .phone(user.getPhone())
                        .role(user.getRole())
                        .avatarLink(user.getAvatar() == null ? "user_image/default.jpg" : "user_image"+ "/" + user.getAvatar().getFileName()+user.getAvatar().getExtension())
                        .build();
            } else return ConsumerDto.builder()
                    .id(user.getId())
                    .tasks(((Consumer) user).getTasks())
                    .address(user.getAddress())
                    .email(user.getEmail())
                    .name(user.getName())
                    .role(user.getRole())
                    .surname(user.getEmail())
                    .phone(user.getPhone())
                    .avatarLink(user.getAvatar() == null ? "resources/user_image/default.jpg" : "resources/user_image/" + user.getAvatar().getFileName()+user.getAvatar().getExtension())
                    .build();
        } else throw new NotFoundException("Such user doesnt exist");
    }

    @Override
    public List<Task> getMyTasks(User user) {
        if (user.getRole().equals(Role.VOLUNTEER)) {
            return taskRepository.findForVolunteer((Volunteer) user);
        } else return taskRepository.findForConsumer((Consumer) user);

    }

    private static final String STORAGE_PATH = "C:\\resources\\user_image";


    @SneakyThrows
    @Override
    @Transactional
    public boolean uploadAvatar(CommonsMultipartFile multipartFile, User user) {
        Optional<Avatar> avatarOptional = avatarRepository.findByOwner(user);
        String path = STORAGE_PATH + "/" +
                UUID.randomUUID().toString() +
                multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        File toSave = new File(path);
        if (!Files.probeContentType(toSave.toPath()).contains("image")){
            return false;
        }
        multipartFile.transferTo(new File(path));
        Avatar avatar;
        user = userRepository.find(user.getId()).get();
        if (avatarOptional.isPresent()) {
            //delete current file
            avatar = avatarOptional.get();
            File currentFile = new File(avatar.getPath());
            currentFile.delete();
            avatar.setSourceFile(toSave);
            avatar.setPath(toSave.toPath().toString());
            avatar.setFileName(toSave.getName());
            user.setAvatar(avatar);
           // avatar = (Avatar) documentService.updateDocument(avatar, toSave);
        } else {
            avatar = Avatar.builder()
                    .fileName(multipartFile.getName())
                    .owner(user)
                    .sourceFile(toSave).build();
            user.setAvatar(avatar);
        }

        return true;
    }
}




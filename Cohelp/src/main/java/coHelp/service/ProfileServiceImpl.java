package coHelp.service;

import coHelp.dto.user.ConsumerDto;
import coHelp.dto.user.UserDto;
import coHelp.dto.user.VolunteerDto;
import coHelp.model.task.Task;
import coHelp.model.user.Consumer;
import coHelp.model.user.Role;
import coHelp.model.user.User;
import coHelp.model.user.Volunteer;
import coHelp.repository.TaskRepository;
import coHelp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;

    @Override
    public UserDto getProfile(Long id) {
        Optional<User> optionalUser = userRepository.find(id);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
        if (user.getRole().equals(Role.VOLUNTEER)) {
            return VolunteerDto.builder()
                    .tasks((((Volunteer)user).getTasks()))
                    .address(user.getAddress())
                    .email(user.getEmail())
                    .name(user.getName())
                    .surname(user.getEmail())
                    .phone(user.getPhone())
                    .role(user.getRole())
                    .build();
        } else return ConsumerDto.builder()
                .tasks(((Consumer)user).getTasks())
                .address(user.getAddress())
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole())
                .surname(user.getEmail())
                .phone(user.getPhone())
                .build();}
        else throw  new NotFoundException("Such user doesnt exist");
    }

    @Override
    public List<Task> getMyTasks(User user) {

        if (user.getRole().equals(Role.VOLUNTEER)){
            return taskRepository.findForVolunteer((Volunteer) user);
        } else return taskRepository.findForConsumer((Consumer) user);

    }


}

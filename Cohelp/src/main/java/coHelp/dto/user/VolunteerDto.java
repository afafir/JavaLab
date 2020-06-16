package coHelp.dto.user;

import coHelp.model.task.Task;
import coHelp.model.user.User;
import coHelp.model.user.Volunteer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class VolunteerDto extends UserDto {
    private List<Task> tasks;

    public static VolunteerDto toVolunteerDto(User user){
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
    }
}

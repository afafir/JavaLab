package coHelp.dto.user;

import coHelp.model.task.Task;
import coHelp.model.user.Consumer;
import coHelp.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ConsumerDto extends UserDto {
    private List<Task> tasks;

    public  static ConsumerDto toConsumerDto(User user){
        return ConsumerDto.builder()
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
    }
}

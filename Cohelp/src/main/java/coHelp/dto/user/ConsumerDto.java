package coHelp.dto.user;

import coHelp.model.task.Task;
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
}

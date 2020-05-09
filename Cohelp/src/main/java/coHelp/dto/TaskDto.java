package coHelp.dto;

import coHelp.model.task.Task;
import coHelp.model.task.Type;
import coHelp.model.user.Consumer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto {
    private String description;
    private String type;
    private long consumer;

}

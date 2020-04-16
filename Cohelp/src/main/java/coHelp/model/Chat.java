package coHelp.model;

import coHelp.model.task.Task;
import lombok.*;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project_chat", schema = "cohelp1")
@NamedQuery(
        name="findChatForTask",
        query="SELECT chat FROM Chat chat WHERE chat.task = :task "
)
@Scope("TaskScope")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_task")
    private Task task;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Message> messageList;
}

package coHelp.model.user;

import coHelp.model.task.Task;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@SuperBuilder
@ToString(exclude = "tasks")
@JsonIgnoreProperties(value = "tasks")
@Table(name = "project_consumer", schema = "cohelp1")
public class Consumer extends User implements Serializable {


    @OneToMany(mappedBy = "consumer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Task> tasks;


}

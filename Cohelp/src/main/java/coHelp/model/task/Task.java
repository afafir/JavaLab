package coHelp.model.task;

import coHelp.model.user.Consumer;
import coHelp.model.user.Volunteer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project_task", schema = "cohelp1")
@JsonIgnoreProperties (     value = {"consumer", "volunteer"})
@ToString(exclude = {"consumer","volunteer"})
@NamedQueries({
@NamedQuery(
        name="findTasksForVolunteer",
        query="SELECT task FROM Task task WHERE task.volunteer = :volunteer "
),
@NamedQuery(
        name="findTasksForConsumer",
        query="SELECT task FROM Task task WHERE task.consumer = :consumer "
)})

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name ="consumer_Id")
    private Consumer consumer;

    @ManyToOne
    @JoinColumn(name ="volunteer_Id")
    private Volunteer volunteer;
    @Enumerated(EnumType.STRING)
    private State state;
    @Enumerated(EnumType.STRING)
    private Type type;
    private String description;

}

package coHelp.model.task;

import coHelp.dto.TaskGetDto;
import coHelp.model.user.Consumer;
import coHelp.model.user.Volunteer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project_task", schema = "cohelp1")
@JsonIgnoreProperties(value = {"consumer", "volunteer"})
@ToString(exclude = {"consumer", "volunteer"})
@NamedQueries({
        @NamedQuery(
                name = "findTasksForVolunteer",
                query = "SELECT task FROM Task task WHERE task.volunteer = :volunteer "
        ),
        @NamedQuery(
                name = "findTasksForConsumer",
                query = "SELECT task FROM Task task WHERE task.consumer = :consumer "
        )})

public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "consumer_Id")
    private Consumer consumer;

    @ManyToOne
    @JoinColumn(name = "volunteer_Id")
    private Volunteer volunteer;
    @Enumerated(EnumType.STRING)
    private State state;
    @Enumerated(EnumType.STRING)
    private Type type;
    private String description;

    public TaskGetDto toTaskGetDto(){
        return  TaskGetDto.builder()
                .description(description)
                .id(id)
                .street(consumer.getAddress().getStreet())
                .district(consumer.getAddress().getDistrict())
                .build();

    }

}

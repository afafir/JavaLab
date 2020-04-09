package coHelp.model.user;


import coHelp.model.task.Task;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data

@SuperBuilder
@Table(name="project_volunteer", schema = "cohelp1")
public class Volunteer extends User implements Serializable {



    private String xd;



    @OneToMany(mappedBy = "volunteer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Task> tasks;




}

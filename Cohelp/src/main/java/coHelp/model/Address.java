package coHelp.model;

import coHelp.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = "user")
@Table(name = "project_address", schema = "cohelp1")
@JsonIgnoreProperties(value = "user")
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String street;
    private String district;
    private String house;
    @OneToOne(mappedBy = "address", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user;


    public String getStringAddress (){
        return city+" "+district+" "+street+" "+house;
    }
}

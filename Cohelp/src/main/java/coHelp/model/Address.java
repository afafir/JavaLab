package coHelp.model;

import coHelp.model.user.User;
import lombok.*;

import javax.persistence.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = "user")
@Table(name = "project_address", schema = "cohelp1")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String street;
    private String district;
    private String house;
    @OneToOne(mappedBy = "address", fetch = FetchType.EAGER, cascade = CascadeType.ALL  )
    private User user;
}

package coHelp.model.user;

import coHelp.model.Address;
import coHelp.model.document.Avatar;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project_user", schema = "cohelp1")
@NamedQueries({
        @NamedQuery(name = "findByEmail",
        query = "SELECT user FROM User user WHERE user.email = :email "),
        @NamedQuery(name = "findInformation",
        query = "select new coHelp.dto.DocumentInformationDto(user.email, (sum (avatar.size))/1024) From User user left join user.avatar " + "" +
                "as avatar where user.id=:userId group by user.id")
})

@Inheritance(
        strategy = InheritanceType.JOINED
)
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    private String surname;
    @Column(unique = true)
    private String phone;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_address")
    private Address address;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private State state;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_avatar")
    private Avatar avatar;
    public Volunteer toVolunteer() {
        return Volunteer.builder().address(this.address)
                .email(this.email)
                .password(password)
                .name(name)
                .surname(surname)
                .phone(phone)
                .role(role)
                .xd("kek")
                .state(state).build();
    }

    public Consumer toConsumer() {
        return Consumer.builder().address(this.address)
                .email(this.email)
                .password(password)
                .name(name)
                .surname(surname)
                .phone(phone)
                .role(role)
                .state(state).build();

    }
}

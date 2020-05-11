package coHelp.model.user;

import coHelp.model.Address;
import coHelp.model.document.Avatar;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project_user", schema = "cohelp1")
@NamedQuery(
        name = "findByEmail",
        query = "SELECT user FROM User user WHERE user.email = :email "
)
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class User {
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

    @OneToOne(cascade = CascadeType.ALL)
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

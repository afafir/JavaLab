package coHelp.dto.user;

import coHelp.model.Address;
import coHelp.model.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserDto {
    private String email;
    private String name;
    private String surname;
    private String phone;
    private Role role;
    private Address address;
    private String avatarLink;

}

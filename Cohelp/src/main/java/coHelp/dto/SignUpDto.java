package coHelp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpDto {
    private String name;
    private String surname;
    private String role;
    private String city;
    private String street;
    private String house;
    private String tel;
    @Email(message = "{Email.signUpDto.email}")
    private String email;
    @Size(min = 6, max = 20, message = "{Size.signUpDto.password}")
    private String password;

}

package coHelp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

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
    private String email;
    private String password;

}

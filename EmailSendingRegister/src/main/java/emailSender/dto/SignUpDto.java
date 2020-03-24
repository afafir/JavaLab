package emailSender.dto;

import emailSender.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SignUpDto {
    private String name;
    private String password;
    private String email;


}

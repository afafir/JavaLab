package emailSender.model;


import emailSender.dto.SignUpDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private boolean enabled;


    //without password
    public static User fromDto(SignUpDto dto) {
        return User.builder().name(dto.getName())
                .email(dto.getEmail())
                .build();

    }


}

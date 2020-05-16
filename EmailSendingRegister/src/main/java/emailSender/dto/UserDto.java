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
public class UserDto {
    private Long id;
    private String name;
    private String email;

    public static UserDto from(User user) {
        return UserDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .id(user.getId())
                .build();
    }
}

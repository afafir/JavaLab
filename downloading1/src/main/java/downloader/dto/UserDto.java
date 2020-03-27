package downloader.dto;

import downloader.models.user.User;
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
    private String username;
    private String email;

    public static UserDto from(User user){
        return UserDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .id(user.getId())
                .build();
    }
}

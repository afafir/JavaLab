package coHelp.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class MessageDto {
    Long senderId;
    @NonNull
    String sender;
    @NonNull
    String message;
    Long taskId;
}

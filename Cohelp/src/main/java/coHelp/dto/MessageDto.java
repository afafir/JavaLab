package coHelp.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class MessageDto {
    @NonNull
    Long senderId;
    @NonNull
    String sender;
    @NonNull
    String message;
    Long taskId;
}

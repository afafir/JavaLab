package chat.dto;

import lombok.Data;

@Data
public class MessageDto {
    private String text;
    private Long from;
    private String userFrom;
}

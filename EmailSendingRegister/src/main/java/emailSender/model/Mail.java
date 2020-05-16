package emailSender.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Data
@Builder
public class Mail {
    private String mailFrom;

    private String mailTo;

    private String mailSubject;

    private String mailContent;

    private String contentType;

    private List<Object> attachments;

    private Map<String, Object> model;

    public Mail() {
        contentType = "text/plain";
    }
}

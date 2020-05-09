package coHelp.model;

import coHelp.dto.MessageDto;
import org.springframework.context.annotation.Scope;

import java.util.List;
import java.util.Map;

@Scope("Task")
public class Messages {
    Map<Long, Map<Long, List<MessageDto>>> messages;
}

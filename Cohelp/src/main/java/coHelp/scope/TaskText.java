package coHelp.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Scope("task")
@Component
public class TaskText {
    String textForPage;

    public TaskText(){
        textForPage = UUID.randomUUID().toString();
    }

}

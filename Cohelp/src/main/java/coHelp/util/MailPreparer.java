package coHelp.util;

import org.springframework.context.annotation.Bean;

import java.util.Map;


public interface MailPreparer {

    String getMailTemplate(Map model);

}

package coHelp.scope;

import coHelp.config.security.jwt.authentication.UserDetailsImpl;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.Scope;
import org.springframework.context.ApplicationContext;

import javax.mail.Session;
import java.util.HashMap;
import java.util.Map;

public class TaskScope implements Scope {

    @Autowired
    public UserDetailsImpl userDetails;
    public static Map<Long, Map<Long, Object>> objects = new HashMap<>();


    @Override
    public Object get(String s, ObjectFactory<?> objectFactory) {
        if(objects.containsKey(Long.valueOf(s))){
            objects.put(Long.valueOf(s),new HashMap<>());
        }
        if (!objects.get(Long.valueOf(s)).containsKey(userDetails.getUserId())){
            objects.get(Long.valueOf(s)).put(userDetails.getUserId(), objectFactory.getObject());
        }

        return objects.get(Long.valueOf(s)).get(userDetails.getUserId());
    }

    @Override
    public Object remove(String s) {
        return null;
    }

    @Override
    public void registerDestructionCallback(String s, Runnable runnable) {

    }

    @Override
    public Object resolveContextualObject(String s) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}

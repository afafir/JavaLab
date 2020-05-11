package coHelp.scope;

import coHelp.config.security.jwt.authentication.UserDetailsImpl;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.Scope;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;

import javax.mail.Session;
import java.util.HashMap;
import java.util.Map;

public class TaskScope implements Scope {

    //Map<String, Map<Long, Object>> objectMap = new HashMap<>();

    Map<String, Object> objectMap = new HashMap<>();



    @Override
    public Object get(String s, ObjectFactory<?> objectFactory) {
        if(!objectMap.containsKey(s)) {
            objectMap.put(s, objectFactory.getObject());
        }

        return objectMap.get(s);
    }

    @Override
    public Object remove(String s) {
        return objectMap.remove(s);
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
        return "user";
    }
}
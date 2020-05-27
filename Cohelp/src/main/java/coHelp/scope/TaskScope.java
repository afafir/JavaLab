package coHelp.scope;

import coHelp.config.security.jwt.authentication.UserDetailsImpl;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.Scope;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.mail.Session;
import java.util.HashMap;
import java.util.Map;

public class TaskScope implements Scope {

    Map<String, Object> objectMap = new HashMap<>();




    @Override
    public Object get(String s, ObjectFactory<?> objectFactory) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (authentication.getPrincipal().equals("anonymousUser")){
            if (!objectMap.containsKey("notAuth")){
                objectMap.put("notAuth", objectFactory.getObject());
            }
            return objectMap.get("notAuth");
        }else
            if (!objectMap.containsKey("auth")){
                objectMap.put("auth", objectFactory.getObject());
            }
            return objectMap.get("auth");
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
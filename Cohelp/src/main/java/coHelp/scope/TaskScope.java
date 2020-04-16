package coHelp.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class TaskScope implements Scope {





    public static Map<Long, Map<String, Object>> objects = new HashMap<>();



    @Override
    public Object get(String s, ObjectFactory<?> objectFactory) {
        return null;
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

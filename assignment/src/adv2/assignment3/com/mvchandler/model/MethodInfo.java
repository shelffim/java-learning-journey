package adv2.assignment3.com.mvchandler.model;

import java.lang.reflect.Method;

public class MethodInfo {
    private Object instance;
    private Method method;

    public MethodInfo(Object instance, Method method) {
        this.instance = instance;
        this.method = method;
    }

    public Object getInstance() {
        return instance;
    }

    public Method getMethod() {
        return method;
    }
}

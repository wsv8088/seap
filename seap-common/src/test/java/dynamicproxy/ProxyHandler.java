package dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2014/10/2 0002.
 */
public class ProxyHandler<T> implements InvocationHandler {
    /**
     * 代理的对象
     */
    private T target;

    public T bind(T t) {
        this.target = t;
        return (T) Proxy.newProxyInstance(t.getClass().getClassLoader(), t.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before invoke...");
        Object res = method.invoke(target, args);
        System.out.println("after invoke...");
        return res;
    }
}

package dynamicproxy;

/**
 * Created by Administrator on 2014/10/2 0002.
 */
public class TestProxy {

    public static void main(String[] args) {
        Animal a = new Cat();
        ProxyHandler<Animal> ph = new ProxyHandler<Animal>();
        Animal ani = ph.bind(a);
        ani.run();
    }
}

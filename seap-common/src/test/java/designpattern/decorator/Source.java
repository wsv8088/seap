package designpattern.decorator;

/**
 * 定义一个源（被装饰）类，可以由装饰器动态给该类添加一些功能
 * 该类的定义主要目的是作为装饰器类构造方法的入参（接口实现类）
 */
public class Source implements Component {

    @Override
    public void decorate() {
        System.out.println("我是被装饰的类");
    }
}

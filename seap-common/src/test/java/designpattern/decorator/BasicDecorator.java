package designpattern.decorator;

/**
 * 设计模式--装饰者模式
 * 动态地将责任附加到对象上。若要扩展功能，装饰者提供了比继承更有弹性的替代方案
 * 具体被装饰者和抽象装饰类都继承于抽象被装饰者类，继承的是类型，而不是行为
 * 行为来自装饰者和基础组件，或与其他装饰者之间的组合关系
 * 装饰者通常是用其他类似于工厂或生成器这样的模式创建的
 *
 * 定义一个装饰器基类
 */
public class BasicDecorator extends Source {

    private Component component;

    public BasicDecorator(Component component) {
        this.component = component;
    }

    @Override
    public void decorate() {
        component.decorate();
    }
}

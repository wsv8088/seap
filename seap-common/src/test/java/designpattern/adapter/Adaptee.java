package designpattern.adapter;

/**
 * 被适配者(源类),如：笔记本电脑
 */
public class Adaptee {
    public void open() {
        System.out.println("被适配者具有open行为");
    }

    public void close() {
        System.out.println("被适配者具有close行为");
    }
}

package designpattern.adapter;

/**
 * 适配器模式把一个类的接口变换成客户端所期待的另一种接口，
 * 从而使原本因接口不匹配而无法在一起工作的两个类能够在一起工作。
 * <p/>
 * 定义一个适配器类,继承自源(被适配者)类,还要实现目标方法（适配到目标对象）
 * 例：笔记本电脑的插头一般都是三相的，即除了阳极、阴极外，还有一个地极,
 * 而有些地方的电源插座却只有两极，没有地极。电源插座与笔记本电脑的电源插头不匹配使得笔记本电脑无法使用。
 * 这时候一个三相到两相的转换器（适配器）就能解决此问题，而这正像是本模式所做的事情。
 */
public class Adapter extends Adaptee implements Target {
    /**
     * 本例为类适配器模式,对象适配器模式只需要将extends源类
     * 修改为===>持有源类的实例即可
     */
    /*private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }*/


    /**
     *
     */


    /**
     * 用户利用适配器的内部实现将source与target衔接起来
     * 对于client来说,只需要使用目标的接口operation即可,真正实现的则是适配器
     */
    @Override
    public void operation() {
        System.out.println("适配器实现目标方法====>operation");
    }

    public static void main(String[] args) {
        Target target = new Adapter();
        target.operation();
    }
}

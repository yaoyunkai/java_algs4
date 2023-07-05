Java 内部类

引用外部类变量的语法: if (TalkingClock.this.beep) Toolkit.getDefaultToolkit().beep();
Outclass.this

outObject.this new InnerClass();


内部类中声明的所有静态域都必须是final。
内部类不能有static方法。


"""
public class com.libyao.common.ch6.innerclass.TalkingClock1$TimePrinter implements java.awt.event.ActionListener {
  final com.libyao.common.ch6.innerclass.TalkingClock1 this$0;
  public com.libyao.common.ch6.innerclass.TalkingClock1$TimePrinter(com.libyao.common.ch6.innerclass.TalkingClock1);
  public void actionPerformed(java.awt.event.ActionEvent);
}

在内部类中 有一个final的属性指向外部类实例



"""

局部内部类

匿名内部类

静态内部类

嵌套类（nested class）


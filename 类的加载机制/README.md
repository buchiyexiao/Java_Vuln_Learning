## 恶意类的加载

网上摘了bit4大佬做的总结，简化一下自己的工作

![img](README.assets/Java%E6%81%B6%E6%84%8F%E7%B1%BB%E7%9A%84%E6%9E%84%E9%80%A0.png)

恶意类的几个点大佬都写得比较清楚，包括了静态代码块在执行类的初始化状态时候执行代码；类加载完成后，实例化时构造函数通过newInstance触发。也可以触发静态代码块；或者对象被调用时，调用其函数执行命令执行，可以触发自定义动态函数代码的方法，都可以触发

基本上就是触发构造函数和自定义动态函数代码，就可以触发静态代码块

### demo

```java
import javax.naming.Context;
import javax.naming.spi.ObjectFactory;
import java.util.Hashtable;

public class test1 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        // 触发方法1
        Class<?> xxx = Class.forName("testClass"); // 执行静态代码块中的命令
        testClass tc1 = (testClass) xxx.newInstance(); // 触发构造函数的命令
        Class<?> yyy = Class.forName("testClass", true, ClassLoader.getSystemClassLoader()); // 执行指定的类加载器
        testClass tc2 = (testClass) yyy.newInstance();

        // 触发方式2 xxxClassLoader().loadClass();
        Class<?> c1 = ClassLoader.getSystemClassLoader().loadClass("testClass"); // 隐式加载不处罚静态代码块
        c1.newInstance(); // 触发静态代码块 触发构造函数

        // 触发方式3 和方式2本质相同
        new testClass();

        // 触发方式4
        new testClass().fun(); // 静态代码 构造函数 自定义函数都会触发
    }
}

class testClass implements ObjectFactory{
    public static String s;
    // 静态代码块
    static {
        try {
            Runtime.getRuntime().exec("explorer.exe");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    // 构造函数
    testClass(){
        try {
            Runtime.getRuntime().exec("calc.exe");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    // 自定义函数
    public void fun(){
        try {
            Runtime.getRuntime().exec("notepad.exe");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    // getObjectInstance命令执行
    // 实现了ObjectFactory接口
    @Override
    public Object getObjectInstance(Object obj, javax.naming.Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
        try {
            Runtime.getRuntime().exec("mstcs.exe");
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
```

### javassist字节码操作动态创建恶意类

使用javassist动态创建恶意类，动态指定命令和类的名称，尝试利用不同的类加载器
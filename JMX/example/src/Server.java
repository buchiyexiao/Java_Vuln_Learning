import javax.management.*;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, IOException {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        System.out.println("Register bean");
        // 实例化一个MBean
        GirlfriendMBean girlfriend = new Girlfriend();
        ObjectName objectName = new ObjectName("JMXGIRL:name=girlfriend");
        // 绑定到MBeanServer
        mBeanServer.registerMBean(girlfriend, objectName);
        // 创建一个rmi registry
        Registry registry = LocateRegistry.createRegistry(1099);
        // 构造JMXServiceURL
        JMXServiceURL jmxServiceURL = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:1099/jmxrmi");
        // 构造JMXConnectorServer
        JMXConnectorServer jmxConnectorServer = JMXConnectorServerFactory.newJMXConnectorServer(jmxServiceURL, null,mBeanServer);
        jmxConnectorServer.start();
        System.out.println("ready");
    }
}

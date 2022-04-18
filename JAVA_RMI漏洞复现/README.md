## CVE-2017-3241复现
CVE-2017-3241 Java RMI Registry.bind()反序列化漏洞

JDK版本：Java SE <= 6u131, <= 7u121, <= 8u112, Java SE Embedded <= 8u111, JRockit <= R28.3.12

### demo
```java
package client;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.Combiner;
import service.ICombine;

public class Client {
	public static void main(String[] args) throws Exception {
		client1();
	}
	
	// rmi的lookup
	public static void client1() throws Exception {
		// 获取注册表
		Registry registry = LocateRegistry.getRegistry("localhost", 1099);
		String name = "combiner";
		// 根据名称 查找对象
		ICombine combiner = (ICombine)registry.lookup(name);
		
		String result = combiner.combine("aaaa", "bbbb");
		System.out.println("client1: " + result);
	}
}
```
```java
package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import service.Combiner;
import service.ICombine;

public class Server {
	public static void main(String[] args) throws Exception {
		Server1();
	}
	
	// Server1 单纯使用RMI 没有用JNDI
	private static void Server1() throws Exception {
		String name = "combiner";
		ICombine combine = new Combiner();
		// 指定远程对象使用的端口
		UnicastRemoteObject.exportObject(combine, 1100);
		
		// 创建本机的1099上端口 RMI registry
		Registry registry = LocateRegistry.createRegistry(1099);
		// 绑定
		registry.rebind(name, combine);
		
	}
}
```
```java
package service;

import java.rmi.RemoteException;

public class Combiner implements ICombine{
	public String combine(String str1, String str2) throws RemoteException {
		// TODO 自动生成的方法存根
		// return null;
		String result = str1 + "&" + str2;
		System.out.println("combine result: " + result);
		return result;
	}
}
```
```java
package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICombine extends Remote{
	public String combine(String str1, String str2) throws RemoteException;
}
```

### 复现

利用Server的代码进行本地环境复现，需要提前准备ysoserial，IDEA+Maven
出现了两个bug，FileUpload1快速添加import class，然后修改对应函数null为(File) null，Jypthon1删掉
绕了很多弯路，看到了这篇文章后半部分才配置好(ysoserial)[https://www.cnblogs.com/gychomie/p/14406399.html]

服务器上运行对应的vulhub
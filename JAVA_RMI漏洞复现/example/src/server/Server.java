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
		ICombine combiner = new Combiner();
		// 指定远程对象使用的端口
		UnicastRemoteObject.exportObject(combiner, 1100);
		
		// 创建本机的1099上端口 RMI registry
		Registry registry = LocateRegistry.createRegistry(1099);
		// 绑定
		registry.rebind(name, combiner);
		
	}
}

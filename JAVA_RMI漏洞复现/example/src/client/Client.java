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

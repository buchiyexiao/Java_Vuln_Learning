package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import model.Hello;
import model.Helloimpl;

public class Server {
	public static void main(String[] args) throws RemoteException{
		// 创建对象
		Hello hello = new Helloimpl();
		// 创建注册表
		Registry registry = LocateRegistry.createRegistry(1099);
		// 绑定对象到注册表 取名为hello
		registry.rebind("hello", hello);
	}
}

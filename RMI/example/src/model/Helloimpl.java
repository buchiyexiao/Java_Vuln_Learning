package model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import model.Hello;

public class Helloimpl extends UnicastRemoteObject implements Hello{
	public Helloimpl() throws RemoteException{
		// TODO 自动生成的构造函数存根
	}
	@Override
	public String welcome(String name) throws RemoteException {
		// TODO 自动生成的方法存根
		return "hello, " + name;
	}
}

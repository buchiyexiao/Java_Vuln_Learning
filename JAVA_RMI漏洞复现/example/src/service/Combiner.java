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

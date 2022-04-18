package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICombine extends Remote{
	public String combine(String str1, String str2) throws RemoteException;
}

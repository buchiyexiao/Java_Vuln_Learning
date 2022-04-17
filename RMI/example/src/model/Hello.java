package model;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello extends Remote{
	public String welcome(String name) throws RemoteException;
}

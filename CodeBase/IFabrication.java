package CodeBase;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IFabrication extends Remote {

    public IMultiplication instanceMul() throws RemoteException;
    public IAddition instanceAdd() throws RemoteException;
    public IDivision instanceDiv() throws RemoteException;
}


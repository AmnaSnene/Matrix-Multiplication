package CodeBase;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAddition extends Remote {

    public void additionner(int[][] A, int[][] B) throws RemoteException;
    public int[][] getresultat() throws RemoteException ;

}

package CodeBase;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IMultiplication extends Remote {

    public void multiplier(int[][] A , int[][] B ) throws RemoteException;
    public int[][] getC() throws RemoteException;
}

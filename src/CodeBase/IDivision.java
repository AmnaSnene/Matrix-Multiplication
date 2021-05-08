package CodeBase;

import java.rmi.Remote;
import java.rmi.RemoteException;
public interface IDivision extends Remote {

    public int[][] diviser( int[][] A ,int n) throws RemoteException ;
    public int[][] getMatrice (int i0, int i1, int j0, int j1, int[][] A)throws RemoteException;


}

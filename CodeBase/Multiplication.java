package CodeBase;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;


public class Multiplication extends RemoteException   implements IMultiplication{

    private int[][] C ;

    public Multiplication( ) throws RemoteException {
        super();
    }

    public int[][] getC() {
        return C;
    }

    //Multiplication de deux matrices A et B passées en paramètre.
    public void multiplier (int[][] A , int[][] B ) throws RemoteException {
        this.C = new int[A.length][B[0].length];
        for(int i=0; i<A.length; i++){
            for(int j=0; j<B[0].length; j++){
                this.C[i][j] = 0;
                for(int k=0; k<B.length ;k++)
                {
                    this.C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
    }

}
   
    

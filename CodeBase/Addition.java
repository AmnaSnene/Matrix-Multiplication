package CodeBase;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class Addition extends UnicastRemoteObject  implements IAddition {

    private int [][] resultat ;

    //Retourner l'attribut resultat.

    public int[][] getresultat() {
        return resultat;
    }

    //Constructeur
    public Addition() throws RemoteException {
        super();

    }

    //Additionner deux matrices A et B passées en paramètre 
    //et Charger le résultat dans l'attribut resultat.

    public void additionner(int[][] A, int[][] B) throws RemoteException {
        this.resultat = new int[A.length][A[0].length] ;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                this.resultat[i][j] = A[i][j] + B[i][j];
            }
        }
    }
}
   
    
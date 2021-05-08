package CodeBase;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Division extends UnicastRemoteObject  implements IDivision {

    //Constructeur.

    public Division() throws RemoteException {
        super();
    }

    //Retourner une sous matrice depuis une matrice mère passée en paramètre
    //avec l'indice de la ligne/colonne du début et ligne/colone fin.
    
    public int[][] getMatrice (int i0, int i1, int j0, int j1, int[][] A) {
        int[][] B = new int[i1-i0+1][j1-j0+1];
        try {
            for (int i = i0; i <= i1; i++) {
                for (int j = j0; j <= j1; j++) {
                    B[i-i0][j-j0] = A[i][j];
                }
            }
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
        }
        return B;
    }

    //La matrice mère est décomposée en 4 sous-matrices comme suit :
    //  sous-matrice n0   sous-matrice n1
    //  sous-matrice n2   sous-matrice n3
    //
    //L'utlisateur passe en paramètre la matrice mère et le numéro de la sous-matrice 
    //à la méthode diviser et elle retourne la sous-matrice choisie.

    public int[][] diviser( int[][] A ,int n) throws RemoteException {
        switch (n) {
            case 0:
                return getMatrice(0,A.length/2-1,0,A[0].length/2-1,A);
            case 1:
                return  getMatrice(0,A.length/2-1,A[0].length/2,A[0].length-1,A);
            case 2:
                return getMatrice(A.length/2,A.length-1,0,A[0].length/2-1,A);
            case 3:
                return  getMatrice(A.length/2,A.length-1,A[0].length/2,A[0].length-1,A);
        }
        return null ;
    }
}
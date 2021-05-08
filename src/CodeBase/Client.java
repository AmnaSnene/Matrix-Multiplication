package CodeBase;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.Vector;

public class Client {


    //La méthode saisie() permet de saisir une matrice quelconque.
     
    public static int[][] saisie() {
        System.out.print("Saisir le nombre de lignes dans la matrice: ");
        Scanner sc = new Scanner(System.in);
        int nbLigne = sc.nextInt();

        System.out.print("Saisir le nombre de colonnes dans la matrice: ");
        int nbColonne = sc.nextInt();
        //déclarer la matrice
         int [][]A= new int[nbLigne][nbColonne];
        for (int i = 0; i < nbLigne; i++) {
            for (int j = 0; j < nbColonne; j++) {
                System.out.print(String.format("Entrez A[%d][%d] : ", i, j));
                A[i][j] = sc.nextInt();
            }
        }
        return A;
    }


    //Afficher une metrice passée en paramètre.
    public void afficher(int[][] A){
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                System.out.print(A[i][j] + "\t");
            }
            System.out.println();
        }
    }

    //Constructeur
    public Client() throws Exception {

        int[][] A  ;
        int[][] B  ;
        //Saisir deux Matrices A et B 
        //avec la condition le nombre de colone de A = le nombre de ligne de B 
        while (true) {
            A = saisie();
            System.out.println("2eme Matrice : ");
            B = saisie();
            //Vérifier la condition
            if (A[0].length == B.length) {
                break;
            }
            else {
                System.out.println("Erreur ! resaisie les deux matrices");
            }
        }

//      *********************************************************
//      *  L'algorithme de Multiplication commence! Allons-y!!  *
//      *********************************************************

        try {

            //Appeler l'objet fabrication depuis le registry. 
            IFabrication fabrication = (IFabrication) Naming.lookup("rmi://localhost:1099/fabrication");
            
            //Créer deux objets division.
            IDivision divA = fabrication.instanceDiv();
            IDivision divB = fabrication.instanceDiv();
            
//         ********************************
            //Créer 8 objets Miltiplication.
            //Multiplier les sous-matrices de A par les sous-matrices de B.
            //8 matrices résultantes.


            IMultiplication C11 =  fabrication.instanceMul();
            //Sous-matrice de A n0 * Sous-matrice de B n0.
            C11.multiplier(divA.diviser(A,0), divB.diviser(B,0));
            IMultiplication C12 = fabrication.instanceMul();
            //Sous-matrice de A n0 * Sous-matrice de B n1.
            C12.multiplier(divA.diviser(A,0), divB.diviser(B,1));
            IMultiplication C21 = fabrication.instanceMul();
            //Sous-matrice de A n2 * Sous-matrice de B n0.
            C21.multiplier(divA.diviser(A,2), divB.diviser(B,0));
            IMultiplication C22 = fabrication.instanceMul();
            //Sous-matrice de A n2 * Sous-matrice de B n1.
            C22.multiplier(divA.diviser(A,2), divB.diviser(B,1));
            IMultiplication T11 = fabrication.instanceMul();
            //Sous-matrice de A n1 * Sous-matrice de B n2.
            T11.multiplier(divA.diviser(A,1),divB.diviser(B,2));
            IMultiplication T12 = fabrication.instanceMul();
            //Sous-matrice de A n1 * Sous-matrice de B n3.
            T12.multiplier(divA.diviser(A,1), divB.diviser(B,3));
            IMultiplication T21 = fabrication.instanceMul();
            //Sous-matrice de A n3 * Sous-matrice de B n2.
            T21.multiplier(divA.diviser(A,3),divB.diviser(B,2));
            IMultiplication T22 = fabrication.instanceMul();
            //Sous-matrice de A n3 * Sous-matrice de B n3.
            T22.multiplier(divA.diviser(A,3),divB.diviser(B,3));

//         ****************************** 
            //Créer 4 objets Addition.
            //Additionner les sous matrices résultantes:

            IAddition add11 = fabrication.instanceAdd();
            IAddition add12 = fabrication.instanceAdd();
            IAddition add21 = fabrication.instanceAdd();
            IAddition add22 = fabrication.instanceAdd();
          
  
            //(A n0 * B n0) + (A n1 * B n3)
            add11.additionner(C11.getC(), T11.getC());

            //(A n0 * B n1) + (A n1 * B n4)
            add12.additionner(C12.getC(), T12.getC());

            //(A n2 * B n0) + (A n2 * B n3)
            add21.additionner(C21.getC(), T21.getC());
            
            //(A n2 * B n1) + (A n2 * B n4)
            add22.additionner(C22.getC(), T22.getC());

//          *********************************
            //Consolider et afficher la matrice résultante finale.

            int[][] finale = new int[A.length][B[0].length];


            for (int i = 0; i < add11.getresultat().length; i++) {
                for (int j = 0; j < add11.getresultat()[0].length; j++) {
                    finale[i][j] = add11.getresultat()[i][j];

            }
            }


            for (int i = 0; i < add12.getresultat().length; i++) {
                for (int j = 0; j < add12.getresultat()[0].length; j++) {
                    finale[i][j+add11.getresultat()[0].length] = add12.getresultat()[i][j];
               
                }
            }

            for (int i = 0; i < add21.getresultat().length; i++) {
                for (int j = 0; j < add21.getresultat()[0].length; j++) {
                    finale[i+add11.getresultat().length][j] = add21.getresultat()[i][j];
                }
            }
            for (int i = 0; i < add22.getresultat().length; i++) {
                for (int j = 0; j < add22.getresultat()[0].length; j++) {
                    finale[i + add12.getresultat().length][j + add21.getresultat()[0].length] = add22.getresultat()[i][j];
                }
            }
            afficher(finale);
        }
        catch (RemoteException e) {
            System.out.println(e.getCause());

        }


    }

}

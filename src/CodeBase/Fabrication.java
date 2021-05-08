package CodeBase;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Fabrication extends UnicastRemoteObject implements IFabrication {

    public Fabrication() throws RemoteException {}

    // Créer un nouveau objet Multiplication.
    public IMultiplication instanceMul( ) throws RemoteException{
        return new Multiplication();
    }
    // Créer un nouveau objet Addition.
    public IAddition instanceAdd() throws RemoteException{
        return new Addition();
    }
    // Créer un nouveau objet Division.
    public IDivision instanceDiv() throws RemoteException{
        return new Division();
    }
}
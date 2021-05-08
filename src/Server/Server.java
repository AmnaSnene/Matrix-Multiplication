package Server;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMIClassLoader;

public class Server {

    
//Lancer le serveur.

    public static void main(String[] args) {
        try {
            System.setProperty("java.security.policy", "/home/snene/TPSAR/src/Server/Server.policy");
            System.setProperty("java.rmi.server.codebase", "file:/home/snene/TPSAR/src/CodeBase");

        // Définir le gestionnaire de sécurité.
            
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
        //Charger le code.
            String url = System.getProperty("java.rmi.server.codebase");
            Class fabricationClass = RMIClassLoader.loadClass(url, "CodeBase.Fabrication");
            Remote fabrication = (Remote) fabricationClass.getConstructors()[0].newInstance();

        //Créer un objet fabrication et l'enregistrer dans le registry.    
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("fabrication", fabrication);

            System.out.println("Server pret!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}

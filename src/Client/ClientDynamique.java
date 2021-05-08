package Client;


import java.rmi.server.RMIClassLoader;

public class ClientDynamique {

    public ClientDynamique() throws Exception {
        
        System.setProperty("java.rmi.server.codebase", "file:/home/snene/TPSAR/src/CodeBase");
        System.setProperty("java.security.policy", "/home/snene/TPSAR/src/Client/Client.policy");

        // Définir le gestionnaire de sécurité.
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        // Charger la classe client.
        
        String url = System.getProperty("java.rmi.server.codebase");

        Class clientClass =  RMIClassLoader.loadClass(url, "CodeBase.Client");


        // Commencer l'éxcution

        clientClass.getConstructors()[0].newInstance(); // Nouveau Client();


    }

    public static void main (String [] args) {
        try{

            ClientDynamique cli = new ClientDynamique(); //Créer un ClienDynamique()
        }
        catch (Exception e) {
            System.out.println (e.getCause());
        }
    }

}

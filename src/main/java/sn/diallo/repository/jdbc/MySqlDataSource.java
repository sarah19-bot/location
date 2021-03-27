package sn.diallo.repository.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class MySqlDataSource implements  DataSource{
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "";
    public static final String NOM_BASE = "location";

    public static final String IP = "localhost";
    public static final String PORT = "3306";

    public Connection createConnection() {
        try {
            Class c = Class.forName("com.mysql.cj.jdbc.Driver");
            Driver pilote = (Driver) c.newInstance();
            // enregistrement du pilote auprès du DriverManager
            DriverManager.registerDriver(pilote);
            // Protocole de connexion
            String protocole = "jdbc:mysql:";
            // Adresse IP de l’hôte de la base et port
            String ip = IP;  // dépend du contexte
            String port = PORT;  // port MySQL par défaut
            // Nom de la base ;
            String nomBase = NOM_BASE;  // dépend du contexte
            // Chaîne de connexion
            String chaineDeConnexion = protocole + "//" + ip + ":" + port + "/" + nomBase;
            // Identifiants de connexion et mot de passe
            String dbUser = DB_USER;  // dépend du contexte
            String dbPassword = DB_PASSWORD;  // dépend du contexte
            // Connexion
            Connection connection = DriverManager.getConnection(chaineDeConnexion, dbUser, dbPassword);
            System.out.println(connection);
            return connection;
        }
        catch (Exception ex){
            System.err.println("Une erreur est survenue lors de la création de la connexion: ");
            System.err.println(ex.getMessage());
            return null;
        }
    }
}

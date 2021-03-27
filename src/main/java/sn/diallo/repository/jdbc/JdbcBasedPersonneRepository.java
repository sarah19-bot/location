package sn.diallo.repository.jdbc;

import sn.diallo.domain.Local;
import sn.diallo.domain.Personne;
import sn.diallo.repository.PersonneRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcBasedPersonneRepository implements PersonneRepository {
    private final DataSource dataSource;

    public JdbcBasedPersonneRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public int add(Personne personne) {
        int ok = 0;
        String query = "INSERT INTO personne (nom, prenom, adresse, telephone, email, type) VALUES(?,?,?,?,?,?)";
        try {
            Connection connection = dataSource.createConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1,personne.getNom());
            statement.setString(2, personne.getPrenom());
            statement.setString(3, personne.getAdresse());
            statement.setString(4, personne.getTelephone());
            statement.setString(5, personne.getEmail());
            statement.setString(6, personne.getType());
            ok = statement.executeUpdate();

            if (ok > 0) {
                System.out.println("Personne ajoutée avec succés!");
            }
            return ok;
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        return 0;
    }

    public int update(Personne personne) {
        String query = "UPDATE  personne SET nom=?, prenom=?, adresse=?, telephone=?, email=?, type=? where id=?";
        int ok = 0;
        try {

            Connection connection = dataSource.createConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1,personne.getNom());
            statement.setString(2, personne.getPrenom());
            statement.setString(3, personne.getAdresse());
            statement.setString(4, personne.getTelephone());
            statement.setString(5, personne.getEmail());
            statement.setString(6, personne.getType());
            statement.setInt(7, personne.getId());

            ok = statement.executeUpdate();

            System.out.println(statement);
            ok = statement.executeUpdate();
            if (ok > 0) {
                System.out.println("Modification réussie!");
            }
            return ok;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }

    public int delete(int id) {
        String query = "DELETE FROM personne where id=?";
        int ok =0;
        try {
            Connection connection = dataSource.createConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ok = statement.executeUpdate();
            if (ok > 0) {
                System.out.println("Suppression Reussie!");
            }
            return ok;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }

    public Personne[] getAll() {
        //requête sql pour récupèrer les infos
        String query = "SELECT * FROM personne";
        //mapper le résultat
        List<Personne> personnes = new ArrayList<Personne>();
        try {
            Connection connection = dataSource.createConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String adresse = rs.getString("adresse");
                String telephone = rs.getString("telephone");
                String email = rs.getString("email");
                String type = rs.getString("type");
                //mapping retour db (relationnel) avec objet Proprietaire
                Personne personne = new Personne(id, nom, prenom, adresse, telephone, email, type);

                personnes.add(personne);
            }
            return personnes.toArray(new Personne[0]);

        } catch (SQLException e) {
            return new Personne[0];
        } catch (Exception ex) {
            return new Personne[0];
        }
    }

    public Personne[] getAllByType(String type) {
        //requête sql pour récupèrer les infos
        String query = "SELECT * FROM personne where type=?";
        //mapper le résultat
        List<Personne> personnes = new ArrayList<Personne>();
        try {
            Connection connection = dataSource.createConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, type);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String adresse = rs.getString("adresse");
                String telephone = rs.getString("telephone");
                String email = rs.getString("email");
                String type1 = rs.getString("type");
                //mapping retour db (relationnel) avec objet Proprietaire
                Personne personne = new Personne(id, nom, prenom, adresse, telephone, email, type1);

                personnes.add(personne);
            }
            return personnes.toArray(new Personne[0]);

        } catch (SQLException e) {
            return new Personne[0];
        } catch (Exception ex) {
            return new Personne[0];
        }
    }

    public Personne getById(int id) {
        String query = "SELECT * from personne where id = ?";

        try {
            Connection connection = dataSource.createConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                System.out.println("ici");
            }
            int retrievedId = rs.getInt("id");
            String retrievedNom = rs.getString("nom");
            String retrievedPrenom = rs.getString("prenom");
            String retrievedAdress = rs.getString("adresse");
            String retrievedTel = rs.getString("telephone");
            String retrievedEmail = rs.getString("email");
            String retrievedType = rs.getString("type");
            Personne personne = new Personne(retrievedId, retrievedNom, retrievedPrenom, retrievedAdress, retrievedTel, retrievedEmail, retrievedType);
            return personne;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public Personne getByLocal(Local local) {
        String query = "SELECT personne.id, nom, prenom, personne.adresse, telephone, email, personne.type from personne INNER JOIN local ON local.id = ?";
        try {
            Connection connection = dataSource.createConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, local.getId());
            ResultSet rs = statement.executeQuery();
            int retrievedId = rs.getInt("id");
            String retrievedNom = rs.getString("nom");
            String retrievedPrenom = rs.getString("prenom");
            String retrievedAdress = rs.getString("adresse");
            String retrievedTel = rs.getString("telephone");
            String retrievedEmail = rs.getString("email");
            String retrievedType = rs.getString("type");
            Personne personne = new Personne(retrievedId, retrievedNom, retrievedPrenom, retrievedAdress, retrievedTel, retrievedEmail, retrievedType);
            return personne;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public int louerLocal(Local local) {
        String query = "UPDATE  local SET louer=? where id=?";
        int ok = 0;
        try {

            Connection connection = dataSource.createConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1,"oui");
            statement.setInt(2, local.getId());
            ok = statement.executeUpdate();

            System.out.println(statement);
            ok = statement.executeUpdate();
            if (ok > 0) {
                System.out.println("Location réussie!");
            }
            return ok;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }
}

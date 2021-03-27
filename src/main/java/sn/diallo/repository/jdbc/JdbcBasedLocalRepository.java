package sn.diallo.repository.jdbc;

import sn.diallo.domain.Local;
import sn.diallo.domain.Personne;
import sn.diallo.repository.LocalRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcBasedLocalRepository implements LocalRepository {

    private final DataSource dataSource;

    public JdbcBasedLocalRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public int add(Local local, Personne proprietaire) {
        int ok = 0;
        String query = "INSERT INTO local (type, superficie, adresse, proprietaire, prix) VALUES(?,?,?,?,?)";
        try {

            Connection connection = dataSource.createConnection();

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,local.getType());
            statement.setString(2, local.getSuperficie());
            statement.setString(3, local.getAdresse());
            statement.setString(4, String.valueOf(proprietaire.getId()));
            statement.setFloat(5, local.getPrix());

            ok = statement.executeUpdate();

            if (ok > 0) {
                System.out.println("Local créé avec succés!");
            }
            return ok;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }

    public int update(Local local, Personne proprietaire) {
        String query = "UPDATE  local SET type=?, superficie=?, adresse=?, proprietaire=?, prix=?   where id=?";
        int ok = 0;
        try {

            Connection connection = dataSource.createConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, local.getType());
            statement.setString(2, local.getSuperficie());
            statement.setString(3, local.getAdresse());
            statement.setString(4, String.valueOf(proprietaire.getId()));
            statement.setFloat(5, local.getPrix());
            statement.setInt(6, local.getId());

            ok = statement.executeUpdate();

            System.out.println(statement);
            ok = statement.executeUpdate();
            if (ok > 0) {
                System.out.println("Local modifié avec succés!");
            }
            return ok;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }

    public int delete(int id) {
        String query = "DELETE FROM local where id=?";
        int ok =0;
        try {
            Connection connection = dataSource.createConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ok = statement.executeUpdate();
            if (ok > 0) {
                System.out.println("local supprimé avec succés!");
            }
            return ok;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }

    public Local[] getAll() {
        //requête sql pour récupèrer les infos
        String query = "SELECT * FROM local";
        //mapper le résultat
        List<Local> locals = new ArrayList<Local>();

        try {
            Connection connection = dataSource.createConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query) ;

            while (rs.next()) {
                int id = rs.getInt("id");
                String type = rs.getString("type");
                String superficie = rs.getString("superficie");
                String adresse = rs.getString("adresse");
                int p = 1;
                JdbcBasedPersonneRepository jdbcpr = new JdbcBasedPersonneRepository(dataSource);
                Personne proprietaire = jdbcpr.getById(p);
                long prix = rs.getLong("prix");
                //mapping retour db (relationnel) avec objet Local
                Local local = new Local(id, type, superficie, adresse, proprietaire, prix);

                locals.add(local);
            }
            return locals.toArray(new Local[0]);

        }
        catch (SQLException e) {
            return new Local[0];
        }
        catch (Exception ex){
            return new Local[0];
        }
    }

    public Local[] getAllByType(String type) {
        //requête sql pour récupèrer les infos
        String query = "SELECT * FROM local where type=?";
        //mapper le résultat
        List<Local> locals = new ArrayList<Local>();

        try {
            Connection connection = dataSource.createConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, type);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String retrivedType = rs.getString("type");
                String retrivedSuperficie = rs.getString("superficie");
                String retrivedAdresse = rs.getString("adresse");
                int retrivedProprietaire = rs.getInt("proprietaire");
                JdbcBasedPersonneRepository jdbcpr = new JdbcBasedPersonneRepository(dataSource);
                Personne proprietaire = jdbcpr.getById(retrivedProprietaire);
                long retrivedPrice = rs.getLong("prix");
                //mapping retour db (relationnel) avec objet Local
                Local local = new Local(id, retrivedType, retrivedSuperficie, retrivedAdresse, proprietaire, retrivedPrice);

                locals.add(local);
            }
            return locals.toArray(new Local[0]);

        }
        catch (SQLException e) {
            return new Local[0];
        }
        catch (Exception ex){
            return new Local[0];
        }
    }

    public Local[] getAllByProprietaire(Personne proprietaire) {
        String query = "SELECT * from local where proprietaire = ?";
        //mapper le résultat
        List<Local> locals = new ArrayList<Local>();
        try {
            Connection connection = dataSource.createConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, proprietaire.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int retrievedId = rs.getInt("id");
                String retrievedType = rs.getString("type");
                String retrievedSuperficie = rs.getString("superficie");
                String retrievedAdress = rs.getString("adresse");
                int retrivedProprietaire = rs.getInt("proprietaire");
                JdbcBasedPersonneRepository jdbcpr = new JdbcBasedPersonneRepository(dataSource);
                Personne retrievedProp = jdbcpr.getById(retrivedProprietaire);
                long retrievedPrice = rs.getLong("prix");
                Local local =  new Local(retrievedId, retrievedType, retrievedSuperficie, retrievedAdress, retrievedProp, retrievedPrice);

                locals.add(local);
            }
            return locals.toArray(new Local[0]);
        }catch (SQLException e) {
            return new Local[0];
        }
        catch (Exception ex){
            return new Local[0];
        }
    }

    public Local[] getAllByTypeAndProprietaire(String type, Personne proprietaire) {
        String query = "SELECT * from local where type = ? AND proprietaire = ?";
        //mapper le résultat
        List<Local> locals = new ArrayList<Local>();
        try {
            Connection connection = dataSource.createConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, type);
            statement.setInt(2, proprietaire.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int retrievedId = rs.getInt("id");
                String retrievedType = rs.getString("type");
                String retrievedSuperficie = rs.getString("superficie");
                String retrievedAdress = rs.getString("adresse");
                int retrivedProprietaire = rs.getInt("proprietaire");
                JdbcBasedPersonneRepository jdbcpr = new JdbcBasedPersonneRepository(dataSource);
                Personne retrievedProp = jdbcpr.getById(retrivedProprietaire);
                long retrievedPrice = rs.getLong("prix");
                Local local =  new Local(retrievedId, retrievedType, retrievedSuperficie, retrievedAdress, retrievedProp, retrievedPrice);

                locals.add(local);
            }
            return locals.toArray(new Local[0]);
        }catch (SQLException e) {
            return new Local[0];
        }
        catch (Exception ex){
            return new Local[0];
        }
    }

    public Local[] getAllByLocataire(Personne locataire) {
        String query = "SELECT local.id, type, superficie, local.adresse, proprietaire, prix from personne INNER JOIN local ON personne.id_local = ? AND personne.id_local = local.id";
        //mapper le résultat
        List<Local> locals = new ArrayList<Local>();
        try {
            Connection connection = dataSource.createConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, locataire.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int retrievedId = rs.getInt("id");
                String retrievedType = rs.getString("type");
                String retrievedSuperficie = rs.getString("superficie");
                String retrievedAdress = rs.getString("adresse");
                int p = rs.getInt("proprietaire");
                JdbcBasedPersonneRepository jdbcpr = new JdbcBasedPersonneRepository(dataSource);
                Personne retrievedProp = jdbcpr.getById(p);
                long retrievedPrix = rs.getLong("prix");
                Local local =  new Local(retrievedId, retrievedType, retrievedSuperficie, retrievedAdress, retrievedProp, retrievedPrix);
                locals.add(local);
            }
            return locals.toArray(new Local[0]);
        }catch (SQLException e) {
            return new Local[0];
        }
        catch (Exception ex){
            return new Local[0];
        }
    }

    public Local getById(int id) {
        String query = "SELECT * from local where id = ?";
        try {
            Connection connection = dataSource.createConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                System.out.printf("ici");
            }
            int retrievedId = rs.getInt("id");
            String retrievedType = rs.getString("type");
            String retrievedSuperficie = rs.getString("superficie");
            String retrievedAdress = rs.getString("adresse");
            int p = rs.getInt("proprietaire");
            JdbcBasedPersonneRepository jdbcpr = new JdbcBasedPersonneRepository(dataSource);
            Personne retrievedProp = jdbcpr.getById(p);
            long retrievedPrix = rs.getLong("prix");
            return new Local(retrievedId, retrievedType, retrievedSuperficie, retrievedAdress, retrievedProp, retrievedPrix);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}

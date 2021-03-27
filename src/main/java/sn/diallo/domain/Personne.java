package sn.diallo.domain;

public class Personne {
    protected int id;
    protected String nom;
    protected String prenom;
    protected String adresse;
    protected String telephone;
    protected String email;
    protected String type;
    protected Local local;

    public Personne(int id, String nom, String prenom, String adresse, String telephone, String email, String type){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.type = type;
    }

    public Personne() {

    }


    public int getId(){
        return id;
    }

    public String getNom(){
        return nom;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public String getPrenom(){
        return prenom;
    }

    public void setPrenom(String prenom){
        this.prenom = prenom;
    }

    public String getAdresse(){
        return adresse;
    }

    public void setAdresse(String adresse){
        this.adresse = adresse;
    }

    public String getTelephone(){
        return telephone;
    }

    public void setTelephone(String telephone){
        this.telephone = telephone;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public String toString(){
        return this.nom+" "+this.prenom+"- email: "+this.email+" -tel: "+this.telephone;
    }
}

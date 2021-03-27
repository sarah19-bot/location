package sn.diallo.domain;

public class Local {
    protected int id;
    protected String type;
    protected String superficie;
    protected String adresse;
    protected Personne proprietaire;
    protected long prix;

    public Local(int id, String type, String superficie, String adresse, Personne proprietaire, long prix) {
        this.id = id;
        this.type = type;
        this.superficie = superficie;
        this.adresse = adresse;
        this.proprietaire = proprietaire;
        this.prix = prix;
    }

    public Local() {

    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getSuperficie() {
        return superficie;
    }

    public Personne getProprietaire() {
        return proprietaire;
    }

    public long getPrix() {
        return prix;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSuperficie(String superficie) {
        this.superficie = superficie;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setProprietaire(Personne proprietaire) {
        this.proprietaire = proprietaire;
    }

    public void setPrix(long prix) {
        this.prix = prix;
    }

    public String toString(){
        String str = this.getClass().getName()+"\n Adresse: "+this.adresse+
                "\n Type: "+this.type+"\n Superficie: "+this.superficie+"\n Proprietaire: "+this.proprietaire+"\n Prix: "+this.prix;
        return str;
    }
}

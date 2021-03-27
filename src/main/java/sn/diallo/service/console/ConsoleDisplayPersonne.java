package sn.diallo.service.console;

import sn.diallo.domain.Local;
import sn.diallo.domain.Personne;
import sn.diallo.repository.LocalRepository;
import sn.diallo.service.DisplayPersonne;

import java.util.Scanner;

public class ConsoleDisplayPersonne implements DisplayPersonne {

    public ConsoleDisplayPersonne(){
    }

    public void afficherMenuParType(String type) {
        if ("Admin".equalsIgnoreCase(type)){
            System.out.println("1 lister les users");
            System.out.println("2 ajouter un user");
            System.out.println("3 modifier un user");
            System.out.println("4 supprimer un user");
            System.out.println("5 Retour");
        }else if("Proprietaire".equalsIgnoreCase(type)){
            System.out.println("1 lister mes locaux disponible");
            System.out.println("2 lister mes locaux en location");
            System.out.println("3 ajouter un local");
            System.out.println("4 modifier un local");
            System.out.println("5 supprimer un local");
            System.out.println("6 Retour");
        }else if("Locataire".equalsIgnoreCase(type)){
            System.out.println("1 appartements");
            System.out.println("2 studios ");
            System.out.println("3 chambre");
            System.out.println("4 profil");
            System.out.println("5 Retour");
        }
    }

    public void afficherMesLocaux(Local[] locals) {
        System.out.println("Mes Locaux :");
        for (int i = 0; i < locals.length; i++) {
            Local local = locals[i];
            System.out.println(String.format("> %s ", local.toString()));
        }
    }

    public Local addLocal(Personne proprietaire) {
        Local local = new Local();
        Scanner sc = new Scanner(System.in);
        System.out.println("entrer le type");
        local.setType(sc.nextLine());
        System.out.println("entrer la superficie");
        local.setSuperficie(sc.nextLine());
        System.out.println("entrer l'adresse");
        local.setAdresse(sc.nextLine());
        local.setProprietaire(proprietaire);
        System.out.println("entrer le prix");
        local.setPrix(sc.nextLong());

        return local;
    }

    public Local updateLocal(Local local) {
        Scanner scanner = new Scanner(System.in);
        int id = local.getId();
        Scanner sc = new Scanner(System.in);
        System.out.println("entrer le nouveau type");
        local.setType(sc.nextLine());
        System.out.println("entrer la nouvelle superficie");
        local.setSuperficie(sc.nextLine());
        System.out.println("entrer la nouvelle adresse");
        local.setAdresse(sc.nextLine());
        System.out.println("entrer le nouveau prix");
        local.setPrix(sc.nextLong());
        return  local;
    }

    public int deleteLocal() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("saisir l'id du local à supprimer");
        return  scanner.nextInt();
    }

    public Personne addPersonne(Local local) {
        Personne personne = new Personne();
        Scanner sc = new Scanner(System.in);
        System.out.println("entrer le nom");
        personne.setNom(sc.nextLine());
        System.out.println("entrer le prenom");
        personne.setPrenom(sc.nextLine());
        System.out.println("entrer l'adresse");
        personne.setAdresse(sc.nextLine());
        System.out.println("entrer le telephone");
        personne.setTelephone(sc.nextLine());
        System.out.println("entrer l'email");
        personne.setEmail(sc.nextLine());
        System.out.println("entrer le type");
        personne.setType(sc.nextLine());
        if("proprietaire".equalsIgnoreCase(personne.getType())){
            personne.setLocal(null);
        }else{
            personne.setLocal(null);
        }

        return personne;
    }

    public Personne updatePersonne(Personne personne) {
        Scanner sc = new Scanner(System.in);
        System.out.println("entrer le nom");
        personne.setNom(sc.nextLine());
        System.out.println("entrer le prenom");
        personne.setPrenom(sc.nextLine());
        System.out.println("entrer l'adresse");
        personne.setAdresse(sc.nextLine());
        System.out.println("entrer le telephone");
        personne.setTelephone(sc.nextLine());
        System.out.println("entrer l'email");
        personne.setEmail(sc.nextLine());
        System.out.println("entrer le type");
        personne.setType(sc.nextLine());

        return personne;
    }

    public int deletePersonne() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("saisir l'id de la à supprimer");
        return  scanner.nextInt();
    }

    public void louerLocal(Local local) {

    }

    public void afficherOptionInconnue() {
        System.out.println("Option Inconnue");
    }
}

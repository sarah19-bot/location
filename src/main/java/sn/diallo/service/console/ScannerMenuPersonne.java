package sn.diallo.service.console;

import sn.diallo.controller.AdminController;
import sn.diallo.controller.LocataireController;
import sn.diallo.controller.ProprietaireController;
import sn.diallo.domain.Local;
import sn.diallo.repository.LocalRepository;
import sn.diallo.repository.PersonneRepository;
import sn.diallo.service.DisplayLocal;
import sn.diallo.service.DisplayPersonne;
import sn.diallo.service.DisplayPrincipal;
import sn.diallo.service.MenuPersonne;

import java.util.Scanner;

public class ScannerMenuPersonne implements MenuPersonne {
    private final Scanner scanner;
    private PersonneRepository personneRepository;
    private LocalRepository localRepository;
    private DisplayPersonne displayPersonne;
    private DisplayPrincipal displayPrincipal;
    private DisplayLocal displayLocal;

    public ScannerMenuPersonne(PersonneRepository personneRepository, LocalRepository localRepository, DisplayPersonne displayPersonne){
        this.scanner = new Scanner(System.in);
        this.personneRepository = personneRepository;
        this.displayPersonne = displayPersonne;
        this.displayPrincipal = new ConsoleDisplayPrincipal();
        this.localRepository = localRepository;
        displayLocal = new ConsoleDisplayLocal(personneRepository);
    }

    private String lireChoix() {
        return scanner.nextLine();
    }

    private int lireChoixInt(){ return scanner.nextInt();}
    public void afficherMenu(String choix){
        if ("p".equalsIgnoreCase(choix)){
            ProprietaireController proprietaireController = new ProprietaireController();
            proprietaireController.process();
        }else if("a".equalsIgnoreCase(choix)){
            AdminController adminController = new AdminController();
            adminController.process();
        }else if("l".equalsIgnoreCase(choix)){
            LocataireController locataireController = new LocataireController();
            locataireController.process();
        }
    }

    public void afficherMenu() {
        displayPrincipal.afficherBienvenue();
        displayPrincipal.afficherMenuPrincipal();
        String choix = lireChoix();
        afficherMenu(choix);
    }

    public void afficherMenuAdmin() {
        int choix = lireChoixInt();
        switch (choix){
            case 1:
                displayLocal.afficherListeProprietaires(personneRepository.getAll());
               break;
            case 2:
                personneRepository.add(displayPersonne.addPersonne(null));
                break;
            case 3:
                System.out.println("Donner l'id de la personne");
                int id = scanner.nextInt();
                personneRepository.update(displayPersonne.updatePersonne(personneRepository.getById(id)));
                break;
            case 4:
                personneRepository.delete(displayPersonne.deletePersonne());
                break;
            case 5:
                System.out.println("Retour");
            default:
                displayLocal.afficherOptionInconnue();
                break;
        }
    }

    public void afficherMenuLocataire() {
        int choix = lireChoixInt();
        Local local = new Local();
        switch (choix){
            case 1:
                displayLocal.afficherListeLocaux(localRepository.getAllByType("appartement"));
                local = louer();
                if (local != null){
                    personneRepository.louerLocal(local);
                }
                break;
            case 2:
                displayLocal.afficherListeLocaux(localRepository.getAllByType("studio"));
                local = louer();
                if (local != null){
                    personneRepository.louerLocal(local);
                }
                break;
            case 3:
                displayLocal.afficherListeLocaux(localRepository.getAllByType("chambre"));
                local = louer();
                if (local != null){
                    personneRepository.louerLocal(local);
                }
                break;
            case 4:
                System.out.println("Profil user");
            default:
                displayLocal.afficherOptionInconnue();
                break;
        }
    }

    public void afficherMenuProprietaire() {
        int choix = lireChoixInt();
        switch (choix){
            case 1:
                displayPersonne.afficherMesLocaux(localRepository.getAllByProprietaire(personneRepository.getById(1)));
                break;
            case 2:
                displayPersonne.afficherMesLocaux(localRepository.getAllByProprietaire(personneRepository.getById(1)));
                break;
            case 3:
                localRepository.add(displayPersonne.addLocal(personneRepository.getById(1)), personneRepository.getById(1));
                break;
            case 4:
                System.out.println("Donner l'id du local à modifier");
                int id = scanner.nextInt();
                localRepository.update(displayPersonne.updateLocal(localRepository.getById(id)), personneRepository.getById(1));
                break;
            case 5:
                localRepository.delete(displayPersonne.deleteLocal());
                break;
            default:
                displayLocal.afficherOptionInconnue();
                break;
        }
    }

    public String continuer(){
        System.out.println("Continuer? (o/n)");
        return scanner.nextLine();
    }

    private Local louer(){
        System.out.println("Louer un local (1/0)");
        int reponse = scanner.nextInt();
        if (reponse == 1){
            System.out.println("Donner l'id du local à louer");
            int id = scanner.nextInt();
            System.out.println("-------------Local------------");
            System.out.println(localRepository.getById(id).toString());
            System.out.println("Confirmer vos la location de ce local? (1/0)");
            if (1 == scanner.nextInt()){
                return localRepository.getById(id);
            }else{
                return null;
            }
        }else{
            return null;
        }

    }
}

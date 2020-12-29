package console;

import objects.BaseClient;
import produit.Achat;
import produit.BaseProduit;
import produit.PanierDuClient;
import produit.Produit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/***************************     La classe pour gerer le magasin a partir de la console     ****************************/

public class Gestion {
    Scanner in = new Scanner( System.in );
    BaseClient baseClient = new BaseClient();
    BaseProduit baseProduit = new BaseProduit();
    static PanierDuClient panierDuClient = null;

    public Gestion() throws IOException {
        /*
        *premier menu pour demander l'utilisateur pour se connecter en tant que administrateur ou client
        */
        boolean isRunning = true;
        while (isRunning) {
            menuPrincipal();

            switch (choix()){
                case "1":
                    logInAdministrateur();

                    break;
                case "2":
                    choixClient();

                    break;
                case "3":
                    isRunning = false;
                    break;

                default:
                    optionInvalid();
            }
        }
    }

    /***************************      Administrateur: variation du stock          ******************************/

    private void choixAdministrateur(){
        boolean repetition =true;
        while (repetition) {
            System.out.println( "\nVeuillez saisir votre choix: " + "\n1 Afficher tous les produit" +
                    "\n2 Ajouter un produit" + "\n3 Supprimer un produit" + "\n4 Afficher le panier du client" + "\n5 Revenir sur le menu principal" );
            switch (choix()) {
                case "1":
                    baseProduit.affichLesListeProduit();
                    break;
                case "2":
                    creerUnProduit();
                    break;
                case "3": {
                    Scanner in = new Scanner( System.in );
                    System.out.println( "Veuillez saisir le nom de produit à supprimer" );
                    String nomDeProduit = in.nextLine();
                    baseProduit.supprimerUnProduit( nomDeProduit );
                    break;
                }
                case "4": {
                    panierDuClient.afficherUnPanier();
                    if (panierDuClient != null){
                        if (panierDuClient.getValiderLePanier()==true){
                            System.out.println("Vous allez recevoir vos produits le plus tôt possible");
                        }

                    }}
                case "5":
                    repetition=false;
            }
        }
    }

    /***************************      Administrateur: creation du produit        ******************************/
    private void creerUnProduit() {
        System.out.print("Nom de produit: ");
        String nomDeProduit = in.nextLine();
        if (baseProduit.siProduitExiste( nomDeProduit )){
            System.out.println("Produit existe déjà" + "\nVeuillez saisir la quantité: ");
            int quantite = Integer.parseInt( in.nextLine() );
            for (int i = 0; i < baseProduit.getListeDeProduit().size(); i++) {
                if (baseProduit.getListeDeProduit().get( i ).getNomProduit().equals (nomDeProduit)){
                    baseProduit.getListeDeProduit().get( i ).setQuantiteEnStock( (baseProduit.getListeDeProduit().get( i ).getQuantiteEnStock()+ quantite ));
                    System.out.println("Le nouveau stock du produit "+ nomDeProduit + " est: "+baseProduit.getListeDeProduit().get( i ).getQuantiteEnStock());
                }

            }

        }else{
                System.out.print("Prix: ");
                float prix = Float.parseFloat( in.nextLine() );
                System.out.print("Quantité de produit: ");
                int quantiteProduit = Integer.parseInt( in.nextLine() );
                Produit produit =new Produit(nomDeProduit, prix, quantiteProduit);
                baseProduit.ajouteProduit( produit);
        }
    }
    /***************************************        Connexion client        *******************************************/
    private void choixClient() throws IOException {
        Scanner in = new Scanner( System.in );
        String choix = "";
        boolean connexionDuClient = false;
        do {
            do {
                System.out.println("\t1 Acceder mon compte\n" +
                        "\t2 Créer un compte" + "\n\t3 Quitter");
                choix = in.nextLine();
            }while (!choix.equals( "1" ) && !choix.equals( "2" ) && !choix.equals( "3" ));

            switch (choix){
                case "1": {
                    connexionDuClient = logInClient();
                    if (connexionDuClient == true){
                        panierDuClient = new PanierDuClient();
                        System.out.println("je suis connecté");
                        panierDuClient = Achat.acheterDesProduit( baseProduit, panierDuClient );
                    }

                }break;
                case "2" : creerUnCompte();
                    break;
                case "3" : connexionDuClient = true;
                    break;
            }

        }while (connexionDuClient == false);
    }

    public boolean logInClient () throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean connexionClientReussi = false;

            System.out.print("Email: ");
            String email = reader.readLine();
            System.out.print("Mot de passe: ");
            String motDePasse = reader.readLine();
                if (baseClient.isClientExist( email, motDePasse )) {
                    System.out.println( "Bienvenue!" );
                    connexionClientReussi = true;
                } else {
                    System.out.println("erreur de saisie ou le compte n'existe pas");
                    connexionClientReussi = false;

                }

        return connexionClientReussi;
    }

    private void creerUnCompte() {
        Scanner in = new Scanner( System.in );
        System.out.print("Nom: ");
        String nom = in.nextLine();
        System.out.print("Prenom: ");
        String prenom = in.nextLine();
        System.out.print("Email: ");
        String email = in.nextLine();
        System.out.print("Mot de passe: ");
        String motDePasse = in.nextLine();
        baseClient.creeClient( nom,prenom,email,motDePasse );
        // probleme : un saisir qui se balade
    }

    private void menuPrincipal() {
        System.out.print( "Bienvenue dans le magasin: \n" +
                "\t 1 Administrateur\n" +
                "\t 2 Client\n" +
                "\t 3 Quitter\n" );
        System.out.print( "Veuillez saisir votre choix ici : " );

    }

    void logInAdministrateur () {
        System.out.println("Nom: ");
        String nom = choix();
        System.out.println("Mot De Passe: ");
        String motDePasse = choix();
        if ((Administrateur.getIdentifiant()).equals( nom ) && (Administrateur.getMotDePasse()).equals( motDePasse )) {
            System.out.println( "Acces l'adminisrateur succes" );
            choixAdministrateur();
        } else {
            System.out.println("Vous n'êtes pas administrateur");
        }
    }


    String choix(){
        String input = in.nextLine();
    return input;
    }

    void optionInvalid(){
        System.out.println("Option Invalid");
    }
}



package produit;

import java.util.Scanner;

/************************************************************************************************************
 *                                                                                                          *
 *                                    Client: achat des produits (console)                                  *
 *                                                                                                          *
 *                                                                                                          *
 * **********************************************************************************************************/


public class Achat {

    private static void menuAchat(){
        System.out.println("Veuillez saisir votre choix: \n" +
                           "1 Ajouter un produit\n" +
                            "2 Supprimer un produit\n" +
                            "3 Afficher la liste des produits\n" +
                            "4 Valider la panier\n" +
                            "5 Abandonner\n");

    }

    public static PanierDuClient acheterDesProduit(BaseProduit baseProduit, PanierDuClient panierClient){
        boolean continueAchat = true;
        Scanner scanner = new Scanner( System.in );
        String choix;

        while (continueAchat){
            menuAchat();

            choix = scanner.nextLine();
            switch (choix){
                case "1": // ajouter un produit
                    panierClient = creationDuPanier( baseProduit, panierClient );
                    break;
                case "2": // supprimer
                    panierClient = supprimerProduitPanier(baseProduit, panierClient);
                    break;
                case "3": // afficher la liste des produits
                    panierClient.afficherUnPanier();
                    break;
                case "4": // Valider la panier
                    {
                        if (panierClient != null) {
                            panierClient.setValiderLePanier( true );
                            continueAchat = false;
                        } else {
                            System.out.println("Votre panier est vide");
                        }
                    }
                    break;
                case "5": // abandoner
                    continueAchat = false;
                    break;

            }

        }
        return panierClient;
    }

    private static PanierDuClient supprimerProduitPanier(BaseProduit baseProduit, PanierDuClient panierClient) {
        Scanner scanner = new Scanner( System.in );
        baseProduit.affichLesListeProduit();
        System.out.println("Donner le nom du produit que vous voulez supprimer: ");
        String nomDeProduit = scanner.nextLine();
        for (int i = 0; i < baseProduit.getListeDeProduit().size(); i++) {
            if (nomDeProduit.equals( baseProduit.getListeDeProduit().get( i ).getNomProduit() )){
                baseProduit.getListeDeProduit().get( i ).setQuantiteEnStock( (baseProduit.getListeDeProduit().get( i ).getQuantiteEnStock() + 1) );
            }
        }

        return panierClient;
    }

    private static PanierDuClient creationDuPanier(BaseProduit baseProduit, PanierDuClient panierClient){
        Scanner scanner = new Scanner( System.in );
        int quantiteEnStock;
        float prix=0;
        baseProduit.affichLesListeProduit();
        System.out.println("Donner le nom du produit que vous voulez ajouter: ");
        String nomDeProduit = scanner.nextLine();

        for (int i = 0; i < baseProduit.getListeDeProduit().size(); i++) {
            if (nomDeProduit.equals( baseProduit.getListeDeProduit().get( i ).getNomProduit() )){
               if (baseProduit.getListeDeProduit().get( i ).getQuantiteEnStock() > 0){
                   prix = baseProduit.getListeDeProduit().get( i ).getPrixUnitaire();
                   baseProduit.getListeDeProduit().get( i ).setQuantiteEnStock( (baseProduit.getListeDeProduit().get( i ).getQuantiteEnStock() - 1) );
                   panierClient.ajouteProduit( new Produit( nomDeProduit,prix,1 ) );
                   return panierClient;

               }else {
                   System.out.println("Désolé, " + nomDeProduit + " est en rupture de stock");
               }
            }
        }
        return panierClient;
    }



}

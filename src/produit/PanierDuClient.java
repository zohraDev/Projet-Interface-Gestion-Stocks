package produit;

import javax.swing.*;
import java.util.ArrayList;

/************************************************************************************************************
 *                                                                                                          *
 *                              Client: panier pour les produits à realiser                                 *
 *                            affichage du panier, ajouter et supprimer un produit                          *
 *                                                                                                          *
 * **********************************************************************************************************/



public class PanierDuClient {
    private ArrayList<Produit> panier = null;
    private boolean validerLePanier;

    public PanierDuClient(){
        panier= new ArrayList<>();

    }

    public ArrayList<Produit> getPanier() {
        return panier;
    }

    public boolean getValiderLePanier() {
        return validerLePanier;
    }

    public void setValiderLePanier(boolean validerLePanier) {
        this.validerLePanier = validerLePanier;
    }





    public void  afficherUnPanier(){
        if (panier != null) {
            for (Produit produit : this.panier) {
                System.out.println( "Le nom de produit: " + produit.getNomProduit() + "\tPrix: " + produit.getPrixUnitaire() + "\tQuantité de produit: " + produit.getQuantiteEnStock() );

            }
            System.out.println( "Le prix total de votre panier est: " + prixTotal() );
        } else {
            System.out.println("le panier est vide");
        }

    }

    public float prixTotal(){
        float prix = 0;
        if (this.panier != null){
            for (Produit produit : this.panier) {
                prix += produit.getPrixUnitaire() * produit.getQuantiteEnStock();
            }
        }return prix;
    }



    public ArrayList<Produit> supprimerUnProduit(Produit produitASupprimer) {
        if (this.panier != null) {
            for (Produit produit : this.panier) {
                if (produit.getNomProduit().equals( produitASupprimer.getNomProduit() )) {
                    this.panier.remove( produit );
                }
            }
        } else {
            JOptionPane.showMessageDialog( null, "Votre panier est vide", "", JOptionPane.ERROR_MESSAGE );
            System.out.println("Votre panier est vide ");
        }
        return panier;
    }


    public void supprimerUnProduitInterface(Produit produitASupprimer) {
        if(this.panier != null){
            for (Produit produit : this.panier) {
                if (produit.getNomProduit().equals( produitASupprimer.getNomProduit() )){
                    this.panier.remove( produit );
                }
            }
        }

    }


    public ArrayList<Produit> ajouteProduit(Produit produitAjouter){
        boolean siProduitExiste = false;
        for (Produit produit : this.panier) {
            if (produit.getNomProduit().equals( produitAjouter.getNomProduit() )){
                produit.setQuantiteEnStock(( produit.getQuantiteEnStock() + 1) );
                siProduitExiste = true;
            }

        }
        if (siProduitExiste == false){
            panier.add(produitAjouter);
        }

        return  panier;
    }


}


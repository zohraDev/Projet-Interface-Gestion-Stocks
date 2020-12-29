package produit;


import javax.swing.*;
import java.util.ArrayList;

/************************************************************************************************************
 *                                                                                                          *
 *                                        Base de données des produits                                      *
 *                                                                                                          *
 *                                                                                                          *
 * **********************************************************************************************************/

public class BaseProduit {
    private ArrayList<Produit> listeDeProduit= null;

    public BaseProduit(){

        listeDeProduit = new ArrayList<>();

    }

    public ArrayList<Produit> getListeDeProduit() {
        return listeDeProduit;
    }


    public ArrayList<Produit> ajouteProduit(Produit produit){

            listeDeProduit.add(produit);

        return  listeDeProduit;
    }


    public boolean siProduitExiste(String nomProduit){
        boolean produitExist = false;
        for (int i=0; i<this.listeDeProduit.size(); i++) {
            if ((this.listeDeProduit.get(i).getNomProduit().equals(nomProduit)) ) {
                produitExist = true;
                return  produitExist;
            }
        }
        return produitExist;
    }
    public void affichLesListeProduit() {
        if (this.listeDeProduit.size() > 0) {
            for (int i = 0; i < this.listeDeProduit.size(); i++) {
                System.out.println( "Nom du produit: " + this.listeDeProduit.get( i ).getNomProduit() +
                        "\tPrix: " + this.listeDeProduit.get( i ).getPrixUnitaire() +
                        "\tQuantité: " + this.listeDeProduit.get( i ).getQuantiteEnStock() );
            }
        } else {
            System.out.println("La liste de produit est vide");
        }

    }

    public ArrayList<Produit> supprimerUnProduit(String nomDeProduit) {
        if (this.listeDeProduit.size() > 0) {
            for (Produit produit : this.listeDeProduit) {
                if (produit.getNomProduit().equals( nomDeProduit )) {
                    this.listeDeProduit.remove( produit );
                }
            }
        } else {
            JOptionPane.showMessageDialog( null, "La liste de produit est vide","",JOptionPane.ERROR_MESSAGE );
            System.out.println("La liste de produit est vide");
        }

        return listeDeProduit;
    }


}

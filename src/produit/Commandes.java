package produit;

import java.io.Serializable;
import java.util.ArrayList;

/************************************************************************************************************
 *                                                                                                          *
 *                                           Liste des commandes serialisé                                  *
 *                                                                                                          *
 *                                                                                                          *
 * **********************************************************************************************************/

public class Commandes implements Serializable {

    private String nomClient;
    private ArrayList<Produit> panier;

    public  Commandes(String nomClient, ArrayList<Produit> panier){
        this.nomClient = nomClient;
        this.panier = panier;


    }

    public String getNomClient() {
        return nomClient;
    }

    public ArrayList<Produit> getPanier() {
        return panier;
    }
}

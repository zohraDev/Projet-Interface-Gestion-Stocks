package produit;

import java.io.Serializable;


public class Produit implements Serializable {
    private String nomProduit;
    private float prixUnitaire;
    private int quantiteEnStock;

    public Produit(String nomProduit, float prix, int quantiteEnStock){
        this.nomProduit = nomProduit;
        this.prixUnitaire = prix;
        this.quantiteEnStock =quantiteEnStock;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public float getPrixUnitaire() {
        return prixUnitaire;
    }

    public int getQuantiteEnStock() {
        return quantiteEnStock;
    }

    public void setQuantiteEnStock(int quantiteEnStock) {
        this.quantiteEnStock = quantiteEnStock;
    }


}

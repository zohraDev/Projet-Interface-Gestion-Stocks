package Fenetres;

import objects.BaseClient;
import produit.BaseProduit;
import produit.Commandes;
import produit.PanierDuClient;
import produit.Produit;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static Fenetres.Bienvenue.consoleListeDesPanier;
import static Fenetres.Bienvenue.nomClientCommande;
import static Fenetres.ListeDesProduits.baseProduit;

/************************************************************************************************************
 *                                                                                                          *
 *                                             Client: achat des produits                                   *
 *                                                                                                          *
 *                                                                                                          *
 * **********************************************************************************************************/

public class AchatDuClient extends JFrame {
    JLabel prixLabel;
    static PanierDuClient panierDuClient = new PanierDuClient();

    public AchatDuClient(){
        super("Achat");

        this.setSize( 800, 600 );
        this.setDefaultCloseOperation( DISPOSE_ON_CLOSE);
        this.setLocation( 50,50 );


        /*******************************     Boutons & textFields: ajouter des produits dans le panier   ******************************/
        JPanel panelBtnLabel = new JPanel();
        panelBtnLabel.setLayout( new GridLayout(4,1) );


        JTextField saisie = new JTextField();
        saisie.setPreferredSize( new Dimension(100,30) );
        saisie.setToolTipText( "Saisir le produit à supprimer ou ajouter" );
        panelBtnLabel.add( saisie );

        JButton ajouterProduit = new JButton("Ajouter");
        panelBtnLabel.add( ajouterProduit );


        ajouterProduit.addActionListener( e->{
            panierDuClient=ajouterUnProduitPanier( baseProduit.getListeDeProduit(),panierDuClient, saisie.getText() );

            String [] lesChamps = {"nom", "prix" , "quantite"};

            String [][] donnees = new String[panierDuClient.getPanier().size()][3];
            for (int i = 0; i < panierDuClient.getPanier().size(); i++) {
                donnees[i][0] = panierDuClient.getPanier().get( i ).getNomProduit();
                donnees[i][1] = Float.toString(panierDuClient.getPanier().get( i ).getPrixUnitaire());
                donnees[i][2] = Integer.toString(panierDuClient.getPanier().get( i ).getQuantiteEnStock());
            }

            JTable table =new JTable(donnees,lesChamps);
            JScrollPane scrollPane = new JScrollPane(table);
            getContentPane().add( scrollPane,BorderLayout.CENTER );

            prixLabel.setText( "Le prix total est: " + panierDuClient.prixTotal());


            /******************     JTable affiche pas sans -> en bas  ********************/
            JLabel print = new JLabel("    ");
            getContentPane().add( print, BorderLayout.PAGE_START );
            print.setText( "");

        } );


        /******************     supprimer un produit  dans le panier    ********************/

        JButton supprimerProduit = new JButton("Supprimer");
        panelBtnLabel.add( supprimerProduit );

        supprimerProduit.addActionListener( e -> {

        supprimerProduitPanier( baseProduit, panierDuClient, saisie.getText() );

        prixLabel.setText( "Le prix total est: " + panierDuClient.prixTotal());

        } );


        /******************     valider le panier    ********************/
        JButton valider = new JButton("Valider la commande");
        panelBtnLabel.add( valider );

        Commandes commandes = new Commandes(  nomClientCommande, panierDuClient.getPanier() );

        valider.addActionListener( e ->{
            FileOutputStream fichierCommandes = null;
            try {
                fichierCommandes = new FileOutputStream("fichierCommandes.ser");
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            ObjectOutputStream oos = null;
            try {
                oos = new ObjectOutputStream(fichierCommandes);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            try {
                oos.writeObject(commandes);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            try {
                oos.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }



           consoleListeDesPanier.add( panierDuClient );


        } );



        /******************     label & bouton menu principal  ********************/
        JPanel panelLabel = new JPanel();
        panelLabel.setLayout( new GridLayout(2,1) );

        prixLabel = new JLabel("Prix Total: ");
        panelLabel.add( prixLabel);

        JButton quitter = new JButton("Menu Principal");
        quitter.setToolTipText( "Revenir au menu principal" );
        panelLabel.add( quitter );


        quitter.addActionListener( e->{
            this.setVisible( false );
            (new Bienvenue()).setVisible( true );

        } );



        JPanel piedsFenetre = new JPanel();
        piedsFenetre.setLayout( new GridLayout(1,2) );
        piedsFenetre.add( panelBtnLabel );
        piedsFenetre.add( panelLabel );

        getContentPane().add( piedsFenetre,BorderLayout.PAGE_END );



    }





    /******************    fonction pour ajouter un produit  ********************/
    private PanierDuClient ajouterUnProduitPanier(ArrayList<Produit>listDesProduits, PanierDuClient produitPanier, String nomDeProduit){
        float prix;
        for (int i = 0; i <listDesProduits.size(); i++) {
            if (nomDeProduit.equals( listDesProduits.get( i ).getNomProduit() )){

                if (listDesProduits.get( i ).getQuantiteEnStock() > 0){

                    prix = listDesProduits.get( i ).getPrixUnitaire();

                    listDesProduits.get( i ).setQuantiteEnStock( (listDesProduits.get( i ).getQuantiteEnStock() - 1) );
                    produitPanier.ajouteProduit( new Produit( nomDeProduit,prix,1 ) );

                    return produitPanier;

                }else {
                    JOptionPane.showMessageDialog( null," Désolé, " + nomDeProduit + " est en rupture de stock" );

                }
            }
        }
        return produitPanier;
    }


    /******************    fonction pour supprimer un produit  ********************/
    private void supprimerProduitPanier(BaseProduit baseProduit, PanierDuClient panierClient, String nomDeProduit) {


        for (int i = 0; i < baseProduit.getListeDeProduit().size(); i++) {
            if (nomDeProduit.equals( baseProduit.getListeDeProduit().get( i ).getNomProduit() )){
                baseProduit.getListeDeProduit().get( i ).setQuantiteEnStock( (baseProduit.getListeDeProduit().get( i ).getQuantiteEnStock() + 1) );
                panierClient.supprimerUnProduit( new Produit( nomDeProduit, baseProduit.getListeDeProduit().get( i ).getPrixUnitaire(), baseProduit.getListeDeProduit().get( i ).getQuantiteEnStock()));
            }
        }
    }

    private float prixTotal(){
        float prix = 0;
        for ( Produit produit : baseProduit.getListeDeProduit()) {
            prix += produit.getPrixUnitaire() * produit.getQuantiteEnStock();

        }
        return prix;
    }


}

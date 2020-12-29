package Fenetres;

import javax.swing.*;
import java.awt.*;

import static Fenetres.ListeDesProduits.baseProduit;


/************************************************************************************************************
 *                                                                                                          *
 *                                     Liste des produits dans le magasin                                   *
 *                                                                                                          *
 *                                                                                                          *
 * **********************************************************************************************************/



public class ListeDesProduitsClient extends JFrame{
    JButton afficherProduitsBtn;
    static String []  lesChamps = {"nom", "prix" , "quantite"};
    static String [][] donnees;
    JPanel pane;


    public ListeDesProduitsClient(){
        super("Liste des produits dans le magasin");

        this.setSize( 800, 600 );
        this.setDefaultCloseOperation( DISPOSE_ON_CLOSE);
        this.setLocation( 900,50 );


        /******************     panel pour les bouton    ********************/
        JPanel panelBtn =new  JPanel();
        panelBtn.setLayout( new GridLayout(1, 2) );
        getContentPane().add( panelBtn, BorderLayout.PAGE_END );

        /******************     panel pour la table    ********************/
        pane= new JPanel();
        getContentPane().add( pane, BorderLayout.CENTER );

        /******************     affichage de la table    ********************/
        afficherProduitsBtn = new JButton("Afficher les produits");
        panelBtn.add( afficherProduitsBtn );
        afficherProduitsBtn.addActionListener( e->{
            this.remove( pane );
            //this.repaint();

            donnees = new String[baseProduit.getListeDeProduit().size()][3];
            for (int i = 0; i < baseProduit.getListeDeProduit().size(); i++) {
                donnees[i][0] = baseProduit.getListeDeProduit().get( i ).getNomProduit();
                donnees[i][1] = Float.toString(baseProduit.getListeDeProduit().get( i ).getPrixUnitaire());
                donnees[i][2] = Integer.toString(baseProduit.getListeDeProduit().get( i ).getQuantiteEnStock());
            }
            pane =new JPanel();

            JTable table =new JTable(donnees,lesChamps);
            JScrollPane scrollPane = new JScrollPane(table);

            pane.add( scrollPane, BorderLayout.CENTER );
            getContentPane().add( pane,BorderLayout.CENTER );

       });




        /******************     menu principal    ********************/
        JButton fermer = new JButton("Fermer");
        panelBtn.add( fermer );

        fermer.addActionListener( e -> {
            this.setVisible( false );

        });

    }
}


package Fenetres;

import produit.Commandes;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

import static Fenetres.Bienvenue.consoleListeDesPanier;


/************************************************************************************************************
 *                                                                                                          *
 *                                         Liste des commandes des clients                                  *
 *                                                                                                          *
 *                                                                                                          *
 * **********************************************************************************************************/


public class ListeDesCommandes extends JFrame {
    JButton afficherProduitsBtn;
    FileInputStream fichierIn ;
    Commandes commandes;
    ObjectInputStream ois ;
    static ArrayList<Commandes> listDeCommandesPanier = new ArrayList<>();

    public ListeDesCommandes(){

        this.setSize( 700, 500 );
        this.setDefaultCloseOperation( DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo( null );
        JPanel panel = (JPanel)this.getContentPane();


        /******************     afficher le produit  (Table des produits)  ********************/
        JPanel panelBtn = new JPanel();
        panelBtn.setLayout( new FlowLayout() );
        panel.add( panelBtn, BorderLayout.PAGE_END );

        afficherProduitsBtn = new JButton("Afficher les paniers");
        afficherProduitsBtn.setPreferredSize( new Dimension(100,30) );
        panelBtn.add( afficherProduitsBtn );

        afficherProduitsBtn.addActionListener( e->{

        /*******************************     importer un ficher serialisé mais pas encore realisé  *********************************/
/*
        while(true){
            try {
                if (!(ois.available() > 0))
                    break;
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            try {
                fichierIn = new FileInputStream( "fichierCommandes.ser" );
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }

            try {
                ois = new ObjectInputStream( fichierIn );
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            try {
                commandes = (Commandes) ois.readObject();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }

            listDeCommandesPanier.add( commandes );
        }
          */


            JPanel panelTable = new JPanel();
            panelTable.setLayout( new GridLayout(1,1));

            String [] lesChamps = { "nom du produit",  "quantite"};
            String [][] donnees = new String[consoleListeDesPanier.size()][2];

            for (int i = 0; i < consoleListeDesPanier.size(); i++) {

                donnees[i][0] = consoleListeDesPanier.get( i ).getPanier().get( 0 ).getNomProduit();
                donnees[i][1] = Integer.toString(consoleListeDesPanier.get( i ).getPanier().get( 1).getQuantiteEnStock());

                System.out.println(donnees[i][0] + " " + donnees[i][1]);

            }
            JTable table =new JTable(donnees,lesChamps);
            JScrollPane scrollPane = new JScrollPane(table);
            panelTable.add( scrollPane );
            panel.add( panelTable, BorderLayout.CENTER );


            /******************     JTable affiche pas si  BorderLayout.PAGE_START est vide  ********************/
            JLabel print = new JLabel("    ");
            panel.add( print, BorderLayout.PAGE_START );
            print.setText( "");



        } );


        /******************     fermer fenetre    ********************/
        JButton fermerFenetre = new JButton("Fermer");
        panelBtn.add( fermerFenetre );

        fermerFenetre.addActionListener( e ->{
            System.exit( 0 );
        } );

        /******************     Menu gestion    ********************/
        JButton menuGestion = new JButton("Menu Gestion");
        panelBtn.add( menuGestion );

        menuGestion.addActionListener( e ->{
            (new GestionAdministrateur()).setVisible( true );
            this.setVisible( false );
        } );


    }
}

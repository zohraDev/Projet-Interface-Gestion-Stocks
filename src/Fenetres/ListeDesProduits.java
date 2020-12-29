package Fenetres;

import produit.BaseProduit;
import produit.Produit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/************************************************************************************************************
 *                                                                                                          *
 *                        Administrateur :  gerer des produit pour le magasin et                            *
 *                                                                                                          *
 *                                                                                                          *
 * **********************************************************************************************************/




public class ListeDesProduits extends JFrame {
    JTextField quantiteProduitTextField;
    JTextField nomProduitTextField;
    JTextField prixProduitTextField;
    JPanel panelDesChamps, panelFinal,panelBtnJTable ;
    JButton ajouterPoduitBtn,afficherProduitsBtn;
    JPanel panelBouton;

    static BaseProduit baseProduit = new BaseProduit();


    public ListeDesProduits(){
        super("Liste des produits");

        this.setSize( 800, 600 );
        this.setDefaultCloseOperation( DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo( null );


        panelFinal = (JPanel) this.getContentPane();
        panelFinal.setLayout( new GridLayout(1,2) );


        /******************     panel pour les saisies   ********************/
        panelDesChamps = new JPanel();
        panelDesChamps.setLayout( new GridLayout(5,2) );


        /******************     nom du produit   ********************/
        JLabel nomProduitLabel = new JLabel("  Nom du produit");

        panelDesChamps.add( nomProduitLabel );

        nomProduitTextField = new JTextField();
        nomProduitTextField.setPreferredSize( new Dimension(100,30) );
        panelDesChamps.add( nomProduitTextField );


        /******************     prix du produit   ********************/
        JLabel prixProduitLabel = new JLabel("  Prix ");
        panelDesChamps.add( prixProduitLabel );

        prixProduitTextField = new JTextField();
        prixProduitTextField.setPreferredSize( new Dimension(100,30) );
        panelDesChamps.add( prixProduitTextField );

        prixProduitTextField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyChar() == '.') {
                    prixProduitTextField.setEditable(true);
                } else {
                    //probleme sur shift
                    prixProduitTextField.setText( "" );
                    prixProduitTextField.setEditable(false);
                    prixProduitTextField.setToolTipText("le champ accepte (0-9 et '.')");
                }
            }
        });


        /******************     quantité du produit   ********************/
        JLabel quantiteProduitLabel = new JLabel("  Quantité");
        panelDesChamps.add( quantiteProduitLabel );

        quantiteProduitTextField = new JTextField();
        quantiteProduitTextField.setPreferredSize( new Dimension(100,30) );
        panelDesChamps.add( quantiteProduitTextField );

        quantiteProduitTextField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
                    quantiteProduitTextField.setEditable(true);
                } else {
                    quantiteProduitTextField.setText("");
                    quantiteProduitTextField.setEditable(false);
                    quantiteProduitTextField.setToolTipText("Veuillez saisir un nombre(0-9)");
                }
            }
        });



        /******************     panel pour les boutons et JTable  ********************/
        panelBtnJTable = new JPanel();
        panelBtnJTable.setLayout( new BorderLayout() );


        /******************     panel pour les boutons   ********************/
        panelBouton = new JPanel();
        panelBouton.setLayout( new GridLayout(1,3) );


 /************************************     Les bouton (ajouter , supprimer, afficher)    **********************************************/

        /******************     ajouter le produit    ********************/
        ajouterPoduitBtn = new JButton("Ajouter");
        panelDesChamps.add( ajouterPoduitBtn );

        ajouterPoduitBtn.addActionListener( e ->{
            if ( baseProduit.siProduitExiste( nomProduitTextField.getText() )){
                int answer=JOptionPane.showConfirmDialog(  null, "Le produit existe déjà, vous voulez ajouter la quantité dans le stock", "Erreur", JOptionPane.YES_NO_OPTION  );
                if (answer==0){
                    ajouteQuantite();
                }
            }else if (nomProduitTextField.getText().equals( "" ) /*|| prixProduitTextField.getText().equals( "" )*/ || quantiteProduitTextField.getText().equals( "" ) ){
                JOptionPane.showMessageDialog( null, "Un des champs est vide, veuillez completer les champs", "Erreur", JOptionPane.ERROR_MESSAGE );
            }
            else {
                Produit produit = new Produit( nomProduitTextField.getText(), Float.parseFloat( prixProduitTextField.getText() ), Integer.parseInt( quantiteProduitTextField.getText() ) );
                baseProduit.ajouteProduit( produit );
            }

            effacerLesChamps();
        } );

        /******************     supprimer le produit    ********************/
        JButton supprimerProduitBtn = new JButton("Supprimer");
        panelBouton.add( supprimerProduitBtn);

        supprimerProduitBtn.addActionListener( e -> {

                baseProduit.supprimerUnProduit( nomProduitTextField.getText() );

            effacerLesChamps();
        } );


        /******************     afficher le produit  (Table des produits)  ********************/
        afficherProduitsBtn = new JButton("Afficher les produits");
        afficherProduitsBtn.setPreferredSize( new Dimension(100,30) );
        panelBouton.add( afficherProduitsBtn );

        afficherProduitsBtn.addActionListener( e->{

            JPanel panelTable = new JPanel();
            panelTable.setLayout( new GridLayout(1,1));

            String [] lesChamps = {"nom", "prix" , "quantite"};
            String [][] donnees = new String[baseProduit.getListeDeProduit().size()][3];
            for (int i = 0; i < baseProduit.getListeDeProduit().size(); i++) {
                donnees[i][0] = baseProduit.getListeDeProduit().get( i ).getNomProduit();
                donnees[i][1] = Float.toString(baseProduit.getListeDeProduit().get( i ).getPrixUnitaire());
                donnees[i][2] = Integer.toString(baseProduit.getListeDeProduit().get( i ).getQuantiteEnStock());
            }
            JTable table =new JTable(donnees,lesChamps);
            JScrollPane scrollPane = new JScrollPane(table);
            panelTable.add( scrollPane );
            panelBtnJTable.add( panelTable, BorderLayout.CENTER );


            /******************     JTable affiche pas si  BorderLayout.PAGE_START est vide  ********************/
            JLabel print = new JLabel("    ");
            panelBtnJTable.add( print, BorderLayout.PAGE_START );
           print.setText( "");

        } );

        /******************     menu gestion    ********************/
        JButton menuPrincipalBtn = new JButton("Menu Gestion");
        panelBouton.add( menuPrincipalBtn );

        menuPrincipalBtn.addActionListener( e -> {
            (new GestionAdministrateur()).setVisible( true );
            this.setVisible( false );

        });

        panelBtnJTable.add( panelBouton, BorderLayout.PAGE_END );
        panelFinal.add( panelDesChamps );
        panelFinal.add( panelBtnJTable );


    }


    /******************    fonctions pour ajouter un produit et effacer les champs    ********************/
    private void ajouteQuantite() {
        for (int i = 0; i < baseProduit.getListeDeProduit().size(); i++) {
            baseProduit.getListeDeProduit().get( i ).setQuantiteEnStock( (baseProduit.getListeDeProduit().get( i ).getQuantiteEnStock()+ Integer.parseInt( quantiteProduitTextField.getText() )));
            System.out.println(baseProduit.getListeDeProduit().get( i ).getQuantiteEnStock());
            }
        }

    private  void effacerLesChamps(){
        nomProduitTextField.setText( "" );
        prixProduitTextField.setText( "" );
        quantiteProduitTextField.setText( "" );

    }


}

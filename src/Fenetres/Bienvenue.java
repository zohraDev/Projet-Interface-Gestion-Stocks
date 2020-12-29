package Fenetres;

import console.Administrateur;
import objects.BaseClient;
import objects.Client;
import produit.PanierDuClient;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/************************************************************************************************************
 *                                                                                                          *
 *                                  depart de notre programme : Interface                                   *
 *                                  Client , Administrateur connexion et                                    *
 *                                  creation compte client                                                  *
 * **********************************************************************************************************/


public class Bienvenue extends JFrame {
   static BaseClient baseClient = new BaseClient();
   static String nomClientCommande;

   static ArrayList<PanierDuClient> consoleListeDesPanier = new ArrayList<>();

    public Bienvenue(){
        super("Bienvenue");

        this.setSize( 700, 500 );
        this.setDefaultCloseOperation( DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo( null );


/******************     creation des panels   ********************/

        JPanel panel = new JPanel();
        panel.setBackground(Color.gray);
        panel.setLayout(new GridLayout(5, 2));

        JPanel panel1 = new JPanel();
        panel1.setLayout( new FlowLayout() );
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel, panel1);
        this.getContentPane().add(split, BorderLayout.CENTER);
/*******************************************     creation de compte client    *****************************************************/

        /******************     Nom   ********************/
        JLabel nvNomLabel = new JLabel("Nom"); //  nom
        JTextField nvNomTextField = new JTextField();
        nvNomTextField.setPreferredSize( new Dimension(100,30) );
        panel.add( nvNomLabel );
        panel.add( nvNomTextField );

        /******************     Prenom   ********************/
        JLabel nvPrenomLabel = new JLabel("Prenom"); //prenom
        JTextField nvPrenomTextField = new JTextField();
        nvPrenomTextField.setPreferredSize( new Dimension(100,30) );
        panel.add( nvPrenomLabel );
        panel.add( nvPrenomTextField );

        /******************     Email    ********************/
        JLabel nvEmailLabel = new JLabel("Email"); // email
        JTextField nvEmailTextField = new JTextField();
        nvEmailTextField.setPreferredSize( new Dimension(100,30) );
        panel.add( nvEmailLabel );
        panel.add( nvEmailTextField );

        /******************     Mot de passe    ********************/

        JLabel nvMotDePasseLabel = new JLabel("Mot De Passe"); // mot de passe
        JPasswordField nvMotDePasseTextField = new JPasswordField();
        nvEmailTextField.setPreferredSize( new Dimension(200,30) );
        panel.add( nvMotDePasseLabel );
        panel.add( nvMotDePasseTextField );

        /******************     Valider creation de compte   ********************/

        JButton validerBouton = new JButton("Valider");
        panel.add( validerBouton );

        validerBouton.addActionListener( e -> {
            if (baseClient.isClientExist( (nvEmailTextField.getText()).trim(), (nvMotDePasseTextField.getText()).trim() )) {
                JOptionPane.showMessageDialog( null, "Le compte existe déjà", "Erreur", JOptionPane.ERROR_MESSAGE );
            }else
                baseClient.creeClient( (nvNomTextField.getText()).trim(), (nvPrenomTextField.getText()).trim(), (nvEmailTextField.getText()).trim(), (nvMotDePasseTextField.getText()).trim() );

                nvNomTextField.setText( "" );
                nvPrenomTextField.setText( "" );
                nvEmailTextField.setText( "" );
                nvMotDePasseTextField.setText( "" );
            });

/*******************************************     Connexion    *****************************************************/


        /******************     identifiant (email)   ********************/
        JLabel idLabel = new JLabel("Identifiant");
        panel1.add( idLabel );

        JTextField idTextField = new JTextField();
        idTextField.setToolTipText( "Email: email@sample.com" );
        idTextField.setPreferredSize( new Dimension(150,30));
        panel1.add( idTextField );

        /******************     Mot de passe    ********************/
        JLabel motDePasseLabel = new JLabel("Mot De Passe");  //mot de passe
        panel1.add( motDePasseLabel );

        JPasswordField motDePasseField = new JPasswordField();
        motDePasseField.setPreferredSize( new Dimension(150,30) );
        panel1.add( motDePasseField );


        /******************     Connexion client    ********************/
        JButton clientBouton = new JButton("Client"); // client
        panel1.add( clientBouton );

        clientBouton.addActionListener( e-> {
            if (baseClient.isClientExist( idTextField.getText().trim(), motDePasseField.getText().trim() )){
                nomClientCommande = recuperNomClient( idTextField.getText() );
                (new AchatDuClient()).setVisible( true );

                ListeDesProduitsClient listeDesProduitsClient = new ListeDesProduitsClient();
                listeDesProduitsClient.setVisible( true );

                this.setVisible( false );

            }else
                JOptionPane.showMessageDialog( null, "Erreur de saisie ou compte n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE );
        } );

        /******************     Connexion Administrateur    ********************/
        JButton administrateurBouton = new JButton("Administrateur"); // administrateur
        panel1.add( administrateurBouton );

        administrateurBouton.addActionListener( e -> {

            if ((idTextField.getText().trim().equals( Administrateur.getIdentifiant())) && (motDePasseField.getText().trim().equals( Administrateur.getMotDePasse() ))){
                this.setVisible( false );
                (new GestionAdministrateur()).setVisible( true );
            }else
                JOptionPane.showMessageDialog( null, "Identifiant ou mot de passe incorrect", "Erreur", JOptionPane.ERROR_MESSAGE );

        } );


        /******************     Quitter    ********************/
        JButton quitterBouton = new JButton("Quitter"); // quitter
        panel1.add( quitterBouton );

        quitterBouton.addActionListener( e ->{
            System.exit( 0 );

        } );

    }

    /******************     methods qui va recuperer le nom de client connecté   ********************/
    private String recuperNomClient(String identifiant){
        String nomDeClient = "";
        for (Client client : baseClient.getListDeClient()) {
            if (client.getEmail().equals( identifiant )){
                nomDeClient = client.getNomClient();

            }
        }
        return nomDeClient;
    }



}

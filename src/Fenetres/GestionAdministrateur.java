package Fenetres;

import javax.swing.*;
import java.awt.*;

/************************************************************************************************************
 *                                                                                                          *
 *                                    Interface  Administrateur:                                            *
 *                                                                                                          *
 *                                                                                                          *
 * **********************************************************************************************************/



public class GestionAdministrateur extends JFrame {

    public GestionAdministrateur(){
        super("Gestion Administrateur");

        this.setSize( 700, 500 );
        this.setDefaultCloseOperation( DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo( null );
        JPanel panelOption = (JPanel)this.getContentPane();
        panelOption.setLayout( new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();


        /******************     Acces sur listes des produits   ********************/
        JButton listesDesProduitsBtn = new JButton("Listes des produits");
        listesDesProduitsBtn.setPreferredSize( new Dimension(200,50) );
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx= 0;
        constraints.gridy= 1;


        listesDesProduitsBtn.addActionListener( e ->{
                (new ListeDesProduits()).setVisible( true );
                this.setVisible( false );
        } );


        panelOption.add( listesDesProduitsBtn, constraints);

        /******************     Listes des commandes   ********************/
        JButton listesDesCommandesBtn = new JButton("Listes des commandes");
        listesDesCommandesBtn.setPreferredSize( new Dimension(200,50) );

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx= 0;
        constraints.gridy= 2;

        panelOption.add( listesDesCommandesBtn);


        listesDesCommandesBtn.addActionListener( e->{
            (new ListeDesCommandes()).setVisible( true );
            this.setVisible( false );

        } );



        /******************     menu principal    ********************/
        JButton menuPrincipalBtn = new JButton("Menu Principal");
        menuPrincipalBtn.setPreferredSize( new Dimension(200,50) );

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx= 0;
        constraints.gridy= 3;

        panelOption.add( menuPrincipalBtn );

        menuPrincipalBtn.addActionListener( e -> {
            (new Bienvenue()).setVisible( true );
            this.setVisible( false );

        });


    }
}



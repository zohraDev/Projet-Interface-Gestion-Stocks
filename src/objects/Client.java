package objects;

import java.util.Date;

/************************************************************************************************************
 *                                                                                                          *
 *                                             compte client                                                *
 *                                                                                                          *
 *                                                                                                          *
 * **********************************************************************************************************/

public class Client {
    private String nomClient;
    private String prenomClient;
    private Date dateNaissance;
    private String email;
    private String adresse;
    private String motDePasseClient;

    public Client(String nomClient, String prenomClient, String email, String motDePasseClient) {
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.email = email;
        this.motDePasseClient = motDePasseClient;

    }

    public String getNomClient() {
        return nomClient;
    }

    public String getEmail() {
        return email;
    }

    public String getMotDePasseClient() {
        return motDePasseClient;
    }
}

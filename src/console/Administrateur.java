package console;


/*****************    compte administration qui contient identifiant et mot de passe fixes   ******************/

public class Administrateur {
    private static String identifiant = "girls";
    private static String motDePasse = "1234";

    public Administrateur(){

    }

    public static String getIdentifiant() {
        return identifiant;
    }

    public static String getMotDePasse() {
        return motDePasse;
    }
}

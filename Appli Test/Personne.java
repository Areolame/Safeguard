import java.sql.Date;
import java.time.LocalDateTime;

public class Personne {

    private String nom, prenom;
    private boolean testPositif = false;
    private Date dateDeNaissance;
    private int id;

    private static int idGlobal = 0;

    public Personne(String nom, String prenom, Date dateDeNaissance) {
        id = idGlobal;
        ++idGlobal;

        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
    }



    public int getId() {
        return id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public Date getDate() {
        return dateDeNaissance;
    }

    
    public boolean getTestPositif() {
        return testPositif;
    }

    public void setTestPositif(boolean testPositif) {
        this.testPositif = testPositif;
    }
}
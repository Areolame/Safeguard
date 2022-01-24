import java.util.ArrayList;

public class PersonneManager {

    private static ArrayList<Personne> personnes = new ArrayList<>();

    public static void initPersonnes()
    {

    }

    public static Personne getUser(int id)
    {
        return personnes.get(id);
    }

    public static ArrayList<Personne> getUsersFromNom(String nom)
    {
        ArrayList<Personne> listPersonne = new ArrayList<>();
        for(Personne personne : personnes)
        {
            if(personne.getNom().equalsIgnoreCase(nom))
            {
                listPersonne.add(personne);
            }
        }
        return listPersonne;
    }

    public static ArrayList<Personne> getUsersFromPrenom(String prenom)
    {
        ArrayList<Personne> listPersonne = new ArrayList<>();
        for(Personne personne : personnes)
        {
            if(personne.getNom().equalsIgnoreCase(prenom))
            {
                listPersonne.add(personne);
            }
        }
        return listPersonne;
    }

}

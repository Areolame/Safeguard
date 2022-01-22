import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LiaisonBDD {

	final static String serverName="localhost:3306";
    final static String mydatabase="gestionhopital";
    final static String URL = "jdbc:mysql://" + serverName + "/" + mydatabase; 
    final static String LOGIN="root";
    final static String PASS="admin";

	public LiaisonBDD()
	{
		// chargement du pilote de bases de donn�es
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e2) {
			System.err.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
		}

	}
	
	public int ajouterPersonne(Personne p) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour=0;
		//connexion � la base de donn�es
		try {
			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			//pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
			//les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvArticle
			ps = con.prepareStatement("INSERT INTO Personne (id, nom, prenom, naissance, positif) VALUES (?, ?, ?, ?, ?)");
			ps.setInt(1,p.getId());
			ps.setString(2,p.getNom());
			ps.setString(3,p.getPrenom());
			ps.setDate(4,p.getDate());
			ps.setBoolean(5,p.getTestPositif());

			//Ex�cution de la requ�te
			retour=ps.executeUpdate();


		} 
		catch (Exception ee) {
			ee.printStackTrace();
		}
		try {if (ps != null)ps.close();} catch (Exception t) {}
		try {if (con != null)con.close();} catch (Exception t) {}

		return retour;
	}
}

import java.sql.*;
import java.time.LocalDateTime;

import Stock.Stock;

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
	
	public int ajouterStock(Stock stock) 
	{
		if(stock.isGel())
		{
		}
		else if(stock.isMasque())
		{
		}
		return 0;
	}

	public int getGel(Stock stock) 
	{
		Connection con = null;
		PreparedStatement ps = null;
		try
		{
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("Select stock_gel From Gel where='" + stock.getName() + "'");
			return ps.getResultSet().findColumn("Stock_gel");
		} 
		catch (Exception ee) {
			ee.printStackTrace();
		}
		try {if (ps != null)ps.close();} catch (Exception t) {}
		try {if (con != null)con.close();} catch (Exception t) {}

		return 0;
	}

	public int getVaccin(Stock stock)
	{
		Connection con = null;
		PreparedStatement ps = null;
		try
		{
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("Select stock_vaccin From Vaccin where='" + stock.getName() + "'");
			return ps.getResultSet().findColumn("stock_vaccin");
		}
		catch (Exception ee) {
			ee.printStackTrace();
		}
		try {if (ps != null)ps.close();} catch (Exception t) {}
		try {if (con != null)con.close();} catch (Exception t) {}

		return 0;
	}

	public int getMasque(Stock stock)
	{
		Connection con = null;
		PreparedStatement ps = null;
		try
		{
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("Select stock_masque From Masque where='" + stock.getName() + "'");
			return ps.getResultSet().findColumn("stock_masque");
		}
		catch (Exception ee) {
			ee.printStackTrace();
		}
		try {if (ps != null)ps.close();} catch (Exception t) {}
		try {if (con != null)con.close();} catch (Exception t) {}

		return 0;
	}

	public int ajouterGel(Stock stock)
	{
		Connection con = null;
		PreparedStatement ps = null;
		int retour=0;
		//connexion � la base de donn�es
		try {
			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			//pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
			//les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvArticle
			ps = con.prepareStatement("INSERT INTO Gel (nom_gel, stock_gel, date_reception) VALUES (?, ?, ?)");
			ps.setString(1, stock.getName());
			ps.setInt(2, getGel(stock) + stock.getNombreDeStock());
			ps.setString(3, dateToString());

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

	public int ajouterMasque(Stock stock)
	{
		Connection con = null;
		PreparedStatement ps = null;
		int retour=0;
		//connexion � la base de donn�es
		try {
			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("INSERT INTO Masque (nom_masque, stock_masque, date_reception) VALUES (?, ?, ?)");
			ps.setString(1, stock.getName());
			ps.setInt(2, getMasque(stock) + stock.getNombreDeStock());
			ps.setString(3, dateToString());

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

	public int ajouterVaccin(Stock stock)
	{
		Connection con = null;
		PreparedStatement ps = null;
		int retour=0;
		//connexion � la base de donn�es
		try {
			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("INSERT INTO Vaccin (nom_vaccin, stock_vaccin, date_expiration) VALUES (?, ?, ?)");
			ps.setString(1, stock.getName());
			ps.setInt(2, getVaccin(stock) + stock.getNombreDeStock());
			ps.setString(3, dateToString());

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

	public int ajouterPersonne(Personne p)
	{
		Connection con = null;
		PreparedStatement ps = null;
		int retour=0;
		//connexion � la base de donn�es
		try {
			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("INSERT INTO Personne (id, nom, prenom, naissance, positif) VALUES (?, ?, ?, ?, ?)");
			ps.setInt(1, p.getId());
			ps.setString(2, p.getNom());
			ps.setString(3, p.getPrenom());
			ps.setString(4, p.getDate());
			ps.setBoolean(5, p.getTest());

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
	
	
	
	private static String dateToString()
	{
		LocalDateTime now = LocalDateTime.now();
		
		return now.getDayOfMonth() + "/" + now.getMonthValue() + "/" + now.getYear();
	}
	
}

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;

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
	
	public LinkedHashMap<String, Integer> getStocks()
    {
        LinkedHashMap<String, Integer> stocks =  new LinkedHashMap<>();
        stocks.putAll(getStockGel());
        stocks.putAll(getStockMasque());
        stocks.putAll(getStockTest());
        stocks.putAll(getStockVaccin());
        return stocks;
    }

	public LinkedHashMap<String, Integer> getStockGel()
	{
		LinkedHashMap<String, Integer> stocks = new LinkedHashMap<>();
		Connection con = null;
		PreparedStatement ps = null;
		try
		{
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("Select nom_gel, stock_gel From Gel");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				stocks.put(rs.getString("nom_gel"), rs.getInt("stock_gel"));
			}
		}
		catch (Exception ee) {
			ee.printStackTrace();
		}
		try {if (ps != null)ps.close();} catch (Exception t) {}
		try {if (con != null)con.close();} catch (Exception t) {}
		return stocks;
	}

	public LinkedHashMap<String, Integer> getStockMasque()
	{
		LinkedHashMap<String, Integer> stocks = new LinkedHashMap<>();
		Connection con = null;
		PreparedStatement ps = null;
		try
		{
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("Select nom_masque, stock_masque From Masque");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				stocks.put(rs.getString("nom_masque"), rs.getInt("stock_masque"));
			}
		}
		catch (Exception ee) {
			ee.printStackTrace();
		}
		try {if (ps != null)ps.close();} catch (Exception t) {}
		try {if (con != null)con.close();} catch (Exception t) {}
		return stocks;
	}

	public LinkedHashMap<String, Integer> getStockVaccin()
	{
		LinkedHashMap<String, Integer> stocks = new LinkedHashMap<>();
		Connection con = null;
		PreparedStatement ps = null;
		try
		{
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("Select nom_vaccin, stock_vaccin From Vaccin");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				stocks.put(rs.getString("nom_vaccin"), rs.getInt("stock_vaccin"));
			}
		}
		catch (Exception ee) {
			ee.printStackTrace();
		}
		try {if (ps != null)ps.close();} catch (Exception t) {}
		try {if (con != null)con.close();} catch (Exception t) {}
		return stocks;
	}

	public LinkedHashMap<String, Integer> getStockTest()
	{
		LinkedHashMap<String, Integer> stocks = new LinkedHashMap<>();
		Connection con = null;
		PreparedStatement ps = null;
		try
		{
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("Select nom_test, stock_test From Test");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				stocks.put(rs.getString("nom_test"), rs.getInt("stock_test"));
			}
		}
		catch (Exception ee) {
			ee.printStackTrace();
		}
		try {if (ps != null)ps.close();} catch (Exception t) {}
		try {if (con != null)con.close();} catch (Exception t) {}
		return stocks;
	}
	
	public int ajouterStock(Stock stock) 
	{
		if(stock.isGel())
		{
			return ajouterGel(stock);
		}
		else if(stock.isMasque())
		{
			return ajouterMasque(stock);
		}
		else if(stock.isVaccin())
		{
			return ajouterVaccin(stock);
		}
		else if (stock.isTest())
		{
			return ajouterTest(stock);
		}
		return -1;
	}

	public int getGel(Stock stock) 
	{
		Connection con = null;
		PreparedStatement ps = null;
		try
		{
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("Select stock_gel, nom_gel From Gel");
			ResultSet rs=ps.executeQuery();
			rs.next();
			String tmp = rs.getString("nom_gel");
			while(!tmp.equalsIgnoreCase(stock.getName())) {
				rs.next();
				tmp = rs.getString("nom_gel");
			}
			return rs.getInt("stock_gel");
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
			ps = con.prepareStatement("Select stock_vaccin, nom_vaccin From Vaccin");
			ResultSet rs=ps.executeQuery();
			rs.next();
			String tmp = rs.getString("nom_vaccin");
			while(!tmp.equalsIgnoreCase(stock.getName())) {
				rs.next();
				tmp = rs.getString("nom_vaccin");
			}
			return rs.getInt("stock_vaccin");
		}
		catch (Exception ee) {
			ee.printStackTrace();
		}
		try {if (ps != null)ps.close();} catch (Exception t) {}
		try {if (con != null)con.close();} catch (Exception t) {}

		return 0;
	}
	
	public int getTest(Stock stock)
	{
		Connection con = null;
		PreparedStatement ps = null;
		try
		{
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("Select stock_test, nom_test From Test");
			ResultSet rs=ps.executeQuery();
			rs.next();
			String tmp = rs.getString("nom_test");
			while(!tmp.equalsIgnoreCase(stock.getName())) {
				rs.next();
				tmp = rs.getString("nom_test");
			}
			return rs.getInt("stock_test");
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
			ps = con.prepareStatement("Select stock_masque, nom_masque From Masque");
			ResultSet rs=ps.executeQuery();
			rs.next();
			String tmp = rs.getString("nom_masque");
			while(!tmp.equalsIgnoreCase(stock.getName())) {
				rs.next();
				tmp = rs.getString("nom_gel");
			}
			return rs.getInt("stock_masque");
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
			ps = con.prepareStatement("UPDATE Gel SET stock_gel = ?, date_reception = ? where nom_gel = ?");
			ps.setInt(1, getGel(stock) + stock.getNombreDeStock());
			ps.setDate(2, java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));
			ps.setString(3, stock.getName());

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
			ps = con.prepareStatement("UPDATE Masque SET stock_masque = ?, date_reception = ? where nom_masque = ?");
			ps.setInt(1, getMasque(stock) + stock.getNombreDeStock());
			ps.setDate(2, java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));
			ps.setString(3, stock.getName());

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
			ps = con.prepareStatement("UPDATE Vaccin SET stock_vaccin = ?, date_expiration = ? where nom_vaccin = ?");
			ps.setInt(1, getVaccin(stock) + stock.getNombreDeStock());
			ps.setDate(2, java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));
			ps.setString(3, stock.getName());

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

	public int ajouterTest(Stock stock)
	{
		Connection con = null;
		PreparedStatement ps = null;
		int retour=0;
		//connexion � la base de donn�es
		try {
			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE Test SET stock_test = ?, date_reception = ? where nom_test = ?");
			ps.setInt(1, getTest(stock) + stock.getNombreDeStock());
			ps.setDate(2, java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));
			ps.setString(3, stock.getName());

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
			ps.setDate(4, java.sql.Date.valueOf(p.getDateDeNaissance().toLocalDate()));
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

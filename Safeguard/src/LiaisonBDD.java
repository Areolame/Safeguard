import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import Stock.*;

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
	
	public ArrayList<Stock> getStocks()
    {
		ArrayList<Stock> stocks =  new ArrayList<Stock>();
        stocks.addAll(getStockGel());
        stocks.addAll(getStockMasque());
        stocks.addAll(getStockTest());
        stocks.addAll(getStockVaccin());
        stocks.addAll(getStockStock());
        return stocks;
    }
	
	public ArrayList<StockAutre> getStockStock() {
		ArrayList<StockAutre> stocks = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		try
		{
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("Select * From Stock");
			ResultSet rs=ps.executeQuery();
			if (rs.next()) {
				ResultSetMetaData meta = rs.getMetaData();
				int nb_colonne = meta.getColumnCount();
				for (int i=2; i<=nb_colonne; i+=2) {
					String tmpStr = rs.getString(i);
					int tmpInt = rs.getInt(i+1);
					stocks.add(new StockAutre(tmpStr,tmpInt, i/2));
				}
			}
		}
		catch (Exception ee) {
			ee.printStackTrace();
		}
		try {if (ps != null)ps.close();} catch (Exception t) {}
		try {if (con != null)con.close();} catch (Exception t) {}
		return stocks;
	}

	public ArrayList<StockGel> getStockGel()
	{
		ArrayList<StockGel> stocks = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		try
		{
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("Select nom_gel, stock_gel From Gel");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				stocks.add(new StockGel(rs.getString("nom_gel"), rs.getInt("stock_gel")));
			}
		}
		catch (Exception ee) {
			ee.printStackTrace();
		}
		try {if (ps != null)ps.close();} catch (Exception t) {}
		try {if (con != null)con.close();} catch (Exception t) {}
		return stocks;
	}

	public ArrayList<StockMasque> getStockMasque()
	{
		ArrayList<StockMasque> stocks = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		try
		{
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("Select nom_masque, stock_masque From Masque");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				stocks.add(new StockMasque(rs.getString("nom_masque"), rs.getInt("stock_masque")));
			}
		}
		catch (Exception ee) {
			ee.printStackTrace();
		}
		try {if (ps != null)ps.close();} catch (Exception t) {}
		try {if (con != null)con.close();} catch (Exception t) {}
		return stocks;
	}

	public ArrayList<StockVaccin> getStockVaccin()
	{
		ArrayList<StockVaccin> stocks = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		try
		{
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("Select nom_vaccin, stock_vaccin From Vaccin");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				stocks.add(new StockVaccin(rs.getString("nom_vaccin"), rs.getInt("stock_vaccin")));
			}
		}
		catch (Exception ee) {
			ee.printStackTrace();
		}
		try {if (ps != null)ps.close();} catch (Exception t) {}
		try {if (con != null)con.close();} catch (Exception t) {}
		return stocks;
	}

	public ArrayList<StockTest> getStockTest()
	{
		ArrayList<StockTest> stocks = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		try
		{
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("Select nom_test, stock_test From Test");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				stocks.add(new StockTest(rs.getString("nom_test"), rs.getInt("stock_test")));
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
		else if (stock.isAutre()) {
			return ajouterAutre(stock, ((StockAutre)(stock)).getNumero());
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
				tmp = rs.getString("nom_masque");
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
	
	public int getAutre(Stock stock, int numero)
	{
		Connection con = null;
		PreparedStatement ps = null;
		int tmp = 0;
		try
		{
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("Select * From Stock");
			ResultSet rs=ps.executeQuery();
			rs.next();
			tmp = rs.getInt("stock" + numero);
		}
		catch (Exception ee) {
			ee.printStackTrace();
		}
		try {if (ps != null)ps.close();} catch (Exception t) {}
		try {if (con != null)con.close();} catch (Exception t) {}

		return tmp;
	}

	public int ajouterGel(Stock stock)
	{
		Connection con = null;
		PreparedStatement ps = null;
		int total = 0;
		//connexion � la base de donn�es
		try {
			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			//pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
			//les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvArticle
			ps = con.prepareStatement("UPDATE Gel SET stock_gel = ?, date_reception = ? where nom_gel = ?");
			total = getGel(stock) + stock.getNombreDeStock();
			ps.setInt(1, total);
			ps.setDate(2, java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));
			ps.setString(3, stock.getName());

			//Ex�cution de la requ�te
			ps.executeUpdate();


		}
		catch (Exception ee) {
			ee.printStackTrace();
		}
		try {if (ps != null)ps.close();} catch (Exception t) {}
		try {if (con != null)con.close();} catch (Exception t) {}

		return total;
	}



	public int ajouterMasque(Stock stock)
	{
		Connection con = null;
		PreparedStatement ps = null;
		int total = 0;
		//connexion � la base de donn�es
		try {
			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE Masque SET stock_masque = ?, date_reception = ? where nom_masque = ?");
			total = getMasque(stock) + stock.getNombreDeStock();
			ps.setInt(1, total);
			ps.setDate(2, java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));
			ps.setString(3, stock.getName());

			//Ex�cution de la requ�te
			ps.executeUpdate();


		}
		catch (Exception ee) {
			ee.printStackTrace();
		}
		try {if (ps != null)ps.close();} catch (Exception t) {}
		try {if (con != null)con.close();} catch (Exception t) {}

		return total;
	}

	public int ajouterVaccin(Stock stock)
	{
		Connection con = null;
		PreparedStatement ps = null;
		int total = 0;
		//connexion � la base de donn�es
		try {
			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE Vaccin SET stock_vaccin = ?, date_reception = ? where nom_vaccin = ?");
			total = getVaccin(stock) + stock.getNombreDeStock();
			ps.setInt(1, total);
			ps.setDate(2, java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));
			ps.setString(3, stock.getName());

			//Ex�cution de la requ�te
			ps.executeUpdate();


		}
		catch (Exception ee) {
			ee.printStackTrace();
		}
		try {if (ps != null)ps.close();} catch (Exception t) {}
		try {if (con != null)con.close();} catch (Exception t) {}

		return total;
	}

	public int ajouterTest(Stock stock)
	{
		Connection con = null;
		PreparedStatement ps = null;
		int total = 0;
		//connexion � la base de donn�es
		try {
			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE Test SET stock_test = ?, date_reception = ? where nom_test = ?");
			total = getTest(stock) + stock.getNombreDeStock();
			ps.setInt(1, total);
			ps.setDate(2, java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));
			ps.setString(3, stock.getName());

			//Ex�cution de la requ�te
			ps.executeUpdate();


		}
		catch (Exception ee) {
			ee.printStackTrace();
		}
		try {if (ps != null)ps.close();} catch (Exception t) {}
		try {if (con != null)con.close();} catch (Exception t) {}

		return total;
	}
	
	public int ajouterAutre(Stock stock, int numero)
	{
		Connection con = null;
		PreparedStatement ps = null;
		int total = 0;
		//connexion � la base de donn�es
		try {
			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE Stock SET stock" + numero + " = ?");
			total = getAutre(stock, numero) + stock.getNombreDeStock();
			ps.setInt(1, total);

			//Ex�cution de la requ�te
			ps.executeUpdate();


		}
		catch (Exception ee) {
			ee.printStackTrace();
		}
		try {if (ps != null)ps.close();} catch (Exception t) {}
		try {if (con != null)con.close();} catch (Exception t) {}

		return total;
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
	
	public ArrayList<Personne> getPersonne()
	{
		ArrayList<Personne> personne = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		try
		{
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("Select * From Personne");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				personne.add(new Personne(rs.getString("nom"), rs.getString("prenom"), rs.getTimestamp("naissance").toLocalDateTime()));
			}
		}
		catch (Exception ee) {
			ee.printStackTrace();
		}
		try {if (ps != null)ps.close();} catch (Exception t) {}
		try {if (con != null)con.close();} catch (Exception t) {}
		return personne;
	}
	
	public Boolean checkExist(String nom_table, String nom_objet) {
		Connection con = null;
		PreparedStatement ps = null;
		Boolean found = false;
		try
		{
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("Select nom_" + nom_table + " From " + nom_table);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				String tmpStr = rs.getString("nom_" + nom_table);
				if (tmpStr.equals(nom_objet)) {
					found = true;
				}
			}
		} 
		catch (Exception ee) {
			ee.printStackTrace();
		}
		try {if (ps != null)ps.close();} catch (Exception t) {}
		try {if (con != null)con.close();} catch (Exception t) {}

		return found;
	}
	
	public Boolean checkExistStock(String nom_objet) 
	{
		Connection con = null;
		PreparedStatement ps = null;
		Boolean found = false;
		try
		{
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("Select * From Stock");
			ResultSet rs=ps.executeQuery();
			if (rs.next()) {
				ResultSetMetaData meta = rs.getMetaData();
				int nb_colonne = meta.getColumnCount();
				for (int i=1; i<=nb_colonne; i++) {
					String tmpStr = rs.getString(i);
					if (tmpStr.equals(nom_objet)) {
						found = true;
					}
				}
			}
		} 
		catch (Exception ee) {
			ee.printStackTrace();
		}
		try {if (ps != null)ps.close();} catch (Exception t) {}
		try {if (con != null)con.close();} catch (Exception t) {}

		return found;
	}
	
	public int tailleTableStock() {
		Connection con = null;
		PreparedStatement ps = null;
		try
		{
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("Select nombre_element From Stock");
			ResultSet rs=ps.executeQuery();
			rs.next();
			return rs.getInt("nombre_element");
		} 
		catch (Exception ee) {
			ee.printStackTrace();
		}
		try {if (ps != null)ps.close();} catch (Exception t) {}
		try {if (con != null)con.close();} catch (Exception t) {}

		return 0;
	}
	
	public void ajoutLigneTable(String nom_table, String nom_champ, int stock_champ) {
		Connection con = null;
		PreparedStatement ps = null;
		String lower_nom_table = nom_table.toLowerCase();
		try
		{
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("INSERT INTO " + nom_table + " (nom_" + lower_nom_table + ",stock_" + lower_nom_table + ", date_reception) VALUES (?, ?, ?)");
			ps.setString(1, nom_champ);
			ps.setInt(2, stock_champ);
			ps.setDate(3, java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));

			//Ex�cution de la requ�te
			ps.executeUpdate();
		} 
		catch (Exception ee) {
			ee.printStackTrace();
		}
		try {if (ps != null)ps.close();} catch (Exception t) {}
		try {if (con != null)con.close();} catch (Exception t) {}
	}
	
	public void ajoutColonneStock(int numero, String nom_champ, int stock_champ) {
		Connection con = null;
		PreparedStatement ps = null;
		try
		{
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("ALTER TABLE stock ADD champ" + numero + " VARCHAR(100) NOT NULL, ADD stock" + numero + " INT NOT NULL");

			//Ex�cution de la requ�te
			ps.executeUpdate();
			
			ps = con.prepareStatement("UPDATE stock SET nombre_element = ?, champ" + numero + " = ?, stock" + numero + " = ?");
			ps.setInt(1, numero);
			ps.setString(2, nom_champ);
			ps.setInt(3, stock_champ);

			//Ex�cution de la requ�te
			ps.executeUpdate();
		} 
		catch (Exception ee) {
			ee.printStackTrace();
		}
		try {if (ps != null)ps.close();} catch (Exception t) {}
		try {if (con != null)con.close();} catch (Exception t) {}
	}
	
	
	private static String dateToString()
	{
		LocalDateTime now = LocalDateTime.now();
		
		return now.getDayOfMonth() + "/" + now.getMonthValue() + "/" + now.getYear();
	}
	
}

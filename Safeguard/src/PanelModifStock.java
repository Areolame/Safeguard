import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Stock.*;
import Stock.Enums.*;

import javax.swing.*;

public class PanelModifStock extends JPanel {

	private static final long serialVersionUID = -5929009624829443824L;
	private ArrayList<JFormattedTextField> fieldList = new ArrayList<JFormattedTextField>();
	private ArrayList<JComboBox<String>> comboList = new ArrayList<JComboBox<String>>();
	public static LinkedHashMap<String, Integer> mapValeur = null;
	public static int nombre_element_table_stock;
	
	// Boutons
	public JButton boutonAdd;
	public JButton boutonCancel;
	
	public PanelModifStock() {
		this.setSize(400,350);
		//création du layout
		GridLayout experimentLayout = new GridLayout(0,1);
		this.setLayout(experimentLayout);
        
        JPanel panel1 = new JPanel();
        GridLayout layoutPanel1 = new GridLayout(0,3);
        layoutPanel1.setHgap(10);
        panel1.setLayout(layoutPanel1);
		//instantiation des  composants graphiques
        JLabel nomPage = new JLabel("Inventory Management", SwingConstants.CENTER);
		boutonAdd = new JButton("Add");
		boutonCancel = new JButton("Cancel");
		
		// Récupération de l'inventaire dans la BDD
		mapValeur = Fenetre.liaison.getStocks();
		int length = mapValeur.keySet().toArray().length;
		String type = TypeStock.getFrom(mapValeur.keySet().toArray()[0].toString());
		Vector<String> liste = new Vector<String>();
		for (String key : mapValeur.keySet()) {
			String tmpType = TypeStock.getFrom(key);
			if (key.equals(mapValeur.keySet().toArray()[length-1])) {
				liste.add(key);
			}
		    if (!tmpType.equalsIgnoreCase(type)||key.equals(mapValeur.keySet().toArray()[length-1])) {
		    	JLabel tmpLabel1 = new JLabel(type, SwingConstants.CENTER);
			    JComboBox<String> listeType1 = new JComboBox<String>(liste);
			    JFormattedTextField tmpField1 = new JFormattedTextField();
			    liste = new Vector<String>();
			    panel1.add(tmpLabel1);
			    panel1.add(listeType1);
			    panel1.add(tmpField1);
			    fieldList.add(tmpField1);
			    comboList.add(listeType1);
		    }
		    type = tmpType;
			liste.add(key);
		}
		
		//ajout des composants sur le container
    	this.add(nomPage);
		this.add(panel1);
		
		JPanel panel2 = new JPanel();
        GridLayout layoutPanel2 = new GridLayout(0,2);
        layoutPanel2.setHgap(10);
        panel2.setLayout(layoutPanel2);
        panel2.add(boutonAdd);
        panel2.add(boutonCancel);
        panel2.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        this.add(panel2);
		
		this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		
	}
	
	public void add() {
		for (int i=0; i < fieldList.size(); i++) {
			String str1 = fieldList.get(i).getText();
			if (!str1.isBlank()) {
				String nom_objet = (String)comboList.get(i).getSelectedItem();
				Integer nombre_stock = Integer.parseInt(str1);
				Integer nombre_total = null;
				Stock stock = null;
				if (i == 0)
				{
					stock = new StockGel(EnumGel.from(nom_objet), nombre_stock);
				}
				if (i == 1)
				{
					stock = new StockMasque(EnumMasque.from(nom_objet), nombre_stock);
				}
				if (i == 2){
					stock = new StockTest(EnumTest.from(nom_objet), nombre_stock);
				}
				if (i == 3)
				{
					stock = new StockVaccin(EnumVaccin.from(nom_objet), nombre_stock);
				}

				nombre_total = Fenetre.liaison.ajouterStock(stock);
				Fenetre.panelGraph.addLog(stock);
				PanelStock.mapLabel.get(nom_objet).setText(String.valueOf(nombre_total));
			}
		}
	}
	
	public void cancel() {
		for (int i=0; i < fieldList.size(); i++) {
			fieldList.get(i).setText("");
		}
	}
	
}

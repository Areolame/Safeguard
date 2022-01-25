import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Stock.*;
import Stock.Enums.*;

import javax.swing.*;
import Stock.*;

public class PanelModifStock extends JPanel {
	
	private ArrayList<JFormattedTextField> fieldList = new ArrayList<JFormattedTextField>();
	private ArrayList<JComboBox<String>> comboList = new ArrayList<JComboBox<String>>();
	public static LinkedHashMap<String, Integer> mapValeur = null;
	
	// Boutons
	public JButton boutonAdd;
	public JButton boutonCancel;
	
	public PanelModifStock() {
		this.setSize(400,350);
		//cr�ation du layout
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
		
		// R�cup�ration de l'inventaire dans la BDD
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
				if (i == 0) Fenetre.liaison.ajouterGel(new StockGel(EnumGel.from(nom_objet), nombre_stock));
				if (i == 1) Fenetre.liaison.ajouterMasque(new StockMasque(EnumMasque.from(nom_objet), nombre_stock));
				if (i == 2) Fenetre.liaison.ajouterVaccin(new StockVaccin(EnumVaccin.from(nom_objet), nombre_stock));
				if (i == 3) Fenetre.liaison.ajouterTest(new StockTest(EnumTest.from(nom_objet), nombre_stock));
				Fenetre.panelStock.mapLabel.get(nom_objet).setText(String.valueOf(nombre_stock));
			}
		}
	}
	
	public void cancel() {
		
	}
	
}

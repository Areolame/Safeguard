import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
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

public class PanelModifStock extends JPanel {
	
	private ArrayList<JFormattedTextField> fieldList = new ArrayList<JFormattedTextField>();
	private ArrayList<JComboBox<String>> comboList = new ArrayList<JComboBox<String>>();
	
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
		HashMap<String, Integer> map = null;
		for (int i=0; i<4; i++) {
			String type = "";
			if (i==0) { 
				map = Fenetre.liaison.getStockGel();
				type = "Gel";
			}
			else if (i==1) {
				map = Fenetre.liaison.getStockMasque();
				type = "Masque";
			}
			else if (i==2) {
				map = Fenetre.liaison.getStockVaccin();
				type = "Vaccin";
			}
			else if (i==3) {
				map = Fenetre.liaison.getStockTest();
				type = "Test";
			}
			Vector<String> liste = new Vector<String>();
			for (String key : map.keySet()) {
			    liste.add(key);
			}
			JLabel tmpLabel1 = new JLabel(type, SwingConstants.CENTER);
		    JComboBox<String> listeType1 = new JComboBox<String>(liste);
		    JFormattedTextField tmpField1 = new JFormattedTextField();
		    panel1.add(tmpLabel1);
		    panel1.add(listeType1);
		    panel1.add(tmpField1);
		    fieldList.add(tmpField1);
		    comboList.add(listeType1);
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
				if (i == 0) Fenetre.liaison.ajouterGel(new StockGel(EnumGel.from((String)comboList.get(i).getSelectedItem()), Integer.parseInt(str1)));
				if (i == 1) Fenetre.liaison.ajouterMasque(new StockMasque(EnumMasque.from((String)comboList.get(i).getSelectedItem()), Integer.parseInt(str1)));
				if (i == 2) Fenetre.liaison.ajouterVaccin(new StockVaccin(EnumVaccin.from((String)comboList.get(i).getSelectedItem()), Integer.parseInt(str1)));
				if (i == 3) Fenetre.liaison.ajouterTest(new StockTest(EnumTest.from((String)comboList.get(i).getSelectedItem()), Integer.parseInt(str1)));
			}
		}
	}
	
	public void cancel() {
		
	}
	
}

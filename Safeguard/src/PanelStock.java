import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.*;
import Stock.*;

public class PanelStock extends JPanel {
	
	private static final long serialVersionUID = 4939220067030058978L;
	// Liste des valeurs de stocks
	public static ArrayList<JLabel> labelList = new ArrayList<JLabel>();
	public static LinkedHashMap<String, JLabel> mapLabel = new LinkedHashMap<String, JLabel>();
	public static int nombre_element_table_stock;
	
	public JPanel panel1;
	
	// Boutons
	public JButton boutonAdd;
	
	public PanelStock() {
		this.setSize(400,350);
		//création du layout
		GridLayout experimentLayout = new GridLayout(0,1);
		this.setLayout(experimentLayout);
        
        panel1 = new JPanel();
        GridLayout layoutPanel1 = new GridLayout(0,2);
        panel1.setLayout(layoutPanel1);
		//instantiation des  composants graphiques
        JLabel nomPage = new JLabel("Stock", SwingConstants.CENTER);
		boutonAdd = new JButton("Add");
		
		// Récupération de l'inventaire dans la BDD
		ArrayList<Stock> stock = Fenetre.liaison.getStocks();
		for (Stock s : stock) {
		    JLabel tmpLabel1 = new JLabel(s.getName(), SwingConstants.CENTER);
		    JLabel tmpLabel2 = new JLabel(String.valueOf(s.getNombreDeStock()), SwingConstants.CENTER);
		    labelList.add(tmpLabel2);
		    panel1.add(tmpLabel1);
		    panel1.add(tmpLabel2);
			mapLabel.put(s.getName(), tmpLabel2);
		}
		
		//ajout des composants sur le container
    	this.add(nomPage);
		this.add(panel1);
		JPanel panel2 = new JPanel();
		panel2.add(boutonAdd);
		panel2.setBorder(BorderFactory.createEmptyBorder(40,10,10,10));
		this.add(panel2);
		this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		
	}
	
	public void nouvelleLigne(String nom, int stock) {
		JLabel tmpLabel1 = new JLabel(nom, SwingConstants.CENTER);
	    JLabel tmpLabel2 = new JLabel(String.valueOf(stock), SwingConstants.CENTER);
	    labelList.add(tmpLabel2);
	    panel1.add(tmpLabel1);
	    panel1.add(tmpLabel2);
		mapLabel.put(nom, tmpLabel2);
		this.updateUI();
	}
	
	public void add() {
		Fenetre.dialogAjoutStock.setVisible(true);
	}
	
}

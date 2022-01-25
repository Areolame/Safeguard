import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.*;

public class PanelStock extends JPanel {
	
	private static final long serialVersionUID = 4939220067030058978L;
	// Liste des valeurs de stocks
	public static ArrayList<JLabel> labelList = new ArrayList<JLabel>();
	public static LinkedHashMap<String, JLabel> mapLabel = new LinkedHashMap<String, JLabel>();
	public static int nombre_element_table_stock;
	
	// Boutons
	public JButton boutonAdd;
	
	public PanelStock() {
		this.setSize(400,350);
		//création du layout
		GridLayout experimentLayout = new GridLayout(0,1);
		this.setLayout(experimentLayout);
        
        JPanel panel1 = new JPanel();
        GridLayout layoutPanel1 = new GridLayout(0,2);
        panel1.setLayout(layoutPanel1);
		//instantiation des  composants graphiques
        JLabel nomPage = new JLabel("Stock", SwingConstants.CENTER);
		boutonAdd = new JButton("Add");
		
		// Récupération de l'inventaire dans la BDD
		
		for (String key : PanelModifStock.mapValeur.keySet()) {
			String value = Integer.toString(PanelModifStock.mapValeur.get(key));
		    JLabel tmpLabel1 = new JLabel(key, SwingConstants.CENTER);
		    JLabel tmpLabel2 = new JLabel(value, SwingConstants.CENTER);
		    labelList.add(tmpLabel2);
		    panel1.add(tmpLabel1);
		    panel1.add(tmpLabel2);
			mapLabel.put(key, tmpLabel2);
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
	
	public void add() {
		Fenetre.dialogAjoutStock.setVisible(true);
	}
	
}

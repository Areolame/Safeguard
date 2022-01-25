import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelAjoutStock extends JPanel {

	private static final long serialVersionUID = -2753194042569740226L;
	// Boutons
	public JButton boutonAdd;
	public Vector<String> listeType = new Vector<String>();
	private JComboBox<String> combo_liste;
	private JFormattedTextField nom_champ;
	private JFormattedTextField stock_champ;
	public static int nombre_element_table_stock;
	
	public PanelAjoutStock() {
		
		this.setSize(400,350);
		//cr�ation du layout
		GridLayout experimentLayout = new GridLayout(0,1);
		this.setLayout(experimentLayout);
        
		// D�finition des types
		listeType.add("Gel");
		listeType.add("Mask");
		listeType.add("Test");
		listeType.add("Vaccine");
		listeType.add("Other");
		
		nombre_element_table_stock = Fenetre.liaison.tailleTableStock();
		
        JPanel panel1 = new JPanel();
        GridLayout layoutPanel1 = new GridLayout(0,3);
        layoutPanel1.setHgap(10);
        panel1.setLayout(layoutPanel1);
		//instantiation des  composants graphiques
        JLabel nomPage = new JLabel("Add New Items", SwingConstants.CENTER);
		boutonAdd = new JButton("Add");
		
		JLabel typeLabel = new JLabel("Item Type", SwingConstants.CENTER);
		JLabel nomLabel = new JLabel("Item Name", SwingConstants.CENTER);
		JLabel stockLabel = new JLabel("Stock", SwingConstants.CENTER);
		panel1.add(typeLabel);
		panel1.add(nomLabel);
		panel1.add(stockLabel);
		
		
		combo_liste = new JComboBox<String>(listeType);
		nom_champ = new JFormattedTextField();
		stock_champ = new JFormattedTextField();
		panel1.add(combo_liste);
		panel1.add(nom_champ);
		panel1.add(stock_champ);
		panel1.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
    	this.add(nomPage);
		this.add(panel1);
		JPanel panel2 = new JPanel();
		panel2.add(boutonAdd);
		panel2.setBorder(BorderFactory.createEmptyBorder(40,10,10,10));
		this.add(panel2);
		this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
	}
	
	public void add() {
		String nom = nom_champ.getText();
		String stock = stock_champ.getText();
		if (!nom.isBlank()&&!stock.isBlank()) {
			String type = (String)combo_liste.getSelectedItem();
			if (type.equals("Other")) {
				Boolean exist = Fenetre.liaison.checkExistStock(nom);
				if (exist) {
					Fenetre.messageErreur("This Item already exists.");
				}
				else {
					nombre_element_table_stock++;
					Fenetre.liaison.ajoutColonneStock(nombre_element_table_stock, nom, Integer.parseInt(stock));
					Fenetre.panelStock.nouvelleLigne(nom, Integer.parseInt(stock));
				}
			}
			else {
				Boolean exist = Fenetre.liaison.checkExist(type, nom);
				if (exist) {
					Fenetre.messageErreur("This Item already exists.");
				}
				else {
					Fenetre.liaison.ajoutLigneTable(type, nom, Integer.parseInt(stock));
				}
			}
			Fenetre.dialogAjoutStock.setVisible(false);
		}
	}
}

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
		//création du layout
		GridLayout experimentLayout = new GridLayout(0,1);
		this.setLayout(experimentLayout);
        
		// Définition des types
		listeType.add("Gel");
		listeType.add("Masque");
		listeType.add("Test");
		listeType.add("Vaccin");
		listeType.add("Autre");
		
		nombre_element_table_stock = Fenetre.liaison.tailleTableStock();
		
        JPanel panel1 = new JPanel();
        GridLayout layoutPanel1 = new GridLayout(0,3);
        layoutPanel1.setHgap(10);
        panel1.setLayout(layoutPanel1);
		//instantiation des  composants graphiques
        JLabel nomPage = new JLabel("Ajout Nouveau Stock", SwingConstants.CENTER);
		boutonAdd = new JButton("Add");
		
		JLabel typeLabel = new JLabel("Type Inventaire", SwingConstants.CENTER);
		JLabel nomLabel = new JLabel("Nom de l'objet", SwingConstants.CENTER);
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
			if (type.equals("Autre")) {
				Boolean exist = Fenetre.liaison.checkExistStock(nom);
				if (exist) {
					Fenetre.messageErreur("Ce champ existe déjà.");
				}
				else {
					nombre_element_table_stock++;
					Fenetre.liaison.ajoutColonneStock(nombre_element_table_stock, nom, Integer.parseInt(stock));
				}
			}
			else {
				Boolean exist = Fenetre.liaison.checkExist(type, nom);
				if (exist) {
					Fenetre.messageErreur("Ce champ existe déjà.");
				}
				else {
					Fenetre.liaison.ajoutLigneTable(type, nom, Integer.parseInt(stock));
				}
			}
			Fenetre.dialogAjoutStock.setVisible(false);
		}
	}
}

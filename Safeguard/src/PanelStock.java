import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.*;

public class PanelStock extends JPanel {
	
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
		HashMap<String, Integer> map = null;
		for (int i=0; i<3; i++) {
			if (i==0) map = Fenetre.liaison.getStockGel();
			else if (i==1) map = Fenetre.liaison.getStockMasque();
			else if (i==2) map = Fenetre.liaison.getStockVaccin();
			for (String key : map.keySet()) {
			    String value = Integer.toString(map.get(key));
			    JLabel tmpLabel1 = new JLabel(key, SwingConstants.CENTER);
			    JLabel tmpLabel2 = new JLabel(value, SwingConstants.CENTER);
			    panel1.add(tmpLabel1);
			    panel1.add(tmpLabel2);
			}
		}
		
		//ajout des composants sur le container
    	this.add(nomPage);
		this.add(panel1);
		this.add(boutonAdd);

		
		//ajouter une bordure vide de taille constante autour de l'ensemble des composants
		this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
	}
	
	public void add() {
		
	}
	
}

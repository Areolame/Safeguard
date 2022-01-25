import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.*;
import Stock.*;

public class PanelPersonne extends JPanel {
	
	private static final long serialVersionUID = -1841437181948227334L;
	public JPanel panel1;
	
	
	public PanelPersonne() {
		this.setSize(400,350);
		//création du layout
		GridLayout experimentLayout = new GridLayout(0,1);
		this.setLayout(experimentLayout);
		JLabel nomPage = new JLabel("Person Management", SwingConstants.CENTER);
        
        panel1 = new JPanel();
        GridLayout layoutPanel1 = new GridLayout(0,3);
        panel1.setLayout(layoutPanel1);

        JLabel nomLabel = new JLabel("Last Name", SwingConstants.CENTER);
	    JLabel prenomLabel = new JLabel("First Name", SwingConstants.CENTER);
	    JLabel dateLabel = new JLabel("Birthday", SwingConstants.CENTER);
	    panel1.add(nomLabel);
	    panel1.add(prenomLabel);
	    panel1.add(dateLabel);
		
		// Récupération de l'inventaire dans la BDD
		ArrayList<Personne> listPersonne = Fenetre.liaison.getPersonne();
		for (Personne p : listPersonne) {
		    JLabel tmpLabel1 = new JLabel(p.getNom(), SwingConstants.CENTER);
		    JLabel tmpLabel2 = new JLabel(p.getPrenom(), SwingConstants.CENTER);
		    JLabel tmpLabel3 = new JLabel(p.getDate(), SwingConstants.CENTER);
		    panel1.add(tmpLabel1);
		    panel1.add(tmpLabel2);
		    panel1.add(tmpLabel3);
		}
		
		//ajout des composants sur le container
    	this.add(nomPage);
		this.add(panel1);
	}
	
	public void nouvelleLigne(String nom, String prenom, String date) {
		JLabel tmpLabel1 = new JLabel(nom, SwingConstants.CENTER);
	    JLabel tmpLabel2 = new JLabel(prenom, SwingConstants.CENTER);
	    JLabel tmpLabel3 = new JLabel(date, SwingConstants.CENTER);
	    panel1.add(tmpLabel1);
	    panel1.add(tmpLabel2);
	    panel1.add(tmpLabel3);
		this.updateUI();
	}
	
}

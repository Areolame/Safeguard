import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelAjoutPersonne extends JPanel {
	
	// Label et champs de textes
	private JLabel labelPrenom;
	public JTextField textFieldPrenom;
	private JLabel labelNom;
	public JTextField textFieldNom;
	private JLabel labelDateNaissance;
	private JLabel labelDateJour;
	private JLabel labelDateMois;
	private JLabel labelDateAnnee;
	public JFormattedTextField textFieldDateJour;
	public JFormattedTextField textFieldDateMois;
	public JFormattedTextField textFieldDateAnnee;
	
	// Boutons
	public JButton boutonAddPersonne;	 	
	public JButton boutonCancelPersonne;
	
	public PanelAjoutPersonne() {
		//création du layout
		GridLayout experimentLayout = new GridLayout(0,1);
		this.setLayout(experimentLayout);
        
        JPanel panel1 = new JPanel();
        GridLayout layoutPanel1 = new GridLayout(0,2);
        panel1.setLayout(layoutPanel1);
		//instantiation des  composants graphiques
        labelPrenom=new JLabel("First Name");
    	textFieldPrenom=new JTextField();
    	labelNom=new JLabel("Last Name");
    	textFieldNom=new JTextField();
    	labelDateNaissance=new JLabel("Date of birth");
    	labelDateJour=new JLabel("Jour");
    	labelDateMois=new JLabel("Mois");
    	labelDateAnnee=new JLabel("Annee");
    	textFieldDateJour = new JFormattedTextField(new SimpleDateFormat("dd"));
    	textFieldDateMois = new JFormattedTextField(new SimpleDateFormat("MM"));
    	textFieldDateAnnee = new JFormattedTextField(new SimpleDateFormat("yyyy"));
    	boutonAddPersonne=new JButton("Add");	 	
    	boutonCancelPersonne=new JButton("Cancel");
		
		//ajout des composants sur le container
    	panel1.add(labelPrenom);
    	panel1.add(textFieldPrenom);

    	panel1.add(labelNom);
    	panel1.add(textFieldNom);
    	this.add(panel1);
		this.add(labelDateNaissance);
		JPanel panel2 = new JPanel();
        GridLayout layoutPanel2 = new GridLayout(0,3);
        panel2.setLayout(layoutPanel2);
        panel2.add(labelDateJour);
        panel2.add(labelDateMois);
        panel2.add(labelDateAnnee);
        panel2.add(textFieldDateJour);
        panel2.add(textFieldDateMois);
        panel2.add(textFieldDateAnnee);
        this.add(panel2);
	
        JPanel panel3 = new JPanel();
        GridLayout layoutPanel3 = new GridLayout(0,2);
        layoutPanel3.setHgap(10);
        panel3.setLayout(layoutPanel3);
        panel3.add(boutonAddPersonne);
		panel3.add(boutonCancelPersonne);
		panel3.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		this.add(panel3);
		
		//ajouter une bordure vide de taille constante autour de l'ensemble des composants
		this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
	}
	
	public void ajouterPersonne() {
		String jour = this.textFieldDateJour.getText();
		String mois = this.textFieldDateMois.getText();
		String annee = this.textFieldDateAnnee.getText();
		String prenom = this.textFieldPrenom.getText();
		String nom = this.textFieldNom.getText();
		if (jour.isBlank()||mois.isBlank()||annee.isBlank()||prenom.isBlank()||nom.isBlank()) {
            Fenetre.messageErreur.setText("L'un des champs est vide");
			Fenetre.erreurBox.setVisible(true); 
		}
		else {
			LocalDateTime dateNaiss = LocalDateTime.of(Integer.parseInt(annee),Integer.parseInt(mois),Integer.parseInt(jour), 0, 0);
			new Personne(this.textFieldPrenom.getText(), this.textFieldNom.getText(), dateNaiss);
		}
	}
	
	public void cancel() {
		this.textFieldDateJour.setText("");
		this.textFieldDateMois.setText("");
		this.textFieldDateAnnee.setText("");
		this.textFieldPrenom.setText("");
		this.textFieldNom.setText("");
	}
	
}

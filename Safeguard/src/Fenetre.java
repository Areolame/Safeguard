import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JFormattedTextField;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Fenetre extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L; 
	private JPanel containerPanel;		
	
	// Label et champs de textes
	private JLabel labelPrenom;
	private JTextField textFieldPrenom;
	private JLabel labelNom;
	private JTextField textFieldNom;
	private JLabel labelDateNaissance;
	private JFormattedTextField textFieldDate;
	
	// Boutons
	private JButton boutonAddPersonne;	 	
	private JButton boutonCancelPersonne;	
	
	// Instance de la classe DAO
	private LiaisonBDD liaison;

	public Fenetre() {
		// on instancie la classe Article DAO
		this.liaison = new LiaisonBDD();
		
		//on fixe le titre de la fen�tre
		this.setTitle("Covid-Statistics");
		//initialisation de la taille de la fen�tre
		this.setSize(400,350);
		
		//cr�ation du conteneur
		containerPanel = new JPanel();
		
		//choix du Layout pour ce conteneur
		//il permet de g�rer la position des �l�ments
		//il autorisera un retaillage de la fen�tre en conservant la pr�sentation
		//BoxLayout permet par exemple de positionner les �lements sur une colonne ( PAGE_AXIS )
		containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.PAGE_AXIS));
		
		//choix de la couleur pour le conteneur
        containerPanel.setBackground(Color.PINK);
        
        
		//instantiation des  composants graphiques
        labelPrenom=new JLabel("First Name");
    	textFieldPrenom=new JTextField();
    	labelNom=new JLabel("Last Name");
    	textFieldNom=new JTextField();
    	labelDateNaissance=new JLabel("Date of birth");
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    	textFieldDate = new JFormattedTextField(df);
    	boutonAddPersonne=new JButton("Add");	 	
    	boutonCancelPersonne=new JButton("Cancel");
		
		//ajout des composants sur le container 
		containerPanel.add(labelPrenom);
		containerPanel.add(Box.createRigidArea(new Dimension(0,5)));
		containerPanel.add(textFieldPrenom);
		containerPanel.add(Box.createRigidArea(new Dimension(0,10)));

		containerPanel.add(labelNom);
		containerPanel.add(Box.createRigidArea(new Dimension(0,5)));
		containerPanel.add(textFieldNom);
		containerPanel.add(Box.createRigidArea(new Dimension(0,10)));
		
		containerPanel.add(labelDateNaissance);
		containerPanel.add(Box.createRigidArea(new Dimension(0,5)));
		containerPanel.add(textFieldDate);
		containerPanel.add(Box.createRigidArea(new Dimension(0,10)));

	
		containerPanel.add(boutonAddPersonne);
		containerPanel.add(Box.createRigidArea(new Dimension(0,5)));
		containerPanel.add(Box.createRigidArea(new Dimension(0,20)));
		containerPanel.add(boutonCancelPersonne);
		
		//ajouter une bordure vide de taille constante autour de l'ensemble des composants
		containerPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		//ajout des �couteurs sur les boutons pour g�rer les �v�nements
		boutonAddPersonne.addActionListener(this);
		boutonCancelPersonne.addActionListener(this);
		
		//permet de quitter l'application si on ferme la fen�tre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setContentPane(containerPanel);

		//affichage de la fen�tre
		this.setVisible(true);
	}
	
	/**
	 * G�re les actions r�alis�es sur les boutons
	 *
	 */
	public void actionPerformed(ActionEvent ae) {
		int retour; // code de retour de la classe ArticleDAO
		
		try {
			if(ae.getSource()==boutonAddPersonne) {
				Calendar date = Calendar.getInstance();
				long millisecondsDate = date.getTimeInMillis();
				Personne p = new Personne(this.textFieldPrenom.getText(), this.textFieldNom.getText(), new Date(millisecondsDate));
				retour = liaison.ajouterPersonne(p);
				System.out.println("" + retour + " ligne ajout�e dans la BDD");
			}
			else if(ae.getSource()==boutonCancelPersonne) {
				// Action bouton Cancel
			}
		}
		catch (Exception e) {
			System.err.println("Veuillez contr�ler vos saisies");
		}
		
	}

	
	public static void main(String[] args) {
		new Fenetre();
    }

}

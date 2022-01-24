import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

public class Fenetre extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L; 
	
	// Instance de la classe liaison
	public static LiaisonBDD liaison;

	// Boite d'erreur
	public static JDialog erreurBox;
	public static JLabel messageErreur;
	
	// Diff�rents panel
	private PanelAjoutPersonne panelAjoutPersonne;
	private PanelStock panelStock;
	
	public Fenetre() {
		// on instancie les diff�rentes classes
		liaison = new LiaisonBDD();
		erreurBox = new JDialog(this,"Erreur");
		messageErreur = new JLabel("");
		erreurBox.add(messageErreur);
		erreurBox.setSize(250, 100);
		
		// Panel Personne
		this.panelAjoutPersonne = new PanelAjoutPersonne();
		this.panelAjoutPersonne.boutonAddPersonne.addActionListener(this);
		this.panelAjoutPersonne.boutonCancelPersonne.addActionListener(this);
		
		this.panelStock = new PanelStock();
		this.panelStock.boutonAdd.addActionListener(this);
		
		//on fixe le titre de la fen�tre
		this.setTitle("Covid-Statistics");
		//initialisation de la taille de la fen�tre
		this.setSize(400,700);
		
		//permet de quitter l'application si on ferme la fen�tre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelPrincipal = new JPanel();
		GridLayout layoutPrincipal = new GridLayout(0,2);
		panelPrincipal.setLayout(layoutPrincipal);
		
		// Ajout des panels dans le panel principal
		panelPrincipal.add(this.panelAjoutPersonne);
		panelPrincipal.add(this.panelStock);
		
		this.setContentPane(panelPrincipal);

		//affichage de la fen�tre
		this.setVisible(true);
	}
	
	// Callback des diff�rents boutons des panels
	public void actionPerformed(ActionEvent ae) {
		int retour; // code de retour si besoin
		try {
			if(ae.getSource()==this.panelAjoutPersonne.boutonAddPersonne) {
				this.panelAjoutPersonne.ajouterPersonne();
			}
			else if(ae.getSource()==this.panelAjoutPersonne.boutonCancelPersonne) {
				this.panelAjoutPersonne.cancel();
			}
			else if (ae.getSource()==this.panelStock.boutonAdd) {
				this.panelStock.add();
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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Fenetre extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L; 
	
	// Instance de la classe liaison
	public static LiaisonBDD liaison;

	// Boite d'erreur
	public static JDialog erreurBox;
	public static JLabel messageErreur;
	
	// Différents panel
	public static JPanel panelPrincipal;
	public static PanelAjoutPersonne panelAjoutPersonne;
	public static PanelStock panelStock;
	public static PanelModifStock panelModifStock;
	public static PanelGraph panelGraph;
	public static PanelUHA panelUHA;
	public static PanelAjoutStock panelAjoutStock;
	public static PanelPersonne panelPersonne;
	
	public static JDialog dialogAjoutStock;
	
	public Fenetre() {
		// on instancie les différentes classes
		liaison = new LiaisonBDD();
		erreurBox = new JDialog(this,"Error");
		messageErreur = new JLabel("");
		erreurBox.add(messageErreur);
		erreurBox.setSize(250, 100);
		dialogAjoutStock = new JDialog(this);
		dialogAjoutStock.setSize(400,350);
		
		// Panel Personne
		panelAjoutPersonne = new PanelAjoutPersonne();
		panelAjoutPersonne.boutonAddPersonne.addActionListener(this);
		panelAjoutPersonne.boutonCancelPersonne.addActionListener(this);
		
		panelModifStock = new PanelModifStock();
		panelModifStock.boutonAdd.addActionListener(this);
		panelModifStock.boutonCancel.addActionListener(this);
		
		panelStock = new PanelStock();
		panelStock.boutonAdd.addActionListener(this);
		
		panelGraph = new PanelGraph();
		
		panelUHA = new PanelUHA();
		
		panelAjoutStock = new PanelAjoutStock();
		panelAjoutStock.boutonAdd.addActionListener(this);
		dialogAjoutStock.add(panelAjoutStock);
		
		panelPersonne = new PanelPersonne();

		//on fixe le titre de la fenêtre
		this.setTitle("Covid-Statistics");
		int sizeX = 400;
		int sizeY = 350;
		int nbPanel = 3;
		//initialisation de la taille de la fenêtre
		this.setSize(sizeX*nbPanel,sizeY*2);
		
		//permet de quitter l'application si on ferme la fenêtre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelPrincipal = new JPanel();
		GridLayout layoutPrincipal = new GridLayout(0,nbPanel);
		panelPrincipal.setLayout(layoutPrincipal);
		
		// Ajout des panels dans le panel principal
		panelPrincipal.add(panelAjoutPersonne);
		panelPrincipal.add(panelStock);
		panelPrincipal.add(panelModifStock);
		panelPrincipal.add(panelGraph);
		panelPrincipal.add(panelUHA);
		panelPrincipal.add(panelPersonne);
		
		this.setContentPane(panelPrincipal);

		//affichage de la fenêtre
		this.setVisible(true);
	}
	
	// Callback des différents boutons des panels
	public void actionPerformed(ActionEvent ae) {
		//int retour; // code de retour si besoin
		try {
			if(ae.getSource()==panelAjoutPersonne.boutonAddPersonne) {
				panelAjoutPersonne.ajouterPersonne();
			}
			else if(ae.getSource()==panelAjoutPersonne.boutonCancelPersonne) {
				panelAjoutPersonne.cancel();
			}
			else if (ae.getSource()==panelStock.boutonAdd) {
				panelStock.add();
			}
			else if (ae.getSource()==panelModifStock.boutonAdd) {
				panelModifStock.add();
			}
			else if (ae.getSource()==panelModifStock.boutonCancel) {
				panelModifStock.cancel();
			}
			else if (ae.getSource()==panelAjoutStock.boutonAdd) {
				panelAjoutStock.add();
			}
		}
		catch (Exception e) {
			System.out.println(e);
			messageErreur.setText("Error: " + e);
			erreurBox.setVisible(true);
		}
		
	}
	
	public static void messageErreur(String str) {
		messageErreur.setText("Error: " + str);
		erreurBox.setVisible(true);
	}

	
	public static void main(String[] args) {
		new Fenetre();
    }

}

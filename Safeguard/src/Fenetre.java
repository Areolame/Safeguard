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
	
	// Diff�rents panel
	public static JPanel panelPrincipal;
	public static PanelAjoutPersonne panelAjoutPersonne;
	public static PanelStock panelStock;
	public static PanelModifStock panelModifStock;
	public static PanelGraph panelGraph;
	public static PanelUHA panelUHA;
	public static PanelAjoutStock panelAjoutStock;
	
	public static JDialog dialogAjoutStock;
	
	public Fenetre() {
		// on instancie les diff�rentes classes
		liaison = new LiaisonBDD();
		erreurBox = new JDialog(this,"Erreur");
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

		//on fixe le titre de la fen�tre
		this.setTitle("Covid-Statistics");
		int sizeX = 400;
		int sizeY = 350;
		int nbPanel = 3;
		//initialisation de la taille de la fen�tre
		this.setSize(sizeX*nbPanel,sizeY*2);
		
		//permet de quitter l'application si on ferme la fen�tre
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
		
		this.setContentPane(panelPrincipal);

		//affichage de la fen�tre
		this.setVisible(true);
	}
	
	// Callback des diff�rents boutons des panels
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
			messageErreur.setText("Erreur: " + e);
			erreurBox.setVisible(true);
		}
		
	}

	
	public static void main(String[] args) {
		new Fenetre();
    }

}

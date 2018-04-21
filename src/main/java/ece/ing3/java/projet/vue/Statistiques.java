package ece.ing3.java.projet.vue;

import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.modele.administration.Service;
import ece.ing3.java.projet.modele.employe.Docteur;
import ece.ing3.java.projet.modele.employe.Employe;
import ece.ing3.java.projet.modele.employe.Infirmier;
import ece.ing3.java.projet.modele.hopital.Chambre;
import ece.ing3.java.projet.modele.hopital.Hospitalisation;
import ece.ing3.java.projet.modele.hopital.Malade;
import ece.ing3.java.projet.modele.hopital.Soigne;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

/**
 * Classe d'affichage de la fenetre de statistiques.
 * 
 * @author Nicolas
 *
 */
public class Statistiques extends JFrame {
	private static final long serialVersionUID = 1L;
	private int numfen = 0; //numéro de la fenêtre

	/**
	 * Constructeur par défaut de la classe Donne les principales caractéristiques
	 * de la fenêtre
	 */
	public Statistiques(int numfen) {
		this.numfen = numfen;
		if (this.numfen == 1) {
			this.setTitle("Statistiques de l'Hôpital (1/2)");
		}
		if (this.numfen == 2) {
			this.setTitle("Statistiques de l'Hôpital (2/2)");
		}

		this.setSize((int) getToolkit().getScreenSize().getWidth(), ((int) getToolkit().getScreenSize().getHeight()));
		this.setResizable(true);
		this.setVisible(true);
		this.toFront(); // place la fenêtre devant les autres.
		this.creer_Statistiques();

	}

	/**
	 * Méthode qui créée des objets de JFreeChart et les ajoute dans 2 fenêtres
	 */
	public void creer_Statistiques() {
		
		// On crée un liste d'employe
		List<Employe> malisteemploye = new ArrayList<>();
		try {
			malisteemploye = Employe.findBaseList(); // récuperation des données de la bdd concernant les employes
		} catch (DatabaseException e1) {
			e1.printStackTrace();
		}

		// On crée un liste de malade
		List<Malade> malistemalade = new ArrayList<>();
		try {
			malistemalade = Malade.findList(); // récuperation des données de la bdd concernant les malade
		} catch (DatabaseException e1) {
			e1.printStackTrace();
		}

		List<Service> malisteservice = new ArrayList<>();
		try {
			malisteservice = Service.findList(); // récuperation des données de la bdd concernant les service
		} catch (DatabaseException e1) {
			e1.printStackTrace();
		}

		List<Hospitalisation> malistehospitalisation = new ArrayList<>();
		try {
			malistehospitalisation = Hospitalisation.findList(); // récuperation des données de la bdd concernant les
																	// hospitalisations
		} catch (DatabaseException e1) {
			e1.printStackTrace();
		}

		List<Chambre> malistechambre = new ArrayList<>();
		try {
			malistechambre = Chambre.findList(); // récuperation des données de la bdd concernant les chambres
		} catch (DatabaseException e1) {
			e1.printStackTrace();
		}

		List<Docteur> malistedocteur = new ArrayList<>();
		try {
			malistedocteur = Docteur.findList(); // récuperation des données de la bdd concernant les docteurs
		} catch (DatabaseException e1) {
			e1.printStackTrace();
		}

		List<Infirmier> malisteinfirmier = new ArrayList<>();
		try {
			malisteinfirmier = Infirmier.findList(); // récuperation des données de la bdd concernant les infirmiers
		} catch (DatabaseException e1) {
			e1.printStackTrace();
		}
		List<Soigne> malistesoigne = new ArrayList<>();
		try {
			malistesoigne = Soigne.findList(); // récuperation des données de la bdd concernant les prises en charge (soigne)
		} catch (DatabaseException e1) {
			e1.printStackTrace();
		}
		

		if (this.numfen == 1) {
			GridLayout mygridLayout = new GridLayout(3, 2);
			this.setLayout(mygridLayout);
			mygridLayout.setHgap(10);
			mygridLayout.setVgap(10);

			PieChart3DMutuelles A = new PieChart3DMutuelles( malistemalade );
			this.getContentPane().add(A.getPieChart3D());
			
			PieChart2DNbreLits B = new PieChart2DNbreLits( malistechambre );
			this.getContentPane().add(B.getPieChart2D());
			
			PieChart2DSpecialitesDoc C = new PieChart2DSpecialitesDoc (malistedocteur);
			this.getContentPane().add(C.getPieChart2D());
			
			PieChart2DRotations D = new PieChart2DRotations(malisteinfirmier);
			this.getContentPane().add(D.getPieChart2D());

			PieChart2DPatients E = new PieChart2DPatients(malistehospitalisation);
			this.getContentPane().add(E.getPieChart2D());
			
			PieChart2DMalades F = new PieChart2DMalades();
			this.getContentPane().add(F.getPieChart2D());
	
			//this.getContentPane().add((new PieChart2DModel(6, null, null, null, null, null, null)).getPieChart2D());

			this.pack();
		}
		

		if (this.numfen == 2) {
			
			GridLayout mygridLayout = new GridLayout(2, 2);
			this.setLayout(mygridLayout);
			mygridLayout.setHgap(10);
			mygridLayout.setVgap(10);
			
			PieChart2DInfirmier G = new PieChart2DInfirmier(malisteinfirmier);
			this.getContentPane().add(G.getPieChart2D());
			
			BarChartSalaires H = new BarChartSalaires( malisteinfirmier );
			this.getContentPane().add(H.getBarChart());
			
			this.pack();

		}

	}
}
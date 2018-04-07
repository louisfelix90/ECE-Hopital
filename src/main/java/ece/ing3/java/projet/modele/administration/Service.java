package ece.ing3.java.projet.modele.administration;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.employe.Docteur;
import ece.ing3.java.projet.modele.employe.Infirmier;
import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.modele.hopital.Chambre;

import java.util.List;

/**
 * Modèle de Service stocké en base de donnée
 */
public class Service extends Model {
	private String code;
	private String nom;
	private String batiment;

	/**
	 * Créer un nouveau service
	 *
	 * @param code      Code du service
	 * @param nom       Nom du service
	 * @param batiment  Bâtiment où le service se trouve
	 * @param directeur Directeur du service
	 */
	public Service( String code, String nom, String batiment, Docteur directeur ) {
		this.code = code;
		this.nom = nom;
		this.batiment = batiment;
		setDirecteur( directeur );
	}

	/**
	 * Récupère le code du service
	 *
	 * @return Code du service
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Récupère le nom du service
	 *
	 * @return Nom du service
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Modifie le nom du service
	 *
	 * @param nom Nouveau nom du service
	 */
	public void setNom( String nom ) {
		this.nom = nom;
	}

	/**
	 * Récupère le bâtiment où se trouve le service
	 *
	 * @return Bâtiment où se trouve le service
	 */
	public String getBatiment() {
		return batiment;
	}

	/**
	 * Modifie le bâtiment où se trouve le service
	 *
	 * @param batiment Nouveau bâtiment
	 */
	public void setBatiment( String batiment ) {
		this.batiment = batiment;
	}

	/**
	 * Récupère les infirmiers rattachés au service
	 *
	 * @return Liste d'infirmiers rattachés au service
	 */
	public List<Infirmier> getInfirmiersRattaches() {
		// TODO : Récupération des infirmiers rattachés au service
		return null;
	}

	/**
	 * Récupère le directeur du service
	 *
	 * @return Directeur du service
	 */
	public Docteur getDirecteur() {
		// TODO : Récupération du directeur de service
		return null;
	}

	/**
	 * Modifie le directeur du service
	 *
	 * @param directeur Nouveau directeur du service
	 */
	public void setDirecteur( Docteur directeur ) {
		// TODO : Modification du directeur de service
	}

	/**
	 * Récupère les chambres rattachées au service
	 *
	 * @return Liste des chambres rattachées au service
	 */
	public List<Chambre> getChambresRattachees() {
		// TODO : Récupération des chambres rattachées au service
		return null;
	}

	/**
	 * Recherche un service de code spécifique
	 *
	 * @param code Code du service à rechercher
	 * @return Service de code donnée ou {@code null}
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public static Service find( String code ) throws DatabaseException {
		return ( new ServiceFinder() ).code( code ).findUnique();
	}

	/**
	 * Récupère l'ensemble des services
	 *
	 * @return Liste des services
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public static List<Service> findList() throws DatabaseException {
		return ( new ServiceFinder() ).findList();
	}

	/**
	 * Génère une représentation textuelle du service
	 *
	 * @return Représentation textuelle du service
	 */
	@Override
	public String toString() {
		return "Service{" +
				"code=" + getCode() +
				", nom='" + getNom() + '\'' +
				", batiment='" + getBatiment() + '\'' +
				", directeur=" + getDirecteur() +
				'}';
	}
}

/*
 * Cette classe sert de "catalyseur" pour les données à
 * insérer ou à modifier
 */

public class Personne {

	// Atributs
	private String nom;
	private String prenom;
	private String pays;
	private String ID;

	// Constructeur
	public Personne(String nom, String prenom, String pays) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.pays = pays;
	}

	// Getters & Setters
	public String getID() {
		return ID;
	}

	public void setID(String string) {
		ID = string;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	// Méthode toString
	@Override
	public String toString() {
		return "Personne [nom=" + nom + ", prenom=" + prenom + ", pays=" + pays + "]";
	}

}

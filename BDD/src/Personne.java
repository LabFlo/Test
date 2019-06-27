
public class Personne {
	
	private String nom;
	private String prenom;
	private String pays;
	private String ID;
	
	public Personne(String nom, String prenom, String pays) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.pays = pays;
	}
	
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

	@Override
	public String toString() {
		return "Personne [nom=" + nom + ", prenom=" + prenom + ", pays=" + pays + "]";
	}
	
}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/*
 * Cette classe ins�re une nouvelle ligne 
 * dans la base de donn�es � partir d'un Objet
 * Personne
 */

public class Insert {

	public void setData(Personne personne) {

		// R�cup�ration des attributs de l'objet Personne
		String nom = personne.getNom();
		String prenom = personne.getPrenom();
		String pays = personne.getPays();

		// La requ�te SQL
		String sql = "INSERT INTO `java`.`personnes` (`Nom`, `Prenom`, `Pays`) " 
				+ "VALUES ('" + nom + "', '" + prenom
				+ "', '" + pays + "');";

		// Les acc�s � la base de donn�es
		String url = Acces.getUrl();
		String user = Acces.getUser();
		String pass = Acces.getPass();

		try {

			// Ex�cution de la requ�te
			Connection con = DriverManager.getConnection(url, user, pass);

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.executeUpdate(sql);

		} catch (Exception ex) {

			ex.printStackTrace();

		}

		return;

	}

}

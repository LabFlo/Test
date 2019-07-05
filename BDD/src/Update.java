import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/*
 * Cette classe modifie une ligne
 * dans la base de données à partir d'un Objet
 * Personne
 */

public class Update {

	public void updateData(Personne personne) {

		// Récupération des attributs de l'objet Personne ET de l'ID
		String nom = personne.getNom();
		String prenom = personne.getPrenom();
		String pays = personne.getPays();
		String id = personne.getID();

		// La requête SQL
		String sql = "UPDATE `java`.`personnes` SET `Nom` = '" + nom + "', `Prenom` = '" + prenom + "' , `Pays` = '"
				+ pays + "' WHERE (`ID` = '" + id + "');";

		// Les accès à la base de données
		String url = Acces.getUrl();
		String user = Acces.getUser();
		String pass = Acces.getPass();

		try {

			// Exécution de la requête
			Connection con = DriverManager.getConnection(url, user, pass);

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.executeUpdate(sql);

		} catch (Exception ex) {

			ex.printStackTrace();

		}

		return;

	}

}
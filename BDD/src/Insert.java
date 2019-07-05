import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/*
 * Cette classe insère une nouvelle ligne 
 * dans la base de données à partir d'un Objet
 * Personne
 */

public class Insert {

	public void setData(Personne personne) {

		// Récupération des attributs de l'objet Personne
		String nom = personne.getNom();
		String prenom = personne.getPrenom();
		String pays = personne.getPays();

		// La requête SQL
		String sql = "INSERT INTO `java`.`personnes` (`Nom`, `Prenom`, `Pays`) " + "VALUES ('" + nom + "', '" + prenom
				+ "', '" + pays + "');";

		// Les accès à la base de données
		String url = "jdbc:mysql://localhost:3306/analyses?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
		String user = "root";
		String pass = "root";

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

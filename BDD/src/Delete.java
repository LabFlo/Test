import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/*
 * Cette classe supprime une ligne du tableau
 */

public class Delete {

	public void deleteData(String id) {

		// La requête SQL
		String sql = "DELETE FROM `java`.`personnes` WHERE (`ID` = '" + id + "');";

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

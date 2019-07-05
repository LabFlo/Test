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

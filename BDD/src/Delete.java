import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/*
 * Cette classe supprime une ligne du tableau
 */

public class Delete {

	public void deleteData(String id) {

		// La requ�te SQL
		String sql = "DELETE FROM `java`.`personnes` WHERE (`ID` = '" + id + "');";

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

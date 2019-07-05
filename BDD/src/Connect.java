import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/*
 * Cette classe crée un nouveau "style" de table, 
 * à la remplir avec les infos de la base de données
 * et à insérer le tout dans le tableau de la classe BDD
 */

public class Connect {

	public DefaultTableModel getData() {

		// Création de la table et des colonnes
		DefaultTableModel dm = new DefaultTableModel();

		dm.addColumn("Id");

		dm.addColumn("Nom");

		dm.addColumn("Prénom");

		dm.addColumn("Pays");

		// La requête SQL
		String sql = "SELECT * FROM java.personnes;";

		// Les accès à la base de données
		String url = Acces.getUrl();
		String user = Acces.getUser();
		String pass = Acces.getPass();

		try {

			// Exécution de la requête
			Connection con = DriverManager.getConnection(url, user, pass);

			PreparedStatement stmt = con.prepareStatement(sql);

			ResultSet rst = stmt.executeQuery(sql);

			System.out.println(rst);

			// création des lignes en fonction de la quantité de données
			while (rst.next()) {

				String id = rst.getString(1);

				String nom = rst.getString(2);

				String prenom = rst.getString(3);

				String ville = rst.getString(4);

				Object[] rowData = { id, nom, prenom, ville };

				// On ajoute la ligne
				dm.addRow(rowData);

				System.out.println(rowData);

			}

			// On retourne le nouveau style rempli
			return dm;

		} catch (Exception ex) {

			ex.printStackTrace();

		}

		return null;

	}

}

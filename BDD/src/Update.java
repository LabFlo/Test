import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Update {

	public void updateData(Personne personne) {

	       String nom = personne.getNom();
	       String prenom = personne.getPrenom();
	       String pays = personne.getPays(); 
	       String id = personne.getID();
	       
		String sql="UPDATE `java`.`personnes` SET `Nom` = '"+nom+"', `Prenom` = '"+prenom+"' , `Pays` = '"+pays+"' WHERE (`ID` = '"+id+"');";   
	       
	       String url = "jdbc:mysql://localhost:3306/analyses?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
	       String user = "root";
	       String pass = "root";

	       
	       try {

	            Connection con = DriverManager.getConnection(url, user, pass);

	            PreparedStatement stmt = con.prepareStatement(sql);            

				stmt.executeUpdate(sql);

	            
	       } catch (Exception ex) {

	            ex.printStackTrace();

	        }

	        return;
		
	}
	
}
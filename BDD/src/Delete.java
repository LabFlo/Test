import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Delete {

	public void deleteData(String id) {
	       
	       String sql="DELETE FROM `java`.`personnes` WHERE (`ID` = '"+id+"');";   
	       
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

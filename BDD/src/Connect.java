import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class Connect {

	public DefaultTableModel getData() {

	       DefaultTableModel dm=new DefaultTableModel();

	       dm.addColumn("Id");

	       dm.addColumn("Nom");

	       dm.addColumn("Prénom");

	       dm.addColumn("Pays");
	       
	       String sql="SELECT * FROM java.personnes;";   
	       
	       String url = "jdbc:mysql://localhost:3306/analyses?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
	       String user = "root";
	       String pass = "root";
	       
	       try {

	            Connection con = DriverManager.getConnection(url, user, pass);

	            PreparedStatement stmt = con.prepareStatement(sql);            
	            
	            ResultSet rst = stmt.executeQuery(sql);

	            System.out.println(rst);            
	            
	            while (rst.next()) {

	                String id = rst.getString(1);

	                String nom = rst.getString(2);

	                String prenom = rst.getString(3);

	                String ville = rst.getString(4);              
	                
	                Object[] rowData = { id, nom, prenom, ville };

	                dm.addRow(rowData);

	                System.out.println(rowData);
	                
	            }          
	            
	            return dm;        
	            
	       } catch (Exception ex) {

	            ex.printStackTrace();

	        }

	        return null;

	    }
	
}

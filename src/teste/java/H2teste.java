import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;


public class H2teste {

	@Test
	public void testDatabaseNoMem() throws SQLException {
		try{
	     DriverManager.registerDriver(new org.h2.Driver());
	        Connection c = DriverManager.getConnection("jdbc:h2:mem:testdb","sa","");
	        PreparedStatement stmt = c.prepareStatement("CREATE TABLE PERSON (ID INT PRIMARY KEY, FIRSTNAME VARCHAR(64), LASTNAME VARCHAR(64))");
	        stmt.execute();
	        System.out.println("criou");
	        
	        PreparedStatement stmt2 = c.prepareStatement("INSERT INTO PERSON (ID, FIRSTNAME, LASTNAME) VALUES (1, 'John', 'Doe')");
	        stmt2.execute();
	        
	        PreparedStatement stmt3 = c.prepareStatement("SELECT * FROM PERSON");
	        ResultSet r= stmt3.executeQuery();
	        
	        if(r.next()) {
		    	System.out.println(r.getString(1));
		    	System.out.println(r.getString(2));
		        System.out.println("data?");
		    }
	        
	        stmt.close();
	        stmt2.close();
	        c.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}

	public void testDatabaseMem() throws SQLException {
		try{
		 DriverManager.registerDriver(new org.h2.Driver());
	        Connection c = DriverManager.getConnection("jdbc:h2:http://10.220.44.47:8082:testdb","sa","");
	        PreparedStatement stmt = c.prepareStatement("INSERT INTO PERSON (ID, FIRSTNAME, LASTNAME) VALUES (1, 'John', 'Doe')");
	        stmt.execute();
	        System.out.println("insert");
	        stmt.close();
	        c.close();
	}catch(Exception e){
		System.out.println(e);
	}
	}

	private void testDatabase(String url) throws SQLException {
	    Connection connection= DriverManager.getConnection(url,"sa","");
	    
	    Statement s=connection.createStatement();
	    try {
	    s.execute("DROP TABLE PERSON");
	    } catch(SQLException sqle) {
	        System.out.println("Table not found, not dropping");
	    }
	 //   s.execute("CREATE TABLE PERSON (ID INT PRIMARY KEY, FIRSTNAME VARCHAR(64), LASTNAME VARCHAR(64))");
	    PreparedStatement ps=connection.prepareStatement("select * from test");
	    ResultSet r=ps.executeQuery();
	    if(r.next()) {
	    	System.out.println(r.getString(1));
	        System.out.println("data?");
	    }
	    r.close();
	    ps.close();
	    s.close();
	    connection.close();
	}
}

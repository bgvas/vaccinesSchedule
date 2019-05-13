package dataBaseWorks;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DataBaseCreation {
	
	private static String connectionElements = "jdbc:h2:file:./vaccinationDB;DB_CLOSE_DELAY=-1";
	private static String user = "bgvas";
	private static String pas = "1111"; 
	private static Connection conn;
		
	public static boolean dataBaseCreation() throws SQLException  {
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection(connectionElements, user, pas);
			Statement st = conn.createStatement();
			st.execute("create table if not exists auth (user varchar (20), pass varchar(20))");
			st.execute("create table if not exists pattients (amka varchar (20), phone varchar(20), age int(10), gender varchar(10))");
			st.execute("create table if not exists diseasesList (disease varchar (200))");
			st.execute("create table if not exists vaccinesList (disease varchar (200), vaccine varchar(200), frequency int(10), age int(10))");
			st.execute("create table if not exists vaccinationsDone(amka varchar (20), vaccine varchar(200), date varchar(20))");
			st.execute("create table if not exists vaccinationsToDo (amka varchar (20), vaccine varchar(200), after int(10))");
			st.execute("create table if not exists disOfPattient (amka varchar (20), disease varchar(200))");
			conn.close();
			return true;
			
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Πρόβλημα με την βάση δεδομένων","",JOptionPane.ERROR_MESSAGE);
			conn.close();
			return false;
		} 
	}
		
	
	public static String dbElements() {
		return connectionElements;
	}
	
	public static String dbUser() {
		return user;
	}
	
	public static String dbPas() {
		return pas;
	}
	

}

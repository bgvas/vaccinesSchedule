package dataBaseWorks;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SaveToDB {
	
	private static String conElements = DataBaseCreation.dbElements();
	private static String user =  DataBaseCreation.dbUser();
	private static String pas = DataBaseCreation.dbPas();
	private static Connection conn;
	
	public static boolean saveRecordToDB(String sql) {
		
		try {
			conn = DriverManager.getConnection(conElements, user, pas);
			Statement st = conn.createStatement();
			String s = sql; //String with sql code
			int i = st.executeUpdate(s);
			conn.close();
			return true;
		}	
		catch (SQLException e) {
			System.out.println("DataBase Problem!!!");
			try {
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return false;
		}
		
	}	
	

}

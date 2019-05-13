package loginWorks;
import java.math.BigInteger;

import dataBaseWorks.RetrieveFromDB;

public class LoginService {
	
	RetrieveFromDB db = new RetrieveFromDB(); 
	
	public String toHex(String word) throws Exception {
		return String.format("%x", new BigInteger(1, word.getBytes("UTF-8"))).toString();
	}
	
	// check if user and pass are correct
	public boolean checkLogin(String user, String pass) {
		boolean loginPassed = false;
		String sql = "select * from auth";
		String usAndPs = db.checkUser(sql);
		int index = usAndPs.indexOf(" ");
		String dbUser = usAndPs.substring(0, index);
		String dbPass = (usAndPs.substring(index + 1)).trim();
		if(dbUser.equals(user) && dbPass.equals(pass)) {
			return true; 
		}
		else return false;
	}

}

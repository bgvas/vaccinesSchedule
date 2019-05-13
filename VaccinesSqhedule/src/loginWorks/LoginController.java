package loginWorks;

public class LoginController {
	
	LoginService service = new LoginService();
	
	// check user auth
	public boolean checkUserAuth(String user, String pass) {
		return service.checkLogin(user, pass);
	}
	
	public String toHex(String word) throws Exception {
		return service.toHex(word);
		
	}
	
	

}

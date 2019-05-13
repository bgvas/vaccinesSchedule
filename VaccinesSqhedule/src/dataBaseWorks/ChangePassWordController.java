package dataBaseWorks;

public class ChangePassWordController {
	
	ChangePassWordService service = new ChangePassWordService();
	
	public boolean checkIfUserExist(String user) {
		return service.checkIfUserExist(user);
	}
	
	public boolean changePassWord(String user, String pass) {
		return service.changePassWord(user, pass);
	}

}

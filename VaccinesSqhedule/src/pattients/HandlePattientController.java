package pattients;

public class HandlePattientController {
	
	HandlePattientService service = new HandlePattientService();
	
	public boolean changePattientProfile(String amka, String phone, int age, String gender, String disease) {
		return service.changePattientProfile(amka, phone, age, gender, disease);
	}
	
	public boolean checkIfAmkaExist(String amka) {
		return service.checkIfAmkaExist(amka);
	}
	
	public boolean deletePattient(String amka) {
		return service.deletePattient(amka);
	}
	
	

}

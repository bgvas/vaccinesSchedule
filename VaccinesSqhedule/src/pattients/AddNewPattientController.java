package pattients;

import java.util.List;

public class AddNewPattientController {

	AddNewPattientService service = new AddNewPattientService();
	
	
	public boolean createNewPattient(String amka, String phone, String age, String gender, String disease) {
		return service.addNewPattient(amka, phone, age, gender, disease);
	}
	
	public boolean checkIfAmkaExist(String amka) {
		return service.checkIfAmkaExist(amka);
	}

	public List<String> getVaccinesPerDisease(String disease) {
		return service.getVaccinesPerDisease(disease);
	}

	public boolean addToDoVaccines(String amka, String disease, String pattientsAge) {
		return service.addTodoVaccines(amka, disease, pattientsAge);
	}
	
	
}

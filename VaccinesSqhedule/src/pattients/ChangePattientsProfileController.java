package pattients;

import java.util.List;



public class ChangePattientsProfileController {
	
	ChangePattientProfileService service = new ChangePattientProfileService();
	
	
	public boolean checkIfAmkaExist(String amka) {
		return service.checkIfAmkaExist(amka);
	}
	
	public List<Pattient> getAllPatientsFromDB(String amka) {
		return service.getAllPattietnsFromDB(amka);
	}
	
	public String getNowDisease(String amka) {
		return service.getNowDisease(amka);
	}

	public boolean updatePattientsElements(String amka, String phone, String age, String selDis, String nowDis) {
		return service.updatePattientsElements(amka, phone, age, selDis, nowDis);
	}

	public List<String> allDiseasesList() {
		return service.getAllDiseasesList();
	}

	public boolean deletePattient(String amka) {
		return service.deletePattient(amka);
	}

}

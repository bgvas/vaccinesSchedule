package pattients;

import java.awt.Component;
import java.util.List;

public class VaccinationCalendarPerPattientContoller {
	
	VaccinationCalendarPerPattientService service = new VaccinationCalendarPerPattientService();
	
	
	public boolean checkIfAmkaExist(String amka) {
		return service.checkIfAmkaExist(amka);
	}
	
	public String getNowDisease(String amka) {
		return service.getNowDisease(amka);
	}
	
	public List<String> ListOfVaccinesDone(String amka){
		return service.ListOfVaccinesDone(amka);
	}
	
	public Pattient getPattient(String amka) {
		return service.getPattient(amka);
	}

	public List<String> listOfToDoVaccines(String amka) {
		return service.listOfToDoVaccines(amka);
	}
	


}

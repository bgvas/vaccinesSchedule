package diseasesAndVaccines;

import java.util.List;

public class VaccinesToDoPerPattientController {

	VaccinesToDoPerPattientService service = new VaccinesToDoPerPattientService();
	
	public boolean VaccinesToDoPerPattient(String amka, String disease){
		return service.vaccinesToDoPerPattientToDB(amka, disease);
	}
	
	public List<String> getToDoVaccinesList(String amka){
		return service.getToDoVaccinesList(amka);
	}
	
	
}

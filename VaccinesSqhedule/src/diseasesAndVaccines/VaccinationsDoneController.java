package diseasesAndVaccines;

import java.util.List;

public class VaccinationsDoneController {
	
	VaccinationsDoneService service = new VaccinationsDoneService();
	
	public boolean vaccinesDone(String amka, String vaccine) {
		return service.vaccinesDone(amka, vaccine);
	}

	public List<String> vaccinesToDo(String amka) {
		return service.vaccinesToDo(amka);
	}

}

package pattients;

import java.util.List;

public class PattientsVaccinationCalendarController {
	
	PattientsVaccinationCalendarService service = new PattientsVaccinationCalendarService();
	
	public List<String> getVaccinesDone(String amka) {
		return service.getVaccinesDone(amka);
	}

}

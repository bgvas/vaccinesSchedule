package dataBaseWorks;

public class ClearUncompletedVaccinesController {

	ClearUncompletedVaccinesService service = new ClearUncompletedVaccinesService();
	
	public boolean  clearUncompletedVaccines(String amka) {
		return service.clearUncompletedVaccines(amka);
	}
}

package pattients;

import java.time.LocalDate;
import java.util.List;

public class GetAllPattientsToDoVaccinesController {
	
	private GetAllPattientsToDoVaccinesService service = new GetAllPattientsToDoVaccinesService();
	
	public List<String> getAllVaccinesForAllPattientsThisYear(){
		return service.getAllVaccinesForAllPattientsThisYear();
	}

	public String getPattientsPhone(String amka) {
		System.out.println("Controller:" + amka);
		return service.getPattientsPhone(amka);
	}
	

}

package diseasesAndVaccines;

import java.awt.Component;
import java.util.List;

public class AddVaccineController {

	private AddVaccineService ser = new AddVaccineService();
	
	
	public List<String> getDiseaseList() {
		return ser.getDiseaseList();
	}


	public boolean saveVaccine(String vaccine, String disease, String freq, int age) {
		return ser.saveVaccine(vaccine, disease, freq, age);
	}

}

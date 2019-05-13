package diseasesAndVaccines;

import java.util.List;

public class GetDiseaseListController {
	
	GetDiseasesListService Service = new GetDiseasesListService();
	
	public List<String> getDiseasesList() {
		return Service.getDiseasesList();
	}

}

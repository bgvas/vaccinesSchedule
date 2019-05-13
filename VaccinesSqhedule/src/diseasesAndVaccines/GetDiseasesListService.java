package diseasesAndVaccines;

import java.util.List;

import dataBaseWorks.RetrieveFromDB;

public class GetDiseasesListService {

	RetrieveFromDB retrieve = new RetrieveFromDB();
	
	public List<String> getDiseasesList() {
		String sql = "select * from diseasesList";
		return retrieve.getDiseasesList(sql);
	}

}

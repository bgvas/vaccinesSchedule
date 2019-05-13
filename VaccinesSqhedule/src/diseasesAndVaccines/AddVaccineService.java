package diseasesAndVaccines;

import java.util.List;

import dataBaseWorks.RetrieveFromDB;
import dataBaseWorks.SaveToDB;

public class AddVaccineService {
	
	private RetrieveFromDB db = new RetrieveFromDB();

	public List<String> getDiseaseList() {
		String sql = "select * from diseasesList";
		return db.getDiseasesList(sql);
	}

	public boolean saveVaccine(String vaccine, String disease, String frequency, int age) {
		int freq = Integer.parseInt(frequency);
		String sql = "insert into vaccinesList (disease, vaccine, frequency, age) values ('" + disease +"', '" + vaccine +"', " + freq + ", " + age +")";
		if(SaveToDB.saveRecordToDB(sql)) {
			return true;
		}
		else return false;
	}

}

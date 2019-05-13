package diseasesAndVaccines;

import java.util.ArrayList;
import java.util.List;

import dataBaseWorks.RetrieveFromDB;
import dataBaseWorks.SaveToDB;

public class InputDiseases {
	
	private List<CrhronicDisease> disease = new ArrayList<>();
	private RetrieveFromDB retrieve = new RetrieveFromDB();
	
	public boolean checkIfDiseaseExist(String disease) {
		String sql = "select * from diseasesList where disease = '" + disease + "'";
		if(retrieve.checkIfDiseaseExist(sql)) {
			return true;
		}
		else return false;
	}
	
	
	public boolean sendDiseasesToDB(String disease) {
		boolean done = false;
			String sql = "insert into diseasesList (disease) values ('" + disease + "')";
			if(SaveToDB.saveRecordToDB(sql)) {
				return true;
			}
			else return false;
		
	}
	
	public List<CrhronicDisease> getDiseases(){
		return disease;
	}
	
	
	

}

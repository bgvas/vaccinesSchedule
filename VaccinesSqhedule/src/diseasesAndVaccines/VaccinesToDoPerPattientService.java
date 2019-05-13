package diseasesAndVaccines;
import java.util.ArrayList;
import java.util.List;
import dataBaseWorks.RetrieveFromDB;
import dataBaseWorks.SaveToDB;

public class VaccinesToDoPerPattientService{

	RetrieveFromDB retrieveFromDb = new RetrieveFromDB();
	List<String> vaccinesToDoList = new ArrayList<>();
	
	
	public List<String> getAllVaccinesToDoPerDisease(String disease) {
		String sql = "select * from vaccinesList where disease = '"+ disease +"'";
		return retrieveFromDb.getVaccinesToDoPerDisease(sql);
	}
		
	public boolean vaccinesToDoPerPattientToDB(String amka, String disease) {
		String sql = "select vaccine, frequency from vaccinesList where disease = '" + disease + "'";
		boolean done = false;
		int freq = 0;
		String vac = null;
		String sql1 = null;
		for(String s:retrieveFromDb.getVaccinesToDoPerDisease(sql)) {
			vac = s.substring(0, s.indexOf(","));
			int index = (s.indexOf(",", 0) + 1);
			freq =  Integer.parseInt(s.substring(index));
			sql1 = "insert into vaccinationsToDo (amka, vaccine, after) values ('" + amka + "', '" + vac + "', '" + freq + "')";
			if(SaveToDB.saveRecordToDB(sql1)) {
				done = true;
			}
			else done = false;
		}
		return done;
	}

	public List<String> getToDoVaccinesList(String amka) {
		String sql = "select * from vaccinationsToDo where amka = '" + amka +"'";
		return retrieveFromDb.getVaccinationsToDoPerPattient(sql);
	}
	
	
	
	

}

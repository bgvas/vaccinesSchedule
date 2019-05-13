package diseasesAndVaccines;
import java.time.LocalDate;
import java.util.List;

import dataBaseWorks.RetrieveFromDB;
import dataBaseWorks.SaveToDB;

public class VaccinationsDoneService {
	
	LocalDate date = LocalDate.now();
	RetrieveFromDB db = new RetrieveFromDB();
	
	public boolean vaccinesDone(String amka, String vaccine) {
		String vacDate = ("" + date.getYear()).trim();
		String sql = "insert into vaccinationsDone(amka, vaccine, date) values ('" + amka +"', '" + vaccine + "', '" + vacDate + "' )";
		String sql1 = "delete from vaccinationsToDo where amka = '" + amka + "' and vaccine = '" + vaccine + "' and after = " + date.getYear() + "";
		String sql2 = "select * from vaccinesList where  disease = '" + nowDiseaseOfPattient(amka) +"' and vaccine = '" + vaccine + "'";
		int fr = db.getFreqOfVaccine(sql2).get(0);
		if(fr > 0 ) {
			int freq = (fr * 2) + date.getYear(); // set next date for this vaccination 
			String sql3 = "insert into vaccinationsToDo (amka, vaccine, after) values ('" + amka + "', '" + vaccine + "', " + freq +" )";
			SaveToDB.saveRecordToDB(sql3);
		}
			return SaveToDB.saveRecordToDB(sql) && SaveToDB.saveRecordToDB(sql1); 
	}
	
	public List<String> getVaccinesDone(String amka){
		String sql = "select * from vaccinationsDone where amka = '" + amka + "'";
		return db.getVaccinesDone(sql);
	}
	
	public List<String> vaccinesToDo (String amka){//getVaccinationsForDisease(String amka) {
		String sql = "select vaccine from vaccinationsToDo where amka = '" + amka + "' and after = '" + date.getYear() + "'";
		return db.getVaccinationToDoPerPattient(sql);
		
	}
	
	public String nowDiseaseOfPattient(String amka){
		String sql = "select disease from disOfPattient where amka = '" + amka +"'";
		return db.getPattientNowDisease(sql);
	}

	

}

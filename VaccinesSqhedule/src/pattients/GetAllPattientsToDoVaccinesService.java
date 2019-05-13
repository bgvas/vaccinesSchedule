package pattients;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dataBaseWorks.RetrieveFromDB;

public class GetAllPattientsToDoVaccinesService {
	
	private RetrieveFromDB db = new RetrieveFromDB();
	
	public List<String> getAllVaccinesForAllPattientsThisYear() {
		List<String> allVaccinesForAllPAttients = new ArrayList<>();
		LocalDate date = LocalDate.now();
		String sql = "select * from vaccinationsToDo where after = " + date.getYear() + "";
		for(String s:db.getVaccinationsToDoPerPattient(sql)){
			int indexA = s.indexOf(" ");
			int indexB = s.indexOf(" ", indexA + 1);
			String amka = s.substring(0, 12);
			allVaccinesForAllPAttients.add(s.substring(0, indexB));	
		}
		
		return allVaccinesForAllPAttients;
	}

	public String getPattientsPhone(String amka) {
		System.out.println("Service:" + amka);
		String sql = "select * from pattients where amka = '" + amka + "'";
		return db.getPhoneOfSpecificPattient(sql);
	}
	
	

}

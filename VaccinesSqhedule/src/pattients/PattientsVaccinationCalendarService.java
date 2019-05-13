package pattients;

import java.util.List;

import dataBaseWorks.RetrieveFromDB;

public class PattientsVaccinationCalendarService {

	
	RetrieveFromDB retrieveData = new RetrieveFromDB();
	
	public List<String> getVaccinesDone(String amka) {
		String sql = "select * from vaccinationsDone where amka = '"+ amka +"'";
		return retrieveData.getVaccinesDone(sql);
	}

}

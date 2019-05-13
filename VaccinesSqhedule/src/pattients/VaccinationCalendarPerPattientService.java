package pattients;

import java.awt.Component;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import dataBaseWorks.RetrieveFromDB;

public class VaccinationCalendarPerPattientService {
	RetrieveFromDB db = new RetrieveFromDB();

	public boolean checkIfAmkaExist(String amka) {
		String sql = "select * from pattients where amka = '" + amka + "'";
		if(db.checkIfAmkaExist(sql)){
			return true;
		}
		else return false;
	}

	public String getNowDisease(String amka) {
		String sql = "select disease from disOfPattient where amka = '" + amka + "'";
		return db.getPattientNowDisease(sql);
	}
	
	public List<String> ListOfVaccinesDone(String amka){
		String sql = "select * from vaccinationsDone where amka = '" + amka + "' order by date ASC";
		return db.ListVaccinesDone(sql);
	}

	public Pattient getPattient(String amka) {
		String sql = "select * from pattients where amka = '" + amka + "'";
		return db.getPattientPerAmka(sql);
	}

	public List<String> listOfToDoVaccines(String amka) {
		List<String> list = new ArrayList<>();
		String sql = "select * from vaccinationsToDo where amka = '" + amka + "' order by after ASC";
		for(String s:db.getVaccinationsToDoPerPattient(sql)) {
			list.add(s.substring(12));
		}
		return list;
	}
}

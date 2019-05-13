package pattients;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JOptionPane;

import dataBaseWorks.RetrieveFromDB;
import dataBaseWorks.SaveToDB;

public class ChangePattientProfileService {

	RetrieveFromDB retrieve = new RetrieveFromDB();
	
	public boolean checkIfAmkaExist(String amka) {
		String sql = "select * from pattients where amka = '" + amka + "'";
		if(retrieve.checkIfAmkaExist(sql)){
			return true;
		}
		else return false;
	}

	public List<Pattient> getAllPattietnsFromDB(String amka) {
		String sql = "Select * from pattients where amka = '" + amka + "'";
		return retrieve.retrieveAllPatientsFromDB(sql);
	}

	public String getNowDisease(String amka) {
		String sql = "select disease from disOfPattient where amka = '" + amka +"'";
		return retrieve.getPattientNowDisease(sql);
	}
	
	
	
	public boolean updatePattientsElements(String amka, String phone, String age, String sel, String nowDis) {
		
		boolean ok = false;
		int ageToInt = Integer.parseInt(age);
		String sql1 = "update pattients set phone = '" + phone +"', age = '" + ageToInt + "' where amka = '" + amka + "'";
		String sql2 = "update disOfPattient set disease = '" + sel + "' where amka = '" + amka + "'";
		
		try {
				if(SaveToDB.saveRecordToDB(sql1) && SaveToDB.saveRecordToDB(sql2)) {
					JOptionPane.showMessageDialog(null,"Οι αλλαγές αποθηκεύτηκαν κανονικά");
					ok = true;
				}
				else {
					JOptionPane.showMessageDialog(null,"Πρόβλημα στην αποθήκευση των αλλαγών","",JOptionPane.ERROR_MESSAGE);
					ok = false;
				}
				// If current disease is different from new Selected disease, change the ToDo list
				if(!nowDis.equals(sel)) {
					updateDisease(amka, sel, age);
				}	
		}catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null,"Δεν βρέθηκαν εγγραφές στην βάση δεδομένων","",JOptionPane.ERROR_MESSAGE);
			ok = false;
		}
		
		return ok;
	}

	
	public List<String> getVaccinesPerDisease(String disease) {
		String sql = "select * from vaccinesList where disease = '" + disease + "'";
		return retrieve.getVaccinesForASpecificDisease(sql);
	}
	
	
	private boolean updateDisease(String amka, String sel, String age) {
		
		LocalDate date = LocalDate.now();
		boolean savedOk = false;
		String sql1 = "delete from vaccinationsToDo where amka = '" + amka + "'"; // delete old vaccinationsToDo List
		if(SaveToDB.saveRecordToDB(sql1)) {
			for(String s:getVaccinesPerDisease(sel)) {	// getting all vaccines for the specific disease
				int ind1 = s.indexOf(" ", 0);
				int ind2 = s.indexOf(" ", ind1 + 1);
				
				String  vaccine = s.substring(ind1 + 1, ind2);
				int ind3 = s.indexOf(" ", ind2 + 1);
				
				int ageForVaccine = Integer.parseInt(s.substring(ind3 + 1));
				int ageOfPat = Integer.parseInt(age);
				
				
				int freq = Integer.parseInt(s.substring(ind2 + 1, ind3));
				int yearNext = freq + date.getYear();
				
				String sql2 = "insert into vaccinationsToDo (amka, vaccine, after) values "
						+ "('" + amka +"', '" + vaccine + "', " + date.getYear() + ")";
				if(ageOfPat >= ageForVaccine) {
					if(SaveToDB.saveRecordToDB(sql2)) {
						System.out.println("Data saved to vaccinationsToDo");
						savedOk = true;
					}
					if(freq > 0) {
						String sql3 = "insert into vaccinationsToDo (amka, vaccine, after) values "
							+ "('" + amka +"', '" + vaccine + "', " + yearNext + ")";
						if(SaveToDB.saveRecordToDB(sql3)) {
							System.out.println("Data saved to vaccinationsToDo");
							savedOk = true;
						}
					}
				}
			}
		}
		return savedOk;
	}

	
	
	public List<String> getAllDiseasesList() {
		String sql = "select * from diseasesList";
		return retrieve.getDiseasesList(sql);
	}

	public boolean deletePattient(String amka) {
		
		String sql1 = "delete from pattients where amka = '" + amka + "'";
		String sql2 = "delete from vaccinationsDone where amka = '" + amka + "'";
		String sql3 = "delete from vaccinationsToDo where amka = '" + amka + "'";
		String sql4 = "delete from disOfPattient where amka = '" + amka + "'";

		if(SaveToDB.saveRecordToDB(sql1) && SaveToDB.saveRecordToDB(sql2) 
			&& SaveToDB.saveRecordToDB(sql3) && SaveToDB.saveRecordToDB(sql4)) {
		 return true;	
		}
		else return false;
	}

}

package pattients;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JOptionPane;

import dataBaseWorks.RetrieveFromDB;
import dataBaseWorks.SaveToDB;

public class AddNewPattientService {
	
	private SaveToDB saveToDb = new SaveToDB();
	private RetrieveFromDB retrieveFromDb = new RetrieveFromDB();
	
		
	// add new pattient in db
	public boolean addNewPattient(String amka, String phone, String age, String gender, String disease) {
		String sql1 = "insert into pattients (amka, phone, age, gender) values "
				+ "('" + amka + "','" + phone + "','" + age + "','" + gender + "')";
		String sql2 = "insert into disOfPattient (amka, disease) values ('"+ amka +"', '" + disease +"')";
		if(saveToDb.saveRecordToDB(sql2) && saveToDb.saveRecordToDB(sql1)) {
			JOptionPane.showMessageDialog(null,"H Eγγραφή σας αποθηκέυτηκε επιτυχώς.");
			return true;
		}
		else {
			JOptionPane.showMessageDialog(null,"Η αποθήκευση δεν ολοκληρώθηκε σωστά!!!","",JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	// check if pattient all ready exist in db
	public boolean checkIfAmkaExist(String amka) {
		String sql = "select * from pattients where amka = '" + amka + "'";
		if(retrieveFromDb.checkIfAmkaExist(sql)){
			return true;
		}
		else return false;
	}

	
	public List<String> getVaccinesPerDisease(String disease) {
		String sql = "select * from vaccinesList where disease = '" + disease + "'";
		return retrieveFromDb.getVaccinesForASpecificDisease(sql);
	}
	
	

	
	public boolean addTodoVaccines(String amka, String disease, String pattientsAge) {
		LocalDate date = LocalDate.now();
		boolean savedOk = false;
		for(String s:getVaccinesPerDisease(disease)) {	// getting all vaccines for the specific disease
			int ind1 = s.indexOf(" ", 0);
			int ind2 = s.indexOf(" ", ind1 + 1);
			
			String  vaccine = s.substring(ind1 + 1, ind2);
			int ind3 = s.indexOf(" ", ind2 + 1);
			int ageForVaccine = Integer.parseInt(s.substring(ind3 + 1));
			int ageOfPat = Integer.parseInt(pattientsAge);
			
			String sql1 = "insert into vaccinationsToDo (amka, vaccine, after) values "
					+ "('" + amka +"', '" + vaccine + "', " + date.getYear() + ")";
			if(ageOfPat >= ageForVaccine) {
				if(SaveToDB.saveRecordToDB(sql1)) {
					System.out.println("Data saved to vaccinationsToDo");
					savedOk = true;
				}
			}
		}
		return savedOk;
	}
	
	public List<String> getTodoVaccines(String amka) {
		String sql = "select * from vaccinationsToDo where amka = '" + amka +"'";
		retrieveFromDb.getVaccinationsToDoPerPattient(sql);
		return null;
	}
}

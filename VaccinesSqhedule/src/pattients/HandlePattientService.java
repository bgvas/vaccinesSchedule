package pattients;

import javax.swing.JOptionPane;

import dataBaseWorks.RetrieveFromDB;
import dataBaseWorks.SaveToDB;

public class HandlePattientService {

	SaveToDB save = new SaveToDB();
	RetrieveFromDB retrieveFromDb = new RetrieveFromDB();
	
	
	public boolean changePattientProfile(String amka, String phone, int age, String gender, String disease) {
				
		String sql1 = "update pattients set phone = '" + phone + "', age = '" + age +"', gender = '"+ gender + "' where amka = '" + amka + "'";
		String sql2 = "insert into disOfPattient (amka, disease) values('" + amka + "', '" + disease + "')" ;
		if(SaveToDB.saveRecordToDB(sql1) && (SaveToDB.saveRecordToDB(sql2))) {
				return  true;
		}
		else {
			  JOptionPane.showMessageDialog(null,"H τροποποίηση του προφίλ, παρουσίασε προβλήματα.","",JOptionPane.ERROR_MESSAGE);
		      return false;
		}
	}

	
	public boolean checkIfAmkaExist(String amka) {
		String sql = "select * from pattients where amka = '" + amka + "'";
		if(retrieveFromDb.checkIfAmkaExist(sql)){
			return true;
		}
		else return false;
	}

	public boolean deletePattient(String amka) {
		String sql = "delete from pattients where amka = '"+ amka +"'";
		if(SaveToDB.saveRecordToDB(sql)) {
			return true;
		}
		else return false;
	}

	

}

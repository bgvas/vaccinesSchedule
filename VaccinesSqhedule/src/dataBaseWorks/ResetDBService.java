package dataBaseWorks;

import javax.swing.JOptionPane;

public class ResetDBService {
	
	public boolean resetDB() {
		if(SaveToDB.saveRecordToDB("delete from pattients") &&
		SaveToDB.saveRecordToDB("delete from disOfPattient") &&
		SaveToDB.saveRecordToDB("delete from VaccinationsDone") &&
		SaveToDB.saveRecordToDB("delete from VaccinationsToDo")) {
			JOptionPane.showMessageDialog(null,"H Bάση δεδομένων είναι κενή");
			return true;
		}
		else JOptionPane.showMessageDialog(null,"H εκκαθάριση δεν ολοκληρώθηκε κανονικά!!!","",JOptionPane.ERROR_MESSAGE);
		return false;
	}

	public boolean changePass(String user, int pas) {
		String sql = "update auth set pass = '" + pas + "' where user = '" + user + "'";
		if(SaveToDB.saveRecordToDB(sql)) {
			return true;
		}
		else return false;
	}

}

package dataBaseWorks;

import javax.swing.JOptionPane;

public class ChangePassWordService {

	RetrieveFromDB db = new RetrieveFromDB();	
	
	public boolean changePassWord(String user, String pass) {
			String sql2 = "update auth set pass = '" + pass + "'";
			if(SaveToDB.saveRecordToDB(sql2)) {
				return true;
			}
			else { 
				JOptionPane.showMessageDialog(null,"Πρόβλημα με την βάση δεδομένων","",JOptionPane.ERROR_MESSAGE);
				return false;
			}
	}

	
	public boolean checkIfUserExist(String user) {
		String sql="select * from auth where user = '" + user + "'";
		if(db.checkIfAmkaExist(sql)) {
			return true;
		}
		else {
			JOptionPane.showMessageDialog(null,"Δεν βρέθηκε αυτός ο χρήστης","",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
	}

}

package dataBaseWorks;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import pattients.Pattient;

public class RetrieveFromDB {
	
	private  String conElements = DataBaseCreation.dbElements();
	private  String user = DataBaseCreation.dbUser();
	private  String pas = DataBaseCreation.dbPas();
	private List<Pattient> allPattientsFromDB = new ArrayList();
	private List<String> vaccinesDone = new ArrayList<>();
	private List<String> vaccinesToDoPerDisease = new ArrayList<>();
	private Connection conn = null;
	

	// get user and password from dataBase 
	public  String checkUser(String sql)  {
		String result = null;
		try {
			conn = DriverManager.getConnection(conElements, user, pas);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			//ok = true;
			while(rs.next()) {
				result = rs.getString(1) + " " + rs.getString(2);
			}
		}	
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Πρόβλημα με την βάση δεδομένων!!!","",JOptionPane.ERROR_MESSAGE);
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	public boolean checkIfAmkaExist(String sql) {
		boolean ok = false;
		try {
			conn = DriverManager.getConnection(conElements, user, pas);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			try {
				while(rs.next()) {
					if(!rs.getString(1).equals(null)) {
						ok = true;
					}
				}
			}catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null,"Δεν βρέθηκα εγγραφές!!!","",JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}	
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Πρόβλημα με την βάση δεδομένων!!!","",JOptionPane.ERROR_MESSAGE);
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ok;	
	}
	
	
	
	
	
	public List<Pattient> retrieveAllPatientsFromDB(String sql) {
		try {
			conn = DriverManager.getConnection(conElements, user, pas);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			try {
				int i = 0;
				while(rs.next()) {
					this.allPattientsFromDB.add(new Pattient(rs.getString(1)));
					this.allPattientsFromDB.get(i).setPhone(rs.getString(2));
					this.allPattientsFromDB.get(i).setAge(rs.getInt(3));
					this.allPattientsFromDB.get(i).setGender(rs.getString(4));
					i++;
				}
			}catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null,"Δεν βρέθηκα εγγραφές!!!","",JOptionPane.ERROR_MESSAGE);
			}
		}	
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Πρόβλημα με την βάση δεδομένων!!!","",JOptionPane.ERROR_MESSAGE);
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.allPattientsFromDB;
	}

	
	

	
	public List<String> getVaccinesDone(String sql) {
		
		try {
			conn = DriverManager.getConnection(conElements, user, pas);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			try {
				while(rs.next()) {
					this.vaccinesDone.add(rs.getString(1) + " " + rs.getString(2) + " " + rs.getInt(3));
				}
			}catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null,"Δεν βρέθηκα εγγραφές!!!","",JOptionPane.ERROR_MESSAGE);
			}
		}	
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Πρόβλημα με την βάση δεδομένων!!!","",JOptionPane.ERROR_MESSAGE);
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.vaccinesDone;
	}


	public List<String> getVaccinesToDoPerDisease(String sql) {
		try {
				conn = DriverManager.getConnection(conElements, user, pas);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql);
				try {
					while(rs.next()) {
						this.vaccinesToDoPerDisease.add(rs.getString(1) + "," + rs.getString(2) + "," + rs.getInt(3));
					}
				}catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null,"Δεν βρέθηκε τίποτα στην βάση δεδομένων!!!","",JOptionPane.ERROR_MESSAGE);
				}
			}	
			catch (SQLException e) {
				JOptionPane.showMessageDialog(null,"Πρόβλημα με την βάση δεδομένων!!!","",JOptionPane.ERROR_MESSAGE);
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 		return this.vaccinesToDoPerDisease;
	}
	
	
	public List<String> getVaccinationToDoPerPattient(String sql) {
		List<String> vaccinesToDoList = new ArrayList<>();
		try {
			conn = DriverManager.getConnection(conElements, user, pas);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			try {
				while(rs.next()) {
					vaccinesToDoList.add(rs.getString(1));// + " " + rs.getString(2) + " " + rs.getString(3));// + " " + rs.getString(4));
				}
			}catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null,"Δεν βρέθηκαν εγγραφές!!!","",JOptionPane.ERROR_MESSAGE);
			}
		}	
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Πρόβλημα με την βάση δεδομένων!!!","",JOptionPane.ERROR_MESSAGE);
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vaccinesToDoList;
	} 


	
	public boolean checkIfDiseaseExist(String sql) {
		try {
			conn = DriverManager.getConnection(conElements, user, pas);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			int count = 0;
			try {
				while(rs.next()) {
					count++;
				}
				if(count > 0) {
				   return true;	
				}
				else return false;
			}catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null,"Δεν βρέθηκαν εγγραφές!!!","",JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}	
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Πρόβλημα με την βάση δεδομένων!!!","",JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}


	
	public List<String> getDiseasesList(String sql) {
		List<String> allDiseasesList = new ArrayList<>();
		try {
			conn = DriverManager.getConnection(conElements, user, pas);
			Statement st;
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			try {
				while(rs.next()) {
					allDiseasesList.add(rs.getString(1));
				}
			}catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, "Δεν βρέθηκαν Χρόνια νοσήματα - καταστάσεις.!!!",null, JOptionPane.ERROR_MESSAGE);
			}
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Πρόβλημα με την βάση δεδομένων!!!",null, JOptionPane.ERROR_MESSAGE);
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allDiseasesList;
	}
	
	
	
	public String getPattientNowDisease(String sql) {
		String nowDisease = null;
		try {
			conn = DriverManager.getConnection(conElements, user, pas);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			try {
				while(rs.next()) {
					nowDisease = rs.getString(1);
				}
			}catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null,"Δεν βρέθηκε τίποτα στη βάση δεδομένων","",JOptionPane.ERROR_MESSAGE);
			}
		}	
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Πρόβλημα με την βάση δεδομένων","",JOptionPane.ERROR_MESSAGE);
			
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nowDisease;
	}
	
	
	
	public List<String> getVaccinesForASpecificDisease(String sql) {
		try {
			conn = DriverManager.getConnection(conElements, user, pas);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			try {
				while(rs.next()) {
						this.vaccinesToDoPerDisease.add(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
				}
			}catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null,"Δεν βρέθηκε κανένα εμβόλιο γι'αυτή την κατάσταση","",JOptionPane.ERROR_MESSAGE);
			}
		}	
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Πρόβλημα με την βάση δεδομένων","",JOptionPane.ERROR_MESSAGE);
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 	return this.vaccinesToDoPerDisease;
	}
	
	
	
	public List<String> getVaccinationsToDoPerPattient(String sql) {
		List<String> vacPerPattient = new ArrayList<>();
		try {
			conn = DriverManager.getConnection(conElements, user, pas);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			try {
				while(rs.next()) {
					
					vacPerPattient.add(rs.getString(1)+ " " + rs.getString(2) + " " + rs.getString(3));
				}
			}catch(NullPointerException e) {
				JOptionPane.showMessageDialog(null,"Δεν βρέθηκε τίποτα στη βάση δεδομένων","",JOptionPane.ERROR_MESSAGE);
			}
		
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null,"Πρόβλημα με την βάση δεδομένων","",JOptionPane.ERROR_MESSAGE);
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vacPerPattient;
	}


		public List<String> getVaccinesPerDisease(String sql) {
			List<String> getVacPerDis = new ArrayList<>();
			try {
				conn = DriverManager.getConnection(conElements, user, pas);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql);
				try {
					while(rs.next()) {
						getVacPerDis.add(rs.getString(1));
					}
				}catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null,"Δεν βρέθηκε κανένα εμβόλιο γι'αυτή την κατάσταση","",JOptionPane.ERROR_MESSAGE);
				}
			}	
			catch (SQLException e) {
				JOptionPane.showMessageDialog(null,"Πρόβλημα με την βάση δεδομένων","",JOptionPane.ERROR_MESSAGE);
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 	return getVacPerDis;
		}
		
		
		
		public List<Integer> getFreqOfVaccine(String sql) {
			List<Integer> getVacPerDis = new ArrayList<>();
			try {
				conn = DriverManager.getConnection(conElements, user, pas);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql);
				try {
					while(rs.next()) {
						getVacPerDis.add(rs.getInt(3));
						
					}
				}catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null,"Δεν βρέθηκε κανένα εμβόλιο γι'αυτή την κατάσταση","",JOptionPane.ERROR_MESSAGE);
				}
			}	
			catch (SQLException e) {
				JOptionPane.showMessageDialog(null,"Πρόβλημα με την βάση δεδομένων","",JOptionPane.ERROR_MESSAGE);
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 	return getVacPerDis;
		}


		public List<String> ListVaccinesDone(String sql) {
			List<String> list = new ArrayList<>();
			try {
				conn = DriverManager.getConnection(conElements, user, pas);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql);
				try {
					while(rs.next()) {
						list.add(rs.getString(2) + " " + rs.getString(3));
					}
				}catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null,"Δεν βρέθηκε κανένα εμβόλιο γι'αυτή την κατάσταση","",JOptionPane.ERROR_MESSAGE);
				}
			}	
			catch (SQLException e) {
				JOptionPane.showMessageDialog(null,"Πρόβλημα με την βάση δεδομένων","",JOptionPane.ERROR_MESSAGE);
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 	return list;
		}


		public Pattient getPattientPerAmka(String sql) {
			Pattient pat = new Pattient();
			try {
				conn = DriverManager.getConnection(conElements, user, pas);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql);
				try {
					while(rs.next()) {
						pat.setAmka(rs.getString(1));
						pat.setPhone(rs.getString(2));
						int age = Integer.parseInt(rs.getString(3));
						pat.setAge(age);
						pat.setGender(rs.getString(4));
						
					}
				}catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null,"Δεν κανένας ασθενής με αυτό το ΑΜΚΑ","",JOptionPane.ERROR_MESSAGE);
				}
			}	
			catch (SQLException e) {
				JOptionPane.showMessageDialog(null,"Πρόβλημα με την βάση δεδομένων","",JOptionPane.ERROR_MESSAGE);
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 	return pat;
		}
		
		
		
		public String getPhoneOfSpecificPattient(String sql) {
				String phone = null;
				try {
					conn = DriverManager.getConnection(conElements, user, pas);
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery(sql);
					try {
						while(rs.next()) {
							phone = rs.getString(1);
						}
					}catch (NullPointerException e) {
						JOptionPane.showMessageDialog(null,"Δεν βρέθηκε τίποτα στη βάση δεδομένων","",JOptionPane.ERROR_MESSAGE);
					}
				}	
				catch (SQLException e) {
					JOptionPane.showMessageDialog(null,"Πρόβλημα με την βάση δεδομένων","",JOptionPane.ERROR_MESSAGE);
					
				}
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return phone;
			}
}
	
	
	
	
	
		



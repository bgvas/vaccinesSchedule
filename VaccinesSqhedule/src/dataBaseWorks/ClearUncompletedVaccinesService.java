package dataBaseWorks;

import java.time.LocalDate;

public class ClearUncompletedVaccinesService {
	RetrieveFromDB db = new RetrieveFromDB();

	
	public boolean clearUncompletedVaccines(String amka) {
		LocalDate date = LocalDate.now();
		String sql = "select after from vaccinationsToDo where amka = '" + amka + "'";
		for(String s:db.getVaccinesPerDisease(sql)) {
			int d = Integer.parseInt(s);
			if(d < date.getYear()) {
				System.out.println(d);
				String sql2 = "delete from vaccinationsToDo where amka = '" + amka + "' and after = '" + d + "'";
				SaveToDB.saveRecordToDB(sql2);
			}
		}
		return false;
	}

}

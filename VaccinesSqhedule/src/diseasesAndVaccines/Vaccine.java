package diseasesAndVaccines;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 
 * @author Vasileios Georgoulas
 *
 */

public class Vaccine {
	
	private String vaccine;
	private int frequency;
	private LocalDate date = LocalDate.now();
		
	public Vaccine() {
		
	}
		
	public void setVaccine(String vaccine) {
		this.vaccine = vaccine;
	}

	public String getVaccine() {
		return this.vaccine;
	}
	
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	public int getFrequency() {
		return this.frequency;
	}
	
	
}

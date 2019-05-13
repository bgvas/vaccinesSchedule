package diseasesAndVaccines;



/**
 * 
 * @author Vasileios Georgoulas
 *
 */

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CrhronicDisease {
	
	private String disease;
		
	public CrhronicDisease() {
		
	}
	
	public CrhronicDisease(String disease) {
		this.disease = disease;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

		
	public void printDisease() {
		System.out.println("Chronic Disease: " + getDisease());
	}
	
}

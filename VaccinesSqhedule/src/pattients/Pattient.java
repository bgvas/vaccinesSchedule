package pattients;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import diseasesAndVaccines.CrhronicDisease;
import diseasesAndVaccines.Vaccine;
/**
 * 
 * @author Vasileios Georgoulas
 *
 */

public class Pattient {
	
	private String amka = null;
	private List<CrhronicDisease> disease = new ArrayList<>();
	private List<Vaccine> vaccine = new ArrayList<>();
	private int age = 0;
	private LocalDate date = LocalDate.now();
	private String phone = null;
	private String gender = null;
	
	public Pattient() {
		
	}
	
	public Pattient(String amka) {
		this.amka = amka;
	}
		
	public String getAmka() {
		return amka;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public void setAmka(String amka) {
		this.amka = amka;
	}
	
	public void setDisease(CrhronicDisease disease) {
		this.disease.add(disease);	
	}
	
	public void setVaccines(Vaccine vaccine) {
		this.vaccine.add(vaccine);
	}
	
	public List<Vaccine> getVaccines() {
		return this.vaccine;
	}
	
	public List<CrhronicDisease> getDiseases(){ 	
		return disease;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void printPattients() {
		System.out.println(getAmka());
		System.out.println(getAge());
		System.out.println("Deseases:");
		for(CrhronicDisease d: disease) {
			System.out.println(d.getDisease());
		}
		
	}
	
	
}

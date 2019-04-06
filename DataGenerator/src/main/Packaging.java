package main;

import java.time.LocalDate;

public class Packaging {
	private int id;
	private String serial;
	private String manufacturer;
	private LocalDate date;
	private LocalDate maintained;
	
	public Packaging(int i, String s, String m, LocalDate d, LocalDate maint) {
		id = i;
		serial = s;
		manufacturer = m;
		date = d;
		maintained = maint;
	}
	
	public int getId() {
		return id;
	}
	
	public String getSerial() {
		return serial;
	}
	
	public String getManu() {
		return manufacturer;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public LocalDate getMaint() {
		return maintained;
	}
	
	public void setMaint(LocalDate maintained) {
		this.maintained = maintained;
	}
}

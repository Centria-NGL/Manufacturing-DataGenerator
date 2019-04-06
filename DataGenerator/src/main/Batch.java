package main;

import java.time.LocalDate;

public class Batch {
	private int id;
	private LocalDate date;
	private LocalDate date_of_entry;
	
	public Batch(int i, LocalDate d, LocalDate entry) {
		id = i;
		date = d;
		date_of_entry = entry;
	}
	
	public Batch() {
		
	}
	
	public int getId() {
		return id;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public LocalDate getEntry() {
		return date_of_entry;
	}
}

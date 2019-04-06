package main;

public class StandardizationTD {
	private int s_id;
	private int b_id;
	private int running_code;
	private int running_time;
	private double fat_percentage;
	private double protein;
	private int processing_time;
	
	public StandardizationTD(int sid, int bid, int rc, int rt, double fat, double prot, int pt) {
		s_id = sid;
		b_id = bid;
		running_code = rc;
		running_time = rt;
		fat_percentage = fat;
		protein = prot;
		processing_time = pt;
	}

	public int getSid() {
		return s_id;
	}

	public int getBid() {
		return b_id;
	}

	public int getRunningCode() {
		return running_code;
	}

	public int getRunningTime() {
		return running_time;
	}

	public double getFatPercentage() {
		return fat_percentage;
	}

	public double getProtein() {
		return protein;
	}

	public int getProcessingTime() {
		return processing_time;
	}

}

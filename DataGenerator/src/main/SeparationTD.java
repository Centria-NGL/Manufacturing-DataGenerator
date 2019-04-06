package main;

public class SeparationTD {
	private int s_id;
	private int b_id;
	private int running_code;
	private int running_time;
	private double cream_amount;
	private double milk_amount;
	private double waste_amount;
	private int processing_time;
	
	public SeparationTD(int sid, int bid, int rc, int rt, double cream, double milk, double waste, int pt) {
		s_id = sid;
		b_id = bid;
		running_code = rc;
		running_time = rt;
		cream_amount = cream;
		milk_amount = milk;
		waste_amount = waste;
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

	public double getCreamAmount() {
		return cream_amount;
	}

	public double getMilkAmount() {
		return milk_amount;
	}

	public double getWasteAmount() {
		return waste_amount;
	}

	public int getProcessingTime() {
		return processing_time;
	}
}

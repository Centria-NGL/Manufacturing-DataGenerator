package main;

public class HomogenizationTD {
	private int h_id;
	private int b_id;
	private int running_code;
	private int running_time;
	private int pressure;
	private double temperature;
	private double fat_percentage;
	private int processing_time;
	
	public HomogenizationTD(int hid, int bid, int rc, int rt, int pres, double temp, double fat, int pt) {
		h_id = hid;
		b_id = bid;
		running_code = rc;
		running_time = rt;
		pressure = pres;
		temperature = temp;
		fat_percentage = fat;
		processing_time = pt;
	}

	public int getHid() {
		return h_id;
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

	public int getPressure() {
		return pressure;
	}

	public double getTemperature() {
		return temperature;
	}

	public double getFatPercentage() {
		return fat_percentage;
	}

	public int getProcessingTime() {
		return processing_time;
	}
}

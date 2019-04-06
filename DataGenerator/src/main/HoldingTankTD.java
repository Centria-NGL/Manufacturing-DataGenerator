package main;


public class HoldingTankTD {
	private int t_id;
	private int b_id;
	private int runningCode;
	private int runningTime;
	private int capacity;
	private double temperature;
	
	public HoldingTankTD(int tid, int bid, int code, int run, int cap, double temp) {
		t_id = tid;
		b_id = bid;
		runningCode = code;
		runningTime = run;
		capacity = cap;
		temperature = temp;
	}
	
	public HoldingTankTD() {
		
	}
	
	public int getTid() {
		return t_id;
	}
	
	public int getBid() {
		return b_id;
	}
	
	public int getCode() {
		return runningCode;
	}
	
	public int getRunningTime() {
		return runningTime;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public double getTemperature() {
		return temperature;
	}
}

package main;

public class PasteurizationTD {
	private int p_id;
	private int b_id;
	private int running_code;
	private int running_time;
	private double temperature;
	private double bacterial_level;
	private int processing_time;
	
	public PasteurizationTD(int pid, int bid, int rc, int rt, double temp, double bl, int pt) {
		p_id = pid;
		b_id = bid;
		running_code = rc;
		running_time = rt;
		temperature = temp;
		bacterial_level = bl;
		processing_time = pt;
	}
	
	public int getPid() {
		return p_id;
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

	public double getTemperature() {
		return temperature;
	}

	public double getBacterialLevel() {
		return bacterial_level;
	}

	public int getProcessingTime() {
		return processing_time;
	}
	
}

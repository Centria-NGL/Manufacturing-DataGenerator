package main;

public class PackagingTD {
	private int p_id;
	private int b_id;
	private int running_code;
	private int running_time;
	private int batch_code;
	private int material_amount;
	private int processing_time;
	
	public PackagingTD(int pid, int bid, int rc, int rt, int code, int mat, int pt) {
		p_id = pid;
		b_id = bid;
		running_code = rc;
		running_time = rt;
		batch_code = code;
		material_amount = mat;
		processing_time = pt;
	}
	
	public PackagingTD() {
		
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

	public int getBatchCode() {
		return batch_code;
	}

	public int getMaterialAmount() {
		return material_amount;
	}

	public int getProcessingTime() {
		return processing_time;
	}
}

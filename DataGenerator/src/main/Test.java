package main;

public class Test {
	private int t_id;
	private int b_id;
	private double fat;
	private int cell_count;
	private double ph;
	private double lactose;
	private double protein;
	
	public Test(int t_id, int b_id, double fat, int cell_count, double ph, double lactose, double protein) {
		this.t_id = t_id;
		this.b_id = b_id;
		this.fat = fat;
		this.cell_count = cell_count;
		this.ph = ph;
		this.lactose = lactose;
		this.protein = protein;
	}

	public int getTid() {
		return t_id;
	}

	public int getBid() {
		return b_id;
	}
	
	public double getFat() {
		return fat;
	}
	
	public int getCellCount() {
		return cell_count;
	}

	public double getPh() {
		return ph;
	}

	public double getLactose() {
		return lactose;
	}

	public double getProtein() {
		return protein;
	}
}

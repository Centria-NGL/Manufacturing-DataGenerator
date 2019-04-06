package main;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	// Generating an array of batch codes
        int[] bcodes = createRandomNumbersWithoutDuplicates(100000, 999999, 10000);
    	List<Batch> batches = new ArrayList<>();
    	
    	List<Test> tests = new ArrayList<>();
    	
    	// Master data lists
    	List<Tank> tanks = new ArrayList<>();
    	List<Pasteurization> pasteurizations = new ArrayList<>();
    	List<Standardization> standardizations = new ArrayList<>();
    	List<Separation> separations = new ArrayList<>();
    	List<Homogenization> homogenizations = new ArrayList<>();
    	List<Packaging> packagings = new ArrayList<>();
    	
    	// Transactional data lists
    	List<HoldingTankTD> tank_td = new ArrayList<>();
    	List<PasteurizationTD> pasteurization_td = new ArrayList<>();
    	List<StandardizationTD> standardization_td = new ArrayList<>();
    	List<SeparationTD> separation_td = new ArrayList<>();
    	List<HomogenizationTD> homogenization_td = new ArrayList<>();
    	List<PackagingTD> packaging_td = new ArrayList<>();
    	
    	// Generating the machine date
    	standardizations = generateStandardization();
    	separations = generateSeparation();
    	tanks = generateTank();
    	pasteurizations = generatePasteurization();
    	homogenizations = generateHomogenization();
    	packagings = generatePackaging();
    	
    	// For loop for generating transactional data
    	for(int i = 1; i < 10001; i++) {
    		HoldingTankTD tank_data = new HoldingTankTD();
    		HoldingTankTD last_tank = new HoldingTankTD();
    		
    		// get the last element of the list
    		if(tank_td != null && !tank_td.isEmpty()) {
    			last_tank = tank_td.get(tank_td.size() - 1);
    		}
    		
    		// if the holding tank list is empty, new one is created with capacity 0
    		// otherwise we pass the last tank's capacity as a parameter
    		if(tank_td == null && tank_td.isEmpty()) {
    			tank_data = generateHoldingTankTD(i, 0);
    		}
    		else {
    			tank_data = generateHoldingTankTD(i, last_tank.getCapacity());
    		}
    		tank_td.add(tank_data);
    		
    		
    		Batch b = new Batch();
    		Batch last_b = new Batch();
    		LocalDate d = createRandomDate(1980, 1990);
    		
    		
    		if(batches != null && !batches.isEmpty()) {
    			last_b = batches.get(batches.size() - 1);
    		}
    		else {
    			last_b = generateBatches(i, d);
    			batches.add(last_b);
    		}
    		
    		b = generateBatches(i, last_b.getEntry());
    		batches.add(b);
    		
    		// Tests
    		Test t = generateTestData(i);
    		tests.add(t);
    		
    		
    		// Separation
    		SeparationTD sep = generateSeparationTD(i);
    		separation_td.add(sep);
    		
    		// Standardization
    		StandardizationTD stand = generateStandardizationTD(i);
    		standardization_td.add(stand);
    		
    		// Pasteurization
    		PasteurizationTD past = generatePasteurizationTD(i);
    		pasteurization_td.add(past);
    		
    		// Homogenization
    		HomogenizationTD hgen = generateHomogenizationTD(i);
    		homogenization_td.add(hgen);
    		
    		// Packaging
    		PackagingTD last_pkg = new PackagingTD();
    		PackagingTD ptd = new PackagingTD();
    		
    		// Get the last element of packages array, if it doesn't exist, this creates a new one with material amount of 0
    		if(packaging_td != null && !packaging_td.isEmpty()) {
    			last_pkg = packaging_td.get(packaging_td.size() - 1);
    		}
    		else {
    			last_pkg = generatePackagingTD(i, bcodes[i-1], 0);
    			packaging_td.add(last_pkg);
    		}
    		ptd = generatePackagingTD(i, bcodes[i-1], last_pkg.getMaterialAmount());
    		packaging_td.add(ptd);
    		
    		
    		// Change machine's maintenance date if running code is not 1
    		// We check if the transactional data's batch id exists in batches list
    		// if it exists, we take the batches entry date and change machine's maintenance date to the entry date
    		if(sep.getRunningCode() != 1) {
    			for(Separation s : separations) {
    				if(s.getId() == sep.getSid()) {
    					LocalDate dt = null;
    					for(Batch bat : batches) {
    						if(sep.getBid() == bat.getId()) {
    							dt = bat.getEntry();
    						}
    					}
    					s.setMaint(dt);
    				}
    			}
    			
    		}
    		
    		if(stand.getRunningCode() != 1) {
    			for(Standardization s : standardizations) {
    				if(s.getId() == stand.getSid()) {
    					LocalDate dt = null;
    					for(Batch bat : batches) {
    						if(sep.getBid() == bat.getId()) {
    							dt = bat.getEntry();
    						}
    					}
    					s.setMaint(dt);
    				}
    			}
    		}
    		
    		if(past.getRunningCode() != 1) {
    			for(Pasteurization p : pasteurizations) {
    				if(p.getId() == past.getPid()) {
    					LocalDate dt = null;
    					for(Batch bat : batches) {
    						if(sep.getBid() == bat.getId()) {
    							dt = bat.getEntry();
    						}
    					}
    					p.setMaint(dt);
    				}
    			}
    		}
    		
    		if(hgen.getRunningCode() != 1) {
    			for(Homogenization h : homogenizations) {
    				if(h.getId() == hgen.getHid()) {
    					LocalDate dt = null;
    					for(Batch bat : batches) {
    						if(sep.getBid() == bat.getId()) {
    							dt = bat.getEntry();
    						}
    					}
    					h.setMaint(dt);
    				}
    			}
    		}
    		
    		if(ptd.getRunningCode() != 1) {
    			for(Packaging p : packagings) {
    				if(p.getId() == ptd.getPid()) {
    					LocalDate dt = null;
    					for(Batch bat : batches) {
    						if(sep.getBid() == bat.getId()) {
    							dt = bat.getEntry();
    						}
    					}
    					p.setMaint(dt);
    				}
    			}
    		}
    	}
    	
    	// Passing the lists as parameters to write functions
    	writeTanks(tanks);
    	writePasteurizations(pasteurizations);
    	writeStandardizations(standardizations);
    	writeSeparations(separations);
    	writeHomogenizations(homogenizations);
    	writePackagings(packagings);
    	writeTankTD(tank_td);
    	writePasteurizationTD(pasteurization_td);
    	writeStandardizationTD(standardization_td);
    	writeSeparationTD(separation_td);
    	writeHomogenizationTD(homogenization_td);
    	writePackagingTD(packaging_td);
    	writeBatches(batches);
    }
    
    // function for creating random int
    public static int createRandom(int start, int end) {
    	return start + (int) Math.round(Math.random() * (end - start));
    }
    
    // function for creating random double
    public static double createRandomDouble(int start, int end) {
    	Random r = new Random();
    	double random = start + r.nextDouble() * (end - start);
    	return random;
    }
    
    // function for creating random date
    public static LocalDate createRandomDate(int startYear, int endYear) {
    	int day = createRandom(1, 28);
    	int month = createRandom(1, 12);
    	int year = createRandom(startYear, endYear);
    	return LocalDate.of(year, month, day);
    }
    
    // function for creating random date, you can specify start and end mont
    public static LocalDate createRandomDateMonth(int startYear, int endYear, int startMonth, int endMonth) {
    	int day = createRandom(1, 28);
    	int month = createRandom(startMonth, endMonth);
    	int year = createRandom(startYear, endYear);
    	return LocalDate.of(year, month, day);
    }
    
    // function for creating and array of numbers, doesn't contain duplicates
    public static int[] createRandomNumbersWithoutDuplicates(int start, int end, int count) {
    	Random rng = new Random();
    	
    	int[] result = new int[count];
    	int cur = 0;
    	int remaining = end - start;
    	
    	for(int i = start; i < end && count > 0; i++) {
    		double probability = rng.nextDouble();
    		if(probability < ((double) count) / (double) remaining) {
    			count--;
    			result[cur++] = i;
    		}
    		remaining--;
    	}
    	return result;
    }
    
    // function for generating holding tank machine data
    // returns a list of Tank objects
    public static List<Tank> generateTank() throws Exception{
        List<Tank> tanks = new ArrayList<>();
        
        for(int i = 1; i < 6; i++) {
        	int id = i;
        	LocalDate date = createRandomDate(1980, 1985);
        	String manu = "Testi";
        	String serial = null;
        	LocalDate maint = createRandomDate(1989, 1990);
        	boolean check = true;
        	while(check) {
        		int s = createRandom(1, 5);
        		final String ser = String.valueOf(s);
        		boolean serialExists = tanks.stream().anyMatch(tank -> ser.equals(tank.getSerial()));
        		if(serialExists) {
        			check = true;
        		}
        		else {
        			serial = ser;
        			tanks.add(new Tank(id, serial, manu, date, maint));
        			check = false;
        		}
        	}
        }
        
        return tanks;
    }
    
    // function for generating pasteurization machine data
    // returns a list of Pasteurization objects
    public static List<Pasteurization> generatePasteurization() {
        List<Pasteurization> pasteurizations = new ArrayList<>();
        
        for(int i = 1; i < 6; i++) {
        	int id = i;
        	LocalDate date = createRandomDate(1980, 1985);
        	String manu = "Testi";
        	String serial = null;
        	LocalDate maint = createRandomDate(1989, 1990);
        	
        	boolean check = true;
        	while(check) {
        		int s = createRandom(1, 5);
        		final String ser = String.valueOf(s);
        		boolean serialExists = pasteurizations.stream().anyMatch(item -> ser.equals(item.getSerial()));
        		if(serialExists) {
        			check = true;
        		}
        		else {
        			serial = ser;
        			pasteurizations.add(new Pasteurization(id, serial, manu, date, maint));
        			check = false;
        		}
        	}
        }
        
        return pasteurizations;
    }
    
    // function for generating standardization machine data
    // returns a list of Standardization objects
    public static List<Standardization> generateStandardization() {
        List<Standardization> standardizations = new ArrayList<>();
        
        for(int i = 1; i < 6; i++) {
        	int id = i;
        	LocalDate date = createRandomDate(1980, 1985);
        	String manu = "Testi";
        	String serial = null;
        	LocalDate maint = createRandomDate(1989, 1990);
        	
        	boolean check = true;
        	while(check) {
        		int s = createRandom(1, 5);
        		final String ser = String.valueOf(s);
        		boolean serialExists = standardizations.stream().anyMatch(item -> ser.equals(item.getSerial()));
        		if(serialExists) {
        			check = true;
        		}
        		else {
        			serial = ser;
        			standardizations.add(new Standardization(id, serial, manu, date, maint));
        			check = false;
        		}
        	}
        }
        
        return standardizations;
    }
    
    // function for generating separation machine data
    // returns a list of Separation objects
    public static List<Separation> generateSeparation() {
        List<Separation> separations = new ArrayList<>();
        
        for(int i = 1; i < 6; i++) {
        	int id = i;
        	LocalDate date = createRandomDate(1980, 1985);
        	String manu = "Testi";
        	String serial = null;
        	LocalDate maint = createRandomDate(1989, 1990);
        	
        	boolean check = true;
        	while(check) {
        		int s = createRandom(1, 5);
        		final String ser = String.valueOf(s);
        		boolean serialExists = separations.stream().anyMatch(item -> ser.equals(item.getSerial()));
        		if(serialExists) {
        			check = true;
        		}
        		else {
        			serial = ser;
        			separations.add(new Separation(id, serial, manu, date, maint));
        			check = false;
        		}
        	}
        }
        
        return separations;
    }
    
    // function for generating homogenization machine data
    // returns a list of Homogenization objects
    public static List<Homogenization> generateHomogenization() {
        List<Homogenization> homogenizations = new ArrayList<>();
        
        for(int i = 1; i < 6; i++) {
        	int id = i;
        	LocalDate date = createRandomDate(1980, 1985);
        	String manu = "Testi";
        	String serial = null;
        	LocalDate maint = createRandomDate(1989, 1990);
        	
        	boolean check = true;
        	while(check) {
        		int s = createRandom(1, 5);
        		final String ser = String.valueOf(s);
        		boolean serialExists = homogenizations.stream().anyMatch(item -> ser.equals(item.getSerial()));
        		if(serialExists) {
        			check = true;
        		}
        		else {
        			serial = ser;
        			homogenizations.add(new Homogenization(id, serial, manu, date, maint));
        			check = false;
        		}
        	}
        }
        
        return homogenizations;
    }
    
    // function for generating packaging machine data
    // returns a list of Packaging objects
    public static List<Packaging> generatePackaging() {
        List<Packaging> packagings = new ArrayList<>();
        
        for(int i = 1; i < 6; i++) {
        	int id = i;
        	LocalDate date = createRandomDate(1980, 1985);
        	String manu = "Testi";
        	String serial = null;
        	LocalDate maint = createRandomDate(1989, 1990);
        	
        	boolean check = true;
        	while(check) {
        		int s = createRandom(1, 5);
        		final String ser = String.valueOf(s);
        		boolean serialExists = packagings.stream().anyMatch(item -> ser.equals(item.getSerial()));
        		if(serialExists) {
        			check = true;
        		}
        		else {
        			serial = ser;
        			packagings.add(new Packaging(id, serial, manu, date, maint));
        			check = false;
        		}
        	}
        }
        
        return packagings;
    }
    
    // function for generating a batch
    // requires batch id and date as parameters
    // returns Batch object
    public static Batch generateBatches(int b_id, LocalDate d) {
    	// creating a temporary date for entry date
    	LocalDate entry = createRandomDate(1989, 1990);
    	
    	// if the random number is 3, we add a day to the entry date compared to the previous batch
    	// if the number isn't 3, entry date will be the same as the previous batches date
    	int rnd = createRandom(1, 3);
    	if(rnd == 3) {
    		entry = d.plusDays(1);
    	}
    	else {
    		entry = d;
    	}
    	LocalDate date = entry;
    	
    	Batch b = new Batch(b_id, date, entry);
    	
    	return b;
    }
    
    // function for generating holding tank transactional data
    // requires batch id and capacity as parameters
    // returns HoldingTankTD object
    public static HoldingTankTD generateHoldingTankTD(int b_id, int capacity){
        int t_id = createRandom(1, 5);
        int rc = 1;
    	int rnd = createRandom(1, 6);
    	if(rnd == 6) {
    		rc = createRandom(2, 10);
    	}
    	else {
    		rc = 1;
    	}
    	int runtime = createRandom(1, 48);
    	int cap = 0;
    	if(capacity >= 100) {
    		cap = capacity - 100;
    	}
    	else {
    		cap = createRandom(110, 800) + capacity;
    	}
    	double temp = createRandomDouble(1, 5);
        
        
        HoldingTankTD tank_td = new HoldingTankTD(t_id, b_id, rc, runtime, cap, temp);
        
        return tank_td;
    }
    
    // function for generating pasteurization transactional data
    // requires batch id as a parameter
    // returns PasteurizationTD object
    public static PasteurizationTD generatePasteurizationTD(int b_id){
        int p_id = createRandom(1, 5);
        int rc = 1;
    	int rnd = createRandom(1, 6);
    	if(rnd == 6) {
    		rc = createRandom(2, 10);
    	}
    	else {
    		rc = 1;
    	}
    	int runtime = createRandom(1, 48);
    	double temp = createRandomDouble(1, 5);
    	double bact = createRandomDouble(0, 3);
    	int process_time = createRandom(10, 120);
        
    	PasteurizationTD paste_td = new PasteurizationTD(p_id, b_id, rc, runtime, temp, bact, process_time);
    	
    	return paste_td;
    }
    
    // function for generating standardization transactional data
    // requires batch id as a parameter
    // returns StandardizationTD object
    public static StandardizationTD generateStandardizationTD(int b_id) {
        int s_id = createRandom(1, 5);
        int rc = 1;
    	int rnd = createRandom(1, 6);
    	if(rnd == 6) {
    		rc = createRandom(2, 10);
    	}
    	else {
    		rc = 1;
    	}
    	int runtime = createRandom(1, 48);
    	double fat = createRandomDouble(1, 5);
    	double prot = createRandomDouble(0, 3);
    	int process_time = createRandom(10, 120);
    	
    	StandardizationTD stand_td = new StandardizationTD(s_id, b_id, rc, runtime, fat, prot, process_time);
    	
    	return stand_td;
    }
    
    // function for generating separation transactional data
    // requires batch id as a parameter
    // returns SeparationTD object
    public static SeparationTD generateSeparationTD(int b_id) {
        int s_id = createRandom(1, 5);
    	int rc = 1;
    	int rnd = createRandom(1, 6);
    	if(rnd == 6) {
    		rc = createRandom(2, 10);
    	}
    	else {
    		rc = 1;
    	}
    	int runtime = createRandom(1, 48);
    	double cream = createRandomDouble(5, 10);
    	double milk = createRandomDouble(50, 100);
    	double waste = createRandomDouble(1, 3);
    	int process_time = createRandom(10, 120);
    	
    	SeparationTD sep_td = new SeparationTD(s_id, b_id, rc, runtime, cream, milk, waste, process_time);
        
    	return sep_td;
    }
    
    // function for generating homogenization transactional data
    // requires batch id as a parameter
    // returns HomogenizationTD object
    public static HomogenizationTD generateHomogenizationTD(int b_id) {
        int h_id = createRandom(1, 5);
    	int rc = 1;
    	int rnd = createRandom(1, 6);
    	if(rnd == 6) {
    		rc = createRandom(2, 10);
    	}
    	else {
    		rc = 1;
    	}
    	int runtime = createRandom(1, 48);
    	int pressure = createRandom(120, 150);
    	double temp = createRandomDouble(1, 5);
    	double fat = createRandomDouble(1, 3);
    	int process_time = createRandom(10, 120);
    	
    	HomogenizationTD homogen_td = new HomogenizationTD(h_id, b_id, rc, runtime, pressure, temp, fat, process_time);
    	return homogen_td;
    }
    
    // function for generating packaging transactional data
    // requires batch id, batch code and material amount as parameters
    // returns PackagingTD object
    public static PackagingTD generatePackagingTD(int b_id, int bcode, int material) {
        int p_id = createRandom(1, 5);
    	int rc = 1;
    	int rnd = createRandom(1, 6);
    	if(rnd == 6) {
    		rc = createRandom(2, 10);
    	}
    	else {
    		rc = 1;
    	}
    	int runtime = createRandom(1, 48);
    	
    	int mat = 0;
    	if(material >= 10) {
    		mat = material - 6;
    	}
    	else {
    		mat = 100 + material;
    	}
    	
    	int process_time = createRandom(10, 120);
    	
    	PackagingTD pack_td = new PackagingTD(p_id, b_id, rc, runtime, bcode, mat, process_time);
    	
    	return pack_td;
    }
    
    // function for generating test data
    // requires batch id as a parameter
    // returns Test object
    public static Test generateTestData(int b_id) {
        int t_id = createRandom(1, 5);
    	double fat = createRandomDouble(1, 4);
    	int cells = createRandom(400000, 750000);
    	double ph = createRandomDouble(6, 8);
    	double lact = createRandomDouble(1, 5);
    	double prot = createRandomDouble(1, 4);
    	
    	Test testi = new Test(t_id, b_id, fat, cells, ph, lact, prot);
    	
    	return testi;
    }
    
    
    
    /*
    	CSV WRITER FUNCTIONS
    
    	they require list of objects as a parameter
    */
    
    
    public static void writeTanks(List<Tank> tanks) throws Exception {
    	// file path
    	String csvFile = "C:/path_to_file/file_name.csv";
        FileWriter writer = new FileWriter(csvFile);
        
        // format for the dates
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        CSVUtils.writeLine(writer, Arrays.asList("Id", "SerialNumber", "Manufacturer", "Date", "LastMaintained"), '	');
        
        for(Tank t : tanks) {
        	// We need to "stringify" all objects variables before we write them to a file
        	List<String> list = new ArrayList<>();
        	list.add(String.valueOf(t.getId()));
        	list.add(String.valueOf(t.getSerial()));
        	list.add(t.getManu());
        	String date = t.getDate().format(formatter);
        	list.add(date);
        	String maint = t.getMaint().format(formatter);
        	list.add(maint);
        	
        	// Custom separator
        	CSVUtils.writeLine(writer, list, '	');
        }
        
        writer.flush();
        writer.close();
    }
    
    public static void writePasteurizations(List<Pasteurization> pasteurizations) throws Exception {
    	
    	String csvFile = "C:/path_to_file/file_name.csv";
        FileWriter writer = new FileWriter(csvFile);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
       
        CSVUtils.writeLine(writer, Arrays.asList("Id", "SerialNumber", "Manufacturer", "Date", "LastMaintained"), '	');
        
        for(Pasteurization p : pasteurizations) {
        	List<String> list = new ArrayList<>();
        	list.add(String.valueOf(p.getId()));
        	list.add(String.valueOf(p.getSerial()));
        	list.add(p.getManu());
        	String date = p.getDate().format(formatter);
        	list.add(date);
        	String maint = p.getMaint().format(formatter);
        	list.add(maint);
        	
        	// Custom separator
        	CSVUtils.writeLine(writer, list, '	');
        }
        
        writer.flush();
        writer.close();
        
    }
    
    public static void writeStandardizations(List<Standardization> standardizations) throws Exception {
    	
    	String csvFile = "C:/path_to_file/file_name.csv";
        FileWriter writer = new FileWriter(csvFile);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        CSVUtils.writeLine(writer, Arrays.asList("Id", "SerialNumber", "Manufacturer", "Date", "LastMaintained"), '	');
        
        for(Standardization s : standardizations) {
        	List<String> list = new ArrayList<>();
        	list.add(String.valueOf(s.getId()));
        	list.add(String.valueOf(s.getSerial()));
        	list.add(s.getManu());
        	String date = s.getDate().format(formatter);
        	list.add(date);
        	String maint = s.getMaint().format(formatter);
        	list.add(maint);
        	
        	// Custom separator
        	CSVUtils.writeLine(writer, list, '	');
        }
        
        writer.flush();
        writer.close();
        
    }
    
    public static void writeSeparations(List<Separation> separations) throws Exception {
    	
    	String csvFile = "C:/path_to_file/file_name.csv";
        FileWriter writer = new FileWriter(csvFile);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
       
        CSVUtils.writeLine(writer, Arrays.asList("Id", "SerialNumber", "Manufacturer", "Date", "LastMaintained"), '	');
        
        for(Separation s : separations) {
        	List<String> list = new ArrayList<>();
        	list.add(String.valueOf(s.getId()));
        	list.add(String.valueOf(s.getSerial()));
        	list.add(s.getManu());
        	String date = s.getDate().format(formatter);
        	list.add(date);
        	String maint = s.getMaint().format(formatter);
        	list.add(maint);
        	
        	// Custom separator
        	CSVUtils.writeLine(writer, list, '	');
        }
        
        writer.flush();
        writer.close();
        
    }
    
    public static void writeHomogenizations(List<Homogenization> homogenizations) throws Exception {
    	
    	String csvFile = "C:/path_to_file/file_name.csv";
        FileWriter writer = new FileWriter(csvFile);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
       
        CSVUtils.writeLine(writer, Arrays.asList("Id", "SerialNumber", "Manufacturer", "Date", "LastMaintainend"), '	');
        
        for(Homogenization h : homogenizations) {
        	List<String> list = new ArrayList<>();
        	list.add(String.valueOf(h.getId()));
        	list.add(String.valueOf(h.getSerial()));
        	list.add(h.getManu());
        	String date = h.getDate().format(formatter);
        	list.add(date);
        	String maint = h.getMaint().format(formatter);
        	list.add(maint);
        	
        	// Custom separator
        	CSVUtils.writeLine(writer, list, '	');
        }
        
        writer.flush();
        writer.close();
        
    }
    
    public static void writeTankTD(List<HoldingTankTD> tank_td) throws Exception {
    	String csvFile = "C:/path_to_file/file_name.csv";
        FileWriter writer = new FileWriter(csvFile);
    	
    	CSVUtils.writeLine(writer, Arrays.asList("TankID", "BatchID", "RunningCode", "RunningTime", "Capacity", "Temperature"), '	');
        
        for(HoldingTankTD p : tank_td) {
        	List<String> list = new ArrayList<>();
        	list.add(String.valueOf(p.getTid()));
        	list.add(String.valueOf(p.getBid()));
        	list.add(String.valueOf(p.getCode()));
        	list.add(String.valueOf(p.getRunningTime()));
        	list.add(String.valueOf(p.getCapacity()));
        	list.add(String.format("%.2f", p.getTemperature()));
        	
        	// Custom separator
        	CSVUtils.writeLine(writer, list, '	');
        }
        
        writer.flush();
        writer.close();
    }
    
    public static void writePackagings(List<Packaging> packagings) throws Exception {
    	
    	String csvFile = "C:/path_to_file/file_name.csv";
        FileWriter writer = new FileWriter(csvFile);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        CSVUtils.writeLine(writer, Arrays.asList("Id", "SerialNumber", "Manufacturer", "Date"), '	');
        
        for(Packaging p : packagings) {
        	List<String> list = new ArrayList<>();
        	list.add(String.valueOf(p.getId()));
        	list.add(String.valueOf(p.getSerial()));
        	list.add(p.getManu());
        	String date = p.getDate().format(formatter);
        	list.add(date);
        	
        	// Custom separator
        	CSVUtils.writeLine(writer, list, '	');
        }
        
        writer.flush();
        writer.close();
        
    }
    
    public static void writeBatches(List<Batch> batches) throws Exception {
    	String csvFile = "C:/path_to_file/file_name.csv";
        FileWriter writer = new FileWriter(csvFile);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	
    	CSVUtils.writeLine(writer, Arrays.asList("BatchID", "EntryDate", "ManufactureDate"), '	');
        
        for(Batch b : batches) {
        	List<String> list = new ArrayList<>();
        	list.add(String.valueOf(b.getId()));
        	String entry = b.getEntry().format(formatter);
        	list.add(entry);
        	String date = b.getDate().format(formatter);
        	list.add(date);
        	
        	// Custom separator
        	CSVUtils.writeLine(writer, list, '	');
        }
        
        writer.flush();
        writer.close();
    }
    
    public static void writePasteurizationTD(List<PasteurizationTD> past_td) throws Exception {
    	
    	String csvFile = "C:/path_to_file/file_name.csv";
        FileWriter writer = new FileWriter(csvFile);
        
        
        CSVUtils.writeLine(writer, Arrays.asList("PasteurizationID", "BatchID", "RunningCode", "RunningTime", "Temperature", "BacterialLevel", "ProcessTime"), '	');
        
        for(PasteurizationTD p : past_td) {
        	List<String> list = new ArrayList<>();
        	list.add(String.valueOf(p.getPid()));
        	list.add(String.valueOf(p.getBid()));
        	list.add(String.valueOf(p.getRunningCode()));
        	list.add(String.valueOf(p.getRunningTime()));
        	list.add(String.format("%.2f", p.getTemperature()));
        	list.add(String.format("%.2f", p.getBacterialLevel()));
        	list.add(String.valueOf(p.getProcessingTime()));
        	
        	// Custom separator
        	CSVUtils.writeLine(writer, list, '	');
        }
        
        writer.flush();
        writer.close();
        
    }
    
    public static void writeStandardizationTD(List<StandardizationTD> stand_td) throws Exception {
    	String csvFile = "C:/path_to_file/file_name.csv";
        FileWriter writer = new FileWriter(csvFile);
        
        
        CSVUtils.writeLine(writer, Arrays.asList("StandardizationID", "BatchID", "RunningCode", "RunningTime", "FatPercentage", "Protein", "ProcessTime"), '	');
        
        for(StandardizationTD s : stand_td) {
        	List<String> list = new ArrayList<>();
        	list.add(String.valueOf(s.getSid()));
        	list.add(String.valueOf(s.getBid()));
        	list.add(String.valueOf(s.getRunningCode()));
        	list.add(String.valueOf(s.getRunningTime()));
        	list.add(String.format("%.2f", s.getFatPercentage()));
        	list.add(String.format("%.2f", s.getProtein()));
        	list.add(String.valueOf(s.getProcessingTime()));
        	
        	// Custom separator
        	CSVUtils.writeLine(writer, list, '	');
        }
        
        writer.flush();
        writer.close();
    }
    
    public static void writeSeparationTD(List<SeparationTD> sep_td) throws Exception {
    	String csvFile = "C:/path_to_file/file_name.csv";
        FileWriter writer = new FileWriter(csvFile);
        
        
        CSVUtils.writeLine(writer, Arrays.asList("SeparationID", "BatchID", "RunningCode", "RunningTime", "Cream", "Milk", "Waste", "ProcessTime"), '	');
        
        for(SeparationTD s : sep_td) {
        	List<String> list = new ArrayList<>();
        	list.add(String.valueOf(s.getSid()));
        	list.add(String.valueOf(s.getBid()));
        	list.add(String.valueOf(s.getRunningCode()));
        	list.add(String.valueOf(s.getRunningTime()));
        	list.add(String.format("%.2f", s.getCreamAmount()));
        	list.add(String.format("%.2f", s.getMilkAmount()));
        	list.add(String.format("%.2f", s.getWasteAmount()));
        	list.add(String.valueOf(s.getProcessingTime()));
        	
        	// Custom separator
        	CSVUtils.writeLine(writer, list, '	');
        }
        
        writer.flush();
        writer.close();
    }
    
    public static void writeHomogenizationTD(List<HomogenizationTD> hgen_td) throws Exception {
    	String csvFile = "C:/path_to_file/file_name.csv";
        FileWriter writer = new FileWriter(csvFile);
        
        
        CSVUtils.writeLine(writer, Arrays.asList("HgenID", "BatchID", "RunningCode", "RunningTime", "Pressure", "Temperature", "Fat", "ProcessTime"), '	');
        
        for(HomogenizationTD h : hgen_td) {
        	List<String> list = new ArrayList<>();
        	list.add(String.valueOf(h.getHid()));
        	list.add(String.valueOf(h.getBid()));
        	list.add(String.valueOf(h.getRunningCode()));
        	list.add(String.valueOf(h.getRunningTime()));
        	list.add(String.valueOf(h.getPressure()));
        	list.add(String.format("%.2f", h.getTemperature()));
        	list.add(String.format("%.2f", h.getFatPercentage()));
        	list.add(String.valueOf(h.getProcessingTime()));
        	
        	// Custom separator
        	CSVUtils.writeLine(writer, list, '	');
        }
        
        writer.flush();
        writer.close();
    }
    
    public static void writePackagingTD(List<PackagingTD> packaging_td) throws Exception {
    	String csvFile = "C:/path_to_file/file_name.csv";
        FileWriter writer = new FileWriter(csvFile);
        
        
        CSVUtils.writeLine(writer, Arrays.asList("PackagingID", "BatchID", "RunningCode", "RunningTime", "BatchCode", "MaterialAmount", "ProcessTime"), '	');
        
        for(PackagingTD p : packaging_td) {
        	List<String> list = new ArrayList<>();
        	list.add(String.valueOf(p.getPid()));
        	list.add(String.valueOf(p.getBid()));
        	list.add(String.valueOf(p.getRunningCode()));
        	list.add(String.valueOf(p.getRunningTime()));
        	list.add(String.valueOf(p.getBatchCode()));
        	list.add(String.valueOf(p.getMaterialAmount()));
        	list.add(String.valueOf(p.getProcessingTime()));
        	
        	// Custom separator
        	CSVUtils.writeLine(writer, list, '	');
        }
        
        writer.flush();
        writer.close();
    }
}

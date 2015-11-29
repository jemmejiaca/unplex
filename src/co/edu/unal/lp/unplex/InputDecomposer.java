package co.edu.unal.lp.unplex;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InputDecomposer {
	public static final int DECLARATIONS_SECTION = 0;
	public static final int RULES_SECTION = 1;
	public static final int JAVA_CODE_SECTION = 2;
	public static final String SECTION_DELIMITER = "%%"; 
	
	private String inputStringPath;
	private Path inputFile;
	private String[] source;
	
	public InputDecomposer(String inputStringPath) {
		this.inputStringPath = inputStringPath;
		this.inputFile = Paths.get(inputStringPath);
		this.source = new String[3];
	}
	
	public InputDecomposer() {
		this("");
	}
	
	public StringBuilder[] parseSource() {
		StringBuilder[] sourceSections = new StringBuilder[3];
		sourceSections[DECLARATIONS_SECTION] = new StringBuilder("");
		sourceSections[RULES_SECTION] = new StringBuilder("");
		sourceSections[JAVA_CODE_SECTION] = new StringBuilder("");
		String s;
		int sectionCounter = -1;
		try {
			InputStream input = new BufferedInputStream(Files.newInputStream(inputFile));
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			s = reader.readLine();
			while(s != null) {
				//System.out.println("s = " + s);
				if (s.equals(SECTION_DELIMITER)){
					sectionCounter++;
					s = reader.readLine();
				}
				
				sourceSections[sectionCounter].append(s + "\n");
				s = reader.readLine();
			}
			reader.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return sourceSections;
	}

}

package con.edu.unal.lp.unplex;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
	private final static char PROMPT_CHAR = '>';
	
	private static InputDecomposer inputDecomposer;
	private static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) {
		
		inputDecomposer = new InputDecomposer(promptFile());
		StringBuilder[] sourceSections = inputDecomposer.parseSource();
		System.out.println(sourceSections[InputDecomposer.JAVA_CODE_SECTION].toString());
		
		//**********Testing a DFA****[PLEASE DELETE ME...]
		List<Character> testAlphabet = Arrays.asList('a', 'b');
		List<State> testStates = Arrays.asList(new State(0), new State(1), new State(2), new State(3));
		List<State> testFinalStates = Arrays.asList(testStates.get(3));
		Map<State, List<State>> testTransitionTable = new HashMap<State, List<State>>(){{
			//												  a			          b
			/*q0*/put(testStates.get(0), Arrays.asList(testStates.get(1), testStates.get(0)));
			/*q1*/put(testStates.get(1), Arrays.asList(testStates.get(1), testStates.get(2)));
			/*q2*/put(testStates.get(2), Arrays.asList(testStates.get(1), testStates.get(3)));
			/*q3*/put(testStates.get(3), Arrays.asList(testStates.get(1), testStates.get(0)));
		}};
		DFA testdfa = new DFA(testAlphabet, testStates, testFinalStates, testTransitionTable);
		System.out.println(testdfa.accept("bbbab"));
		
		
	}
	
	public static String promptFile() {
		String sourceName;
		System.out.println("Please enter the name of the source file " + PROMPT_CHAR + " ");
		sourceName = keyboard.next();
		return sourceName;
	}

}

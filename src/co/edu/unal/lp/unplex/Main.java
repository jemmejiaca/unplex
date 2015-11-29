package co.edu.unal.lp.unplex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.omg.PortableServer._ServantActivatorStub;

public class Main {
	private final static char PROMPT_CHAR = '>';
	
	private static InputDecomposer inputDecomposer;
	private static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) {
		
		//inputDecomposer = new InputDecomposer(promptFile());
		//StringBuilder[] sourceSections = inputDecomposer.parseSource();
		//System.out.println(sourceSections[InputDecomposer.JAVA_CODE_SECTION].toString());
		
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
		
		//**********Testing a NFA****[PLEASE DELETE ME...]
		ArrayList<Character> _testAlphabet = new ArrayList<>(Arrays.asList('a', 'b'));
		List<State> _testStates = Arrays.asList(
				new State(0), 
				new State(1), 
				new State(2), 
				new State(3),
				new State(4),
				new State(5),
				new State(6),
				new State(7),
				new State(8),
				new State(9),
				new State(10));
		List<State> _testFinalStates = Arrays.asList(_testStates.get(10));
		Map< State, List< Set<State> > >  _testTransitionTable = 
				new HashMap<State, List< Set<State> > >(){{
			/*q0*/put(_testStates.get(0), Arrays.asList(
					NFA.EMPTY_SET, //a
					NFA.EMPTY_SET, //b
					new HashSet<State>(Arrays.asList(_testStates.get(1), _testStates.get(7))))); //lambda
			/*q1*/put(_testStates.get(1), Arrays.asList(
					NFA.EMPTY_SET, //a
					NFA.EMPTY_SET, //b
					new HashSet<State>(Arrays.asList(_testStates.get(2), _testStates.get(4))))); //lambda
			/*q2*/put(_testStates.get(2), Arrays.asList(
					new HashSet<State>(Arrays.asList(_testStates.get(3))), //a
					NFA.EMPTY_SET, 	 //b
					NFA.EMPTY_SET)); //lambda
			/*q3*/put(_testStates.get(3), Arrays.asList(
					NFA.EMPTY_SET, //a
					NFA.EMPTY_SET, //b
					new HashSet<State>(Arrays.asList(_testStates.get(6))))); //lambda
			/*q4*/put(_testStates.get(4), Arrays.asList(
					NFA.EMPTY_SET,
					new HashSet<State>(Arrays.asList(_testStates.get(5))),
					NFA.EMPTY_SET));
			/*q5*/put(_testStates.get(5), Arrays.asList(
					NFA.EMPTY_SET,
					NFA.EMPTY_SET,
					new HashSet<State>(Arrays.asList(_testStates.get(6))))); //lambda
			/*q6*/put(_testStates.get(6), Arrays.asList(
					NFA.EMPTY_SET,
					NFA.EMPTY_SET,
					new HashSet<State>(Arrays.asList(_testStates.get(1), _testStates.get(7))))); //lambda
			/*q7*/put(_testStates.get(7), Arrays.asList(
					new HashSet<State>(Arrays.asList(_testStates.get(8))),
					NFA.EMPTY_SET,
					NFA.EMPTY_SET));
			/*q8*/put(_testStates.get(8), Arrays.asList(
					NFA.EMPTY_SET,
					new HashSet<State>(Arrays.asList(_testStates.get(9))),
					NFA.EMPTY_SET));
			/*q9*/put(_testStates.get(9), Arrays.asList(
					NFA.EMPTY_SET,
					new HashSet<State>(Arrays.asList(_testStates.get(10))),
					NFA.EMPTY_SET));
			/*q10*/put(_testStates.get(10), Arrays.asList(
					NFA.EMPTY_SET,
					NFA.EMPTY_SET,
					NFA.EMPTY_SET));
			
		}};
		NFA testnfa = new NFA(_testAlphabet, _testStates, _testFinalStates, _testTransitionTable);
		Set<State> atestSet = new HashSet<>(Arrays.asList(_testStates.get(3), _testStates.get(8)));
		Set<State> lambdaClo = testnfa.lambdaClosure(atestSet);
		System.out.println(lambdaClo);
		State s0 = _testStates.get(0);
		System.out.println(testnfa.lambdaClosure(s0));
		DFA converted = testnfa.convertToDFA();
		
				
		
	}
	
	public static String promptFile() {
		String sourceName;
		System.out.println("Please enter the name of the source file " + PROMPT_CHAR + " ");
		sourceName = keyboard.next();
		return sourceName;
	}

}

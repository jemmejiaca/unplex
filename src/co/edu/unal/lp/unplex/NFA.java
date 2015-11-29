package co.edu.unal.lp.unplex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import javax.swing.plaf.FontUIResource;


public class NFA {
	public static final Character LAMBDA = new Character('_'); 
	public static final Set<State> EMPTY_SET = Collections.emptySet();
	
	private ArrayList< Character > alphabet;		// Symbols here are sorted.
	private List< State > states;			// 0 is the initial state.
	private List< State > acceptingStates;
	private Map < State, List< Set<State> > > transitionTable;
	
	public NFA(ArrayList<Character> alphabet,
			List<State> states, 
			List<State> acceptingStates, 
			Map< State, List< Set<State> > > transitionTable) {
		
		this.alphabet = alphabet;
		this.alphabet.add(LAMBDA); 
		
		this.states = states;
		this.acceptingStates = acceptingStates;
		this.transitionTable = transitionTable;
	}
	
	public NFA(String regex) {
		this.alphabet = findAlphabet(regex);
		this.alphabet.add(LAMBDA); // suspicious
		
		
	}
	
	/**
	 * Find a transition table from a regular expression.
	 * @return a transition table
	 */
	public Map <State, List<Set<State>>> findTransitionTable() {
		Map <State, List<Set<State>>> foundedTransitionTable = 
				new HashMap<>();
		
		//TODO Falata terminar esto
		
		return foundedTransitionTable;
		
	}
	
	public ArrayList<Character> findAlphabet(String regex) {
		ArrayList<Character> foundedAlphabet = new ArrayList<>();
		for (int i = 0; i < regex.length(); ++i) {
			if (!foundedAlphabet.contains(regex.charAt(i))) {
				foundedAlphabet.add(regex.charAt(i));
			}
		}
		return foundedAlphabet;
	}
	
	public Set<State> lambdaClosure(State s) {
		return lambdaClosure(new HashSet<State>(Arrays.asList(s)));
	}
	
	public Set<State> lambdaClosure(Set<State> T) {
		Set<State> result = T;
		Stack<State> stack = new Stack<>();
		for (State s : T)
			stack.push(s);
		while (!stack.isEmpty()) {
			State t = stack.pop();
			for (State u : move(new HashSet<State>(Arrays.asList(t)), LAMBDA)) {
				if (!result.contains(u)) {
					result.add(u);
					stack.push(u);
				}
			}
		}
		return result;
	}
	
	public Set<State> move(Set<State> T, Character symbol) {
		Set<State> result = new HashSet<>();
		int index = alphabet.indexOf(symbol);
		for (State s : T) {
			result.addAll(transitionTable.get(s).get(index));
		}
		return result;
	}
	
	public DFA convertToDFA() {
		Set<State> lambdaS0 = lambdaClosure(states.get(0));
		
		// This is a little dark, I know. It's just stand for Dtran[T, a] = U.  
		Map < Set<State>, List< Set<State> > > rowTransitionTable = new HashMap < Set<State>, List< Set<State> > >();
		
		ArrayList<Integer> dIntStates = new ArrayList<>();
		ArrayList<Set<State>> dSetStates = new ArrayList<>();
		ArrayList<Set<State>> unmarked = new ArrayList<>();
		dIntStates.add(0);
		dSetStates.add(lambdaS0);
		unmarked.add(lambdaS0);
		DFA dfa = new DFA();
		int index = 1;
		while (!unmarked.isEmpty()) {
			Set<State> T = unmarked.remove(0);	// mark T
			rowTransitionTable.put(T, new ArrayList<Set<State>>(alphabet.size()));
			for (Character symbol : alphabet) {
				Set<State> U = lambdaClosure(move(T, symbol));
				if (!dSetStates.contains(U)) {
					dSetStates.add(U);
					unmarked.add(U);
					dIntStates.add(index);
					index++;
				}
				rowTransitionTable.get(T).add(U);
			}
		}
		System.out.println(rowTransitionTable);
		return dfa;
	}

}

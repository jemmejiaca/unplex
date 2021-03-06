package co.edu.unal.lp.unplex;

import java.util.List;
import java.util.Map;

public class DFA {
	private String regex;
	private List<Character> alphabet;
	private List<State> states;		// It is assumed q0 as initial state.
	private List<State> acceptingStates;
	private Map<State, List<State>> transitionTable;
	
	public DFA(List<Character> alphabet, 
			List<State> states, 
			List<State> acceptingStates, 
			Map<State, List<State>> transitionTable) {
		this.alphabet = alphabet;
		this.states = states;
		this.acceptingStates = acceptingStates;
		this.transitionTable = transitionTable;
	}
	
	public DFA() {
		this(null, null, null, null);
	}
	
	public boolean accept(String string) {
		State currentState = states.get(0);
		int index = 0;
		Character c;
		while (index < string.length()) {
			c = string.charAt(index);
			currentState = move(currentState, c);
			index++;
		}
		if (acceptingStates.contains(currentState))
			return true;
		return false;
	}
	
	public State move(State state, Character symbol) {
		int index = alphabet.indexOf(symbol);
		return transitionTable.get(state).get(index);
	}

	public List<Character> getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(List<Character> alphabet) {
		this.alphabet = alphabet;
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}

	public List<State> getAcceptingStates() {
		return acceptingStates;
	}

	public void setAcceptingStates(List<State> acceptingStates) {
		this.acceptingStates = acceptingStates;
	}

	public Map<State, List<State>> getTransitionTable() {
		return transitionTable;
	}

	public void setTransitionTable(Map<State, List<State>> transitionTable) {
		this.transitionTable = transitionTable;
	}
	
}

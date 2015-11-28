package con.edu.unal.lp.unplex;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class NFA {
	public static final Character LAMBDA = ' '; 
	String regex;
	private List<Character> alphabet;
	private List<State> states;		// It is assumed q0 as initial state.
	private List<State> acceptingStates;
	private Map< State, List< Set<State> > > transitionTable;
	
	public NFA(List<Character> alphabet, 
			List<State> states, 
			List<State> acceptingStates, 
			Map< State, List< Set<State> > > transitionTable) {
		this.alphabet = alphabet;
		this.states = states;
		this.acceptingStates = acceptingStates;
		this.transitionTable = transitionTable;
	}

}

package pushdownautomaton.cc.etsii.ull.es;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class PushDownAutomaton {

	public final static String EPSYLON = "$";
	private HashMap<String, ArrayList<Transition>> automaton;
	private String actualState;
	private ArrayList<String> finalStates;
	private Alphabet inputStringAlphabet;
	private Alphabet stackAlphabet;
	private String startingState;
	private String startingStackSymbol;
	private Stack<String> stack;
	private InputString inputString;
	
	public PushDownAutomaton() {
		setAutomaton(new HashMap<String, ArrayList<Transition>>());
		setFinalStates(new ArrayList<String>());
		setInputStringAlphabet(new Alphabet());
		setStackAlphabet(new Alphabet());
		setStack(new Stack<String>());
		setStartingStackSymbol(null);
		setStartingState(null);
		
	}

	public boolean evaluateEntry() {
		/*
		 * TODO
		 */
		return false;
	}
	public boolean stateExist(String state) {
		return getAutomaton().containsKey(state);
	}
	public void addState(String newState){
		if (getAutomaton().containsKey(newState))
			throw new IllegalArgumentException("El estado " + newState + " ya existe.");
		else
			getAutomaton().put(newState, new ArrayList<Transition>());		
	}
	
	public void addFinalState(String finalState) {
		if (getFinalStates().contains(finalState))
			throw new IllegalArgumentException("El estado " + finalState + " ya es un estado final.");
		else
			getFinalStates().add(finalState);
	}
	
	public void addElementToInputAlphabet(String newElement) {
		if (getInputStringAlphabet().elementBelongsToAlphabet(newElement))
			throw new IllegalArgumentException("El elemento " + newElement + " ya forma parte del alfabeto de entrada.");
		else
			getInputStringAlphabet().addElementToAlphabet(newElement);
	}
	
	public void addElementToStackAlphabet(String newElement) {
		if (getStackAlphabet().elementBelongsToAlphabet(newElement))
			throw new IllegalArgumentException("El elemento " + newElement + " ya forma parte del alfabeto de la pila.");
		else
			getStackAlphabet().addElementToAlphabet(newElement);
	}
	
	public void addTransition(String origin, String entryToConsume, String stackSymbolToConsume, String destiny, String symbolsToPush)throws IllegalArgumentException {
		if (!stateExist(origin))
			throw new IllegalArgumentException("El elemento " + origin + " no forma parte del conjunto de estados.");
		if (!stateExist(destiny))
			throw new IllegalArgumentException("El elemento " + destiny + " no forma parte del conjunto de estados.");
		if (!getInputStringAlphabet().elementBelongsToAlphabet(entryToConsume))
			throw new IllegalArgumentException("El elemento " + entryToConsume + " no forma parte del alfabeto de entrada.");
		if (!getStackAlphabet().elementBelongsToAlphabet(stackSymbolToConsume))
			throw new IllegalArgumentException("El elemento " + stackSymbolToConsume + " no forma parte del alfabeto de la pila.");
		
		for (int i = 0; i < symbolsToPush.length(); i++) {
			if(!getStackAlphabet().elementBelongsToAlphabet(symbolsToPush.substring(i, i + 1)))
				throw new IllegalArgumentException("El elemento " + symbolsToPush.substring(i, i + 1) + " no forma parte del alfabeto de la pila.");	
		}
		
		getAutomaton().get(origin).add(new Transition(origin, destiny, entryToConsume, stackSymbolToConsume, symbolsToPush.split("")));
	}
	/**
	 **************************************** Getters y Setters.*********************
	 */
	
	public InputString getInputString() {
		return inputString;
	}
	
	public void setInputString(String input) {
		setInputString(new InputString(input));
	}
	public void setInputString(InputString inputString) {
		this.inputString = inputString;
	}

	private HashMap<String, ArrayList<Transition>> getAutomaton() {
		return automaton;
	}

	private void setAutomaton(HashMap<String, ArrayList<Transition>> automaton) {
		this.automaton = automaton;
	}

	private String getActualState() {
		return actualState;
	}

	private void setActualState(String actualState) {
		this.actualState = actualState;
	}

	public ArrayList<String> getFinalStates() {
		return finalStates;
	}

	private void setFinalStates(ArrayList<String> finalStates) {
		this.finalStates = finalStates;
	}

	private Alphabet getInputStringAlphabet() {
		return inputStringAlphabet;
	}

	private void setInputStringAlphabet(Alphabet inputStringAlphabet) {
		this.inputStringAlphabet = inputStringAlphabet;
	}

	private Alphabet getStackAlphabet() {
		return stackAlphabet;
	}

	private void setStackAlphabet(Alphabet stackAlphabet) {
		this.stackAlphabet = stackAlphabet;
	}

	public String getStartingState() {
		return startingState;
	}

	public void setStartingState(String startingState) {
		this.startingState = startingState;
		setActualState(startingState);
	}

	public String getStartingStackSymbol() {
		return startingStackSymbol;
	}

	public void setStartingStackSymbol(String startingStackSymbol) {
		this.startingStackSymbol = startingStackSymbol;
		getStack().push(startingStackSymbol);
	}

	private Stack<String> getStack() {
		return stack;
	}

	private void setStack(Stack<String> stack) {
		this.stack = stack;
	}
	
}

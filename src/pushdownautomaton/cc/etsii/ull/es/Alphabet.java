package pushdownautomaton.cc.etsii.ull.es;

import java.util.ArrayList;

public class Alphabet {
	private ArrayList<String> alphabet;
	
	
	public Alphabet(){
		setAlphabet(new ArrayList<String>());
	}
	
	public void addElementToAlphabet(String element) {
		if (!getAlphabet().contains(element))
			getAlphabet().add(element);
	}
	
	public boolean elementBelongsToAlphabet(String element){
		return getAlphabet().contains(element);
	}
	
	private ArrayList<String> getAlphabet() {
		return alphabet;
	}

	private void setAlphabet(ArrayList<String> alphabet) {
		this.alphabet = alphabet;
	}
	
}
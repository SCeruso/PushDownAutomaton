package pushdownautomaton.cc.etsii.ull.es;

import java.util.ArrayList;

public class InputString {

	private ArrayList<String> inputString;
	private int actualIndex;
	
	public InputString (String input) {
		setInputString(new ArrayList<String>());
		
		setInputString(input);
		
	}
	public void setInputString(String input) {
		for (int i = 0; i < input.length(); i++) 
			getInputString().add(input.substring(i, i + 1));
		setActualIndex(0);
	}
	public String getReadNextElement() {
		String result = getInputString().get(getActualIndex());
		
		setActualIndex(getActualIndex() + 1);
		
		return result;
	}
	
	private ArrayList<String> getInputString() {
		return inputString;
	}
	private void setInputString(ArrayList<String> inputString) {
		this.inputString = inputString;
	}
	public int getActualIndex() {
		return actualIndex;
	}
	public void setActualIndex(int actualIndex) {
		this.actualIndex = actualIndex;
	}
	
	
}

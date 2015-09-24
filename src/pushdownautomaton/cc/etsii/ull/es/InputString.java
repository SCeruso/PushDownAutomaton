package pushdownautomaton.cc.etsii.ull.es;
/**
 * @author Sabato Ceruso.
 * @email sab7093@gmail.com
 * Complejidad computacional.
 * Universidad de la Laguna, España.
 */
import java.util.ArrayList;

public class InputString {

	private ArrayList<String> inputString;		// Cadena de entrada.
	private int actualIndex;					// Indice actual de lectura de la entrada.
	
	/**
	 * Crea una cadena de entrada nueva.
	 * @param input
	 */
	public InputString (String input) {
		setInputString(new ArrayList<String>());
		
		setInputString(input);
		
	}
	/**
	 * Lee el siguiente elemento y avanza en el índice.
	 * @return	elemento leído, null si se ha llegado al final.
	 */
	public String getReadNextElement() {
		String result = null;
		if (getActualIndex() < getInputString().size()) {
			result = getInputString().get(getActualIndex());
			setActualIndex(getActualIndex() + 1);
		}
		return result;
	}
	/**
	 * Getters y Setters.
	 * @param input
	 */
	public void setInputString(String input) {
		for (int i = 0; i < input.length(); i++) 
			getInputString().add(input.substring(i, i + 1));
		setActualIndex(0);
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

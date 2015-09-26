package pushdownautomaton.cc.etsii.ull.es;
/**
 * @author Sabato Ceruso.
 * @email sab7093@gmail.com
 * Complejidad computacional.
 * Universidad de la Laguna, Espa�a.
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
	 * 
	 * @param input
	 */
	public InputString(InputString input) {
		setInputString(input.getInputString());
		setActualIndex(input.getActualIndex());
	}
	/**
	 * Verifica si se ha leido toda la cadena de entrada.
	 * @return
	 */
	public boolean entryEnded() {
		return getActualIndex() == getInputString().size();
	}
	/**
	 * Lee el siguiente elemento y avanza en el �ndice.
	 * @return	elemento le�do, null si se ha llegado al final.
	 */
	public String readNextElement() {
		String result = null;
		if (getActualIndex() < getInputString().size()) {
			result = getInputString().get(getActualIndex());
			setActualIndex(getActualIndex() + 1);
		}
		return result;
	}
	
	public String readNextElementWithoutAdvance() {
		String result = null;
		if (getActualIndex() < getInputString().size()) {
			result = getInputString().get(getActualIndex());
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

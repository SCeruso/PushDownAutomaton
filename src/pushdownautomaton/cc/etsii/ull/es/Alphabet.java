package pushdownautomaton.cc.etsii.ull.es;
/**
 * @author Sabato Ceruso.
 * @email sab7093@gmail.com
 * Complejidad computacional.
 * Universidad de la Laguna, España.
 */
import java.util.ArrayList;

public class Alphabet {
	private ArrayList<String> alphabet;				// Lista de simbolos que componen el alfabeto.
	
	/**
	 * Crea un alfabeto vacio.
	 */
	public Alphabet(){
		setAlphabet(new ArrayList<String>());
	}
	/**
	 * Añade el elemento recibido al alfabeto.
	 * @param element
	 */
	public void addElementToAlphabet(String element) {
		if (!getAlphabet().contains(element) && !element.equals(PushDownAutomaton.EPSYLON))
			getAlphabet().add(element);
	}
	/**
	 * Verifica si el elemento pertenece al alfabeto.
	 * @param element	elemento a analizar.
	 * @return			true si element pertenece al alfabeto.
	 */
	public boolean elementBelongsToAlphabet(String element){
		return getAlphabet().contains(element) || element.equals(PushDownAutomaton.EPSYLON);
	}
	/**
	 * Getters y Setters.
	 */
	private ArrayList<String> getAlphabet() {
		return alphabet;
	}

	private void setAlphabet(ArrayList<String> alphabet) {
		this.alphabet = alphabet;
	}
	
}
package pushdownautomaton.cc.etsii.ull.es;
/**
 * @author Sabato Ceruso.
 * @email sab7093@gmail.com
 * Complejidad computacional.
 * Universidad de la Laguna, España.
 */

/**
 * Clase encargada de definir un error durante el parsing.
 */
public class ParsingException extends Exception {

	public ParsingException(String m) {
		super(m);
	}
}

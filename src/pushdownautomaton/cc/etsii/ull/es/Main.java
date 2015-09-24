package pushdownautomaton.cc.etsii.ull.es;
/**
 * @author Sabato Ceruso.
 * @email sab7093@gmail.com
 * Complejidad computacional.
 * Universidad de la Laguna, España.
 */
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		try {
			PushDownAutomatonParser.parseFromFile("automatas/prueba1");
		} catch (IOException | ParsingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

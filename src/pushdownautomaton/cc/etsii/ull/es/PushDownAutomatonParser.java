package pushdownautomaton.cc.etsii.ull.es;
/**
 * @author Sabato Ceruso.
 * @email sab7093@gmail.com
 * Complejidad computacional.
 * Universidad de la Laguna, España.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class PushDownAutomatonParser {

	public final static char COMMENT = '#';							// Simbolo de inicio de comentario de linea.
	public final static String EMPTY_FINAL_STATES = "NONE";			// Simbolo para marcar que no tiene estados finales.
	private static PushDownAutomaton automaton;						// Automata a construir.
	
	/**
	 * Crea un automata a partir de un archivo.
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 * @throws ParsingException
	 */
	public static PushDownAutomaton parseFromFile(String filename)throws IOException, ParsingException {
		setAutomaton(new PushDownAutomaton());
		
		Scanner scanner = null;
		String aux;
		try {
			scanner = new Scanner(new File(filename));		
			
			aux = skipLineComments(scanner);
			aux = readStates(scanner, aux);
			aux = readSigma(scanner, aux);
			aux = readTau(scanner, aux);
			aux = readStartingSymbol(scanner, aux);
			aux = readStartingStackSymbol(scanner, aux);
			aux = readFinalStates(scanner, aux);
			while (aux != null)
				aux = readTransitions(scanner, aux);
		}
		catch(FileNotFoundException e) {
			throw new FileNotFoundException(filename + " not found.");
		}
		catch (ParsingException e) {
			System.err.println(e.getMessage());
			throw new ParsingException(e.getMessage());
		}
		finally {
			scanner.close();
		}
		return getAutomaton();
	}
	
	/**
	 * Se salta todos los comentarios y lineas vacías y devuelve la próxima línea 
	 * que no es un comentario o null si no queda nada más por leer.
	 * @param scanner
	 * @return
	 */
	private static String skipLineComments(Scanner scanner) {
		String aux = null;
		if (scanner.hasNextLine())
			do{  								
			scanner = scanner.skip("[\t\r \n]*"); 
			aux = scanner.nextLine();
			}while (scanner.hasNextLine() && aux.charAt(0) == COMMENT);						/// Nos saltamos todos los comentarios del inicio	
		return aux;
	}
	/**
	 * Parsea la línea de estados.
	 * @param scanner
	 * @param line
	 * @return
	 */
	private static String readStates(Scanner scanner, String line) {
		String states[];
		
		states = line.split("[\t ]+");
		
		for (int i = 0; i < states.length; i++) {
			if (states[i].charAt(0) == COMMENT)
				break;
			getAutomaton().addState(states[i]);
		}
		
		return skipLineComments(scanner);
	}
	/**
	 * Parsea la linea de alfabeto sigma.
	 * @param scanner
	 * @param line
	 * @return
	 */
	private static String readSigma(Scanner scanner, String line) {
		String states[];
		states = line.split("[\t ]+");
		
		for (int i = 0; i < states.length; i++) {
			if (states[i].charAt(0) == COMMENT)
				break;
			getAutomaton().addElementToInputAlphabet(states[i]);
		}
		return skipLineComments(scanner);
	}
	/**
	 * Parsea la linea de alfabeto tau.
	 * @param scanner
	 * @param line
	 * @return
	 */
	private static String readTau(Scanner scanner, String line) {		
		String states[];
		states = line.split("[\t ]+");
		
		for (int i = 0; i < states.length; i++) {
			if (states[i].charAt(0) == COMMENT)
				break;
			getAutomaton().addElementToStackAlphabet(states[i]);
		}
		return skipLineComments(scanner);
	}
	/**
	 * Parsea la linea de simbolo de arranque.
	 * @param scanner
	 * @param line
	 * @return
	 * @throws ParsingException
	 */
	private static String readStartingSymbol(Scanner scanner, String line) throws ParsingException {	
		String states[];
		states = line.split("[\t ]+");
		
		for (int i = 0; i < states.length; i++) {
			if (states[i].charAt(0) == COMMENT)
				break;
			if (getAutomaton().getStartingState() != null)
				throw new ParsingException("Solo puede haber un unico simbolo de arranque.");
			getAutomaton().setStartingState(states[i]);
		}
		return skipLineComments(scanner);
		
	}
	/**
	 * Parsea el simbolo de inicio de la pila.
	 * @param scanner
	 * @param line
	 * @return
	 * @throws ParsingException
	 */
	private static String readStartingStackSymbol(Scanner scanner, String line) throws ParsingException {
		String states[];
		states = line.split("[\t ]+");
		
		for (int i = 0; i < states.length; i++) {
			if (states[i].charAt(0) == COMMENT)
				break;
			if (getAutomaton().getStartingStackSymbol() != null)
				throw new ParsingException("Solo puede haber un unico simbolo de inicio de pila.");
			getAutomaton().setStartingStackSymbol(states[i]);
		}
		return skipLineComments(scanner);
		
	
	}
	/**
	 * Parsea la lista de estados finales, si es NONE no 
	 * añade ninguno.
	 * @param scanner
	 * @param line
	 * @return
	 * @throws ParsingException
	 */
	private static String readFinalStates(Scanner scanner, String line) throws ParsingException {
		String states[];
		states = line.split("[\t ]+");
		int i = 0;
		int j = 0;
		boolean emptyFound = false;
		
		j = getNextCommentIndex(states);
		
		for (i = 0; i < j; i++) {		
			if (states[i].equals(EMPTY_FINAL_STATES)) {
				if (!getAutomaton().getFinalStates().isEmpty())
					throw new ParsingException("Error en los estados finales");
				emptyFound = true;
			}
			else if (!getAutomaton().stateExist(states[i]))
				throw new ParsingException("El estado " + states[i] + " no existe.");
			else if (emptyFound)
				throw new ParsingException("No se puede indicar NONE si se añaden estados finales.");
			getAutomaton().addFinalState(states[i]);
		}
	
		
		return skipLineComments(scanner);
	}
	/**
	 * Parsea la siguiente linea transicion.
	 * @param scanner
	 * @param line
	 * @return
	 * @throws ParsingException
	 */
	private static String readTransitions(Scanner scanner, String line) throws ParsingException {
		
		String states[];
		states = line.split("[\t ]+");
		int j = getNextCommentIndex(states);
		
		if (states.length < 5 || j != 5)
			throw new ParsingException("Error en las transiciones");
		try {
		getAutomaton().addTransition(states[0], states[1], states[2], states[3], states[4]);
		}
		catch (IllegalArgumentException e) {
			throw new ParsingException(e.getMessage());
		}
		return skipLineComments(scanner);
	
	}
	/**
	 * Obtiene de una lista de simbolos, el siguiente
	 * el indice donde se encuentra el simbolo de comentario
	 * o el indice del ultimo elemento si no tiene.
	 * @param states
	 * @return
	 */
	private static int getNextCommentIndex(String[] states) {
		int i;
		for (i = 0; i < states.length; i++) {
			if (states[i].charAt(0) == COMMENT)
				break;
		}
		return i;
	}
	/**
	 * Getters y Setters.
	 */
	private static PushDownAutomaton getAutomaton() {
		return automaton;
	}

	private static void setAutomaton(PushDownAutomaton automaton) {
		PushDownAutomatonParser.automaton = automaton;
	}
	
}

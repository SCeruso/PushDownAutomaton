package pushdownautomaton.cc.etsii.ull.es;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class PushDownAutomatonParser {

	public final static char COMMENT = '#';
	public final static String EMPTY_FINAL_STATES = "NONE";
	private static PushDownAutomaton automaton;
	
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
	
	private static String skipLineComments(Scanner scanner) {
		String aux = null;
		if (scanner.hasNextLine())
			do{  								
			scanner = scanner.skip("[\t\r \n]*"); 
			aux = scanner.nextLine();
			}while (scanner.hasNextLine() && aux.charAt(0) == COMMENT);						/// Nos saltamos todos los comentarios del inicio	
		return aux;
	}
	
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
	private static int getNextCommentIndex(String[] states) {
		int i;
		for (i = 0; i < states.length; i++) {
			if (states[i].charAt(0) == COMMENT)
				break;
		}
		return i;
	}
	private static PushDownAutomaton getAutomaton() {
		return automaton;
	}

	private static void setAutomaton(PushDownAutomaton automaton) {
		PushDownAutomatonParser.automaton = automaton;
	}
	
}

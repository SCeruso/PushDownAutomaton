package pushdownautomaton.cc.etsii.ull.es;

public class Transition {

	private String characterToRead;
	private String stackCharToConsume;
	private String[] stackSymbolsToPush;
	private String originState;
	private String destinyState;
	
	public Transition (String origin, String destiny, String charToRead, String stackCharToConsume, String[] stackCharsToPush) {
		
		setOriginState(origin);
		setDestinyState(destiny);
		setCharacterToRead(charToRead);
		setStackCharToConsume(stackCharToConsume);
		setStackCharsToPush(stackCharsToPush);
	}
	
	public String getOriginState() {
		return originState;
	}

	public void setOriginState(String originState) {
		this.originState = originState;
	}

	public String getDestinyState() {
		return destinyState;
	}

	public void setDestinyState(String destinyState) {
		this.destinyState = destinyState;
	}

	public String getCharacterToRead() {
		return characterToRead;
	}
	public void setCharacterToRead(String characterToRead) {
		this.characterToRead = characterToRead;
	}
	public String getStackCharToConsume() {
		return stackCharToConsume;
	}
	public void setStackCharToConsume(String stackCharToConsume) {
		this.stackCharToConsume = stackCharToConsume;
	}
	public String[] getStackCharsToPush() {
		return stackSymbolsToPush;
	}
	public void setStackCharsToPush(String[] stackCharsToPush) {
		this.stackSymbolsToPush = stackCharsToPush;
	}
	
	
	
}

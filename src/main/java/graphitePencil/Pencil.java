package graphitePencil;

/**
 * @author Adrian Hernandez
 */
public class Pencil {
	private int writePoints;
	private int erasePoints;
	private int pencilLength;
	private final int defaultWritePoints;
	
	// Set paper to given string on instantiation
	Pencil(int writePoints, int erasePoints, int pencilLength){
		this.writePoints = writePoints;
		this.erasePoints = erasePoints;
		this.pencilLength = pencilLength;
		this.defaultWritePoints = writePoints;
	}

	public void writeToPaper(Paper paper, String writeText) {
		StringBuilder degradation = new StringBuilder();
		
		for(int i = 0; i < writeText.length(); i++) {
			char writeChar = writeText.charAt(i);
			characterPointWriting(degradation,writeChar);
		}
		
		String degraded = degradation.toString();
		System.out.println(degraded);
		Writer.writeToPaper(paper, degraded);
	}
	
	private void characterPointWriting(StringBuilder degradation, char writeChar) {
		char appendChar = writeChar;
		if(writePoints > 0) {
			if(Character.isLowerCase(writeChar)) {
				writePoints--;
			} else if(Character.isUpperCase(writeChar)) {
				if(writePoints > 1) {
					writePoints -= 2;
				}  else {
					// penalize for attempting to write without enough points
					// essentially zero out and place a blank space
					writePoints--;						
					appendChar = ' ';
				}
			} else if(!Character.isWhitespace(writeChar)){
				// default to one for anything else that isn't whitespace
				writePoints--;
			}
		} else {
			if(!Character.isWhitespace(appendChar)) {
				appendChar = ' ';
			}
		}
		
		degradation.append(appendChar);
	}
	
	public boolean sharpen() {
		this.writePoints = this.defaultWritePoints;
		return true;
	}

	public int getWritePoints() {
		return this.writePoints;
	}
	
	public int getPencilLength() {
		return this.pencilLength;
	}

	public int getErasePoints() {
		return this.erasePoints;
	}
	


}

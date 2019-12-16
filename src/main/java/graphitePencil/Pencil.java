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
		StringBuilder degradationWriteText = new StringBuilder();
		
		// Create a "degrading" version of writeText while tracking writePoints
		for(int i = 0; i < writeText.length(); i++) {
			char writeChar = writeText.charAt(i);
			characterPointWriting(degradationWriteText,writeChar);
		}
		
		// Use "degraded" writeText on paper
		String degradedWriteText = degradationWriteText.toString();
		Writer.writeToPaper(paper, degradedWriteText);
	}
	
	public void editOnPaper(Paper paper, String replacementText, int startIndex) {
		StringBuilder degradationReplacementText = new StringBuilder();
		String paperText = paper.getText();
		int paperLength = paperText.length();
		
		// Create a "degrading" version of replacementText while tracking writePoints 
		// and inserting '@' where necessary collisions
		for(int i = 0; i < replacementText.length(); i++) {
			char replaceChar = replacementText.charAt(i);
			if(i + startIndex >= paperLength ) {
				// avoid IndexOutOfBounds error and handle overflow text
				characterPointWriting(degradationReplacementText,replaceChar);
			} else {
				char paperChar = paperText.charAt(startIndex + i);
				if(!Character.isWhitespace(paperChar)) {
					// Write a space or @ symbol for collision
					collisionPointWriting(degradationReplacementText);
				} else {
					// regular point processing with whitespace preservation
					characterPointWriting(degradationReplacementText,replaceChar);
				}
			}
		}
		
		String degradedReplacementText = degradationReplacementText.toString();
		Editor.editOnPaper(paper, degradedReplacementText, startIndex);
	}
	
	public void eraseFromPaper() {
		
	}
	
	private void characterPointWriting(StringBuilder degradation, char writeChar) {
		char appendChar = writeChar;
		if(writePoints > 0) {
			if(Character.isUpperCase(writeChar)) {
				if(writePoints > 1) {
					writePoints -= 2;
				}  else {
					// Any failed attempts cost a point
					// essentially zero out and place a blank space
					writePoints--;						
					appendChar = ' ';
				}
			} else if(!Character.isWhitespace(writeChar)){
				// default to one for anything else that isn't whitespace
				writePoints--;
			}
		} else {
			// no points: preserve white space when possible, blanks for all else
			if(!Character.isWhitespace(appendChar)) {
				appendChar = ' ';
			}
		}
		
		degradation.append(appendChar);
	}
	
	private void collisionPointWriting(StringBuilder degradation) {
		// Collisions will count for 1 point, Any failed attempts cost a point
		if(writePoints > 0) {
			degradation.append('@');
			writePoints--;
		} else {
			degradation.append(' ');
		}
	}
	
	public boolean sharpen() {
		if(pencilLength > 0) {
			// Only reset if we still have length on pencil
			writePoints = defaultWritePoints;
			pencilLength--;
			
			// notify user of successful sharpening
			return true;			
		} else {
			// notify user of failed sharpening
			return false;
		}
	}

	public int getWritePoints() {
		return writePoints;
	}

	public int getErasePoints() {
		return erasePoints;
	}
	
	public int getPencilLength() {
		return pencilLength;
	}
	
	public int getDefaultWritePoints() {
		return defaultWritePoints;
	}

}

package graphitePencil;

/**
 * @author Adrian Hernandez
 */
public class Pencil extends AbstractPencil {
	private Erasable eraser = ErasableFactory.getErasable(Erasables.Eraser);
	
	public Pencil(int writePoints, int erasePoints, int pencilLength){
		super(writePoints,erasePoints,pencilLength);
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
				if(!Character.isWhitespace(paperChar) && !Character.isWhitespace(replaceChar)) {
					// write a space or @ symbol for collision
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
	
	public void eraseFromPaper(Paper paper, String eraseText) {
		int erasableCharacters = characterPointErasing(eraseText);
		eraser.eraseFromPaper(paper, eraseText, erasableCharacters);
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
	
	private int characterPointErasing(String text) {
		// Get number of non white-space characters
		int rightPortion = text.replaceAll("\\s+", "").length();
		
		if(erasePoints >= rightPortion) {
			erasePoints -= rightPortion;
		} else {
			rightPortion = erasePoints;
			erasePoints = 0;
		}

		return rightPortion;
	}
}

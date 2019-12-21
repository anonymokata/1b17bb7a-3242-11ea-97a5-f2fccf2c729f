package pencil;

import eraser.AbstractErasable;
import editor.Editable;
import writer.Writable;
import paper.Paperable;

// I believe this should not be public because it only provides customization
// for different implementations, but more importantly, it does not break from
// or add anything from Pencilable in the public scope.
abstract class AbstractPencil
	implements Pencilable {
	
	// it could be beneficial to to have access to this in all subclasses
	protected int writePoints;
	protected int erasePoints;
	protected int pencilLength;
	protected final int defaultWritePoints;
	
	// Dull Point writing
	DullStyle writingWithNoPoints;
	
	// These are for modular customization of OUR pencil logic.
	// since this is for our logic simplification, keep it in the package
	AbstractErasable eraser;
	Editable editor;
	Writable writer;
	
	// sets the inital value and default value of write points at same time
	public AbstractPencil(int writePoints, int erasePoints, int pencilLength){
		this.writePoints = writePoints;
		this.erasePoints = erasePoints;
		this.pencilLength = pencilLength;
		this.defaultWritePoints = writePoints;
	}
	
	public AbstractPencil(int initialWritePoints, int erasePoints, int pencilLength, int defaultWritePoints) {
		this.writePoints = initialWritePoints;
		this.erasePoints = erasePoints;
		this.pencilLength = pencilLength;
		this.defaultWritePoints = defaultWritePoints;
	}
	
	public void writeToPaper(Paperable paper, String writeText) {
		StringBuilder degradationWriteText = new StringBuilder();
		
		// Create a "degrading" version of writeText while tracking writePoints
		for(int i = 0; i < writeText.length(); i++) {
			char writeChar = writeText.charAt(i);
			characterPointWriting(degradationWriteText,writeChar);
		}
		
		// Use "degraded" writeText on paper
		String degradedWriteText = degradationWriteText.toString();
		writer.writeToPaper(paper, degradedWriteText);
	}
	
	public boolean editOnPaper(Paperable paper, String replacementText, int startIndex) {
		StringBuilder degradationReplacementText = new StringBuilder();
		StringBuilder overflowText = new StringBuilder();
		String paperText = paper.getText();
		int paperLength = paperText.length();
		
		// Create a "degrading" version of replacementText while tracking writePoints 
		// and inserting '@' where necessary collisions
		for(int i = 0; i < replacementText.length(); i++) {
			char replaceChar = replacementText.charAt(i);
			if(i + startIndex >= paperLength ) {
				// avoid IndexOutOfBounds error and handle overflow text
				characterPointWriting(overflowText,replaceChar);
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
		boolean edited = editor.editOnPaper(paper, degradedReplacementText, startIndex);
		writer.writeToPaper(paper, overflowText.toString());
		
		return edited;
	}
	
	public boolean eraseFromPaper(Paperable paper, String eraseText) {
		int erasableCharacters = characterPointErasing(eraseText);
		return eraser.eraseFromPaper(paper, eraseText, erasableCharacters);
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
	
	
	// These are helper methods process points and strings for the system 
	// during major events such as collisions, writing, and erasing
	// Use only for within the package to create different versions of the "same" 
	// pencil while following the same structure
	void characterPointWriting(StringBuilder degradation, char writeChar) {
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
			appendChar = dullPointWriting(writeChar);
		}
		
		degradation.append(appendChar);
	}
	
	void collisionPointWriting(StringBuilder degradation) {
		// Collisions will count for 1 point, Any failed attempts cost a point
		if(writePoints > 0) {
			degradation.append('@');
			writePoints--;
		} else {
			degradation.append(' ');
		}
	}
	
	int characterPointErasing(String text) {
		int spaceAdjusted = 0;
		
		// Get number of non white-space characters
		int rightPortion = text.replaceAll("\\s+", "").length();
		if(erasePoints >= rightPortion) {
			erasePoints -= rightPortion;
		} else {
			rightPortion = erasePoints;
			erasePoints = 0;
		}
		
		// find number of characters from including whitespace
		// while considering how many non-white space chars we can erase
		for(int i = text.length() - 1; i >= 0 && rightPortion > 0; i--) {
			if(!Character.isWhitespace(text.charAt(i))){
				rightPortion--;
			}
			spaceAdjusted++;
		}
		
		return spaceAdjusted;
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
	
	
	// Dull Point processing
	DullStyle getDullStyle() {
		return writingWithNoPoints;
	}
	
	public Pencilable setDullStyle(DullStyle style) {
		writingWithNoPoints = style;
		return this;
	}
	
	char dullPointWriting(char writeChar) {
		if(Character.isWhitespace(writeChar) 
				&& getDullStyle() == DullStyle.WhiteSpaceCompatible) {
			return writeChar;
		} else {
			return ' ';
		}
	}
}

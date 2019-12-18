package graphitePencil;

import editor.Editable;
import editor.Editor;
import eraser.Erasable;
import paper.Paperable;
import writer.Writable;
import writer.Writer;

public abstract class AbstractPencil {
	int writePoints;
	int erasePoints;
	int pencilLength;
	final int defaultWritePoints;
	
	Erasable eraser;
	Editable editor;
	Writable writer;
	
	public AbstractPencil(int writePoints, int erasePoints, int pencilLength){
		this.writePoints = writePoints;
		this.erasePoints = erasePoints;
		this.pencilLength = pencilLength;
		this.defaultWritePoints = writePoints;
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
	
	public void editOnPaper(Paperable paper, String replacementText, int startIndex) {
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
		editor.editOnPaper(paper, degradedReplacementText, startIndex);
	}
	
	public void eraseFromPaper(Paperable paper, String eraseText) {
		int erasableCharacters = characterPointErasing(eraseText);
		eraser.eraseFromPaper(paper, eraseText, erasableCharacters);
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
	
	
	// These methods process points and strings for the system  in
	// major events such as collisions, writing, and erasing
	abstract void characterPointWriting(StringBuilder degradation, char writeChar);
	abstract void collisionPointWriting(StringBuilder degradation);
	abstract int characterPointErasing(String text);
	
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

package pencil;

import eraser.AbstractErasable;
import editor.Editable;
import writer.Writable;
import paper.Paperable;

/** AbstractPencil is a base implementation that is used by all classes within
 * the pencil package. It defines many of the variables and methods that are 
 * used in modeling a graphite pencil which helps centralize most of the logic
 * for concrete pencilables found in this package. All the concrete pencilable
 * classes vary mostly in whitespace handling.
 * 
 * IMPORTANT NOTES: 
 * I believe this should not be public because it only provides customization 
 * for different implementations, but more importantly, it does not break from
 * or add anything from Pencilable in the public scope.                       
 * @author Adrian Hernandez
 */
abstract class AbstractPencil
	implements Pencilable {
	
	// it could be beneficial to have access to these in ALL subclasses
	// including those outside of the package extending the concrete classes
	protected int writePoints;
	protected int erasePoints;
	protected int pencilLength;
	protected final int defaultWritePoints;
	
	// Dull Point writing
	DullStyle writingWithNoPoints;
	
	// These are for modular customization of OUR pencil logic
	// to edit these, use CustomizablePencils instead.
	AbstractErasable eraser;
	Editable editor;
	Writable writer;
	
	/** This constructor initializes the degradation values as specified, while
	 * setting defaultWritePoints and writePoints to the same value
	 * @param writePoints the default and initialization for write points of pencil
	 * @param erasePoints the default and initialization for erase points of pencil
	 * @param pencilLength the amount of times pencil can be sharpened             
	 */
	public AbstractPencil(int writePoints, int erasePoints, int pencilLength){
		this.writePoints = writePoints;
		this.erasePoints = erasePoints;
		this.pencilLength = pencilLength;
		this.defaultWritePoints = writePoints;
	}
	
	/** This constructor initializes the degradation values as specified
	 * @param writePoints initialization for write points of pencil                      
	 * @param erasePoints the default and initialization for erase points of pencil      
	 * @param pencilLength the amount of times pencil can be sharpened                   
	 * @param defaultWritePoints the default value writePoints is set to after sharpening
	 */
	public AbstractPencil(int initialWritePoints, int erasePoints, int pencilLength, int defaultWritePoints) {
		this.writePoints = initialWritePoints;
		this.erasePoints = erasePoints;
		this.pencilLength = pencilLength;
		this.defaultWritePoints = defaultWritePoints;
	}
	
	@Override
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
	
	@Override
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
	
	@Override
	public boolean eraseFromPaper(Paperable paper, String eraseText) {
		int erasableCharacters = characterPointErasing(eraseText);
		return eraser.eraseFromPaper(paper, eraseText, erasableCharacters);
	}
	
	@Override
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
	
	
	/* characterPointWriting, characterPointErasing, collisionPointWriting
	* These are helper methods process points and strings for the system 
	* during major events such as collisions, writing, and erasing.
	* Used only within the package to create different versions of the "same" 
	* pencil while keeping the same structure */
	
	/** 
	 * @param degradation the StringBuilder to add new 'degraded' character
	 * @param writeChar the character to go through degradation logic
	 */
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
	
	/**
	 * @param degradation
	 */
	void collisionPointWriting(StringBuilder degradation) {
		// Collisions will count for 1 point, Any failed attempts cost a point
		if(writePoints > 0) {
			degradation.append('@');
			writePoints--;
		} else {
			degradation.append(' ');
		}
	}
	
	/**
	 * @param text
	 * @return
	 */
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
	
	/* These are get methods. These values should NOT be edited directly, therefore
	 * there are no setter methods. */
	
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
	
	
	/* Dull Point processing for whitespace */
	
	
	/**
	 * @return
	 */
	public DullStyle getDullStyle() {
		return writingWithNoPoints;
	}
	
	/**
	 * @param writeChar
	 * @return
	 */
	char dullPointWriting(char writeChar) {
		if(Character.isWhitespace(writeChar) 
				&& getDullStyle() == DullStyle.WhiteSpaceCompatible) {
			return writeChar;
		} else {
			return ' ';
		}
	}
}

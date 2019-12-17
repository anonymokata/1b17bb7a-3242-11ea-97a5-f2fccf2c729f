package graphitePencil;

import eraser.ErasableFactory;

/**
 * @author Adrian Hernandez
 */
public class Pencil extends AbstractPencil {
	public Pencil(int writePoints, int erasePoints, int pencilLength){
		super(writePoints,erasePoints,pencilLength);
		eraser = ErasableFactory.getErasable(Erasables.Eraser);
	}
	
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
			// no points: preserve white space when possible, blanks for all else
			if(!Character.isWhitespace(appendChar)) {
				appendChar = ' ';
			}
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

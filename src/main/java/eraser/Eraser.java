package eraser;

import paper.Paperable;

/** Eraser Implementation:
 * Erases replaces all characters with a blank space, but will
 * leave existing white space, such as new lines, in paper alone.
 * 
 * All whitespace will not count towards the boolean return value.
 * @author Adrian Hernandez
 */
public class Eraser extends AbstractEraser {

	/** eraseFromPaper (indexed) Replaces all non-white space characters
	 * with a blank space. White space in paper is preserved as it was and
	 * will not be modified by the eraser.
	 * @param paper The instance of paper that should be edited
	 * @param startIndex The startIndex to which begin erasing from
	 * @param endIndex The endIndex to stop erasing at, not inclusive.
	 * @return true if any character was replaced in paper
	 */
	@Override
	boolean eraseFromPaper(Paperable paper, int startIndex, int endIndex) {
		// Erase by replacing with spaces using indexes [Inclusive,Exclusive)
		// Existing white space is preserved
		StringBuilder eraseText = new StringBuilder(paper.getText());
		
		boolean erased = false;
		for(int i = startIndex; i < endIndex; i++) {
			char currentChar = eraseText.charAt(i);
			if(!Character.isWhitespace(currentChar)) {
				eraseText.setCharAt(i, ' ');
				erased = true;
			}
		}
		
		String erasedPaper = eraseText.toString();
		paper.setText(erasedPaper);
		return erased;
	}
}

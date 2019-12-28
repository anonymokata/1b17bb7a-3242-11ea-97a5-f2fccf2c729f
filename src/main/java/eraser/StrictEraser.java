package eraser;

import paper.Paperable;

/** StrictEraser Implementation:
 * StrictEraser replaces all characters with a blank space, destroying
 * any existing whitespace the already existed.
 * 
 * Only blank space will not count towards the boolean return value.
 * @author Adrian Hernandez
 */
public class StrictEraser extends AbstractEraser {
	
	/** eraseFromPaper (indexed) Replaces all character with a blank space. 
	 * Existing white space, such as new lines, in paper is destroyed.
	 * @param paper The instance of paper that should be edited
	 * @param startIndex The startIndex to which begin erasing from
	 * @param endIndex The endIndex to stop erasing at, not inclusive.
	 * @return true if any character was replaced in paper
	 */
	@Override
	boolean eraseFromPaper(Paperable paper, int startIndex, int endIndex) {
		// Erase by replacing with spaces using indexes [Inclusive,Exclusive)
		// Existing white space on paper is NOT preserved
		StringBuilder eraseText = new StringBuilder(paper.getText());
		
		boolean erased = false;
		for(int i = startIndex; i < endIndex; i++) {
			char currentChar = eraseText.charAt(i);
			if(currentChar != ' ') {
				eraseText.setCharAt(i, ' ');
				erased = true;
			}
		}
		
		String erasedPaper = eraseText.toString();
		paper.setText(erasedPaper);
		return erased;
	}

}

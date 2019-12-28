package eraser;

import paper.Paperable;

/** AbstractEraser is an implementation used within this package that centralizes
 * all repeated structure and code between StrictEraser and Eraser for DRY's sake.
 * The difference was from the whitespace handling in eraseFromPaper (indexed). 
 * 
 * IMPORTANT NOTES:
 * I closed off this class from any further modification given that
 * AbstractErasable implementations rely on eraseFromPaper (indexed), so I
 * removed the public modifier to make it package protected. It's really just
 * specific to the package. I then added a public interface so that we are 
 * still open to extensibility. Should I make my concrete Erasers final?
 * @author Adrian Hernandez
 */
abstract class AbstractEraser implements AbstractErasable {
	/** eraseFromPaper will remove the last index of desiredText
	 * found in paper.
	 * @param paper The instance of paper that should be erased
	 * @param desiredText The desiredText that should be erased
	 * @return return true if ANY character has been erased
	 */
	@Override
	public boolean eraseFromPaper(Paperable paper, String desiredText) {
		// Find last index, returning false if desiredText not found
		int erasureStart = paper.getText().lastIndexOf(desiredText);
		if (erasureStart == -1) {return false;}
		int erasureEnd = erasureStart + desiredText.length();
		
		// Reuse of a method that can also be modified if needed for
		// DRY principle's sake.
		return eraseFromPaper(paper,erasureStart,erasureEnd);
	}
	
	/** eraseFromPaper (portioned) should only erase portion characters counting 
	 * from the right of the last index of desiredText
	 * @param paper The instance of paper that should be edited
	 * @param desiredText The last index of desiredText to erase from paper
	 * @param portion left to right erasing of text, stop at portion characters 
	 * counting from the right
	 * @return true if any character was replaced in paper
	 */
	@Override
	public boolean eraseFromPaper(Paperable paper, String desiredText, int portion) {
		// Find last index, returning false if desiredText not found
		int erasureStart = paper.getText().lastIndexOf(desiredText);
		if (erasureStart == -1) {return false;}
		int erasureEnd = erasureStart + desiredText.length();
		
		// this takes into account right to left degradation
		erasureStart += desiredText.length() - portion;
		return eraseFromPaper(paper,erasureStart,erasureEnd);
	}
	
	/** eraseFromPaper (indexed) will always erase the specified range of text from 
	 * the paper, not including the endIndex. [Inclusive,Exclusive)
	 * 
	 * NOTE: This is for customization within this package only. (IE not public or protected)
	 * I have debated whether or not to add this to an interface or make it public, but have 
	 * not had a specific need to. It works for the existing structure and I still 
	 * pass my unit tests soooooo ....  *shrug*
	 * 
	 * @param paper The instance of paper that should be edited
	 * @param startIndex The startIndex to which begin erasing from
	 * @param endIndex The endIndex to stop erasing at, not inclusive.
	 * @return true if any character was replaced in paper
	 */
	abstract boolean eraseFromPaper(Paperable paper, int startIndex, int endIndex);
}

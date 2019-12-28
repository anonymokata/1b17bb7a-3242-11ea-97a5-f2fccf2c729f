package eraser;

import paper.Paperable;

/** AbstractErasable has one more required method that enables left to right
 * degraded erasing. The reason I moved this to a separate method was to
 * ensure that the text that would have been matched by Erasable.eraseFromPaper
 * is the same. If we had: 
 * 1.) original desiredText such as "I am winner"
 * 2.) only enough points to erase the "winner" portion
 * 3.) in a paper with text "I am a winner and a winner I shall be."
 * then Erase.eraseFromPaper(paper,"winner") would not erase that first winner
 * 
 * Therefore, I had to make a new method that is able to handle this situation
 * when having degradation. Woe is me.
 * @author Adrian Hernandez
 */
public interface AbstractErasable extends Erasable {
	/** eraseFromPaper (portioned) should only erase portion characters counting 
	 * from the right of the last index of desiredText
	 * @param paper The instance of paper that should be edited
	 * @param desiredText The last index of desiredText to erase from paper
	 * @param portion left to right erasing of text, stop at portion characters 
	 * counting from the right
	 * @return true if any character was replaced in paper
	 */
	boolean eraseFromPaper(Paperable paper, String desiredText, int portion);
}

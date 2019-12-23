package eraser;

import paper.Paperable;

/** Erasables in the eraser package will erase the last index of the
 * desired text found in paper. Paper's white space handling is 
 * implemented differently between Erasables.
 * 
 * IMPORTANT NOTES:
 * For eraser package, all Erasers desiredText must be exactly the 
 * same as the text on paper for a match.
 * 
 * StrictEraser is part of the space-only set so it will not process
 * be kind to newlines and replace those with spaces. This is good
 * for other types of pencils if wanting to reset everything.
 * @author Adrian Hernandez
 */
public interface Erasable {
	/** eraseFromPaper will remove the last index of desiredText
	 * found in paper.
	 * @param paper The instance of paper that should be erased
	 * @param desiredText The desiredText that should be erased
	 * @return return true if ANY character has been erased
	 */
	boolean eraseFromPaper(Paperable paper, String desiredText);
}

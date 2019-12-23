package writer;

import paper.Paperable;

/** Writables in the writer package will only append text to paper. 
 * Strict writer will NOT allow special white to be written to paper
 * and convert them to spaces instead.
 * @author Adrian Hernandez
 */
public interface Writable {
	/** writeToPaper appends newText to paper
	 * @param paper The instance of paper that should be edited
	 * @param newText The text to APPEND to paper's text
	 */
	public void writeToPaper(Paperable paper, String newText);
}

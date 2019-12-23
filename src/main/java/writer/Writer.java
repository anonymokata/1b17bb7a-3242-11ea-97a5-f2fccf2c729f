package writer;

import paper.Paperable;


/** Writer Implementation:
 * Writer accepts all text and all whitespace as-is and APPENDS
 * text to paper.
 * @author Adrian Hernandez
 */
public class Writer implements Writable {
	
	/** writeToPaper appends newText to paper as-is
	 * @param paper The instance of paper that should be edited
	 * @param newText The text to APPEND to paper's text
	 */
	@Override
	public void writeToPaper(Paperable paper, String newText) {
		String paperText = paper.getText();
		paperText += newText;
		paper.setText(paperText);
	}
}

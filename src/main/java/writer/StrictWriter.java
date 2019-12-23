package writer;

import paper.Paperable;

/** StrictWriter Implementation:
 * StrictWriter will replace all incoming forms of whitespace 
 * with a blank space. This is 1 for 1 replacmenet, meaning 
 * that 'Apple\n\n\tPen' would result in 'Apple   Pen'.
 * 
 * IMPORTANT NOTE:
 * StrictWriter will NOT affect the existing text. StrictWriter
 * will only affect and cleanse incoming text.
 * @author Adrian Hernandez
 */
public class StrictWriter implements Writable {

	/** writeToPaper appends newText to paper, replace special 
	 * whitespace from income
	 * @param paper The instance of paper that should be edited
	 * @param newText The text to APPEND to paper's text
	 */
	@Override
	public void writeToPaper(Paperable paper, String newText) {
		String paperText = paper.getText();
		paperText += newText.replaceAll("\\s", " ");
		paper.setText(paperText);
	}

}

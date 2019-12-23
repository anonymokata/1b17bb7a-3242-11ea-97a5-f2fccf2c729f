package paper;

/** Representing a paper that holds text
 * @author Adrian Hernandez
 */
public interface Paperable {
	
	/** Incoming text will completely replace this paper's text
	 * @param text The new text to replace into Paper
	 */
	public void setText(String text);
	
	/** Get the entire text of the paper
	 * @return 
	 */
	public String getText();
}

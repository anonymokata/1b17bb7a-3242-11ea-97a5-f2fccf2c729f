package paper;

/** StrictPaper Implementation:
 * StrictPaper converts all whitespace to blank spaces and then
 * APPENDS the incoming text to paper.
 * @author Adrian Hernandez
 *
 */
public class StrictPaper implements Paperable {
	/**
	 *  Represents ALL the text in the paper
	 */
	private String text;
	
	
	/**
	 * Initialize with an empty string
	 */
	public StrictPaper(){
		this.setText("");
	}
	
	/** Initialize paper with text
	 * @param text - to be assigned to paper's text role using setText logic
	 */
	public StrictPaper(String text){
		this.setText(text);
	}

	/** Get ALL the text in paper
	 * @return the text
	 */
	@Override
	public String getText() {
		return text;
	}

	/** setText will replace all whitespace with blank spaces and set the 
	 * resulting text to be the paper's text
	 * @param text text will have all whitespace converted to blank spaces
	 * and then completely replace this paper's text
	 */
	@Override
	public void setText(String text) {
		this.text = text.replaceAll("\\s", " ");
	}
	
}

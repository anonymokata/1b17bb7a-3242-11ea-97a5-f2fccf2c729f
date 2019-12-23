package paper;

/** Concrete paper implementation that accepts all text and all whitespace
 * @author Adrian Hernandez
 * A paper's text should only be set and retreived. No fancy function
 * should exist. That should be kept with the Pencil's utilities
 */
public class Paper implements Paperable {
	/**
	 *  Represents ALL the text in the paper
	 */
	private String text;
	
	/**
	 * Initialize with an empty string
	 */
	public Paper(){
		this.setText("");
	}
	
	/** Initialize paper with text
	 * @param text to be assigned as paper's text role using setText logic
	 */
	public Paper(String text){
		this.setText(text);
	}

	/** Get ALL the text in paper
	 * @return the text
	 */
	@Override
	public String getText() {
		return text;
	}

	/** Replace this paper's text with incoming text using only blank space for white space
	 * @param text all whitespace converted to blank spaces (1-for-1character) and then 
	 * completely replaces this paper's text
	 */
	@Override
	public void setText(String text) {
		this.text = text;
	}	
}

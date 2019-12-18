package paper;

/**
 * @author Adrian Hernandez
 * A paper's text should only be set and retreived. No fancy function
 * should exist. That should be kept with the Pencil's utilities
 */
public class Paper implements Paperable {
	private String text;
	
	public Paper(){
		this.setText("");
	}
	
	public Paper(String text){
		this.setText(text);
	}

	/**
	 * @return the text
	 */
	@Override
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	@Override
	public void setText(String text) {
		this.text = text;
	}	
}

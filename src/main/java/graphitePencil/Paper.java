package graphitePencil;

/**
 * @author Adrian Hernandez
 * A paper's text should only be set and retreived. No fancy function
 * should exist. That should be kept with the Pencil's utilities
 */
public class Paper {
	private String text;
	
	Paper(){
		this.setText("");
	}
	
	Paper(String text){
		this.setText(text);
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}	
}

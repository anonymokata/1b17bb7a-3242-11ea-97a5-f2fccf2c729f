package paper;

public class StrictPaper implements Paperable {
	private String text;
	
	public StrictPaper(){
		this.setText("");
	}
	
	public StrictPaper(String text){
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
		this.text = text.replaceAll("\\s", " ");
	}
	
}

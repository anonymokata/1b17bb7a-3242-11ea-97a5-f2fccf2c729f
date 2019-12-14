package graphitePencil;

public class Eraser {
	static public boolean eraseFromPaper(Paper paper, String desiredText) {
		StringBuilder paperText = new StringBuilder(paper.getText());
		
		// Find last index and number of spaces to be used
		int erasureLength = desiredText.length();
		int erasureIndex = paperText.lastIndexOf(desiredText);
		
		// Return false if desiredText not found
		if(erasureIndex == -1) {
			return false;
		}
		
		// Erase by replacing with spaces
		for(int i = erasureIndex; i < erasureLength; i++) {
			paperText.setCharAt(i, ' ');
		}
		
		String erasedPaper = paperText.toString();
		paper.setText(erasedPaper);
		return true;
	}
}
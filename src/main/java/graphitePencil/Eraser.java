package graphitePencil;

public class Eraser {
	static public boolean eraseFromPaper(Paper paper, String desiredText) {
		// Find last index, returning false if desiredText not found
		int erasureStart = paper.getText().lastIndexOf(desiredText);
		if (erasureStart == -1) {return false;}
		int erasureEnd = erasureStart + desiredText.length();
		
		return eraseFromPaper(paper,erasureStart,erasureEnd);
	}
	
	static public boolean eraseFromPaper(Paper paper, String desiredText, int portion) {
		// Find last index, returning false if desiredText not found
		int erasureStart = paper.getText().lastIndexOf(desiredText);
		if (erasureStart == -1) {return false;}
		int erasureEnd = erasureStart + desiredText.length();
		
		// this takes into account right to left degradation
		erasureStart += desiredText.length() - portion;
		
		
		return eraseFromPaper(paper,erasureStart,erasureEnd);
	}
	
	static public boolean eraseFromPaper(Paper paper, int startIndex, int endIndex) {
		// Erase by replacing with spaces using indexes [Inclusive,Exclusive)
		// Preserves existing white space
		StringBuilder eraseText = new StringBuilder(paper.getText());
		
		boolean erased = false;
		for(int i = startIndex; i < endIndex; i++) {
			char currentChar = eraseText.charAt(i);
			if(!Character.isWhitespace(currentChar)) {
				eraseText.setCharAt(i, ' ');
				erased = true;
			}
		}
		
		String erasedPaper = eraseText.toString();
		paper.setText(erasedPaper);
		return erased;
	}
	
	/* Strict Processing: Eraser will replace newline,tabs,etc with a single space */
	static public boolean eraseFromPaperStrict(Paper paper, String desiredText) {
		// Find last index, returning false if desiredText not found
		int erasureStart = paper.getText().lastIndexOf(desiredText);
		if (erasureStart == -1) {return false;}
		int erasureEnd = erasureStart + desiredText.length();
		
		return eraseFromPaperStrict(paper,erasureStart,erasureEnd);
	}
	
	static public boolean eraseFromPaperStrict(Paper paper, String desiredText, int portion) {
		// Find last index, returning false if desiredText not found
		int erasureStart = paper.getText().lastIndexOf(desiredText);
		if (erasureStart == -1) {return false;}
		int erasureEnd = erasureStart + desiredText.length();
		
		// this takes into account right to left degradation
		erasureStart += desiredText.length() - portion;
		
		
		return eraseFromPaperStrict(paper,erasureStart,erasureEnd);
	}
	
	static public boolean eraseFromPaperStrict(Paper paper, int startIndex, int endIndex) {
		// Erase by replacing with spaces using indexes [Inclusive,Exclusive)
		// Existing white space on paper is NOT preserved
		StringBuilder eraseText = new StringBuilder(paper.getText());
		
		boolean erased = false;
		for(int i = startIndex; i < endIndex; i++) {
			char currentChar = eraseText.charAt(i);
			if(currentChar != ' ') {
				eraseText.setCharAt(i, ' ');
				erased = true;
			}
		}
		
		String erasedPaper = eraseText.toString();
		paper.setText(erasedPaper);
		return erased;
	}
}

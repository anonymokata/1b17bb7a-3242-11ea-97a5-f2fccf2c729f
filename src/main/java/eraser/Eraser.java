package eraser;

import graphitePencil.Paper;

public class Eraser extends Erasable {

	public boolean eraseFromPaper(Paper paper, int startIndex, int endIndex) {
		// Erase by replacing with spaces using indexes [Inclusive,Exclusive)
		// Existing white space is preserved
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
}

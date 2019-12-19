package eraser;

import paper.Paperable;

public class StrictEraser extends AbstractEraser {
	
	// replace everything on paper with spaces
	// white space on paper NOT preserved
	@Override
	boolean eraseFromPaper(Paperable paper, int startIndex, int endIndex) {
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

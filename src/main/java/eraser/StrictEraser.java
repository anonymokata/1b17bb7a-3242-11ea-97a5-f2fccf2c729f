package eraser;

import paper.Paper;

public class StrictEraser extends Erasable {
	
	@Override
	public boolean eraseFromPaper(Paper paper, int startIndex, int endIndex) {
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

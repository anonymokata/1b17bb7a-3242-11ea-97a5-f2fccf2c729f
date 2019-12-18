package eraser;

import paper.Paperable;

public abstract class AbstractEraser implements Erasable {
	public boolean eraseFromPaper(Paperable paper, String desiredText) {
		// Find last index, returning false if desiredText not found
		int erasureStart = paper.getText().lastIndexOf(desiredText);
		if (erasureStart == -1) {return false;}
		int erasureEnd = erasureStart + desiredText.length();
		
		return eraseFromPaper(paper,erasureStart,erasureEnd);
	}
	
	public boolean eraseFromPaper(Paperable paper, String desiredText, int portion) {
		// Find last index, returning false if desiredText not found
		int erasureStart = paper.getText().lastIndexOf(desiredText);
		if (erasureStart == -1) {return false;}
		int erasureEnd = erasureStart + desiredText.length();
		
		// this takes into account right to left degradation
		erasureStart += desiredText.length() - portion;
		
		
		return eraseFromPaper(paper,erasureStart,erasureEnd);
	}
	
	abstract public boolean eraseFromPaper(Paperable paper, int startIndex, int endIndex);
}

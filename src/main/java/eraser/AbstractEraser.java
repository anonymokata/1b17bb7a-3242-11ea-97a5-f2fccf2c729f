package eraser;

import paper.Paperable;

// This is my specific implementation that I need to use at AbstractPencil
// I still wanted the erasing to be separate even if it cost me another
// class/interface because of a necessary edit function.

// In order to close AbstractEraser from modification, I had to make 
// a new interface to still allow for extensibility
abstract class AbstractEraser implements AbstractErasable {
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
	
	// This is for customization within this package only. (IE not public or protected)
	abstract boolean eraseFromPaper(Paperable paper, int startIndex, int endIndex);
}

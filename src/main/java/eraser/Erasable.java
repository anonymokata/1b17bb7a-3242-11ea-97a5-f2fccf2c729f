package eraser;

import paper.Paperable;

public interface Erasable {
	boolean eraseFromPaper(Paperable paper, String desiredText);
}

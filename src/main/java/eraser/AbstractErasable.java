package eraser;

import paper.Paperable;

public interface AbstractErasable extends Erasable {
	boolean eraseFromPaper(Paperable paper, String desiredText, int portion);
}

package editor;

import paper.Paperable;

public interface Editable {
	boolean editOnPaper(Paperable paper, String replacementText, int startIndex);
}

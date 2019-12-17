package editor;

import paper.Paper;

public interface Editable {
	boolean editOnPaper(Paper paper, String replacementText, int startIndex);
}

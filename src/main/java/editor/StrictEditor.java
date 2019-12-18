package editor;

import paper.Paperable;

public class StrictEditor implements Editable {

	@Override
	public boolean editOnPaper(Paperable paper, String replacementText, int startIndex) {
		return false;
	}

}

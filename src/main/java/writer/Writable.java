package writer;

import paper.Paperable;

public interface Writable {
	public void writeToPaper(Paperable paper, String newText);
}

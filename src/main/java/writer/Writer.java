package writer;

import paper.Paperable;

public class Writer implements Writable {
	
	@Override
	public void writeToPaper(Paperable paper, String newText) {
		String paperText = paper.getText();
		paperText += newText;
		paper.setText(paperText);
	}
}

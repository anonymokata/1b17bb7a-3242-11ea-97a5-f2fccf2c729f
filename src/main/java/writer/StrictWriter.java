package writer;

import paper.Paperable;

public class StrictWriter implements Writable {

	@Override
	public void writeToPaper(Paperable paper, String newText) {
		String paperText = paper.getText().replaceAll("\\s", " ");
		paperText += newText;
		paper.setText(paperText);
	}

}

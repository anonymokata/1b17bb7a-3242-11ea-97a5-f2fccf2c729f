package writer;

import paper.Paper;

public class Writer {
	static public void writeToPaper(Paper paper, String newText) {
		String paperText = paper.getText();
		paperText += newText;
		paper.setText(paperText);
	}
}

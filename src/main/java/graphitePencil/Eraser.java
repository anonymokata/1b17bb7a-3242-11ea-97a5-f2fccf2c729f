package graphitePencil;

public class Eraser {
	static public void eraseFromPaper(Paper paper, String desiredText) {
		StringBuilder paperText = new StringBuilder(paper.getText());
		
		int erasureLength = desiredText.length();
		int erasureIndex = paperText.lastIndexOf(desiredText);
		
		for(int i = erasureIndex; i < erasureLength; i++) {
			
		}
	}
}

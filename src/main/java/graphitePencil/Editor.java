package graphitePencil;

public class Editor {
	public static void editOnPaper(Paper paper, String replacementText, int startIndex) {
		StringBuilder paperText = new StringBuilder(paper.getText());
		
		// Replace literal space characters with letters from replacementText 
		// @ for any chracter collisions
		int replacementLength = replacementText.length();
		for(int i = 0; i < replacementLength; i++) {
			char currentChar = paperText.charAt(startIndex + i);
			char replaceChar = replacementText.charAt(startIndex + i);
			if(currentChar == ' ') {
				paperText.setCharAt(startIndex + i, replaceChar);
			}
		}
		
	}
	
}

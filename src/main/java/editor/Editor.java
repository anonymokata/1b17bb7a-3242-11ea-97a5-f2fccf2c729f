package editor;

import paper.Paper;

public class Editor {
	public static boolean editOnPaper(Paper paper, String replacementText, int startIndex) {
		StringBuilder paperText = new StringBuilder(paper.getText());
		int replacementLength = replacementText.length();
		
		if(startIndex <= paperText.length()) {
			// Replace literal ' ' with replacementText 
			// @ for any character collisions
			for(int i = 0; i < replacementLength; i++) {
				char replaceChar = replacementText.charAt(i);
				
				if(startIndex + i >= paperText.length()) {
					paperText.append(replaceChar);
				} else {
					// current character must be read here or IndexOutOfBounds happens
					char paperChar = paperText.charAt(startIndex + i);
					
					if(Character.isWhitespace(replaceChar)) {
						paperText.setCharAt(startIndex + i, paperChar);
					} else if(paperChar == ' ') { 
						paperText.setCharAt(startIndex + i, replaceChar);
					} else if(!Character.isWhitespace(paperChar)) {
						paperText.setCharAt(startIndex + i, '@');
					} else {
						// paperChar is a special whitespace
						paperText.setCharAt(startIndex + i, paperChar);
					}
				}
			}
			
			String editedPaper = paperText.toString();
			paper.setText(editedPaper);
			return true;
		} else {
			return false;
		}
	}
	
}

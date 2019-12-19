package editor;

import paper.Paperable;

/* Editor Implementation: 
 * Should incoming WS overwrite a paper's special WS? No
 * Should incoming text overwrite a paper's special WS? No
 */

public class WeakEditor implements Editable {
	
	@Override
	public boolean editOnPaper(Paperable paper, String replacementText, int startIndex) {
		StringBuilder paperText = new StringBuilder(paper.getText());
		int replacementLength = replacementText.length();
		
		if(startIndex < paperText.length()) {
			// Replace literal ' ' with replacementText 
			// @ for any character collisions
			for(int i = 0; i < replacementLength; i++) {
				char replaceChar = replacementText.charAt(i);
				
				if(startIndex + i >= paperText.length()) {
					break;
				} else {
					// current character must be read here or IndexOutOfBounds happens
					char paperChar = paperText.charAt(startIndex + i);
					
					// Incoming WS
					if(Character.isWhitespace(replaceChar)) { 
						// Paper WS
						if(Character.isWhitespace(paperChar)) {
							// Incoming WS versus Paper WS: 
							paperText.setCharAt(startIndex + i, paperChar);
						// Paper Text
						} else {
							// Incoming WS versus Paper Text: 
							// this scenario always keeps gives Paper Text right of way
							paperText.setCharAt(startIndex + i, paperChar);
						}
					// Incoming Text
					} else {
						// Paper Special WS
						if(Character.isWhitespace(paperChar) && paperChar !=' ') {
							// Incoming Text versus Paper Special WS:
							paperText.setCharAt(startIndex + i, paperChar);
						// Paper Blank Space
						} else if (paperChar == ' '){
							// Incoming Text versus Paper Blank Space
							// this scenario always gives Incoming Text right of way
							paperText.setCharAt(startIndex + i, replaceChar);
						// Paper Text
						} else {
							// Incoming Text versus Paper Text:
							// this scenario always collides
							paperText.setCharAt(startIndex + i, '@');
						}
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

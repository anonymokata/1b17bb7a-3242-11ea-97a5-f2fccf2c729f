package graphitePencil;

public class Editor {
	public static boolean editOnPaper(Paper paper, String replacementText, int startIndex) {
		// TODO must check that startIndex is <= paper.length or throw error
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
					// current character must be read here or IdexOutOfBounds happens
					char currentChar = paperText.charAt(startIndex + i);
					
					if(currentChar == ' ') {
						paperText.setCharAt(startIndex + i, replaceChar);
					} else if(!Character.isWhitespace(currentChar)) {
						paperText.setCharAt(startIndex + i, '@');
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

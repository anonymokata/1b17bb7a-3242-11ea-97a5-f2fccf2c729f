package graphitePencil;

public class Editor {
	public static int editOnPaper(Paper paper, String replacementText, int startIndex) {
		// TODO must check that startIndex is <= paper.length or throw error
		StringBuilder paperText = new StringBuilder(paper.getText());
		boolean collision = false;
		boolean wrotePast = false;
		
		// Replace literal space characters with letters from replacementText 
		// @ for any character collisions
		int replacementLength = replacementText.length();
		for(int i = 0; i < replacementLength; i++) {
			char replaceChar = replacementText.charAt(i);
			
			if(startIndex + i >= paperText.length()) {
				paperText.append(replaceChar);
				wrotePast = true;
			} else {
				// current character must be read here or IdexOutOfBounds happens
				char currentChar = paperText.charAt(startIndex + i);
				
				if(currentChar == ' ') {
					paperText.setCharAt(startIndex + i, replaceChar);
				} else if(!Character.isWhitespace(currentChar)) {
					paperText.setCharAt(startIndex + i, '@');
					collision = true;
				}
			}
		}
		
		String editedPaper = paperText.toString();
		paper.setText(editedPaper);
		
		if(collision && wrotePast) {
			return 3;
		} else if(collision) {
			return 2;
		} else if(wrotePast) {
			return 1;
		} else {
			return 0;
		}
	}
	
}

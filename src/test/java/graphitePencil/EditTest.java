package graphitePencil;

import static org.junit.Assert.*;

import org.junit.Test;

public class EditTest {

	@Test
	public void editorShouldEditBlankSpaceOnPaperWithReplacementString() {
		String text = "She       me. She loves me not.";
		Paper story = new Paper(text);
		
		// Starting 1 space after 'She'
		int startIndex = 4;
		String replacement = "loves";
		Editor.editOnPaper(story,replacement,startIndex);
		
		String expected = "She loves me. She loves me not.";
		String paperText = story.getText();
		assertEquals(expected,paperText);
	}

	@Test
	public void editorShouldNotEditSpecialWhiteSpaceOnPaperWithReplacementString() {
		// Use WSEditor to edit special white space characters
		String text = "The cow jumped over the moon.\n\n\f\tmoo!";
		Paper story = new Paper(text);
		
		// Starting at new line
		int startIndex = 29;
		String replacement = "oink";
		Editor.editOnPaper(story,replacement,startIndex);
		
		String expected = "The cow jumped over the moon.\n\n\f\tmoo!";
		String paperText = story.getText();
		assertEquals(expected,paperText);
	}
	
	@Test
	public void editorShouldNotCollideWithSpecialWhiteSpaceCharacters() {
		// WSEditor can write over special whitespace characters
		String text = "The cow jumped over the moon.\nmooooo!";
		Paper story = new Paper(text);
		
		// Starting at new line
		int startIndex = 29;
		String replacement = "oink";
		Editor.editOnPaper(story,replacement,startIndex);
		
		String expected = "The cow jumped over the moon.\n@@@ooo!";
		String paperText = story.getText();
		assertEquals(expected,paperText);
	}	
	
	@Test
	public void editorShouldCollideWithAllNonWhiteSpaceCharactersWhenEditing() {
		String text = "The cow jumped over the moon.\nmooooo!";
		Paper story = new Paper(text);
		
		//Starting at new line
		int startIndex = 29;
		String replacement = "oiink";
		Editor.editOnPaper(story,replacement,startIndex);
		
		String expected = "The cow jumped over the moon.\n@@@@oo!";
		String paperText = story.getText();
		assertEquals(expected,paperText);
	}

	@Test
	public void editorShouldWriteToPaperIfReplacementTextGoesPastEndOfPaper() {
		String text = "The cow goes ";
		Paper story = new Paper(text);
		
		// Starting after 'goes'
		int startIndex = 12;
		String replacement = " moo!";
		Editor.editOnPaper(story,replacement,startIndex);
		
		String expected = "The cow goes moo!";
		String paperText = story.getText();
		assertEquals(expected,paperText);
	}

	@Test
	public void editorShouldNotEditPaperIfStartIndexGreaterThanPaperLength() {
		Paper story = new Paper();
		
		int startIndex = 1;
		String replacement = "Hello World!";
		Editor.editOnPaper(story, replacement, startIndex);
		
		String expected = "";
		String paperText = story.getText();
		assertEquals(expected,paperText);
	}
	
	@Test
	public void editorShouldNotCollideBlankSpacesFromReplacementText() {
		
	}
	
	@Test
	public void editorShouldNotCollideSpecialWhiteSpaceFromReplacementText() {
		
	}
	
	@Test
	public void editorShouldReturnFalseIfStartIndexGreaterThanPaperLength() {
		Paper story = new Paper();
		
		int startIndex = 1;
		String replacement = "Hello World!";
		boolean edited = Editor.editOnPaper(story, replacement, startIndex);
		
		assertFalse(edited);
	}
	
	@Test
	public void editorShouldReturnTrueIfStartIndexLessThanOrEqualToPaperLength() {
		String text = "She       me. She loves me not.";
		Paper story = new Paper(text);
		
		// Start 1 space after 'She'
		int startIndex = 4;
		String replacement = "loves";
		boolean edited = Editor.editOnPaper(story,replacement,startIndex);
		
		assertTrue(edited);
	}
}

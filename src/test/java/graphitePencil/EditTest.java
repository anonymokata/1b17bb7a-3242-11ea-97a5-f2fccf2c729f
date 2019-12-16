package graphitePencil;

import static org.junit.Assert.*;

import org.junit.Test;

public class EditTest {

	@Test
	public void editorShouldEditWhiteSpaceOnPaperWithReplacementString() {
		String text = "She       me. She loves me not.";
		Paper story = new Paper(text);
		
		// Starting one space after 'She'
		int startIndex = 4;
		String replacement = "loves";
		Editor.editOnPaper(story,replacement,startIndex);
		
		String expected = "She loves me. She loves me not.";
		String paperText = story.getText();
		assertEquals(expected,paperText);
	}
	
	@Test
	public void editorShouldEditSpecialWhiteSpaceCharactersWithReplacementString() {
		String text = "The cow jumped over the moon.\n\n\f\t\nmooooo!";
		Paper story = new Paper(text);
		
		// Starting at new line in string
		int startIndex = 29;
		String replacement = "oiink";
		Editor.editOnPaper(story,replacement,startIndex);
		
		String expected = "The cow jumped over the moon.oiinkmooooo!";
		String paperText = story.getText();
		assertEquals(expected,paperText);
	}

	@Test
	public void editorShouldCollideWithAllNonWhiteSpaceCharacters() {
		String text = "The     jumped over the moon.";
		Paper story = new Paper(text);
		
		// Starting 1 space after 'The'
		int startIndex = 4;
		String replacement = "whale";
		Editor.editOnPaper(story,replacement,startIndex);
		
		String expected = "The whal@umped over the moon.";
		String paperText = story.getText();
		assertEquals(expected,paperText);
	}
	
	@Test
	public void editorShouldNotCollideWithWhiteSpaceCharacters() {
		String text = "The cow jumped over the moon.\nmooooo!";
		Paper story = new Paper(text);
		
		// Starting at new line in string
		int startIndex = 29;
		String replacement = "oiink";
		Editor.editOnPaper(story,replacement,startIndex);
		
		String expected = "The cow jumped over the moon.o@@@@oo!";
		String paperText = story.getText();
		assertEquals(expected,paperText);
	}
	
	@Test
	public void editorShouldNotCollideBlankSpacesFromReplacementText() {
		String text = "The     jumped over the moon.";
		Paper story = new Paper(text);
		
		// Starting 1 space after 'The'
		int startIndex = 4;
		String replacement = "cow ran over";
		Editor.editOnPaper(story,replacement,startIndex);
		
		String expected = "The cow @@@p@@e@ver the moon.";
		String paperText = story.getText();
		assertEquals(expected,paperText);
	}
	
	@Test
	public void editorShouldWriteToPaperIfReplacementTextGoesPastEndOfPaper() {
		String text = "The cow goes ";
		Paper story = new Paper(text);
		
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
		
		int startIndex = 4;
		String replacement = "loves";
		boolean edited = Editor.editOnPaper(story,replacement,startIndex);
		
		assertTrue(edited);
	}
}

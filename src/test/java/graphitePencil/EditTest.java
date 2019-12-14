package graphitePencil;

import static org.junit.Assert.*;

import org.junit.Test;

public class EditTest {

	@Test
	public void editorShouldEditWhiteSpaceOnPaperWithReplacementString() {
		String text1 = "She       me. She loves me not.";
		Paper story = new Paper(text1);
		
		int startIndex = 4;
		String replacement = "loves";
		Editor.editOnPaper(story,replacement,startIndex);
		
		String expected = "She loves me. She loves me not.";
		String paperText = story.getText();
		assertEquals(expected,paperText);
	}

	@Test
	public void editorShouldCollideWithAllNonWhiteSpaceCharactersWhenEditing() {
		String text1 = "The     jumped over the moon.";
		Paper story = new Paper(text1);
		
		int startIndex = 4;
		String replacement = "whale";
		Editor.editOnPaper(story,replacement,startIndex);
		
		String expected = "The whal@umped over the moon.";
		String paperText = story.getText();
		assertEquals(expected,paperText);
	}
}

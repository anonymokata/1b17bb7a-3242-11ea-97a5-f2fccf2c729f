package graphitePencil;

import static org.junit.Assert.*;

import org.junit.Test;

public class EditTest {

	@Test
	public void editorShouldEditTextOnPaper() {
		String text1 = "She       me. She loves me not.";
		Paper story = new Paper(text1);
		
		int startIndex = 4;
		String newWords = "loves";
		Editor.editOnPaper(story,"loves",4);
		
		String expected = "She loves me. She loves me not.";
		String paperText = story.getText();
		assertEquals(expected,paperText);
	}

}

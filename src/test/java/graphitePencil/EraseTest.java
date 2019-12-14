package graphitePencil;

import static org.junit.Assert.*;

import org.junit.Test;

public class EraseTest {

	@Test
	public void eraserShouldEraseTextFromPaper() {
		String text1 = "She told me that she loved me. I said I didn't love her back.";
		Paper story = new Paper(text1);
		
		Eraser.eraseFromPaper(story, "n't");
		
		String expected = "She told me that she loved me. I said I did    love her back.";
		String paperText = story.getText();
		
		System.out.println(expected);
		System.out.println(paperText);
		assertEquals(expected,paperText);
	}

}

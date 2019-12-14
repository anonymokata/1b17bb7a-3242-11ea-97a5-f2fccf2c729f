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
		assertEquals(expected,paperText);
	}

	@Test
	public void eraserShouldEraseLastOccurenceOfTextFromPaper() {
		String text1 = "She told me that she loved me. I said I loved her back.";
		Paper story = new Paper(text1);
		
		Eraser.eraseFromPaper(story, "loved");
		
		String expected = "She told me that she loved me. I said I       her back.";
		String paperText = story.getText();
		assertEquals(expected,paperText);		
	}
	
	@Test
	public void eraserShouldContinuetoEraseLastOccurenceofTextFromPaper() {
		String text1 = "She told me that she loved me. I said I loved her back and my love would never stop.";
		Paper story = new Paper(text1);
		
		// Test first rightmost removal
		Eraser.eraseFromPaper(story, "love");
		String expected = "She told me that she loved me. I said I loved her back and my      would never stop.";
		String paperText = story.getText();
		assertEquals(expected,paperText);
		
		// Test second rightmost removal
		Eraser.eraseFromPaper(story, "love");
		expected = "She told me that she loved me. I said I     d her back and my      would never stop.";
		paperText = story.getText();
		assertEquals(expected,paperText);

		// Test second rightmost removal
		Eraser.eraseFromPaper(story, "love");
		expected = "She told me that she     d me. I said I     d her back and my      would never stop.";
		paperText = story.getText();
		assertEquals(expected,paperText);
	}
	
	@Test
	public void eraserShouldEraseNonAlphanumbericalCharactersFromPaper() {
		String text1 = "She told me that she loved me!! :) I said I alway$ loved her! #myfirst";
		Paper story = new Paper(text1);
		
		// Removing a string containing white space and # literal
		Eraser.eraseFromPaper(story, " #myfirst");
		String expected = "She told me that she loved me!! :) I said I alway$ loved her!         ";
		String paperText = story.getText();
		assertEquals("Failed to remove string with white space and '#'",
				expected,paperText);
		
		// Removing a string with no alphanumeric characters
		Eraser.eraseFromPaper(story, "! :)");
		expected = "She told me that she loved me!     I said I alway$ loved her!         ";
		paperText = story.getText();
		assertEquals("Failed to remove string with no alphanumeric charactes",
				expected,paperText);
	}
	

}

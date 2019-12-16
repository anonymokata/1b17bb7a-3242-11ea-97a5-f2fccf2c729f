package graphitePencil;

import static org.junit.Assert.*;

import org.junit.Test;

public class EraseTest {

	/*********************** Erase Tests ***********************/
	@Test
	public void eraserShouldEraseTextFromPaper() {
		// Create paper with text
		String text = "She told me that she loved me. I said I didn't love her back.";
		Paper story = new Paper(text);
		
		Eraser.eraseFromPaper(story, "n't");
		String expected = "She told me that she loved me. I said I did    love her back.";
		String paperText = story.getText();
		assertEquals(expected,paperText);
	}

	@Test
	public void eraserShouldEraseLastOccurenceOfTextFromPaper() {
		String text = "She told me that she loved me. I said I loved her back.";
		Paper story = new Paper(text);
		
		Eraser.eraseFromPaper(story, "loved");
		String expected = "She told me that she loved me. I said I       her back.";
		String paperText = story.getText();
		assertEquals(expected,paperText);		
	}
	
	@Test
	public void eraserShouldContinueToEraseLastOccurenceOfTextFromPaper() {
		String text = "She told me that she loved me. I said I loved her back and my love would never stop.";
		Paper story = new Paper(text);
		
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
		String text = "She told me that she loved me!! :) I said I alway$ loved her! #myfirst";
		Paper story = new Paper(text);
		
		// Erasing with a string containing white space and # literal
		Eraser.eraseFromPaper(story, " #myfirst");
		String expected = "She told me that she loved me!! :) I said I alway$ loved her!         ";
		String paperText = story.getText();
		assertEquals("Failed to remove string with white space and '#'",
				expected,paperText);
		
		// Erasing with a string with no alphanumeric characters
		Eraser.eraseFromPaper(story, "! :)");
		expected = "She told me that she loved me!     I said I alway$ loved her!         ";
		paperText = story.getText();
		assertEquals("Failed to remove string with no alphanumeric charactes",
				expected,paperText);
	}
	
	@Test
	public void eraserShouldNotEraseSpecialWhiteCharactersFromPaper() {
		// Use WSEraser to erase special whitespace characters
		String text = "She told me that she loved me!! :)\n\tI said I alway$ loved her!\n#myfirst";
		Paper story = new Paper(text);
		
		// Removing with a string containing a new line
		Eraser.eraseFromPaper(story, "\n#myfirst");
		String expected = "She told me that she loved me!! :)\n\tI said I alway$ loved her!\n        ";
		String paperText = story.getText();
		assertEquals("Failed for removing new line",expected,paperText);
		
		// Removing with a string containing a tab
		Eraser.eraseFromPaper(story, "\tI");
		expected = "She told me that she loved me!! :)\n\t  said I alway$ loved her!\n        ";
		paperText = story.getText();
		assertEquals("Failed for removing tab",expected,paperText);
	}
	
	@Test
	public void eraserShouldEraseFromMultiplePapers() {
		// Setup papers
		String text1 = "I am a biography.";
		String text2 = "I am a story.";
		String text3 = "I am a poem.";
		String eraseText = "I";
		
		Paper paper1 = new Paper(text1);
		Paper paper2 = new Paper(text2);
		Paper paper3 = new Paper(text3);
		
		// Pencil 1 write and test
		Eraser.eraseFromPaper(paper1,eraseText);
		String expected = "  am a biography.";
		String paperText = paper1.getText();
		assertEquals(expected,paperText);
		
		// Pencil 2 write and test
		Eraser.eraseFromPaper(paper2,eraseText);
		expected = "  am a story.";
		paperText = paper2.getText();
		assertEquals(expected,paperText);
		
		// Pencil 3 write and test
		Eraser.eraseFromPaper(paper3,eraseText);
		expected = "  am a poem.";
		paperText = paper3.getText();
		assertEquals(expected,paperText);
	}
	
	/*********************** Erase Detection Tests ***********************/
	@Test
	public void eraserShouldReturnFalseIfNoCharactersWereErased() {
		String text = "She told me that she loved me.\nI said I didn't love her back. ###";
		Paper story = new Paper(text);
		
		boolean erasedNewLine = Eraser.eraseFromPaper(story, "\n");
		boolean erasedPhrase = Eraser.eraseFromPaper(story, "boogah");
		boolean erasedWhite = Eraser.eraseFromPaper(story, " ");

		assertFalse(erasedNewLine);
		assertFalse(erasedPhrase);
		assertFalse(erasedWhite);
	}
	
	@Test
	public void eraserShouldReturnTrueIfCharactersWereErased() {
		String text = "She told me that she loved me.\nI said I didn't love her back. ###";
		Paper story = new Paper(text);
		
		boolean erasedLowerCaseShe = Eraser.eraseFromPaper(story, "she");
		boolean erasedNewLineI = Eraser.eraseFromPaper(story, "\nI");
		boolean erasedHashTags = Eraser.eraseFromPaper(story, "###");
		
		assertTrue(erasedLowerCaseShe);
		assertTrue(erasedNewLineI);
		assertTrue(erasedHashTags);
	}
}

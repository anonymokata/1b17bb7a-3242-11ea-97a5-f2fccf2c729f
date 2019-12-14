package graphitePencil;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Adrian Hernandez
 *
 */
public class WriteTest {

	@Test
	public void writerShouldWriteToBlankPaper() {
		// Create a pencil object
		Paper blankPaper = new Paper();
		Pencil pencil = new Pencil(blankPaper);
		
		// Define what should be written
		String message = "I can write on paper";
		
		// Make pencil write on paper (stored internally)
		pencil.writeToPaper(message);
		
		// Return current paper pencil is writing on
		String paperText = blankPaper.getText();
		
		// Compare message to paper
		assertEquals(message,paperText);
	}
	
	@Test
	public void writerShouldWriteToExistingPaper() {
		// Create a pencil object
		String originalText = "Pre-existing words on paper.";
		Paper originalPaper = new Paper(originalText);
		Pencil pencil = new Pencil(originalPaper);
		
		// Define what should be written
		String message = " I can write more stuff on paper";
		
		// Make pencil write on paper (stored internally)
		pencil.writeToPaper(message);
		
		// Return current paper pencil is writing on
		String paperText = originalPaper.getText();
		
		// Compare expected message to paper
		String expectedText = originalText + message;
		assertEquals(expectedText,paperText);
	}
	
	@Test
	public void writerShouldWriteToMultiplePapers() {
		// Setup papers
		String text1 = "I am a biography. ";
		String text2 = "I am a story. ";
		String text3 = "I am a poem. ";
		String writeText = "This is an ending. ";
		
		Paper paper1 = new Paper(text1);
		Paper paper2 = new Paper(text2);
		Paper paper3 = new Paper(text3);
		
		// Pencil 1 write and test
		Writer.writeToPaper(paper1,writeText);

		String expected = text1 + writeText;
		String paperText = paper1.getText();
		assertEquals(expected,paperText);
		
		// Pencil 2 write and test
		Writer.writeToPaper(paper2,writeText);
		
		expected = text2 + writeText;
		paperText = paper2.getText();
		assertEquals(expected,paperText);
		
		// Pencil 3 write and test
		Writer.writeToPaper(paper3,writeText);
		
		expected = text3 + writeText;
		paperText = paper3.getText();
		assertEquals(expected,paperText);
	}
	
	@Test
	public void writerShouldWriteToPaperMultipleTimes() {
		String text1 = "I went to the store.";
		String text2 = " I found the milk I was look for.";
		String text3 = " I paid the cashier and left the store";
		
		Paper story = new Paper();
		
		Writer.writeToPaper(story, text1);
		Writer.writeToPaper(story, text2);
		Writer.writeToPaper(story, text3);
		
		String expected = text1 + text2 + text3;
		String paperText = story.getText();
		
		assertEquals(expected,paperText);
	}
}

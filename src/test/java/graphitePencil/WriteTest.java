package graphitePencil;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Adrian Hernandez
 *
 */
public class WriteTest {

	@Test
	public void pencilShouldWriteOnBlankPaper() {
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
	public void pencilShouldWriteOnExistingPaper() {
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
}

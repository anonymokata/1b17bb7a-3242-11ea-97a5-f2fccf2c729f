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
		Pencil pencil = new Pencil();
		
		// Define what should be written
		String message = "I can write on paper";
		
		// Make pencil write on paper (stored internally)
		pencil.writeOnPaper(message);
		
		// Return current paper pencil is writing on
		String paperText = pencil.getPaper();
		
		// Compare message to paper
		assertEquals(message,paperText);
	}
	
	@Test
	public void pencilShouldWriteOnExistingPaper() {
		// Create a pencil object
		String originalPaper = "Pre-existing words on paper.";
		Pencil pencil = new Pencil(originalPaper);
		
		// Define what should be written
		String message = " I can write more stuff on paper";
		
		// Make pencil write on paper (stored internally)
		pencil.writeOnPaper(message);
		
		// Return current paper pencil is writing on
		String paperText = pencil.getPaper();
		
		// Compare expected message to paper
		String expectedText = originalPaper + message;
		assertEquals(expectedText,paperText);
	}
}

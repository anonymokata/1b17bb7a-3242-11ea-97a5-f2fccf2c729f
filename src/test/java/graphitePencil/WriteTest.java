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
		Paper paper = pencil.getPaper();
		
		// 
		assertEquals
	}

}

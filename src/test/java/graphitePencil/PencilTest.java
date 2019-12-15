package graphitePencil;

import static org.junit.Assert.*;

import org.junit.Test;

public class PencilTest {
	
	@Test
	public void whenWritingPencilShouldLoseOneWritePointPerLowerCaseLetter() {
		Pencil pencil = new Pencil(10,10,100);
		Paper paper = new Paper();
		
		// Test with 1 character
		pencil.writeToPaper(paper, "a");
		int availablePoints = pencil.getWritePoints();
		assertEquals(9,availablePoints);
		
		// Test with 3 characters
		pencil.writeToPaper(paper, "aaa");
		availablePoints = pencil.getWritePoints();
		assertEquals(6,availablePoints);		
	}
	
	public void whenWritingPencilShouldNotLosePointsForAnyWhiteSpace() {
		Pencil pencil = new Pencil(10,10,100);
		Paper paper = new Paper();
		
		// Test with 1 character
		pencil.writeToPaper(paper, "    ");
		int availablePoints = pencil.getWritePoints();
		assertEquals(10,availablePoints);
		
		// Test with 3 characters
		pencil.writeToPaper(paper, "\t\n\r \f  ");
		availablePoints = pencil.getWritePoints();
		assertEquals(10,availablePoints);
	}
	@Test
	public void pencilShoulWriteBlankSpacesWhenOutOfWritePoints() {
		Pencil pencil = new Pencil(10,10,100);
		Paper paper = new Paper();
		
		pencil.writeToPaper(paper, "I am more than 10 characters");
	}

}

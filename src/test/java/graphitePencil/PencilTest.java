package graphitePencil;

import static org.junit.Assert.*;

import org.junit.Test;

public class PencilTest {
	
	@Test
	public void pencilShouldWriteOnPaper() {
		Pencil pencil = new Pencil(10,10,100);
		Paper paper = new Paper();
		
		// Simple write to paper using pencil
		pencil.writeToPaper(paper, "a");
		String expected = "a";
		String paperText = paper.getText();
		assertEquals(expected,paperText);		
	}
	
	/*******************************************
	 * POINT DEGRADATION UNIT TESTING : START
	 ******************************************/
	// WRITING POINTS : START
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
	
	@Test
	public void whenWritingPencilShouldLoseTwoWritePointPerUpperCaseLetter() {
		Pencil pencil = new Pencil(10,10,100);
		Paper paper = new Paper();
		
		// Test with 1 character
		pencil.writeToPaper(paper, "A");
		int availablePoints = pencil.getWritePoints();
		assertEquals(8,availablePoints);
		
		// Test with 3 characters
		pencil.writeToPaper(paper, "BBB");
		availablePoints = pencil.getWritePoints();
		assertEquals(2,availablePoints);		
	}
	
	@Test
	public void whenWritingPencilShouldNotLoseWritePointsForAnyWhiteSpace() {
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
	public void whenWritingPencilShouldLoseOneWritePointPerNonWhiteSpaceNonAlphabeticalCharacter() {
		// Basically, default to one point loss for everything else
		Pencil pencil = new Pencil(20,10,100);
		Paper paper = new Paper();
		
		// Test with 1 character
		pencil.writeToPaper(paper, "!@#$%^&*()_+-=~");
		int availablePoints = pencil.getWritePoints();
		assertEquals(5,availablePoints);
		
		// Test with 3 characters
		pencil.writeToPaper(paper, "<.  />");
		availablePoints = pencil.getWritePoints();
		assertEquals(1,availablePoints);
	}	
	// WRITING POINTS : END
	
	// EDITING POINTS : START
	
	// EDITING POINTS : END
	
	// ERASING POINTS : START
	
	// ERASING POINTS : END
	/**************************************
	/* POINT DEGRADATION UNIT TESTS : END *
	 **************************************/
	@Test
	public void pencilShouldWriteBlankSpacesWhenOutOfWritePoints() {
		Pencil pencil = new Pencil(10,10,100);
		String text = "I am more than 10";
		Paper paper = new Paper();
		
		pencil.writeToPaper(paper, text);
		String expected = "I am more th     ";
		String paperText = paper.getText();
		assertEquals(expected,paperText);
	}

}

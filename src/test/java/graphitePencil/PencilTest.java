package graphitePencil;

import static org.junit.Assert.*;

import org.junit.Test;

public class PencilTest {

	/****************************
	 * WRITE UNIT TESTS : START *
	 ****************************/
	@Test
	public void pencilShouldWriteOnPaper() {
		Pencil pencil = new Pencil(10,10,10);
		Paper paper = new Paper();
		
		// Simple write to paper using pencil
		pencil.writeToPaper(paper, "a");
		String expected = "a";
		String paperText = paper.getText();
		assertEquals(expected,paperText);		
	}
	
	// Point Degradation Tests : Start
	@Test
	public void whenWritingPencilShouldLoseOneWritePointPerLowerCaseLetter() {
		Pencil pencil = new Pencil(10,10,10);
		Paper paper = new Paper();
		
		// Test with 1 lower character
		pencil.writeToPaper(paper, "a");
		int availablePoints = pencil.getWritePoints();
		assertEquals(9,availablePoints);
		
		// Test with 3 lowercase characters
		pencil.writeToPaper(paper, "aaa");
		availablePoints = pencil.getWritePoints();
		assertEquals(6,availablePoints);		
	}
	
	@Test
	public void whenWritingPencilShouldLoseTwoWritePointsPerUpperCaseLetter() {
		Pencil pencil = new Pencil(10,10,10);
		Paper paper = new Paper();
		
		// Test with 1 uppercase character
		pencil.writeToPaper(paper, "A");
		int availablePoints = pencil.getWritePoints();
		assertEquals(8,availablePoints);
		
		// Test with 3 uppercase characters
		pencil.writeToPaper(paper, "BBB");
		availablePoints = pencil.getWritePoints();
		assertEquals(2,availablePoints);		
	}
	
	@Test
	public void whenWritingPencilShouldNotLoseWritePointsForAnyWhiteSpace() {
		Pencil pencil = new Pencil(10,10,10);
		Paper paper = new Paper();
		
		// Test with space character
		pencil.writeToPaper(paper, "    ");
		int availablePoints = pencil.getWritePoints();
		assertEquals(10,availablePoints);
		
		// Test with multiple forms of white space
		pencil.writeToPaper(paper, "\t\n\r \f  ");
		availablePoints = pencil.getWritePoints();
		assertEquals(10,availablePoints);
	}
	
	@Test
	public void whenWritingPencilShouldLoseOneWritePointPerNonWhiteSpaceNonAlphabeticalCharacter() {
		// Basically, default to one point loss for everything else
		Pencil pencil = new Pencil(20,10,10);
		Paper paper = new Paper();
		
		// Test with 1 character
		pencil.writeToPaper(paper, "!@#$%^&*()_+-=~");
		int availablePoints = pencil.getWritePoints();
		assertEquals(5,availablePoints);
		
		// Test with 3 characters
		pencil.writeToPaper(paper, "<.  />9");
		availablePoints = pencil.getWritePoints();
		assertEquals(0,availablePoints);
	}
	// Point Degradation Tests : End
	
	// Write Degradation Tests : Start
	@Test
	public void whenWritingPencilShouldAlwaysPreserveWhiteSpaceCharactersEvenWhenOutOfPoints() {
		Pencil pencil = new Pencil(4,10,10);
		String text = "I am newline \n";
		Paper paper = new Paper();
		
		pencil.writeToPaper(paper, text);
		String expected = "I am         \n";
		String paperText = paper.getText();
		assertEquals(expected,paperText);
	}
	
	@Test
	public void whenOutOfWritePointsPencilShouldWriteBlankSpacesForNonWhiteSpaceCharacters() {
		Pencil pencil = new Pencil(10,10,10);
		String text = "I am more than 10";
		Paper paper = new Paper();
		
		pencil.writeToPaper(paper, text);
		String expected = "I am more th     ";
		String paperText = paper.getText();
		assertEquals(expected,paperText);
	}
	// Write Degradation Tests : End
	/**************************
	 * WRITE UNIT TESTS : END *
	 **************************/
	
	/******************************
	 * SHARPEN UNIT TESTS : START *
	 ******************************/
	@Test
	public void afterSharpeningPencilWritingPointsShouldGoBackToDefault() {
		Pencil pencil = new Pencil(10,10,10);
		Paper paper = new Paper();
		
		pencil.writeToPaper(paper, "abcd");
		pencil.sharpen();
		int availablePoints = pencil.getWritePoints();
		int defaultPoints = pencil.getDefaultWritePoints();
		assertEquals(defaultPoints,availablePoints);
	}
	
	@Test
	public void afterSharpeningPencilLengthShouldDecreaseByOne() {
		Pencil pencil = new Pencil(10,10,10);
		Paper paper = new Paper();
		
		pencil.writeToPaper(paper, "abcd");
		pencil.sharpen();
		int pencilLength = pencil.getPencilLength();
		assertEquals(9,pencilLength);
	}
	
	@Test
	public void pencilShouldNotSharpenIfPencilLengthIsZero() {
		Pencil pencil = new Pencil(10,10,0);
		Paper paper = new Paper();
		
		pencil.writeToPaper(paper, "abcd");
		pencil.sharpen();
		int writePoints = pencil.getWritePoints();
		assertEquals(6,writePoints);
	}
	
	@Test
	public void pencilShouldReturnFalseIfFailedToSharpen() {
		Pencil pencil = new Pencil(10,10,0);
		Paper paper = new Paper();
		
		pencil.writeToPaper(paper, "abcd");
		boolean sharpened = pencil.sharpen();
		assertFalse(sharpened);
	}
	
	@Test
	public void pencilShouldReturnTrueIfSharpened() {
		Pencil pencil = new Pencil(10,10,1);
		Paper paper = new Paper();
		
		pencil.writeToPaper(paper, "abcd");
		boolean sharpened = pencil.sharpen();
		assertTrue(sharpened);
	}
	/****************************
	 * SHARPEN UNIT TESTS : END *
	 ****************************/
	
	/***************************
	 * EDIT UNIT TESTS : START *
	 ***************************/
	@Test
	public void whenOutOfWritePointsEditorShouldNotCollideAnyCharactersFromReplacementText() {
		
	}
	/*************************
	 * EDIT UNIT TESTS : END *
	 *************************/
}

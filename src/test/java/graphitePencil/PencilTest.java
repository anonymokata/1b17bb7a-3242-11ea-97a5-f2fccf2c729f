package graphitePencil;

import static org.junit.Assert.*;

import org.junit.Test;

import paper.Paper;

public class PencilTest {

	/****************************
	 *     WRITE UNIT TESTS     *
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
	
	/*********************** Point Degradation Tests ***********************/
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
	
	@Test
	public void pencilShouldLoseOnePointForAnyFailedWriteAttempts() {
		Pencil pencil = new Pencil(1,10,10);
		Paper paper = new Paper();
		
		// Test with 1 character
		pencil.writeToPaper(paper, "A");
		int availablePoints = pencil.getWritePoints();
		assertEquals(0,availablePoints);
	}
	
	@Test
	public void whenOutOfWritePointsPencilShouldNotLostAnymorePointsWhileWriting() {
		// IE should never have negative write points
	}
	
	/*********************** Write Degradation Tests ***********************/
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
	
	public void whenNotEnoughPointsPencilShouldWriteBlankSpacesForNonWhiteSpaceCharacters() {
		Pencil pencil = new Pencil(1,10,10);
		Paper paper = new Paper();
		
		// Test with 1 character
		pencil.writeToPaper(paper, "A");
		String expected = " ";
		String paperText = paper.getText();
		assertEquals(expected,paperText);
	}
	
	/******************************
	 *     SHARPEN UNIT TESTS     *
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
	
	/*********************** Sharpen Detection Tests ***********************/
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
	
	
	/***************************
	 *     EDIT UNIT TESTS     *
	 ***************************/
	@Test
	public void pencilShouldEditPaper() {
		Pencil pencil = new Pencil(10,10,10);
		Paper paper = new Paper(" ");
		
		// Test with 1 lower character
		pencil.editOnPaper(paper, "a",0);
		String expected = "a";
		String paperText = paper.getText();
		assertEquals(expected,paperText);
	}
	
	@Test
	public void pencilShouldCollideIfPaperAndReplacementAreNonWhiteSpaceCharacters() {
		
	}
	
	@Test
	public void pencilNotEditPaperIfReplacementStringIsAWhiteSpaceCharacter() {
		
	}
	
	public void pencilShouldWriteAllOverflowTextAsIsToPaper() {
		
	}
	
	/*********************** Point Degradation Tests ***********************/
	@Test
	public void whenEditingPencilShouldLoseOneWritePointPerLowerCaseLetter() {
		Pencil pencil = new Pencil(10,10,10);
		Paper paper = new Paper("    ");
		
		// Test with 1 lower character
		pencil.editOnPaper(paper, "a",0);
		int availablePoints = pencil.getWritePoints();
		assertEquals(9,availablePoints);
		
		// Test with 3 lowercase characters
		pencil.editOnPaper(paper, "bcd",1);
		availablePoints = pencil.getWritePoints();
		assertEquals(6,availablePoints);		
	}
	
	@Test
	public void whenEditingPencilShouldLoseTwoWritePointsPerUpperCaseLetter() {
		Pencil pencil = new Pencil(10,10,10);
		Paper paper = new Paper("    ");
		
		// Test with 1 uppercase character
		pencil.editOnPaper(paper, "A",0);
		int availablePoints = pencil.getWritePoints();
		assertEquals(8,availablePoints);
		
		// Test with 3 uppercase characters
		pencil.editOnPaper(paper, "BCD",1);
		availablePoints = pencil.getWritePoints();
		assertEquals(2,availablePoints);
	}
	
	@Test
	public void whenEditingPencilShouldNotLoseWritePointsForBlankSpace() {
		Pencil pencil = new Pencil(10,10,10);
		Paper paper = new Paper("    ");
		
		// Test with 1 blank space character
		pencil.editOnPaper(paper, " ",0);
		int availablePoints = pencil.getWritePoints();
		assertEquals(10,availablePoints);
		
		// Test with 3 blank space characters
		pencil.editOnPaper(paper, "   ",1);
		availablePoints = pencil.getWritePoints();
		assertEquals(10,availablePoints);
	}
	
	@Test
	public void whenEditingPencilShouldLoseOneWritePointPerCollision() {
		Pencil pencil = new Pencil(10,10,10);
		Paper paper = new Paper("aaaaaa");
		
		// Test with 1  character
		pencil.editOnPaper(paper, "b",0);
		int availablePoints = pencil.getWritePoints();
		assertEquals(9,availablePoints);
		
		// Test with random characters
		pencil.editOnPaper(paper, "cDE $!",1);
		availablePoints = pencil.getWritePoints();
		assertEquals(4,availablePoints);
	}
	
	@Test
	public void whenOutOfWritePointsPencilShouldNotLoseAnymorePointsWhileEditing() {
		// IE should never have negative write points
		Pencil pencil = new Pencil(10,10,10);
		Paper paper = new Paper("       ");
		
		// Get down to zero points, don't sharpen
		pencil.editOnPaper(paper, "AAAAA",0);
		int availablePoints = pencil.getWritePoints();
		assertEquals(0,availablePoints);
		
		// Test with 3 lowercase characters
		pencil.editOnPaper(paper, "cccccc",1);
		availablePoints = pencil.getWritePoints();
		assertEquals(0,availablePoints);
	}
	
	@Test
	public void whenEditingPencilShouldNotLoseWritePointsForSpecialWhiteSpaceCharacters() {
		Pencil pencil = new Pencil(10,10,10);
		Paper paper = new Paper("The     jumped over the moon.");
		
		// Starting 1 space after 'The'
		int startIndex = 4;
		String replacement = "cow \n\n";
		pencil.editOnPaper(paper,replacement,startIndex);
		
		int availablePoints = pencil.getWritePoints();
		assertEquals(7,availablePoints);
	}
	
	/*********************** Edit Degradation Tests ***********************/
	@Test
	public void whenOutOfWritePointsPencilShouldNotCollideAnyCharacters() {
		Pencil pencil = new Pencil(3,10,10);
		Paper paper = new Paper("The     jumped over the moon.");
		
		// Starting 1 space after 'The'
		int startIndex = 4;
		String replacement = "cow ran";
		pencil.editOnPaper(paper,replacement,startIndex);
	}
	
	@Test
	public void whenOutOfWritePointsPencilShoudNotOverWriteAnyCharactersOnPaper() {
		Pencil pencil = new Pencil(3,10,10);
		Paper paper = new Paper("The     jumped over the moon.");
		
		// Starting 1 space after 'The'
		int startIndex = 4;
		String replacement = "cow ran\n\n\t\f";
		pencil.editOnPaper(paper,replacement,startIndex);
		
		String expected = "The cow jumped over the moon.";
		String paperText = paper.getText();
		assertEquals(expected,paperText);
	}
	
	@Test
	public void whenOutOfWritePointsOverflowTextWhiteSpaceShouldBePreserved() {
		// IE pencil should still keeps \n\t\f etc for overflow only
		Pencil pencil = new Pencil(3,10,10);
		Paper paper = new Paper("The    ");
		
		// Starting 1 space after 'The'
		int startIndex = 4;
		String replacement = "cow ran\n\n\t\f";
		pencil.editOnPaper(paper,replacement,startIndex);
		
		String expected = "The cow    \n\n\t\f";
		String paperText = paper.getText();
		assertEquals(expected,paperText);
	}
	
	/*****************************
	 *     ERASER UNIT TESTS     *
	 *****************************/
	@Test
	public void pencilShouldEraseTextFromPaper() {
		
	}
	
	@Test
	public void pencilShouldNotEraseSpecialWhiteCharacters() {
		
	}
	
	@Test
	public void whenOutOfErasePointsPencilShouldNotEraseFromPaper() {
		
	}
}

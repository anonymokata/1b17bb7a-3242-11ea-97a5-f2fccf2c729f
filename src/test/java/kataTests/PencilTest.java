package kataTests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

import eraser.Erasable;
import eraser.ErasableFactory;
import eraser.Erasables;
import paper.Paper;
import pencil.Pencil;
import pencil.Pencilable;
import pencil.PencilableFactory;
import pencil.Pencilables;

//Tells runner to run tests with different data sets
@RunWith(Parameterized.class)
public class PencilTest {
	// public static Object[][] data() {}
	// This sets the "database" that the runner will pull from
	// The column types of this "database" are your parameters
	// Runner will 
	//    1. Read a "row" of data (the inner array) 
	//    2. Assign parameters (column types) as specified below
	//    3. Run all unit test with "row" of data
	//    4. Repeat 1-3 until all "rows" have been read
	
	// name = "{1} : myString {3}" 
	// Sets a label to be attached to the unit tests where
	// {#} is the toString() of data in column # in current row
	
	@Parameterized.Parameters(name = "{0}")
    public static Object[][] data() {
        return new Object[][] {
        	{"Pencil", Pencilables.Pencil, true},
        	{"StrictPencil", Pencilables.StrictPencil, false }
        };
    }
	
	// This helps identify which class of eraser was used in a clean way.
	// This is only used for a reference in the name = {0}
	// because name = {1} gives me excess (nasty looking) information 
	@Parameter(0)
	public String forTestLabelingOnly;
	// This is the type of generic object under test.
	@Parameter(1)
	public Pencilables pencilType;
	// Used for testing of diverging functionalities 
	@Parameter(2)
	public boolean whiteSpaceFriendlyPencil;
	
	
	/****************************
	 *     WRITE UNIT TESTS     *
	 ****************************/
	@Test
	public void pencilShouldWriteOnPaper() {
		Pencilable pencil = PencilableFactory.getPencilable(pencilType,10,10,10);
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
		Pencilable pencil = PencilableFactory.getPencilable(pencilType,10,10,10);
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
		Pencilable pencil = PencilableFactory.getPencilable(pencilType,10,10,10);
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
		Pencilable pencil = PencilableFactory.getPencilable(pencilType,10,10,10);
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
		Pencilable pencil = PencilableFactory.getPencilable(pencilType,20,10,10);
		Paper paper = new Paper();
		
		// Test with 1 character
		pencil.writeToPaper(paper, "!@#$%^&*()_+-=~");
		int availablePoints = pencil.getWritePoints();
		assertEquals(5,availablePoints);
		
		pencil.writeToPaper(paper, "<.  />9");
		availablePoints = pencil.getWritePoints();
		assertEquals(0,availablePoints);
	}
	
	@Test
	public void pencilShouldLoseOnePointForAnyFailedWriteAttempts() {
		Pencilable pencil = PencilableFactory.getPencilable(pencilType,1,10,10);
		Paper paper = new Paper();
		
		// Test with 1 character
		pencil.writeToPaper(paper, "A");
		int availablePoints = pencil.getWritePoints();
		assertEquals(0,availablePoints);
	}
	
	@Test
	public void whenOutOfWritePointsPencilShouldNotLostAnymorePointsWhileWriting() {
		// IE should never have negative write points
		Pencilable pencil = PencilableFactory.getPencilable(pencilType,1,10,10);
		Paper paper = new Paper();
		
		// Test with 1 character
		pencil.writeToPaper(paper, "Abc");
		int availablePoints = pencil.getWritePoints();
		assertEquals(0,availablePoints);
	}
	
	/*********************** Write Degradation Tests ***********************/
	@Test
	public void whenWritingPencilShouldAlwaysPreserveWhiteSpaceCharactersEvenWhenOutOfPoints() {
		Pencilable pencil = PencilableFactory.getPencilable(pencilType,4,10,10);
		String text = "I am newline \n";
		Paper paper = new Paper();
		
		pencil.writeToPaper(paper, text);
		String expected = "I am         \n";
		String paperText = paper.getText();
		assertEquals(expected,paperText);
	}
	
	@Test
	public void whenOutOfWritePointsPencilShouldWriteBlankSpacesForNonWhiteSpaceCharacters() {
		Pencilable pencil = PencilableFactory.getPencilable(pencilType,10,10,10);
		String text = "I am more than 10";
		Paper paper = new Paper();
		
		pencil.writeToPaper(paper, text);
		String expected = "I am more th     ";
		String paperText = paper.getText();
		assertEquals(expected,paperText);
	}
	
	public void whenNotEnoughPointsPencilShouldWriteBlankSpacesForNonWhiteSpaceCharacters() {
		Pencilable pencil = PencilableFactory.getPencilable(pencilType,1,10,10);
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
		Pencilable pencil = PencilableFactory.getPencilable(pencilType,10,10,10);
		Paper paper = new Paper();
		
		pencil.writeToPaper(paper, "abcd");
		pencil.sharpen();
		int availablePoints = pencil.getWritePoints();
		int defaultPoints = pencil.getDefaultWritePoints();
		assertEquals(defaultPoints,availablePoints);
	}
	
	@Test
	public void afterSharpeningPencilLengthShouldDecreaseByOne() {
		Pencilable pencil = PencilableFactory.getPencilable(pencilType,10,10,10);
		Paper paper = new Paper();
		
		pencil.writeToPaper(paper, "abcd");
		pencil.sharpen();
		int pencilLength = pencil.getPencilLength();
		assertEquals(9,pencilLength);
	}
	
	/*********************** Sharpen Detection Tests ***********************/
	@Test
	public void pencilShouldNotSharpenIfPencilLengthIsZero() {
		Pencilable pencil = PencilableFactory.getPencilable(pencilType,10,10,0);
		Paper paper = new Paper();
		
		pencil.writeToPaper(paper, "abcd");
		pencil.sharpen();
		int writePoints = pencil.getWritePoints();
		assertEquals(6,writePoints);
	}
	
	@Test
	public void pencilShouldReturnFalseIfFailedToSharpen() {
		Pencilable pencil = PencilableFactory.getPencilable(pencilType,10,10,0);
		Paper paper = new Paper();
		
		pencil.writeToPaper(paper, "abcd");
		boolean sharpened = pencil.sharpen();
		assertFalse(sharpened);
	}
	
	@Test
	public void pencilShouldReturnTrueIfSharpened() {
		Pencilable pencil = PencilableFactory.getPencilable(pencilType,10,10,1);
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
		Pencilable pencil = PencilableFactory.getPencilable(pencilType,10,10,10);
		Paper paper = new Paper(" ");
		
		// Test with 1 lower character
		pencil.editOnPaper(paper, "a",0);
		String expected = "a";
		String paperText = paper.getText();
		assertEquals(expected,paperText);
	}
	
	@Test
	public void pencilShouldCollideIfPaperAndReplacementAreNonWhiteSpaceCharacters() {
		Pencilable pencil = PencilableFactory.getPencilable(pencilType,10,10,10);
		Paper paper = new Paper("abcdef!J,/");
		
		// Test with random characters
		pencil.editOnPaper(paper, "/;/FGcsag$",0);
		String expected = "@@@@@@@@@@";
		String paperText = paper.getText();
		assertEquals(expected,paperText);
	}
	
	@Test
	public void pencilShouldNotEditPaperIfReplacementStringIsAWhiteSpaceCharacter() {
		Pencilable pencil = PencilableFactory.getPencilable(pencilType,10,10,10);
		Paper paper = new Paper("abcdef");
		
		// Test with blank spaces and random characters
		pencil.editOnPaper(paper, "/;/ \t\n",0);
		String expected = "@@@def";
		String paperText = paper.getText();
		assertEquals(expected,paperText);
	}
	
	public void pencilShouldWriteAllOverflowTextAsIsToPaper() {
		Pencilable pencil = PencilableFactory.getPencilable(pencilType,10,10,10);
		Paper paper = new Paper("a");
		
		// Test with random
		pencil.editOnPaper(paper, "cd \n\tclowns",0);
		String expected = "@d \\n\\tclowns";
		String paperText = paper.getText();
		assertEquals(expected,paperText);
	}
	
	/*********************** Point Degradation Tests ***********************/
	@Test
	public void whenEditingPencilShouldLoseOneWritePointPerLowerCaseLetter() {
		Pencilable pencil = PencilableFactory.getPencilable(pencilType,10,10,10);
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
		Pencilable pencil = PencilableFactory.getPencilable(pencilType,10,10,10);
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
		Pencilable pencil = PencilableFactory.getPencilable(pencilType,10,10,10);
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
		Pencilable pencil = PencilableFactory.getPencilable(pencilType,10,10,10);
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
		Pencilable pencil = PencilableFactory.getPencilable(pencilType,10,10,10);
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
		Pencilable pencil = PencilableFactory.getPencilable(pencilType,10,10,10);
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
		Pencilable pencil = PencilableFactory.getPencilable(pencilType,3,10,10);
		Paper paper = new Paper("The     jumped over the moon.");
		
		// Starting 1 space after 'The'
		int startIndex = 4;
		String replacement = "cow ran";
		pencil.editOnPaper(paper,replacement,startIndex);
	}
	
	@Test
	public void whenOutOfWritePointsPencilShoudNotOverWriteAnyCharactersOnPaper() {
		Pencilable pencil = PencilableFactory.getPencilable(pencilType,3,10,10);
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
		Pencilable pencil = PencilableFactory.getPencilable(pencilType,3,10,10);
		Paper paper = new Paper("The    ");
		
		// Starting 1 space after 'The'
		int startIndex = 4;
		String replacement = "cow ran\n\n\t\f";
		pencil.editOnPaper(paper,replacement,startIndex);
		
		String paperText = paper.getText();
		if(whiteSpaceFriendlyPencil) {
			String expected = "The cow    \n\n\t\f";
			assertEquals(expected,paperText);
		} else {
			String expected = "The cow        ";
			assertEquals(expected,paperText);
		}

	}
	
	
	/*****************************
	 *     ERASER UNIT TESTS     *
	 *****************************/
	@Test
	public void pencilShouldEraseTextFromPaper() {
		Pencilable pencil = PencilableFactory.getPencilable(pencilType,10,10,10);
		Paper paper = new Paper("The The The The The");
		
		// Erase last 'The'
		String erasure = "The";
		pencil.eraseFromPaper(paper, erasure);
		String expected =  "The The The The    ";
		String paperText = paper.getText();
		assertEquals(expected,paperText);
	}
	
	@Test
	public void pencilShouldNotEraseSpecialWhiteCharacters() {
		Pencilable pencil = PencilableFactory.getPencilable(pencilType,10,10,10);
		Paper paper = new Paper("a\na");
		
		// Erases only the a's , preserving whitespace
		String erasure = "a\na";
		pencil.eraseFromPaper(paper, erasure);

		String paperText = paper.getText();
		
		if(whiteSpaceFriendlyPencil) {
			String expected =  " \n ";
			assertEquals(expected,paperText);
		} else {
			String expected =  "   ";
			assertEquals(expected,paperText);
		}

	}
	
	/*********************** Point Degradation Tests ***********************/
	@Test
	public void pencilShouldLoseOneErasePointPerNonWhiteSpaceCharacters(){
		Pencilable pencil = PencilableFactory.getPencilable(pencilType,10,10,10);
		Paper paper = new Paper("a\na");
		
		// Erases only the a's , preserving whitespace
		String erasure = "a\na";
		pencil.eraseFromPaper(paper, erasure);
		int availablePoints = pencil.getErasePoints();
		assertEquals(8,availablePoints);
	}
	
	@Test
	public void pencilShouldNotLosePointsForAnyMatchingWhiteSpaceCharacters() {
		Pencilable pencil = PencilableFactory.getPencilable(pencilType,10,10,10);
		Paper paper = new Paper("\n\n\nsssss\tpppp ");
		
		// Erases only the a's , preserving whitespace
		String erasure = "s\tpppp ";
		pencil.eraseFromPaper(paper, erasure);
		int availablePoints = pencil.getErasePoints();
		assertEquals(5,availablePoints);
	}
	
	/*********************** Erase Degradation Tests ***********************/
	@Test
	public void whenOutOfErasePointsPencilShouldNotEraseFromPaper() {
		Pencilable pencil = PencilableFactory.getPencilable(pencilType,10,5,10);
		Paper paper = new Paper("abcdefghij");
		
		// Should not completely erase string
		String erasure = "abcdefghij";
		pencil.eraseFromPaper(paper, erasure);
		String expected =  "abcde     ";
		String paperText = paper.getText();
		assertEquals(expected,paperText);
	}
}

package kataTests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

import paper.Paper;
import writer.Writable;
import writer.WritableFactory;
import writer.Writables;

/**
 * @author Adrian Hernandez
 *
 */
//Tells runner to run tests with different data sets
@RunWith(Parameterized.class)
public class WritableTest {
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
        	{"Writer", WritableFactory.getWritable(Writables.Writer), true},
        	{"StrictWriter", WritableFactory.getWritable(Writables.StrictWriter), false}
        };
    }
	
	
	// This helps identify which class of eraser was used in a clean way.
	// This is only used for a reference in the name = {0}
	// because name = {1} gives me excess (nasty looking) information 
	@Parameter(0)
	public String forTestLabelingOnly;
	// This is the generic object under test.
	@Parameter(1)
	public Writable Writer;
	// Used for testing of diverging functionalities 
	@Parameter(2)
	public boolean whiteSpaceFriendlyWriter;
	
	/*********************** Write Tests ***********************/
	@Test
	public void writerShouldWriteToBlankPaper() {
		// Create a pencil object
		Paper blankPaper = new Paper();
		
		// Define what should be written
		String addition = "I can write on paper";
		
		// Make pencil write on paper (stored internally)
		Writer.writeToPaper(blankPaper,addition);
		
		// Return current paper pencil is writing on
		String paperText = blankPaper.getText();
		
		// Compare message to paper
		assertEquals(addition,paperText);
	}
	
	@Test
	public void writerShouldWriteToExistingPaper() {
		// Create a pencil object
		String text = "Pre-existing words on paper.";
		Paper story = new Paper(text);
		
		// Define what should be written
		String addition = " I can write more stuff on paper";
		
		// Make Writer write on paper
		Writer.writeToPaper(story,addition);
		
		// Return current paper pencil is writing on
		String paperText = story.getText();
		
		// Compare expected message to paper
		String expected = text + addition;
		assertEquals(expected,paperText);
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
	
	@Test
	public void writerShouldProcessSpecialWhiteSpaceCorrectly() {
		String text = "I love\nlove love\nyou";
		Paper story = new Paper();
		Writer.writeToPaper(story, text);
		
		String expected = "I love\nlove love\nyou";
		String paperText = story.getText();
		boolean whiteSpaceFriendly = paperText.contentEquals(expected);
		assertEquals(whiteSpaceFriendlyWriter,whiteSpaceFriendly);
	}
}

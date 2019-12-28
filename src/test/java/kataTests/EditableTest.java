package kataTests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

import editor.Editable;
import editor.EditableFactory;
import editor.Editables;
import paper.Paper;

//Tells runner to run tests with different data sets
@RunWith(Parameterized.class)
public class EditableTest {
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
        	{"WeakEditor", EditableFactory.getEditable(Editables.WeakEditor), false, false},
        	{"LooseEditor", EditableFactory.getEditable(Editables.LooseEditor), true, false},
        	{"StrictEditor", EditableFactory.getEditable(Editables.StrictEditor), false, true},
        	{"StrongEditor", EditableFactory.getEditable(Editables.StrongEditor), true, true}
        };
    }
	
	// This helps identify which class of eraser was used in a clean way.
	// This is only used for a reference in the name = {0}
	// because name = {1} gives me excess (nasty looking) information 
	@Parameter(0)
	public String forTestLabelingOnly;
	// This is the generic object under test.
	@Parameter(1)
	public Editable Editor;
	// Check if editor will overwrite WS on paper with incoming WS
	@Parameter(2)
	public boolean incomingWhiteSpacePriority;
	// Check if editor will overwrite WS on paper with incoming text
	@Parameter(3)
	public boolean incomingTextPriority;
	
	/*********************** Edit Tests ***********************/
	@Test
	public void editorShouldEditBlankSpaceOnPaperWithReplacementString() {
		String text = "She       me. She loves me not.";
		Paper story = new Paper(text);
		
		// Starting 1 space after 'She'
		int startIndex = 4;
		String replacement = "loves";
		Editor.editOnPaper(story,replacement,startIndex);
		
		String expected = "She loves me. She loves me not.";
		String paperText = story.getText();
		assertEquals(expected,paperText);
	}

	@Test
	public void editorShouldNotEditSpecialWhiteSpaceOnPaperWithReplacementString() {
		String text = "The cow jumped over the moon.\n\n\f\tmoo!";
		Paper story = new Paper(text);
		
		// Starting at new line
		int startIndex = 29;
		String replacement = "oink";
		Editor.editOnPaper(story,replacement,startIndex);
		
		String expected = "The cow jumped over the moon.\n\n\f\tmoo!";
		String paperText = story.getText();
		boolean textOnNewLines = expected.equals(paperText);
		assertEquals(incomingTextPriority,!textOnNewLines);
	}
	
	@Test
	public void editorShouldNotWriteSpecialWhiteSpaceFromReplacementString() {
		String text = "The     jumped over the moon.";
		Paper story = new Paper(text);
		
		// Starting 1 space after 'The'
		int startIndex = 4;
		String replacement = "cow\n\n";
		Editor.editOnPaper(story,replacement,startIndex);
		
		String paperText = story.getText();
		if(incomingWhiteSpacePriority) {
			String expected = "The cow\njumped over the moon.";
			assertEquals(expected,paperText);
		} else {
			String expected = "The cow jumped over the moon.";
			assertEquals(expected,paperText);
		}
	}
	
	/*********************** Collision Tests ***********************/
	@Test
	public void editorShouldNotCollideWithSpecialWhiteSpaceCharacters() {
		String text = "The cow jumped over the moon.\nmooooo!";
		Paper story = new Paper(text);
		
		// Starting at new line
		int startIndex = 29;
		String replacement = "oink";
		Editor.editOnPaper(story,replacement,startIndex);
		
		String paperText = story.getText();
		if(incomingTextPriority) {
			String expected = "The cow jumped over the moon.o@@@ooo!";
			assertEquals(expected,paperText);
		} else {
			String expected = "The cow jumped over the moon.\n@@@ooo!";
			assertEquals(expected,paperText);
		}
	}	
	
	@Test
	public void editorShouldCollideWithAllNonWhiteSpaceCharactersWhenEditing() {
		String text = "The cow jumped over the moon.mooooo!";
		Paper story = new Paper(text);
		
		// Starting at new line
		int startIndex = 29;
		String replacement = "oiink";
		Editor.editOnPaper(story,replacement,startIndex);
		
		String expected = "The cow jumped over the moon.@@@@@o!";
		String paperText = story.getText();
		assertEquals(expected,paperText);
	}
	
	@Test
	public void editorShouldNotCollideBlankSpacesFromReplacementText() {
		String text = "The     jumped over the moon.";
		Paper story = new Paper(text);
		
		// Starting at new line
		int startIndex = 4;
		String replacement = "cow ran over";
		Editor.editOnPaper(story,replacement,startIndex);
		
		String expected = "The cow @@@p@@e@ver the moon.";
		String paperText = story.getText();
		assertEquals(expected,paperText);
	}
	
	@Test
	public void editorShouldNotCollideSpecialWhiteSpaceFromReplacementText() {
		String text = "The     jumped over the moon.";
		Paper story = new Paper(text);
		
		// Starting at new line
		int startIndex = 4;
		String replacement = "cow\nran\nover";
		Editor.editOnPaper(story,replacement,startIndex);
		
		String paperText = story.getText();
		if(incomingWhiteSpacePriority) {
			String expected = "The cow\n@@@p@@e@ver the moon.";
			assertEquals(expected,paperText);
		} else {
			String expected = "The cow @@@p@@e@ver the moon.";
			assertEquals(expected,paperText);
		}
	}
	
	/*********************** Overflow Tests ***********************/
	@Test
	public void editorShouldNotWriteToPaperIfReplacementTextGoesPastEndOfPaper() {
		String text = "The cow goes ";
		Paper story = new Paper(text);
		
		// Starting after 'goes'
		int startIndex = 12;
		String replacement = " moo!";
		Editor.editOnPaper(story,replacement,startIndex);
		
		String expected = text;
		String paperText = story.getText();
		assertEquals(expected,paperText);
	}

	/*********************** Edit Detection Testing ***********************/
	@Test
	public void editorShouldNotEditPaperIfStartIndexGreaterThanPaperLength() {
		Paper story = new Paper();
		
		int startIndex = 1;
		String replacement = "Hello World!";
		Editor.editOnPaper(story, replacement, startIndex);
		
		String expected = "";
		String paperText = story.getText();
		assertEquals(expected,paperText);
	}
	
	@Test
	public void editorShouldReturnFalseIfStartIndexGreaterThanPaperLength() {
		Paper story = new Paper();
		
		int startIndex = 1;
		String replacement = "Hello World!";
		boolean edited = Editor.editOnPaper(story, replacement, startIndex);
		
		assertFalse(edited);
	}
	
	@Test
	public void editorShouldReturnTrueIfStartIndexLessThanOrEqualToPaperLength() {
		String text = "She       me. She loves me not.";
		Paper story = new Paper(text);
		
		// Start 1 space after 'She'
		int startIndex = 4;
		String replacement = "loves";
		boolean edited = Editor.editOnPaper(story,replacement,startIndex);
		
		assertTrue(edited);
	}
}

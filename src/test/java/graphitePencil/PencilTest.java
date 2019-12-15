package graphitePencil;

import static org.junit.Assert.*;

import org.junit.Test;

public class PencilTest {
	
	@Test
	public void whenWritingPencilShouldLoseOneWritePointPerLowerCaseLetter() {
		Pencil pencil = new Pencil(10,10,100);
		Paper paper = new Paper();
		
		pencil.writeToPaper(paper, "a");
		int availablePoints = pencil.getWritePoints();
		
		assertEquals(9,availablePoints);
	}
	
	@Test
	public void pencilShouldNotWriteWhenOutOfWritePoints() {
		Pencil pencil = new Pencil(10,10,100);
		Paper paper = new Paper();
		
		pencil.writeToPaper(paper, "I am more than 10 characters");
	}

}

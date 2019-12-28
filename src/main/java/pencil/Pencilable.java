package pencil;

import editor.Editable;
import eraser.Erasable;
import writer.Writable;

/**
 * @author Adrian Hernandez
 *
 */
public interface Pencilable 
	extends Writable, Editable, Erasable  {
	
	// Resets your sharpen points to original defaults
	// and loses pencil length as well
	boolean sharpen();
	
	int getWritePoints();
	int getErasePoints();
	int getPencilLength();
	int getDefaultWritePoints();
}

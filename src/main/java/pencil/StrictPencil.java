package pencil;

import editor.EditableFactory;
import editor.Editables;
import eraser.AbstractErasable;
import eraser.ErasableFactory;
import eraser.Erasables;
import writer.WritableFactory;
import writer.Writables;

/** StrictPencil sets the writer, editor, and eraser to their strict versions.
 * This causes StrictPencil to process all forms of white space as a classic
 * blank space ' ' to help simplify all logic. Eraser will erase new lines.
 * Editor will write over new lines with text, but not replace them with new 
 * forms of whitespace. Writer will write ' ' for every form of whitespace.
 * 
 * @author Adrian Hernandez
 */
public class StrictPencil extends AbstractPencil {
	/** This constructor initializes the degradation values as specified, while
	 * setting defaultWritePoints and writePoints to the same value
	 * @param writePoints default and initialization for write points of pencil
	 * @param erasePoints initialization for erase points of pencil
	 * @param pencilLength the amount of times pencil can be sharpened 
	 */
	public StrictPencil(int writePoints, int erasePoints, int pencilLength){
		super(writePoints,erasePoints,pencilLength);
		writer = WritableFactory.getWritable(Writables.StrictWriter);
		eraser = (AbstractErasable) ErasableFactory.getErasable(Erasables.StrictEraser);
		editor = EditableFactory.getEditable(Editables.StrictEditor);
		writingWithNoPoints = DullStyle.BlankSpaceOnly;
	}
	
	/** This constructor initializes the degradation values as specified
	 * @param writePoints initialization for write points of pencil                      
	 * @param erasePoints initialization for erase points of pencil      
	 * @param pencilLength amount of times pencil can be sharpened                   
	 * @param defaultWritePoints default value writePoints is set to after sharpening
	 */
	public StrictPencil(int initialWritePoints, int erasePoints, int pencilLength, int defaultWritePoints){
		super(initialWritePoints,erasePoints,pencilLength, defaultWritePoints);
		writer = WritableFactory.getWritable(Writables.StrictWriter);
		eraser = (AbstractErasable) ErasableFactory.getErasable(Erasables.StrictEraser);
		editor = EditableFactory.getEditable(Editables.StrictEditor);
		writingWithNoPoints = DullStyle.BlankSpaceOnly;
	}
}

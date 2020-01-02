package pencil;

import editor.EditableFactory;
import editor.Editables;
import eraser.AbstractErasable;
import eraser.ErasableFactory;
import eraser.Erasables;
import writer.WritableFactory;
import writer.Writables;

/** This pencil is what I believe best models the pencil as described by the
 * kata. There were a lot of assumptions with special whitespace processing 
 * that I made which might not be correct. If it isn't, I made a custom class
 * from which I can easily swap out the editor, writer, eraser and dull point
 * processing to make the most "correct" kata pencil possible with ease.
 * @author Adrian Hernandez
 */
public class Pencil extends AbstractPencil {
	/** This constructor initializes the degradation values as specified, while
	 * setting defaultWritePoints and writePoints to the same value
	 * @param writePoints default and initialization for write points of pencil
	 * @param erasePoints initialization for erase points of pencil
	 * @param pencilLength the amount of times pencil can be sharpened 
	 */
	public Pencil(int writePoints, int erasePoints, int pencilLength){
		super(writePoints,erasePoints,pencilLength);
		writer = WritableFactory.getWritable(Writables.Writer);
		eraser = (AbstractErasable) ErasableFactory.getErasable(Erasables.Eraser);
		editor = EditableFactory.getEditable(Editables.WeakEditor);
		writingWithNoPoints = DullStyle.WhiteSpaceCompatible;
	}
	
	/** This constructor initializes the degradation values as specified
	 * @param writePoints initialization for write points of pencil                      
	 * @param erasePoints initialization for erase points of pencil      
	 * @param pencilLength amount of times pencil can be sharpened                   
	 * @param defaultWritePoints default value writePoints is set to after sharpening
	 */
	public Pencil(int initialWritePoints, int erasePoints, int pencilLength, int defaultWritePoints){
		super(initialWritePoints,erasePoints,pencilLength, defaultWritePoints);
		writer = WritableFactory.getWritable(Writables.Writer);
		eraser = (AbstractErasable) ErasableFactory.getErasable(Erasables.Eraser);
		editor = EditableFactory.getEditable(Editables.WeakEditor);
		writingWithNoPoints = DullStyle.WhiteSpaceCompatible;
	}
}

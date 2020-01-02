package pencil;

import editor.EditableFactory;
import editor.Editables;
import eraser.AbstractErasable;
import eraser.ErasableFactory;
import eraser.Erasables;
import writer.WritableFactory;
import writer.Writables;

/**
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

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
	public Pencil(int writePoints, int erasePoints, int pencilLength){
		super(writePoints,erasePoints,pencilLength);
		writer = WritableFactory.getWritable(Writables.Writer);
		eraser = (AbstractErasable) ErasableFactory.getErasable(Erasables.Eraser);
		editor = EditableFactory.getEditable(Editables.WeakEditor);
		writingWithNoPoints = DullStyle.WhiteSpaceCompatible;
	}
	
	public Pencil(int initialWritePoints, int erasePoints, int pencilLength, int defaultWritePoints){
		super(initialWritePoints,erasePoints,pencilLength, defaultWritePoints);
		writer = WritableFactory.getWritable(Writables.Writer);
		eraser = (AbstractErasable) ErasableFactory.getErasable(Erasables.Eraser);
		editor = EditableFactory.getEditable(Editables.WeakEditor);
		writingWithNoPoints = DullStyle.WhiteSpaceCompatible;
	}
	
	
}

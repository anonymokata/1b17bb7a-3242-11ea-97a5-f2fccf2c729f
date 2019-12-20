package pencil;

import editor.EditableFactory;
import editor.Editables;
import eraser.AbstractErasable;
import eraser.ErasableFactory;
import eraser.Erasables;
import writer.WritableFactory;
import writer.Writables;

public class StrictPencil extends AbstractPencil {

	// Use in conjunction with Strict paper for newline free experience
	public StrictPencil(int writePoints, int erasePoints, int pencilLength){
		super(writePoints,erasePoints,pencilLength);
		writer = WritableFactory.getWritable(Writables.StrictWriter);
		eraser = (AbstractErasable) ErasableFactory.getErasable(Erasables.StrictEraser);
		editor = EditableFactory.getEditable(Editables.StrictEditor);
		writingWithNoPoints = DullStyle.BlankSpaceOnly;
	}
	
	public StrictPencil(int initialWritePoints, int erasePoints, int pencilLength, int defaultWritePoints){
		super(initialWritePoints,erasePoints,pencilLength, defaultWritePoints);
		writer = WritableFactory.getWritable(Writables.StrictWriter);
		eraser = (AbstractErasable) ErasableFactory.getErasable(Erasables.StrictEraser);
		editor = EditableFactory.getEditable(Editables.StrictEditor);
		writingWithNoPoints = DullStyle.BlankSpaceOnly;
	}
}

package pencil;

import editor.Editable;
import editor.EditableFactory;
import editor.Editables;
import eraser.AbstractErasable;
import eraser.ErasableFactory;
import eraser.Erasables;
import writer.Writable;
import writer.WritableFactory;
import writer.Writables;

// Can do something like 
// CustomPencil(2,4,5,6)
// .setWritable(Writer)
// .setEditable(LooseEditor)
// .setErasable(Erasable)
// .setDullStyle(BlankSpaceOnly)
public class CustomPencil extends AbstractPencil 
	implements CustomizablePencil<CustomPencil> {
	// Use in conjunction with Strict paper for newline free experience
	public CustomPencil(int writePoints, int erasePoints, int pencilLength){
		super(writePoints,erasePoints,pencilLength);
		writer = WritableFactory.getWritable(Writables.Writer);
		eraser = (AbstractErasable) ErasableFactory.getErasable(Erasables.Eraser);
		editor = EditableFactory.getEditable(Editables.StrongEditor);
		writingWithNoPoints = DullStyle.WhiteSpaceCompatible;
	}
	
	public CustomPencil(int initialWritePoints, int erasePoints, int pencilLength, int defaultWritePoints){
		super(initialWritePoints,erasePoints,pencilLength, defaultWritePoints);
		writer = WritableFactory.getWritable(Writables.StrictWriter);
		eraser = (AbstractErasable) ErasableFactory.getErasable(Erasables.StrictEraser);
		editor = EditableFactory.getEditable(Editables.StrongEditor);
		writingWithNoPoints = DullStyle.WhiteSpaceCompatible;
	}
	
	@Override
	public CustomPencil setWritable(Writable writer) {
		this.writer = writer;
		return this;
	}
	
	@Override
	public CustomPencil setEditable(Editable editor) {
		this.editor = editor;
		return this;
	}
	
	@Override
	public CustomPencil setErasable(AbstractErasable eraser) {
		this.eraser = eraser;
		return this;
	}
	
	@Override
	public CustomPencil setDullStyle(DullStyle writingWithNoPoints) {
		this.writingWithNoPoints = writingWithNoPoints;
		return this;
	}
}
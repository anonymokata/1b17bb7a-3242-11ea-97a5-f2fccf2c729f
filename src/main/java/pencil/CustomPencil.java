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

/** A "fluent" implementation to configure a custom pencil. Example:
 * CustomPencil(2,4,5,6)        
 * .setWritable(Writer)         
 * .setEditable(LooseEditor)    
 * .setErasable(Erasable)       
 * .setDullStyle(BlankSpaceOnly)
 * @author Adrian Hernandez
 */
public class CustomPencil extends AbstractPencil 
	implements CustomizablePencil<CustomPencil> {
	/** This constructor initializes the degradation values as specified, while
	 * setting defaultWritePoints and writePoints to the same value
	 * @param writePoints default and initialization for write points of pencil
	 * @param erasePoints initialization for erase points of pencil
	 * @param pencilLength the amount of times pencil can be sharpened             
	 */
	public CustomPencil(int writePoints, int erasePoints, int pencilLength){
		super(writePoints,erasePoints,pencilLength);
		writer = WritableFactory.getWritable(Writables.Writer);
		eraser = (AbstractErasable) ErasableFactory.getErasable(Erasables.Eraser);
		editor = EditableFactory.getEditable(Editables.StrongEditor);
		writingWithNoPoints = DullStyle.WhiteSpaceCompatible;
	}
	
	/** This constructor initializes the degradation values as specified
	 * @param writePoints initialization for write points of pencil                      
	 * @param erasePoints initialization for erase points of pencil      
	 * @param pencilLength amount of times pencil can be sharpened                   
	 * @param defaultWritePoints default value writePoints is set to after sharpening
	 */
	public CustomPencil(int writePoints, int erasePoints, int pencilLength, int defaultWritePoints){
		super(writePoints,erasePoints,pencilLength, defaultWritePoints);
		writer = WritableFactory.getWritable(Writables.StrictWriter);
		eraser = (AbstractErasable) ErasableFactory.getErasable(Erasables.StrictEraser);
		editor = EditableFactory.getEditable(Editables.StrongEditor);
		writingWithNoPoints = DullStyle.WhiteSpaceCompatible;
	}
	
	/**
	 * @param writer desired concrete Writable
	 * @return this
	 */
	@Override
	public CustomPencil setWritable(Writable writer) {
		this.writer = writer;
		return this;
	}
	
	/**
	 * @param editor desired concrete Editable
	 * @return this
	 */
	@Override
	public CustomPencil setEditable(Editable editor) {
		this.editor = editor;
		return this;
	}
	
	/**
	 * @param eraser desired concrete AbstractErasable
	 * @return the object that called this method ("return this")
	 */
	@Override
	public CustomPencil setErasable(AbstractErasable eraser) {
		this.eraser = eraser;
		return this;
	}
	
	/**
	 * @param dullWriting desired DullStyle to use when out of write points
	 * @return this
	 */
	@Override
	public CustomPencil setDullStyle(DullStyle dullWriting) {
		this.writingWithNoPoints = dullWriting;
		return this;
	}
}
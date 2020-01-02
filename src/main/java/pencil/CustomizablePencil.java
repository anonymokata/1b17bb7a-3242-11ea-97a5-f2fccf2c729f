package pencil;

import editor.Editable;
import eraser.AbstractErasable;
import writer.Writable;

// might need to use erasable
// TODO: confirm above
/** CustomizablePencil is able to create different combinations of 
 * writer, editor, eraser, and dull styles in case pencil was not
 * close enough to the kata description.
 * @author Adrian Hernandez
 * @param <T> The concrete class that is implementing this interface. You 
 * should return the object which called the function to model a fluent 
 * interface (roughly, a builder pattern without the .build portion):
 * pencil.setWritable(W)
 * .setEditable(E)
 * .setEraserable(A)
 * .setDullStyle(D)
 */
public interface CustomizablePencil<T> {
	/**
	 * @param writer desired concrete Writable
	 * @return this
	 */
	T setWritable(Writable writer);
	/**
	 * @param editor desired concrete Editable
	 * @return this
	 */
	T setEditable(Editable editor);
	/**
	 * @param eraser desired concrete AbstractErasable
	 * @return the object that called this method ("return this")
	 */
	T setErasable(AbstractErasable eraser);
	/**
	 * @param dullWriting desired DullStyle to use when out of write points
	 * @return this
	 */
	T setDullStyle(DullStyle dullWriting);
}

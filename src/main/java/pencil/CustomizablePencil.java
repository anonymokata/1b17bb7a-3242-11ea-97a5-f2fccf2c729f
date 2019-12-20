package pencil;

import editor.Editable;
import eraser.AbstractErasable;
import writer.Writable;

// might need to use erasable
// TODO: confirm above
public interface CustomizablePencil<T> {
	T setWritable(Writable writer);
	T setEditable(Editable editor);
	T setErasable(AbstractErasable eraser);
	T setDullStyle(DullStyle dullWriting);
}

package editor;

public class EditableFactory {
	
	// Return an instance of desired object
	static public Editable getEditable(Editables type) {
        Editable eraser = null;
        switch (type) {
	        case StrictEditor:
	            eraser = new StrictEditor();
	            break;
	        case Editor:
	            eraser = new Editor();
	            break;
	        default:
	            break;
        }
        return eraser;
	}
}

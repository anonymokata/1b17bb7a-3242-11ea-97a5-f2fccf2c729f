package editor;

public class EditableFactory {
	
	// Return an instance of desired object
	static public Editable getEditable(Editables type) {
        Editable eraser = null;
        switch (type) {
	        case StrongEditor:
	            eraser = new StrongEditor();
	            break;
	        case StrictEditor:
	            eraser = new StrictEditor();
	            break;	
	        case LooseEditor:
        		eraser = new LooseEditor();
        		break;
	        case WeakEditor:
	            eraser = new WeakEditor();
	            break;
	        default:
	            break;
        }
        return eraser;
	}
	
	static public StrongEditor getStrongEditor() {
		return new StrongEditor();
	}
	
	static public StrictEditor getStrictEditor() {
		return new StrictEditor();
	}
	
	static public LooseEditor getLooseEditor() {
		return new LooseEditor();
	}
	
	static public WeakEditor getWeakEditor() {
		return new WeakEditor();
	}
}

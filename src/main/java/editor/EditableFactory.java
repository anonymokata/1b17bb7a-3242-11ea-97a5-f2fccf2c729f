package editor;

/** A static factory that returns a Editable object using desired subclass.
 * Factory can also return type-specific instances of a desired subclass.
 * @author Adrian Hernandez
 */
public class EditableFactory {
	
	/** Create a new Editable instance from desired subclass
	 * @param type the enumerated concrete classes of Editable
	 * @return a new instance of Editable using desired subclass
	 */
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
	
	/** Get an instance of type StrongEditor
	 * @return a new instance of StrongEditor
	 */
	static public StrongEditor getStrongEditor() {
		return new StrongEditor();
	}
	
	/** Get an instance of type StrictEditor
	 * @return a new instance of StrictEditor
	 */
	static public StrictEditor getStrictEditor() {
		return new StrictEditor();
	}
	
	/** Get an instance of type LooseEditor
	 * @return a new instance of LooseEditor
	 */
	static public LooseEditor getLooseEditor() {
		return new LooseEditor();
	}
	
	/** Get an instance of type WeakEditor
	 * @return a new instance of WeakEditor
	 */
	static public WeakEditor getWeakEditor() {
		return new WeakEditor();
	}
}

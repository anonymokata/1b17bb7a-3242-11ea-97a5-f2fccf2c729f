package eraser;

/** A static factory that returns a Erasable object using desired subclass.
 * Factory can also return type-specific instances of a desired subclass.
 * @author Adrian Hernandez
 */
public class ErasableFactory {
	
	/** Create a new Erasable instance from desired subclass
	 * @param type the enumerated concrete classes of Erasable
	 * @return a new instance of Erasable using desired subclass
	 */
	static public Erasable getErasable(Erasables type) {
        Erasable eraser = null;
        switch (type) {
	        case StrictEraser:
	            eraser = new StrictEraser();
	            break;
	        case Eraser:
	            eraser = new Eraser();
	            break;
	        default:
	            break;
        }
        return eraser;
	}
	
	/** Get an instance of type StrictEraser
	 * @return a new instance of StrictEraser
	 */
	static public StrictEraser getStrictEraser() {
		return new StrictEraser();
	}
	
	/** Get an instance of type Eraser
	 * @return a new instance of Eraser
	 */
	static public Eraser getEraser() {
		return new Eraser();
	}
}

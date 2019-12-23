package writer;

/** A static factory that returns a Writeable object using desired subclass.
 * Factory can also return type-specific instances of a desired subclass.
 * @author Adrian Hernandez
 */
public class WritableFactory {
	
	/** Create a new Writeable instance from desired subclass
	 * @param type the enumerated concrete classes of Writeable
	 * @return a new instance of Writeable using desired subclass
	 */
	static public Writable getWritable(Writables type) {
        Writable writer = null;
        switch (type) {
        	case StrictWriter:
        		writer = new StrictWriter();
        		break;
	        case Writer:
	            writer = new Writer();
	            break;
	        default:
	            break;
        }
        return writer;
	}
	
	/** Get an instance of type Writer
	 * @return a new instance of Writer
	 */
	static public Writer getWriter() {
		return new Writer();
	}
	
	/** Get an instance of type StrictWriter
	 * @return a new instance of StrictWriter
	 */
	static public StrictWriter getStrictWriter() {
		return new StrictWriter();
	}
}

package writer;

public class WritableFactory {
	
	// Return an instance of desired object
	static public Writable getWritable(Writables type) {
        Writable writer = null;
        switch (type) {
        	case StrictWriter:
        		writer = new StrictWriter();
	        case Writer:
	            writer = new Writer();
	            break;
	        default:
	            break;
        }
        return writer;
	}
}

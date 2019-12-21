package writer;

public class WritableFactory {
	
	// Return an instance of desired object
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
	
	static public Writer getWriter() {
		return new Writer();
	}
	
	static public StrictWriter getStrictWriter() {
		return new StrictWriter();
	}
}

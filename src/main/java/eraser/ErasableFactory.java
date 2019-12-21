package eraser;

// tried to use generic for this but you can instantiate generics
// I don't want class objects being thrown around either
public class ErasableFactory {
	
	// Return an instance of desired object
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
	
	static public StrictEraser getStrictEraser() {
		return new StrictEraser();
	}
	
	static public Eraser getEraser() {
		return new Eraser();
	}
}

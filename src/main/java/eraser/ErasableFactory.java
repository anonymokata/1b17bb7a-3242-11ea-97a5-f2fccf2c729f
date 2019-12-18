package eraser;

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
}

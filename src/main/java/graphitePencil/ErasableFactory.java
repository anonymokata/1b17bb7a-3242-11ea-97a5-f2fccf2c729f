package graphitePencil;

public class ErasableFactory {
	
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

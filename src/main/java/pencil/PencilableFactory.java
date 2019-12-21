package pencil;

public class PencilableFactory {
	// Return an instance of desired object
	static public AbstractPencil getPencilable(Pencilables type, int writePoints,
			int erasePoints, int pencilLength) {
        AbstractPencil pencil = null;
        switch (type) {
        	case CustomPencil:
        		pencil = new CustomPencil(writePoints,erasePoints,pencilLength);
        		break;
        	case StrictPencil:
	            pencil = new StrictPencil(writePoints,erasePoints,pencilLength);
	            break;
	        case Pencil:
	            pencil = new Pencil(writePoints,erasePoints,pencilLength);
	            break;
	        default:
	            break;
        }
        return pencil;
	}
	
	// Return an instance of desired object
	static public AbstractPencil getPencilable(Pencilables type, int initialWritePoints,
			int erasePoints, int pencilLength, int defaultWritePoints) {
        AbstractPencil pencil = null;
        switch (type) {
        	case CustomPencil:
        		pencil = new CustomPencil(initialWritePoints,erasePoints,pencilLength,defaultWritePoints);
        		break;
        	case StrictPencil:
	            pencil = new StrictPencil(initialWritePoints,erasePoints,pencilLength,defaultWritePoints);
	            break;
	        case Pencil:
	            pencil = new Pencil(initialWritePoints,erasePoints,pencilLength,defaultWritePoints);
	            break;
	        default:
	            break;
        }
        return pencil;
	}
	
	static public CustomPencil getCustomPencil(int initialWritePoints, int erasePoints, 
			int pencilLength, int defaultWritePoints) {
		return new CustomPencil(initialWritePoints,erasePoints,pencilLength,defaultWritePoints);
	}
	
	static public CustomPencil getCustomPencil(int initialWritePoints, int erasePoints, 
			int pencilLength) {
		return new CustomPencil(initialWritePoints,erasePoints,pencilLength);
	}
	
	static public StrictPencil getStrictPencil(int initialWritePoints, int erasePoints, 
			int pencilLength, int defaultWritePoints) {
		return new StrictPencil(initialWritePoints,erasePoints,pencilLength,defaultWritePoints);
	}
	
	static public StrictPencil getStrictPencil(int initialWritePoints, int erasePoints, 
			int pencilLength) {
		return new StrictPencil(initialWritePoints,erasePoints,pencilLength);
	}
	
	static public Pencil getPencil(int initialWritePoints, int erasePoints, 
			int pencilLength, int defaultWritePoints) {
		return new Pencil(initialWritePoints,erasePoints,pencilLength,defaultWritePoints);
	}
	
	static public Pencil getPencil(int initialWritePoints, int erasePoints, 
			int pencilLength) {
		return new Pencil(initialWritePoints,erasePoints,pencilLength);
	}
}

package graphitePencil;

public class PencilableFactory {
	// Return an instance of desired object
	static public Pencilable getPencilable(Pencilables type, int writePoints,
			int erasePoints, int pencilLength) {
        Pencilable pencil = null;
        switch (type) {
        	case KataPencil:
        		pencil = new KataPencil(writePoints,erasePoints,pencilLength);
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
}

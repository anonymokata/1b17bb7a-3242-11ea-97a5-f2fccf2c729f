package pencil;

public class PencilableFactory {
	// Return an instance of desired object
	static public AbstractPencil getPencilable(Pencilables type, int writePoints,
			int erasePoints, int pencilLength) {
        AbstractPencil pencil = null;
        switch (type) {
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

package pencil;

/** A static factory that returns a Pencilable object using desired subclass.
 * Factory can also return type-specific instances of a desired subclass.
 * @author Adrian Hernandez
 */
public class PencilableFactory {
	/** Create a new Pencilable instance from desired subclass
	 * @param type the enumerated concrete classes of Pencilables
	 * @param writePoints the default and initialization for write points of pencil
	 * @param erasePoints the default and initialization for erase points of pencil
	 * @param pencilLength the amount of times pencil can be sharpened
	 * @return a new instance of Pencilable using desired subclass
	 */
	static public Pencilable getPencilable(Pencilables type, int writePoints,
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
	
	/** Create a new Pencilable instance from desired subclass
	 * @param type the enumerated concrete classes of Pencilables
	 * @param writePoints initialization for write points of pencil
	 * @param erasePoints the default and initialization for erase points of pencil
	 * @param pencilLength the amount of times pencil can be sharpened
	 * @param defaultWritePoints the default value writePoints is set to after sharpening
	 * @return a new instance of Pencilable using desired subclass
	 */
	static public Pencilable getPencilable(Pencilables type, int writePoints,
			int erasePoints, int pencilLength, int defaultWritePoints) {
        AbstractPencil pencil = null;
        switch (type) {
        	case CustomPencil:
        		pencil = new CustomPencil(writePoints,erasePoints,pencilLength,defaultWritePoints);
        		break;
        	case StrictPencil:
	            pencil = new StrictPencil(writePoints,erasePoints,pencilLength,defaultWritePoints);
	            break;
	        case Pencil:
	            pencil = new Pencil(writePoints,erasePoints,pencilLength,defaultWritePoints);
	            break;
	        default:
	            break;
        }
        return pencil;
	}
	
	/** Get an instance of type CustomPencil
	 * @param writePoints initialization for write points of pencil
	 * @param erasePoints the default and initialization for erase points of pencil
	 * @param pencilLength the amount of times pencil can be sharpened
	 * @param defaultWritePoints the default value writePoints is set to after sharpening
	 * @return a new instance of type CustomPencil
	 */
	static public CustomPencil getCustomPencil(int writePoints, int erasePoints, 
			int pencilLength, int defaultWritePoints) {
		return new CustomPencil(writePoints,erasePoints,pencilLength,defaultWritePoints);
	}
	
	/** Get an instance of type CustomPencil
	 * @param writePoints the default and initialization for write points of pencil
	 * @param erasePoints the default and initialization for erase points of pencil
	 * @param pencilLength the amount of times pencil can be sharpened
	 * @return a new instance of type CustomPencil
	 */
	static public CustomPencil getCustomPencil(int writePoints, int erasePoints, 
			int pencilLength) {
		return new CustomPencil(writePoints,erasePoints,pencilLength);
	}
	
	/** Get an instance of type StrictPencil
	 * @param writePoints initialization for write points of pencil
	 * @param erasePoints the default and initialization for erase points of pencil
	 * @param pencilLength the amount of times pencil can be sharpened
	 * @param defaultWritePoints the default value writePoints is set to after sharpening
	 * @return a new instance of type StrictPencil
	 */
	static public StrictPencil getStrictPencil(int writePoints, int erasePoints, 
			int pencilLength, int defaultWritePoints) {
		return new StrictPencil(writePoints,erasePoints,pencilLength,defaultWritePoints);
	}
	
	/** Get an instance of type StrictPencil
	 * @param writePoints the default and initialization for write points of pencil
	 * @param erasePoints the initialization for erase points of pencil
	 * @param pencilLength the amount of times pencil can be sharpened
	 * @return a new instance of type StrictPencil
	 */
	static public StrictPencil getStrictPencil(int writePoints, int erasePoints, 
			int pencilLength) {
		return new StrictPencil(writePoints,erasePoints,pencilLength);
	}
	
	/** Get an instance of type Pencil
	 * @param writePoints initialization for write points of pencil
	 * @param erasePoints the default and initialization for erase points of pencil
	 * @param pencilLength the amount of times pencil can be sharpened
	 * @param defaultWritePoints the default value writePoints is set to after sharpening
	 * @return a new instance of type Pencil
	 */
	static public Pencil getPencil(int writePoints, int erasePoints, 
			int pencilLength, int defaultWritePoints) {
		return new Pencil(writePoints,erasePoints,pencilLength,defaultWritePoints);
	}
	
	/** Get an instance of type Pencil
	 * @param writePoints the default and initialization for write points of pencil
	 * @param erasePoints the initialization for erase points of pencil
	 * @param pencilLength the amount of times pencil can be sharpened
	 * @return a new instance of type Pencil
	 */
	static public Pencil getPencil(int writePoints, int erasePoints, 
			int pencilLength) {
		return new Pencil(writePoints,erasePoints,pencilLength);
	}
}

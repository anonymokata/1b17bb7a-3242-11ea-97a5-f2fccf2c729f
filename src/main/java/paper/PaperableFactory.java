package paper;

/** A static factory that returns a Paperable object using desired subclass.
 * Factory can also return type-specific instances of a desired subclass.
 * @author Adrian Hernandez
 */
public class PaperableFactory {

	/** Create a new Paperable instance from desired subclass
	 * @param type the enumerated concrete classes of Paperable
	 * @return a new instance of Paperable using desired subclass
	 */
	static public Paperable getPaperable(Paperables type) {
        Paperable paper = null;
        switch (type) {
	        case StrictPaper:
	            paper = new StrictPaper();
	            break;
	        case Paper:
	            paper = new Paper();
	            break;
	        default:
	            break;
        }
        return paper;
	}
	
	/** Get an instance of type StrictPaper
	 * @return a new instance of StrictPaper 
	 */
	static public StrictPaper getStrictPaper() {
		return new StrictPaper();
	}
	
	/** Get an instance of type Paper
	 * @return a new instance of Paper
	 */
	static public Paper getPaper() {
		return new Paper();
	}
}

package paper;

public class PaperableFactory {

	// return desired type of paper
	static public Paperable getErasable(Paperables type) {
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
}

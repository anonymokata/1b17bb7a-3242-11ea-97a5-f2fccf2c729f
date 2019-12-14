package graphitePencil;

/**
 * @author Adrian Hernandez
 */
public class Pencil {
	private Paper paper;
	
	// Initialize blank paper if no string given
	Pencil(){
		this.setPaper(new Paper());
	}
	
	// Set paper to given string on instantiation
	Pencil(Paper paper){
		this.setPaper(paper);
	}

	public void writeToPaper(String newText) {
		Writer.writeToPaper(getPaper(), newText);
	}

	/**
	 * @return the paper
	 */
	public Paper getPaper() {
		return this.paper;
	}

	/**
	 * @param paper the paper to use
	 */
	public void setPaper(Paper paper) {
		this.paper = paper;
	}
	

}

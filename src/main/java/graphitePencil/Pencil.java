package graphitePencil;

/**
 * @author Adrian Hernandez
 */
public class Pencil {
	private String paper;
	
	// Initialize blank paper if no string given
	Pencil(){
		this.setPaper("");
	}
	
	// Set paper to given string on instantiation
	Pencil(String paper){
		this.setPaper(paper);
	}

	public void writeOnPaper(String message) {
		this.setPaper(this.getPaper() + message);
	}

	/**
	 * @return the paper
	 */
	public String getPaper() {
		return this.paper;
	}

	/**
	 * @param paper the paper to use
	 */
	public void setPaper(String paper) {
		this.paper = paper;
	}
	

}

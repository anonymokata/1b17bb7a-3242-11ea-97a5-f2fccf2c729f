package graphitePencil;

/**
 * @author Adrian Hernandez
 */
public class Pencil {
	private int graphitePoints;
	private int writePoints;
	private int erasePoints;
	
	// Set paper to given string on instantiation
	Pencil(int writePoints, int erasePoints, int graphitePoints){
		this.writePoints = writePoints;
		this.erasePoints = erasePoints;
		this.graphitePoints = graphitePoints;
	}

	public void writeToPaper(Paper paper, String newText) {
		int lowerCount = 0;
	}

	public int getWritePoints() {
		return this.writePoints;
	}

}

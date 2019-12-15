package graphitePencil;

/**
 * @author Adrian Hernandez
 */
public class Pencil {
	private int lifePoints;
	private int writePoints;
	private int erasePoints;
	
	// Set paper to given string on instantiation
	Pencil(int writePoints, int erasePoints, int lifPoints){
		this.lifePoints = 40;
		this.writePoints = 5;
		this.erasePoints = 15;
	}

	public void writeToPaper(String newText) {
	}



}

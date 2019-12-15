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

	public void writeToPaper(Paper paper, String writeText) {
		StringBuilder degradation = new StringBuilder(writeText);
		
		for(int i = 0; i < writeText.length(); i++) {
			char writeChar = writeText.charAt(i);
			if(writePoints > 0) {
				if(Character.isLowerCase(writeChar)) {
					writePoints--;
					graphitePoints--;
				}
				degradation.append(writeChar);
			}
		}
		
		String degraded = degradation.toString();
		Writer.writeToPaper(paper, degraded);
	}

	public int getWritePoints() {
		return this.writePoints;
	}
	
	public int getGraphitePoints() {
		return this.graphitePoints;
	}

	public int getErasePoints() {
		return this.erasePoints;
	}

}

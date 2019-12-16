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
		StringBuilder degradation = new StringBuilder();
		
		for(int i = 0; i < writeText.length(); i++) {
			char writeChar = writeText.charAt(i);
			characterPointWriting(degradation,writeChar);
		}
		
		String degraded = degradation.toString();
		System.out.println(degraded);
		Writer.writeToPaper(paper, degraded);
	}
	
	private void characterPointWriting(StringBuilder degradation, char writeChar) {
		if(writePoints > 0) {
			if(Character.isLowerCase(writeChar)) {
				writePoints--;
				graphitePoints--;
				degradation.append(writeChar);
			} else if(Character.isUpperCase(writeChar)) {
				if(writePoints > 1) {
					writePoints -= 2;
					graphitePoints -= 2;
					degradation.append(writeChar);
				}  else {
					// penalize for attempting to write without enough points
					// essentially zero out and place a blank space
					writePoints--;
					graphitePoints--;						
					degradation.append(' ');
				}
			} else if(!Character.isWhitespace(writeChar)){
				// default to one for anything else that isn't whitespace
				writePoints--;
				graphitePoints--;
				degradation.append(writeChar);
			}
		} else {
			degradation.append(' ');
		}
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

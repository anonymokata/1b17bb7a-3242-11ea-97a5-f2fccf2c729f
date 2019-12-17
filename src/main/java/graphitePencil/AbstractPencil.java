package graphitePencil;

public abstract class AbstractPencil {
	protected int writePoints;
	protected int erasePoints;
	protected int pencilLength;
	protected final int defaultWritePoints;
	
	public AbstractPencil(int writePoints, int erasePoints, int pencilLength){
		this.writePoints = writePoints;
		this.erasePoints = erasePoints;
		this.pencilLength = pencilLength;
		this.defaultWritePoints = writePoints;
	}
	
	abstract public void writeToPaper(Paper paper, String writeText);
	
	abstract public void editOnPaper(Paper paper, String editText, int startIndex);
	
	abstract public void eraseFromPaper(Paper paper, String eraseText);
	
	public boolean sharpen() {
		if(pencilLength > 0) {
			// Only reset if we still have length on pencil
			writePoints = defaultWritePoints;
			pencilLength--;
			
			// notify user of successful sharpening
			return true;			
		} else {
			// notify user of failed sharpening
			return false;
		}
	}
	
	public int getWritePoints() {
		return writePoints;
	}

	public int getErasePoints() {
		return erasePoints;
	}
	
	public int getPencilLength() {
		return pencilLength;
	}
	
	public int getDefaultWritePoints() {
		return defaultWritePoints;
	}
}

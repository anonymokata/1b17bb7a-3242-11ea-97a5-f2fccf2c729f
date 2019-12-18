package pencil;

public class StrictPencil extends Pencilable {

	public StrictPencil(int writePoints, int erasePoints, int pencilLength) {
		super(writePoints, erasePoints, pencilLength);
		// TODO Auto-generated constructor stub
	}

	@Override
	void characterPointWriting(StringBuilder degradation, char writeChar) {
		// TODO Auto-generated method stub

	}

	@Override
	void collisionPointWriting(StringBuilder degradation) {
		// TODO Auto-generated method stub

	}

	@Override
	int characterPointErasing(String text) {
		// TODO Auto-generated method stub
		return 0;
	}

}

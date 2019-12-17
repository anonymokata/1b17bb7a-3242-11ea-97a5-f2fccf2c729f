package paper;

public class StrictPaper extends Paper {
	@Override
	public void setText(String text) {
		String replaceSpace = text.replaceAll("\\s", " ");
		super.setText(replaceSpace);
	}
	
}

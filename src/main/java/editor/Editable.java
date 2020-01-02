package editor;

import paper.Paperable;

/** Editables in the editor package will only edit existing text from paper.
 * Any excess going past end of paper from replacement string will be ignored. 
 * They accept an instance of paper to edit and return true if startIndex was 
 * within the paper's length.
 * 
 * IMPORTANT NOTES: 
 * For editor package, all concrete editors will never collide incoming newlines 
 * with text on paper. IE All incoming white space does not affect text on paper.
 * 
 * For editor package, all concrete editors will never handle overflow text. Once 
 * the end of the paper is reached, the editing ends even if there was more 
 * incoming string to parse.
 * 
 * For editor package, all concrete editors will still clash identical characters.
 * IE Trying to add a 'B' over a 'B' on paper will result in '@'
 * 
 * 
 * WS = whitespace
 * There are four variations of our editors that handle two ambiguous scenarios.
 * The first scenario is if incoming text should overwrite a paper's special WS.
 * The second scenario is if incoming WS should overwrite a paper's special WS.
 * I decided to handle all 4 combinations. The top of the concrete classes will
 * include comments of the two scenarios above and answer Yes or No to them.
 * 
 * @author Adrian Hernandez
 */
public interface Editable {
	/** editOnPaper will insert all text into allowed whitespace on paper and 
	 * will clash all text on text with a '@' character. For editor package, 
	 * implementation varies on handling special white space (non blank spaces).
	 * @param paper The instance of paper that should be edited
	 * @param replacementText The replacementText that should be inserted
	 * @param startIndex The index the text should be inserted
	 * @return Return true if index was in Paper index range
	 */
	boolean editOnPaper(Paperable paper, String replacementText, int startIndex);
}

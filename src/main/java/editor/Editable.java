package editor;

import paper.Paperable;

/* IMPORTANT NOTES:
 * For editor package, all concrete editors will never collide incoming newlines 
 * with text on paper. IE All incoming white space does not affect text on paper.
 * 
 * For editor package, all concrete editors will never handle overflow text. Once 
 * the end of the paper is reached, the editing ends even if there was more 
 * incoming string to parse.
 * 
 * WS = whitespace
 * There are four variations of our editors that handle two ambiguous scenarios.
 * The first scenario is if incoming text should overwrite a paper's special WS.
 * The second scenario is if incoming WS should overwrite a paper's special WS.
 * I decided to handle all 4 combinations. The top of the concrete classes will
 * include comments of the two scenarios above and answer Yes or No to them.
 */
public interface Editable {
	boolean editOnPaper(Paperable paper, String replacementText, int startIndex);
}

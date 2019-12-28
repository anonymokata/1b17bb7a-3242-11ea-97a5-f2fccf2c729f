package pencil;

/** DullStyle specifies how a pencil should write when out of 
 * write points. 
 * 
 * Given "a\nb" and zero write points:
 * 1.) BlankSpaceOnly would yield "   "
 * 2.) WhiteSpaceCompatible would yield " \n "
 * @author Adrian Hernandez
 */
public enum DullStyle {
	BlankSpaceOnly, WhiteSpaceCompatible
}

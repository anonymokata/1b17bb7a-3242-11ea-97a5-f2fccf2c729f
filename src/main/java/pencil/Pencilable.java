package pencil;

import editor.Editable;
import eraser.Erasable;
import writer.Writable;

/** A pencilable can perform all the functions of a Writable, Editable, & Erasable
 * with degrading characteristics for all the implemented classes. This helps to 
 * more accurately model a graphite pencil.
 * @author Adrian Hernandez
 */
public interface Pencilable 
	extends Writable, Editable, Erasable  {
	
	/** Set writePoints back to its default if pencilLength allows it and reduces
	 * pencilLength if successful
	 * @return whether or not pencil was sharpened
	 */
	boolean sharpen();
	
	/** writePoints models a pencil tip. Any editing/writing should degrade 
	 * the amount of points available and should never return a negative value.
	 * The implementer will decide how points are degraded.
	 * @return number of points left for writing
	 */
	int getWritePoints();
	/** erasePoints models a pencil's eraser. Any erasing should degrade the amount
	 * of points available and should never return a negative value. Since pencil
	 * erasers are finite and can not be replace, no "sharpening" is available.
	 * The implementer will decide how points are degraded.
	 * @return number of points left for erasing 
	 */
	int getErasePoints();
	/** pencilLength is the number of times a pencil can be sharpened. 
	 * 
	 * Alternative Implementation Idea: 
	 * pencilLength can also be the total number of write points a pencil can have where
	 * sharpening reduces from the amount of total write points.
	 * @return number of times pencil can be sharpened
	 */
	int getPencilLength();
	/** defaultWritePoints models a pencil tip's limits. This is the total amount of
	 * writePoints a pencil tip can use before needing to be sharpened. Sharpening will
	 * reset writePoints back to this number. 
	 * @return number of available writePoints after a sharpen
	 */
	int getDefaultWritePoints();
}

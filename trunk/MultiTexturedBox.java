/**
 * @author Lars
 * MultiTexturedBox is a subclass of Shape3D (currently a subclass of Box)
 * which allows the use of different textures on each of its six faces.
 */

import com.sun.j3d.utils.geometry.*;

// Q: will different Facelets on the same Face have the same Appearance,
// given that they share a Texture but use it at different offsets?

public class MultiTexturedBox extends Box {

	// use e.g. getShape(Box.LEFT).setAppearance(...)
}

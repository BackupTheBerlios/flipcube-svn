/* Face.java
 * Copyright (c) 2003 by Lars Huttar.
 * Distributed under GPL.  No warranty.
 */

// Data structure for each facelet of the FlipCube.
// Keeps track of what face it belongs to, and what part of the face's image to display.

// import com.sun.j3d.utils.geometry.*;
// import javax.media.j3d.*;
// import javax.vecmath.*;

public class Facelet {

    Face face; // face that this facelet belongs to
    int uIndex, vIndex; // what part of the face this facelet is
    
	public Facelet(Face f, int u, int v) { // constructor for Facelet
		// parameters:
		//    face of which this is a part
        //    offset of this facelet within larger face (to know what part of image to show)		
		face = f;
        uIndex = u;
        vIndex = v;
	}

}

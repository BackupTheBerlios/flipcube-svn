/* Cubelet.java
 * Copyright (c) 2003 by Lars Huttar.
 * Distributed under GPL.  No warranty.
 */

// A cubelet is one of the eight small cubes that makes up
// the larger cube.
// A cubelet has six facelets.

import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.image.TextureLoader;
import java.net.URL;

public class Cubelet extends MultiTexturedBox {
    public static final float cubeletSize = 0.1f;

    Facelet facelets[] = new Facelet[6];
    // Each cubelet has six facelets.
    // Order of facelets in the array is: -x, +x, -y, +y, -z, +z.

    int index; 			// which # of cubelet this is (0-7).

    public TransformGroup objTG; // The TG that is the parent of this cubelet.

    static Appearance dummyApp = new Appearance(); // for super() constructor

    public Cubelet (int i, TransformGroup parentTG, Canvas3D canvas3D) {
    	// Call the superclass constructor for Box
    	super(cubeletSize, cubeletSize, cubeletSize,
    			Box.GENERATE_TEXTURE_COORDS, dummyApp);
    	this.index = i;
    	this.objTG = parentTG;
    	
    	// Create appearance object for textured cube
    	Appearance app = new Appearance();
    	// ## do we want a new textureloader for each cublet?
        // ## Need to read these filenames from a directory or a file...
    	Texture tex = new TextureLoader("images/256/hippo.jpg", canvas3D).getTexture();
    	app.setTexture(tex);
    	// TextureAttributes texAttr = new TextureAttributes();
    	// texAttr.setTextureMode(TextureAttributes.MODULATE); // ???##
    	// app.setTextureAttributes(texAttr);
    	this.setAppearance(app);
    }
    
    // Data for each cubelet:
    //   - its offset from the previous one (translation)
    //   {?- its initial orientation, as a rotation from the previous
    //     one's orientation (around what axis??)}
    //   - the axis around which it can swivel (a point and a vector,
    //     using right-handed rule for ccw) on hinge to previous cubelet.
    //     Relative to origin (0, 0, 0) of this cubelet.
    //  First cubelet is treated as if relative to last cubelet, as though
    //  they were a ring. (Maybe it should instead be relative to origin
    //  of coord system? Or of whole cube??)
    
    static class CubeletData {
    	Vector3f offset;
    	Vector3f hingeAxis;
    	Vector3f hingePoint;
    	
    	public CubeletData(Vector3f o, Vector3f a, Vector3f p) {
    		offset = o;
    		hingeAxis = a;
    		hingePoint = p;
    	}
    }

    static CubeletData[] cubeletData = new CubeletData[] {
		// cubelet a (000)
		new CubeletData(new Vector3f(-1f, 0f, 0f), // offset
				new Vector3f(0f, -1f, 0f), // hingeAxis: y, clockwise
				new Vector3f(1f, 0f, 0f)), // hingePoint
		// cubelet c (010)
		new CubeletData(new Vector3f(0f, 1f, 0f),  // offset
				new Vector3f(0f, 0f, 1f),  // hingeAxis
				new Vector3f(0f, 0f, 0f)), // hingePoint
		// cubelet g (011)
		new CubeletData(new Vector3f(0f, 0f, 1f),  // offset
				new Vector3f(0f, -1f, 0f), // hingeAxis
				new Vector3f(0f, 0f, 0f)), // hingePoint
		// cubelet e (001)
		new CubeletData(new Vector3f(0f, -1f, 0f), // offset
				new Vector3f(0f, 0f, -1f), // hingeAxis
				new Vector3f(0f, 1f, 0f)), // hingePoint
    	// cubelet f (101)
    	new CubeletData(new Vector3f(1f, 0f, 0f),  // offset
    			new Vector3f(0f, -1f, 0f), // hingeAxis
    			new Vector3f(0f, 0f, 1f)), // hingePoint
    	// cubelet h (111)
    	new CubeletData(new Vector3f(0f, 1f, 0f),  // offset
    			new Vector3f(0f, 0f, -1f), // hingeAxis
    			new Vector3f(1f, 0f, 0f)), // hingePoint
    	// cubelet d (110)
    	new CubeletData(new Vector3f(0f, 0f, -1f), // offset
    			new Vector3f(1f, 0f, 0f),  // hingeAxis
    			new Vector3f(0f, 1f, 1f)), // hingePoint
    	// cubelet b (100)
    	new CubeletData(new Vector3f(0f, -1f, 0f), // offset
    			new Vector3f(0f, 0f, 1f),  // hingeAxis
    			new Vector3f(1f, 1f, 0f)), // hingePoint
    };

    static URL getImageURL(String filename) {
    	try {
    		return (new java.net.URL(filename));
    	}
    	catch (java.net.MalformedURLException ex) {
    		System.out.println(ex.getMessage());
    		System.exit(1);
    	}
    	return null;
    }
}

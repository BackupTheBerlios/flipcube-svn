/* Face.java - part of FlipCube application
 * Copyright (c) 2004 by Lars Huttar.
 * Distributed under GPL.  No warranty.
 */

// Face - class for faces of the large cube.
// A face consists of four to eight facelets, and has a
// single image which is spread across the facelets.
// 
public class Face {
    //   image (shared by 4 or 8 cubelet faces)
    //   transformation of image
    //     scaling: stretch to fit face or not;
    //        also stretch to fit quadrant of image on facelet.
    //     center?
    //     rotation: orientation of image. (If we set up the faces right,
    //       there may be no need to rotate any images -- unless we need
    //       this to be configurable, e.g. for 2x4 tall images.)

    //##image;
    int uScale, vScale; // is the image 2x2 facelets? or 2x4? or 4x2?
    int index; // which face is this, in the faces array?
    //##orientation; // expressed how?
    
    // probable parameters:
    //    Picture, dimensions of picture in cubelet faces (2x2, 2x4, 4x2)
    //    location of this facelet
    
	public Face(int i, String fnImage, int u, int v /* index, image, height, width, scaling, orientation? */) {
        // load image...
		// set width & height of this face based on size of image
        uScale = u;
        vScale = v;
        // set scaling, center pic

    }
    
    static Face[] initFaces() {
    	Face[] faces = new Face[9];
    	int u, v; // width & height of this face in facelets
        for (int i=0; i < faces.length; i++) {
        	if (i >= 6) u = 4; else u = 2;
            v = 2;
            faces[i] = new Face(i, image_names[i], u, v /* , ... */);
        }
        return faces;
    }
    
    static String[] image_names = {
    		"hippo", "camel", "boat", "elephants", "natpark", "ostrich",
			"coatofarms-w", "kenyamap-w", "nairobi-w"
    };
}

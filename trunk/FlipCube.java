/* FlipCube.java
 * Copyright (c) 2003 by Lars Huttar.
 * Distributed under GPL.  No warranty.
 */

//Main class of FlipCube screensaver application.
//Displays a folding cube, made of 8 cubelets,
//with images on their faces.

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;
// import java.awt.event.*;
import java.awt.GraphicsConfiguration;
import com.sun.j3d.utils.applet.MainFrame; 
import com.sun.j3d.utils.universe.*; 
// import com.sun.j3d.utils.geometry.ColorCube;
import javax.media.j3d.*;
import javax.vecmath.*;

//The main class

public class FlipCube extends Applet {
	
	//  Allow this class to be run as an application or an applet.
	public static void main(String[] args) {
        FlipCube fc = new FlipCube(); 
		Frame frame = new MainFrame(fc, 256, 256);
        fc.mainLoop();
	}
	
	public Canvas3D canvas3D;
	
	Cubelet cubelets[] = new Cubelet[8];
    Face[] faces;
	
	int currState = 6; 		// index of current State
	boolean done = false;   // Turn this on to stop the program.  

    static State[] states;
    
	public FlipCube () {
        // initialize array of possible states
        states = State.initStates();
        faces = Face.initFaces();
        
        setLayout(new BorderLayout());
		GraphicsConfiguration config =
			SimpleUniverse.getPreferredConfiguration();
		this.canvas3D = new Canvas3D(config);
		add("Center", canvas3D);
		
		// use SimpleUniverse convenience utility class
		SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
		// Move ViewPlatform back a bit so objects are visible.
		simpleU.getViewingPlatform().setNominalViewingTransform();
		
		BranchGroup scene = createSceneGraph();
		simpleU.addBranchGraph(scene);

	} // end of FlipCube (constructor)
	
	public BranchGroup createSceneGraph() {
		// Create the root of the branch graph
		BranchGroup objRoot = new BranchGroup();
		TransformGroup objMain = new TransformGroup();
		objRoot.addChild(objMain);
		
		// Rotate the main TG (about y axis)
		Alpha rotationAlpha = new Alpha(-1, 15000);
		RotationInterpolator rotator =
			new RotationInterpolator(rotationAlpha, objMain);
		BoundingSphere bounds = new BoundingSphere();
		rotator.setSchedulingBounds(bounds);
		objMain.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		objMain.addChild(rotator);
		
		// Create a chain of TG's, each the child of the previous,
		// and each with its own translation/rotation, and each with
		// a cubelet child.
		TransformGroup objPrev = objMain;
		TransformGroup objTG;
		
		for (int i = 0; i < 8; i++) {
			objTG = new TransformGroup(getTranslation(i));
			objPrev.addChild(objTG);
			objPrev = objTG;
			
			objTG = new TransformGroup(getRotation(i));
			objTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			
			cubelets[i] = new Cubelet(i, objTG, canvas3D);
			objTG.addChild(cubelets[i]);
			
			objPrev.addChild(objTG);
			objPrev = objTG;
		}
		
		// Rotate the fourth TG (about its z axis)
		Alpha alphaSpin = new Alpha(-1, 15000);
		objTG = cubelets[4].objTG;
		Transform3D zAxis = new Transform3D();
		// To make a z axis, we make a Transform3D that transforms
		// the default (a y axis) into our z axis.  Therefore our
		// transform has to be a 90-degree rotation around the x axis.
		// :-p !!
		zAxis.rotX(Math.PI/2.0f);
		RotationInterpolator spinner =
			new RotationInterpolator(alphaSpin, objTG,
					zAxis, 0.0f, (float)Math.PI);
		BoundingSphere bounds2 = new BoundingSphere();
		spinner.setSchedulingBounds(bounds2);
		// objTG.addChild(spinner);
		
		return objRoot;
	} // end of CreateSceneGraph method of FlipCube
	
	static private Transform3D translate = new Transform3D();
	static private Vector3f vec = new Vector3f();
	
	private Transform3D getTranslation(int i) {
		Cubelet.CubeletData cd = Cubelet.cubeletData[i];
        // I don't understand why the *2 is necessary, but without it,
        // the cubelets overlap on the screen.
		vec.scale(Cubelet.cubeletSize*2, cd.offset);
		translate.setTranslation(vec);
		return translate;
	}
	
	static private Transform3D rotate = new Transform3D();
	static private AxisAngle4f aa = new AxisAngle4f();
	
    // set up static variable as a transform for given cubelet?##
	private Transform3D getRotation(int i) {
		Cubelet.CubeletData cd = Cubelet.cubeletData[i];
		rotate.setIdentity();
		if (states[currState].hingeRots[i] == 1) {
			// translate to hingePoint
			// ## rotate.setTranslation(cd.hingePoint);
			// rotate around hingeAxis
			aa.set(cd.hingeAxis, (float)Math.PI);
			//## rotate.
			// translate back from hingePoint
			//## translate.setTranslation(..);
			//## rotate...;
			// aa.set(1f, 0f, 0f, (float)(Math.PI/20.0));
			// no rotation for now...
			//## rotate.setRotation(..);
		}
		return rotate;
	}
	
	public void mainLoop() {
        // while (!done) {
        	//## pick a successor state
            
            //## transition to that state
   
            //## Wait till transition is done
        
        // }
    }      
} // end of class FlipCube

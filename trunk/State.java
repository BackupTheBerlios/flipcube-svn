/* State.java - part of FlipCube application
 * Copyright (c) 2003 by Lars Huttar.
 * Distributed under GPL.  No warranty.
 */

// State class: manage the state of a FlipCube.
// State is a model of a particular configuration of the cube,
// including its orientation, and to what degree each of its hinges are
// folded (0 or 180 degrees). Then transitions from
// state to state can be computed. Some states are not for display;
// they are only intermediate points between two display states.

import javax.media.j3d.*;
import javax.vecmath.*;

class State {
    // Note: hinges really do have only two useful rotation states, 0 or 180
    // degrees. 90 degrees is never useful. Try it.
	int[] hingeRots;    // 0 or 1
	Transform3D rotation;
	int[] adjacentStates;
	
	public State(int[] hinges, Transform3D rot, int[] adjs) {
		this.hingeRots = hinges;
		this.rotation = rot;
		this.adjacentStates = adjs;
	}
    
    private static int[] zeroes = {0, 0, 0, 0, 0, 0, 0, 0};
    static final float d90 = (float)(Math.PI/2.0);
    static final float d180 = (float)(Math.PI);
    
    static State[] initStates() {
        return new State[] {
            // First six states involve no folding, only rotation.
            new State(zeroes, new Transform3D(),  // state 0
                    new int[] {1, 2, 3, 4, 5, 6, 7}),
            new State(zeroes, new Transform3D(),  // state 1
                    new int[] {0, 2, 3, 4, 5, 6, 7}),
            new State(zeroes, new Transform3D(),  // state 2
                    new int[] {0, 1, 3, 4, 5, 6, 7}),
            new State(zeroes, new Transform3D(),  // state 3 
                    new int[] {0, 1, 2, 4, 5, 6, 7}),
            new State(zeroes, new Transform3D(),  // state 4
                    new int[] {0, 1, 2, 3, 5, 6, 7}),
            new State(zeroes, new Transform3D(),  // state 5
                    new int[] {0, 1, 2, 3, 4, 6, 7}),
            // (un)folding states:
            // This is opening the top.  // state 6
            new State(new int[] {0, 1, 0, 1, 0, 1, 0, 1},
                    // rotate 90 about x axis
                    makeEulerTransform(d90, 0f, 0f),
                    new int[] {0, 1, 2, 3, 4, 5}),
            // This is opening the bottom. // state 7
            new State(new int[] {0, 0, 1, 0, 0, 0, 1, 0},
                    makeEulerTransform(0f, 0f, d90), //##?
                    new int[] {0, 1, 2, 3, 4, 5, 8}),
            // This is opening the bottom twice (to the center). // state 8
            new State(new int[] {1, 0, 1, 0, 1, 0, 1, 0},
                    makeEulerTransform(-d90, -d90, 0f), //##?
                    new int[] {7}),
            // Should add an intermediate state or two to get from state 8
            // to one of the other states besides 7. There are two "useless"
            // foldings that I know of.
            // (There are six possible foldings of the cube. The first,
            // with all-zero hinge folding, has six useful orientations
            // [the six outer faces]. That accounts for states 0-5.
            // Three other foldings are useful, states 6-8. That leaves
            // two "useless" foldings.
            // However, if the pictures on the cube were configured
            // differently, the set of useful and useless foldings might
            // be different.)
        };
    }

    // The idea here is to create transformations statically, rather
    // than using multiple statements, for the static initialization in FlipCube.initStates().
    // An Euler transform is a rotation matrix formed by concatenating
    // a rotation around the global X axis, a rotation around global Y, and
    // a rotation around global Z. This can get problematic if more than one of
    // these rotations is non-zero, but in this case I'm trying the setEuler
    // method as a convenient way to express just a single rotation about any
    // one of the three axes.
    static Vector3d v;
    static Transform3D makeEulerTransform(double x, double y, double z) {
        if (v == null) v = new Vector3d();
        v.set(x, y, z);
        Transform3D t = new Transform3D();
        t.setEuler(v);
        return t;
    }

}

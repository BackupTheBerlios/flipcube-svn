/* Lars Huttar (c) 2004 */

/* Render a mockup of a flipcube with images on the faces.
   For http://flipcube.berlios.de/
*/


/* To do:
 - figure out what I'm doing with the translation of the image_map, don't just try-and-error.
 */
 
#include "shapes.inc"
#include "transforms.inc"

camera {
   perspective
  #declare cam_loc=<2,4,4>*0.8;
  #declare targ=<0,0,0>;
   look_at targ
   location cam_loc 
   up y
   right -4*x/3
   angle 60
}

global_settings {
   ambient_light
   rgb <1.0,1.0,1.0>
}

background {
   color rgb <0,0,0>
}

light_source {
   <3,5,5>
   color rgb 1.5
   shadowless
}

#declare faceletx0 = union { Quad(<0,0,0>, <0,1,0>, <0,1,1>, <0,0,1>) pigment {color rgb <1,0,1>}};
#declare faceletx1 = union { Quad(<1,0,0>, <1,0,1>, <1,1,1>, <1,1,0>) pigment {color rgb <1,0,1>}};
#declare facelety0 = union { Quad(<0,0,0>, <0,0,1>, <1,0,1>, <1,0,0>) pigment {color rgb <1,0,1>}};
#declare facelety1 = union { Quad(<0,1,0>, <1,1,0>, <1,1,1>, <0,1,1>) pigment {color rgb <1,0,1>}};
#declare faceletz0 = union { Quad(<0,0,0>, <1,0,0>, <1,1,0>, <0,1,0>) pigment {color rgb <1,0,1>}};
#declare faceletz1 = union { Quad(<0,0,1>, <0,1,1>, <1,1,1>, <1,0,1>) pigment {color rgb <1,0,1>}};


// cubelet a
union {
  object { faceletx0 }
  object { faceletx1 }
  object { facelety0 }
  object { facelety1 
    pigment { image_map { jpeg "../images/256/kenyamap-w.jpg" }
      rotate x*90 scale <4,1,-2> translate -1*x} }
  object { faceletz0 }
  object { faceletz1 }
  translate <-1,-1,-1>
}

// cubelet b
union {
  object { faceletx0 }
  object { faceletx1 }
  object { facelety0 
    pigment { image_map { jpeg "../images/256/kenyamap-w.jpg" }
      rotate x*90 scale <-4,1,-2> translate -3*x} }
  object { facelety1 }
  object { faceletz0 }
  object { faceletz1 }
  translate <-1,0,-1>
  Rotate_Around_Trans(z*135, -x)
}

// cubelet c
union {
  object { faceletx0 }
  object { faceletx1 }
  object { facelety0  
    pigment { image_map { jpeg "../images/256/kenyamap-w.jpg" }
      rotate x*90 scale <-4,1,-2> translate -3*x-z} }
  object { facelety1 }
  object { faceletz0 }
  object { faceletz1
    pigment { image_map { jpeg "../images/256/elephants.jpg" }
      scale 2 translate -y } }
  translate <-1,0,0>
  Rotate_Around_Trans(z*135, -x)
}

// cubelet d
union {
  object { faceletx0 }
  object { faceletx1 }
  object { facelety0 }
  object { facelety1 
    pigment { image_map { jpeg "../images/256/kenyamap-w.jpg" }
      rotate x*90 scale <4,1,-2> translate <-1,0,-1>} }
  object { faceletz0 }
  object { faceletz1
    pigment { image_map { jpeg "../images/256/elephants.jpg" }
      scale 2 translate 0 } }
  translate <-1,-1,0>
}

// cubelet e
union {
  object { faceletx0 }
  object { faceletx1 }
  object { facelety0 }
  object { facelety1 
    pigment { image_map { jpeg "../images/256/kenyamap-w.jpg" }
      rotate x*90 scale <4,1,-2> translate <-2,0,-1>} }
  object { faceletz0 }
  object { faceletz1
    pigment { image_map { jpeg "../images/256/elephants.jpg" }
      scale 2 translate -x } }
  translate <0,-1,0>
}

// cubelet f
union {
  object { faceletx0 }
  object { faceletx1 }
  object { facelety0  
    pigment { image_map { jpeg "../images/256/kenyamap-w.jpg" }
      rotate x*90 scale <-4,1,-2> translate -z} }
  object { facelety1 }
  object { faceletz0 }
  object { faceletz1
    pigment { image_map { jpeg "../images/256/elephants.jpg" }
      scale 2 translate -y-x } }
  translate 0
  Rotate_Around_Trans(z*-135, x)
}

// cubelet g
union {
  object { faceletx0 }
  object { faceletx1 }
  object { facelety0  
    pigment { image_map { jpeg "../images/256/kenyamap-w.jpg" }
      rotate x*90 scale <-4,1,-2> translate 0} }
  object { facelety1 }
  object { faceletz0 }
  object { faceletz1 }
  translate -z
  Rotate_Around_Trans(z*-135, x)
}

// cubelet h
union {
  object { faceletx0 }
  object { faceletx1 }
  object { facelety0 }
  object { facelety1 
    pigment { image_map { jpeg "../images/256/kenyamap-w.jpg" }
      rotate x*90 scale <4,1,-2> translate -2*x} }
  object { faceletz0 }
  object { faceletz1 }
  translate <0,-1,-1>
}


/*
object { cubelet translate x }

object { cubelet translate y }

object { cubelet translate z }

object { cubelet translate x+y }

object { cubelet translate y+z }

object { cubelet translate z+x }

object { cubelet translate x+y+z }
*/

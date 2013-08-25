package madparker.gametools.util;

import processing.core.PVector;

public class Util {

	public static PVector cloneVector(PVector v){
		return new PVector(v.x, v.y, v.z);
	}
	
	public static void dupeVector(PVector v1, PVector v2){
		v1.x = v2.x;
		v1.y = v2.y;
		v1.z = v2.z;
	}
	
}

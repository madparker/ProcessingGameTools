package madparker.gametools.screen.sceneGraph.ui.bar;

import processing.core.PVector;
import madparker.gametools.screen.sceneGraph.SceneGraphElement;

public class Bar extends SceneGraphElement {

	public final static int HORIZONTAL = 0;
	public final static int VERTICAL   = 1;
	
	public float width, height;
	
	public int color;
	
	public float percent = 1;
	
	public int orientation;
	
	public Bar(PVector position, float width, float height, int color, int orientation) {
		super();
		this.position = position;
		this.width = width;
		this.height = height;
		this.color = color;
		this.orientation = orientation;
	}

	public void update(){
		
	}
	
	public void draw() {
		pApplet.fill(color, 255);
		pApplet.noStroke();
		
		if(orientation == HORIZONTAL){
			pApplet.rect(0, 0, width * percent, height);
		}else
			pApplet.rect(0, 0, width, height * percent);
	}
	
	public void keyPressed(){
		
	}
}

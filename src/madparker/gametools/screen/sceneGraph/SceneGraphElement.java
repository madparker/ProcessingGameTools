package madparker.gametools.screen.sceneGraph;

import java.util.ArrayList;
import java.util.List;

import processing.core.PVector;

import madparker.gametools.util.GameElement;

public class SceneGraphElement extends GameElement {

	public boolean pause = false;
	public boolean active = true;

	public float scale = 1;
	public float rotate = 0;
	
	public boolean isAnimElement = false; //Is this an element that can accept input during active animations
	
	public PVector position = new PVector();

	public List<SceneGraphElement> children = new ArrayList<SceneGraphElement>();

	public void updateGraph() {
		if (!pause && active) {
			
			update();
			
			for (SceneGraphElement child : children) {
				child.updateGraph();
			}
		}
	}

	public void displayGraph() {
		if (active) {
			pApplet.pushMatrix();
			pApplet.translate(position.x, position.y);
			pApplet.scale(scale);
			pApplet.rotate(rotate);
			
			draw();

			for (SceneGraphElement child : children) {
				child.displayGraph();
			}

			pApplet.popMatrix();
		}
	}
	

	public void keyPressedGraph() {
		if (!pause && active) {
			
			keyPressed();
			
			for (SceneGraphElement child : children) {
				child.keyPressedGraph();
			}
		}
	}
	

	public void mousePressedGraph() {
		if (!pause && active) {
			
			mousePressed();
			
			for (SceneGraphElement child : children) {
				child.mousePressedGraph();
			}
		}
	}
	
	public void animMousePressedGraph() {
		if (!pause && active) {
			
			if(isAnimElement)
				animMousePressed();
			
			for (SceneGraphElement child : children) {
				child.animMousePressedGraph();
			}
		}
	}

}

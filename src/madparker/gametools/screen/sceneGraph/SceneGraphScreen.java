package madparker.gametools.screen.sceneGraph;

import madparker.gametools.screen.Screen;

public class SceneGraphScreen extends Screen {
	
	public SceneGraphElement graph = new SceneGraphElement();
	
	public void setup(){
		super.setup();
	}
	
	public void update(){
		graph.updateGraph();
	}
	
	public void draw(){
		graph.displayGraph();
	}
	
	public void keyPressed() {
		graph.keyPressedGraph();
	}
	
	public void mousePressed() {
		graph.mousePressedGraph();
	}
	
	public void animMousePressed() {
		super.animMousePressed();
		graph.animMousePressedGraph();
	}
}

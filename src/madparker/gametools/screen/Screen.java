package madparker.gametools.screen;

import madparker.gametools.GameTemplate;
import madparker.gametools.util.GameElement;

public class Screen extends GameElement {

	public boolean isInit = true;
	public float foregroundFadeAlpha = 0;
	public int   foregroundFadeColor = 0;
	public GameTemplate game;
	
	public void init(){
		isInit = false;
		game = (GameTemplate)pApplet;
	}
	
	public void setup(){
		if(isInit){
			init();
		}
	}
	
	public void update(){
		
	}
	
	public void draw(){
		
	}
	
	public void exit(){
		
	}
	
	public void keyPressed() {
	}
	
	public void keyReleased() {
	}
	
	public void mousePressed() {
	}
	
	public void animMousePressed() {
	}
}

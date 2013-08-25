package madparker.gametools;

import java.util.ArrayList;
import java.util.List;

import madparker.gametools.animation.ForegroundAnimation;
import madparker.gametools.screen.Screen;
import madparker.gametools.util.GameElement;
import madparker.gametools.util.ProcessingObject;
import madparker.gametools.util.Timer;
import processing.core.PApplet;

/**************************************/
/* When using Game Tools, extend this */
/* class in place of PApplet          */
/**************************************/

public class GameTemplate extends PApplet {
	
	public Screen currentScreen;
	public List<Timer> timers;
	
	public void setup(){
		
		ProcessingObject.pApplet = this;
		GameElement.prevTime = System.currentTimeMillis();
		GameElement.currentTime = System.currentTimeMillis();
		
		timers = new ArrayList<Timer>();
	}
	
	public void draw(){
		GameElement.prevTime = GameElement.currentTime;
		GameElement.currentTime = System.currentTimeMillis();
		
		if (timers != null)
			for (Timer timer : timers) {
				if (timer.isActive())
					timer.update();
			}

		currentScreen.update();
		currentScreen.draw();
		
		noStroke();
		fill(currentScreen.foregroundFadeColor, currentScreen.foregroundFadeAlpha);
		rect(0, 0, width, height);
	}
	
	@Override
	public void mousePressed(){
		if(!ForegroundAnimation.activeAnimation)
			currentScreen.mousePressed();
		
		currentScreen.animMousePressed();
	}

	@Override
	public void keyPressed(){
		if(!ForegroundAnimation.activeAnimation)
			currentScreen.keyPressed();
	}
	@Override
	public void keyReleased(){
		if(!ForegroundAnimation.activeAnimation)
			currentScreen.keyReleased();
	}
	
	public void changeScreen(Screen screen){
		currentScreen = screen;
		currentScreen.setup();
	}
	
	public static void main(String _args[]) {
		PApplet.main(new String[] { GameTemplate.class.getName() });
	}
}

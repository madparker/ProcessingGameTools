package madparker.gametools.animation;

import processing.core.PApplet;
import madparker.gametools.GameTemplate;
import madparker.gametools.screen.Screen;

public class CrossFadeScreensAnimation extends ProgressAnimation {

	public GameTemplate game;
	public Screen currentScreen;
	public Screen nextScreen;
	public boolean switchScreens;

	public void trigger(GameTemplate game, Screen currentScreen, Screen nextScreen, int duration){ 
		super.trigger();
		this.game = game;
		this.currentScreen = currentScreen;
		this.nextScreen = nextScreen;
		this.duration = duration;
		switchScreens = true;
	}
	
	@Override
	protected void executeBegin() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void step() {

		if(percent < 0.5f){
			currentScreen.foregroundFadeAlpha = 255 * percent * 2;
		} else if(switchScreens){
			switchScreens = false;
			nextScreen.foregroundFadeAlpha = 255;
			nextScreen.setup();
			game.currentScreen = nextScreen;
		}
		
		if(!switchScreens){
			nextScreen.foregroundFadeAlpha = 255 * (1 - ((percent - 0.5f) * 2));
		}
	}

	@Override
	protected void executeEnd() {
		currentScreen.foregroundFadeAlpha = 0;
		nextScreen.foregroundFadeAlpha = 0;
	}

}

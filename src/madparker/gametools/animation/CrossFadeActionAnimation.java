package madparker.gametools.animation;

import processing.core.PApplet;
import madparker.gametools.GameTemplate;
import madparker.gametools.screen.Screen;

public abstract class CrossFadeActionAnimation extends ProgressAnimation {

	public GameTemplate game;
	public Screen currentScreen;
	public boolean doAction;

	public void trigger(GameTemplate game, Screen currentScreen, int duration){ 
		super.trigger();
		this.game = game;
		this.currentScreen = currentScreen;
		this.duration = duration;
		doAction = true;
	}
	
	@Override
	protected void executeBegin() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void step() {

		if(percent < 0.5f){
			currentScreen.foregroundFadeAlpha = 255 * percent * 2;
		} else if(doAction){
			doAction = false;
			currentScreen.foregroundFadeAlpha = 255;
			action();
		}
		
		if(!doAction){
			currentScreen.foregroundFadeAlpha = 255 * (1 - ((percent - 0.5f) * 2));
		}
	}

	public abstract void action();
	
	@Override
	protected void executeEnd() {
		currentScreen.foregroundFadeAlpha = 0;
	}

}

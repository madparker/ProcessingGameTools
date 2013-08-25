package madparker.gametools.animation;

import processing.core.PApplet;

public abstract class ProgressAnimation extends ForegroundAnimation {

	protected long duration;
	protected long progress;
	protected float percent;
	public static float speedUp = 1;
	
	public void trigger(){
		super.trigger();
		progress = 0;
		percent = 0;
	}
	
	public void setSpeedUp(float speed){
		speedUp = speed;

		progress = (long) (percent *  duration/speedUp);
	}
	
	@Override
	public void update(){

		progress += frameMillis;
		
		percent = PApplet.map(progress, 0, duration/speedUp, 0, 1);
		
		if(percent > 1){
			percent = 1;
		}
		
		super.update();
		
		if(isEnd()){
			activeFgAnimations.remove(this);
			
			if(activeFgAnimations.size() == 0){
				activeAnimation = false;
			}
		}
	}
	
	@Override
	protected void executeEnd() {
		percent = 1;
		step();
	}
	
	@Override
	protected boolean isEnd() {
		return (progress >= duration);
	}

}

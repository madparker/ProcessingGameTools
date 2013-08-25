package madparker.gametools.animation;

import madparker.gametools.util.Timer;

import processing.core.PApplet;

public abstract class BackgroundAnimation extends Timer {

	protected long duration;
	private long progress;
	protected float percent;

	public final static int MODE_LOOP = 0;
	public final static int MODE_PING_PONG = 1;
	
	public int mode;
	
	public int dir;
	
	public void trigger(int mode){
		super.trigger();
		progress = 0;
		percent = 0;
		dir = 1;
		this.mode = mode;
	}
	
	@Override
	public void update(){

		progress += frameMillis;
		
		if(dir > 0)
			percent = PApplet.map(progress, 0, duration, 0, 1);
		else
			percent = PApplet.map(progress, 0, duration, 1, 0);
		
		if(percent > 1){
			percent = 1;
		} else if (percent < 0){
			percent = 0;
		}		
		
		super.update();
	}
	
	@Override
	protected void executeEnd() {
		percent = 1;
		step();
	}
	
	@Override
	protected boolean isEnd() {
		if (progress >= duration){
			if(mode == MODE_PING_PONG)
				dir *= -1;
			
			progress = 0;
			
		}
			
		return false;
	}

}



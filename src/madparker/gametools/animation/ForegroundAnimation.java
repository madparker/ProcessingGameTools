package madparker.gametools.animation;

import java.util.ArrayList;
import java.util.List;

import madparker.gametools.util.Timer;

public abstract class ForegroundAnimation extends Timer {
	
	public static boolean activeAnimation;
	
	public static List<ForegroundAnimation> activeFgAnimations = new ArrayList<ForegroundAnimation>();
	
	@Override
	public void trigger(){
		super.trigger();
		activeAnimation = true;
		if(!activeFgAnimations.contains(this))
			activeFgAnimations.add(this);
	}
	
	@Override
	public void update(){
		super.update();
		
		if(isEnd()){
			activeFgAnimations.remove(this);
			
			if(activeFgAnimations.size() == 0){
				activeAnimation = false;
			}
//			else {
//				for(ForegroundAnimation fg: activeFgAnimations){
//					System.out.println(fg);
//				}
//			}
		}
	}
	
}

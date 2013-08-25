package madparker.gametools.util;

public class StepTimer extends GameElement{

	protected long begin;
	protected long end;
	
//	protected long baseBegin;
//	protected long baseEnd;
	
	protected long interval;
	protected long lastCheck;
	protected long lastInterval;
	
	public boolean active = false;
	
	protected long currentTime;
	
	public StepTimer(long interval, long length) {
		this.interval = interval;
		this.end = length;
	}
		
	public void trigger() {
		active = true;
		begin = 0;
//		end = baseEnd + begin;
		lastInterval = begin;
		currentTime = 0;
		executeBegin();
	}
	
	public void update() {
		currentTime += pApplet.millis();
		
		if (currentTime > end){
			active = false;
			executeEnd();
		}
		
		if (active && (currentTime > begin) && (currentTime >= lastInterval + interval)) {
			executeStep();
			lastInterval = currentTime;
		}
		lastCheck = currentTime;
	}
	
	public long getElapsed() {
		return lastCheck - begin;
	}
	
	public long getRemaining() {
		return end - lastCheck;
	}
	
	protected void executeBegin() {
	}
	
	protected void executeStep() {
	}
	
	protected void executeEnd() {
	}
	
	public long getBegin() {
		return begin;
	}
	public long getEnd() {
		return end;
	}
	public long getInterval() {
		return interval;
	}
	public void setInterval(long interval) {
		this.interval = interval;
	}
	public boolean isActive() {
		return active;
	}
	
	public void deactivate() {
		executeEnd();
		active = false;
	}
	
}

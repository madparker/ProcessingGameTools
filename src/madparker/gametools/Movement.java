package madparker.gametools;

import madparker.gametools.util.GameElement;

public class Movement extends GameElement {

	public int second;
	public long millis;
	public int action;
	public boolean performed;
	
	public Movement(int second, long millis, int action) {
		super();
		this.second = second;
		this.millis = millis;
		this.action = action;
		performed = false;
	}
}

package madparker.gametools.sprite;

import java.util.ArrayList;
import java.util.List;

import madparker.gametools.util.GameElement;
import processing.core.PImage;

public class AnimatedSprite extends Sprite {

	protected int rows;
	protected int columns;
	
	protected int currentFrame;
	protected int startFrame;
	protected int endFrame;

	protected long prevFrameTime;
	
	protected int rate;

	public final static int MODE_LOOP = 0;
	public final static int MODE_PING_PONG = 1;
	
	protected int direction = 1;
	
	public int animationMode;
	
	public List<PImage> frames = new ArrayList<PImage>();
	
	public AnimatedSprite(String imgStr, float x, float y, int rows, int columns, int rate) {
		super(imgStr, x, y);
		this.rows = rows;
		this.columns = columns;
		this.startFrame = 0;
		this.endFrame = rows * columns - 1;
		this.spriteWidth  = pImage.width/columns;
		this.spriteHeight = pImage.height/rows;
		this.rate = rate;
		
		for(int i = 0; i < rows * columns; i++){
			frames.add(i, pImage.get((i%columns) * spriteWidth, (i/columns) * spriteHeight, 
					spriteWidth, spriteHeight));
		}
		
		currentFrame = startFrame;
		
		prevFrameTime = GameElement.currentTime;
	}
	
	public AnimatedSprite(String imgStr, float x, float y, int rows, int columns, int rate, int startFrame, int endFrame) {
		super(imgStr, x, y);
		this.rows = rows;
		this.columns = columns;
		this.startFrame = startFrame;
		this.endFrame = endFrame;
		this.spriteWidth  = pImage.width/columns;
		this.spriteHeight = pImage.height/rows;
		this.rate = rate;

		currentFrame = startFrame;
		
		prevFrameTime = currentTime;
	}
	
	public void changeAnimation(int startFrame, int endFrame){
		this.startFrame = startFrame;
		this.endFrame = endFrame;
		
		this.currentFrame = startFrame;
		
	}
	
	public void update(){
		super.update();
		
		if(currentTime - prevFrameTime > 1000/rate){
			increment();
			prevFrameTime = currentTime;
		}
		
		if(currentFrame >= endFrame){
			if(animationMode == MODE_LOOP){
				currentFrame = startFrame;
			} else {
				currentFrame = endFrame;
				direction = -1;
			}
		}
		
		if(currentFrame == startFrame && direction < 0){
			direction = 1;
		}
		
		if(currentFrame < startFrame){
			currentFrame = startFrame;
		}
	}
	
	public void increment(){
		if(animationMode == MODE_LOOP){
			currentFrame++;
		} else if (animationMode == MODE_PING_PONG){
			currentFrame += direction;
		}
	}
	
	public void draw(){
		
//		System.out.println(currentFrame);
		
		PImage pImg = frames.get(currentFrame);

		pApplet.pushMatrix();
		pApplet.translate(position.x, position.y);
		if(flip){
			pApplet.scale(-1.0f, 1.0f);
			pApplet.image(pImg,-pImg.width, 0);
		} else {
			pApplet.image(pImg, 0, 0);
		}
		pApplet.popMatrix();
		
		
	}
}

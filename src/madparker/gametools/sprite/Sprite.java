package madparker.gametools.sprite;

import madparker.gametools.util.GameElement;
import processing.core.PImage;
import processing.core.PVector;

public class Sprite extends GameElement {

	public PImage pImage;
	public PVector position;
	public boolean flip = false;

	public int spriteHeight;
	public int spriteWidth;
	
	public PVector velocity;
	public PVector acceleration;
	
	public Sprite(String imgStr, float x, float y){
		pImage = pApplet.loadImage(imgStr);
		position = new PVector(x, y);
		velocity = new PVector();

		spriteWidth = pImage.width;
		spriteHeight = pImage.height;
		
		acceleration = new PVector();
	}
	
	public void update(){
		velocity.x += acceleration.x;
		velocity.y += acceleration.y;
		
		position.x += velocity.x;
		position.y += velocity.y;
	}
	
	public void draw() {
		pApplet.pushMatrix();
		pApplet.translate(position.x, position.y);
		if(flip){
			pApplet.scale(-1.0f, 1.0f);
			pApplet.image(pImage,-pImage.width, 0);
		} else {
			pApplet.image(pImage, 0, 0);
		}
		pApplet.popMatrix();
	}

}

package madparker.gametools.sprite;

import processing.core.PImage;

public class SideScrollBackgroundSprite extends Sprite {
	
	public SideScrollBackgroundSprite(String imgName, int x, int y) {
		super(imgName, x, y);
	}

	@Override
	public void draw(){
//		super.draw();
		pApplet.image(pImage, position.x - pImage.width, position.y);
		pApplet.image(pImage, position.x, position.y);
		pApplet.image(pImage, position.x + pImage.width, position.y);
		
		if(position.x <= -pImage.width){
			position.x = 0;
		} else if (position.x >= pImage.width){
			position.x = 0;
		}
	}
	
}

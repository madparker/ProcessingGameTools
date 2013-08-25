package madparker.gametools;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;


public class SpriteSheetAnimator extends PApplet {

	public String sheetName = "dove.png";
	
	PImage img;

	private int rows = 2;
	private int columns = 4;
	
	int position = 0;
	
	public PFont font;
	
	public void setup() {
		img = loadImage(sheetName);
		
		size(img.width/columns, img.height/rows);
		
		font = loadFont("AmericanTypewriter-24.vlw");
		
		textFont(font);
		
		frameRate(10);
	}

	public void draw() {
		background(125);
		
		image(img, (position%columns) * -img.width/columns, (position/columns) * -img.height/rows);

		fill(0);
		rect(img.width/columns, 0, img.width/columns, img.height/rows);
		
		println("x: " +(position%columns));
		println("y: " +(position/columns));
		println(position);

		fill(255);
		text(position, width * .5f, height *.5f);
		
		position++;
		
		if(position == columns * rows){
			position = 0;
		}
	}
}

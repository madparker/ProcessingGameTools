package madparker.gametools;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;


public class SpriteSheetGenerator extends PApplet {

	public String path = "";
	public String imgType = ".JPG";

	public int startNum = 1;
	public int endNum = 12;
	
	public int rows = 3;
	public int columns = (endNum - startNum)/rows;
	
	ArrayList<PImage> imgs = new ArrayList<PImage>();
	
	float size = 128;
	
	public void setup() {
		System.out.println(columns);
		
		if(columns * rows < endNum - startNum)
			rows++;
		
		for(int i = startNum;  i <=endNum; i++){
			PImage img = loadImage(path + i + imgType);
			
			float scale = size/img.height;
			
			println("scale: " + scale);
			if(img != null){
				img.resize((int)(img.width * scale), (int)(img.height * scale));
			
				imgs.add(img);
			}
		}
		
		println(imgs.get(0).height * ((endNum-startNum)/rows));
		
		size( imgs.get(0).width * columns, 
				imgs.get(0).height * rows);
		

		
		System.out.println("width: " + imgs.get(0).width * columns);
	}

	public void draw() {
		for(int i = 0; i < imgs.size(); i++){
			PImage img = imgs.get(i);
			image(img, (i%columns) * img.width, (i/columns) * img.height);
		}
	}
	
	public void keyPressed(){
		saveFrame("spritesheet.jpg");
		println("saved");
	}
	public static void main(String _args[]) {
		PApplet.main(new String[] { SpriteSheetGenerator.class.getName() });
	}
}

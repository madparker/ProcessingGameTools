package madparker.gametools;

import processing.core.PApplet;

public class PlatformerExample extends PApplet {

	final int WIDTH = 30;
	final int HEIGHT = 23;
	int[][] level = new int[HEIGHT][WIDTH];

	Player p1;

	// booleans for key presses to get a simple yes or no press and
	// to not have to worry about the a,aaaaaaaaaaaaa thing
	boolean right = false, left = false, up = false;

	public void setup() {
		size(480, 368);
		p1 = new Player(WIDTH * 8, HEIGHT * 8); // put the player in the middle
												// of the window
	}

	public void draw() {
		p1.update();

		background(200);
		drawLevel();
		p1.show();
	}

	public void drawLevel() {
		fill(0);
		noStroke();
		for (int ix = 0; ix < WIDTH; ix++) {
			for (int iy = 0; iy < HEIGHT; iy++) {
				switch (level[iy][ix]) {
				case 1:
					rect(ix * 16, iy * 16, 16, 16);
				}
			}
		}
	}

	public boolean place_free(int xx, int yy) {
		// checks if a given point (xx,yy) is free (no block at that point) or
		// not
		yy = PApplet.parseInt(floor(yy / 16.0f));
		xx = PApplet.parseInt(floor(xx / 16.0f));
		if (xx > -1 && xx < level[0].length && yy > -1 && yy < level.length) {
			if (level[yy][xx] == 0) {
				return true;
			}
		}
		return false;
	}

	public void keyPressed() {
		switch (keyCode) {
		case RIGHT:
			right = true;
			break;
		case LEFT:
			left = true;
			break;
		case UP:
			up = true;
			break;
		}
	}

	public void keyReleased() {
		switch (keyCode) {
		case RIGHT:
			right = false;
			break;
		case LEFT:
			left = false;
			break;
		case UP:
			up = false;
			break;
		}
	}

	public void mousePressed() {
		// Left click creates/destroys a block
		if (mouseButton == LEFT) {
			level[PApplet.parseInt(floor(mouseY / 16.0f))][PApplet
					.parseInt(floor(mouseX / 16.0f))] ^= 1;
		}
	}

	class Player {
		int x, y;
		float xSpeed, ySpeed;
		float accel, deccel;
		float maxXspd, maxYspd;
		float xSave, ySave;
		int xRep, yRep;
		float gravity;

		Player(int _x, int _y) {
			x = _x;
			y = _y;
			xSpeed = 0;
			ySpeed = 0;
			accel = 0.5f;
			deccel = 0.5f;
			maxXspd = 2;
			maxYspd = 12;
			xSave = 0;
			ySave = 0;
			xRep = 0;
			yRep = 0;
			gravity = 0.25f;
		}

		public void update() {
			if (right) {
				xSpeed += accel;
				if (xSpeed > maxXspd) {
					xSpeed = maxXspd;
				}
			} else if (left) {
				xSpeed -= accel;
				if (xSpeed < -maxXspd) {
					xSpeed = -maxXspd;
				}
			} else { // neither right or left pressed, decelerate
				if (xSpeed > 0) {
					xSpeed -= deccel;
					if (xSpeed < 0) {
						xSpeed = 0;
					}
				} else if (xSpeed < 0) {
					xSpeed += deccel;
					if (xSpeed > 0) {
						xSpeed = 0;
					}
				}
			}

			if (up) {
				if (!place_free(x, y + 16) || !place_free(x + 15, y + 16)) {
					ySpeed = -5.3f;
				}
			}

			ySpeed += gravity;

			/*
			 * // The technique used for movement involves taking the integer
			 * (without the decimal) // part of the player's xSpeed and ySpeed
			 * for the number of pixels to try to move, // respectively. The
			 * decimal part is accumulated in xSave and ySave so that once //
			 * they reach a value of 1, the player should try to move 1 more
			 * pixel. This jump // is not normally visible if it is moving fast
			 * enough. This method is used because // is guarantees that
			 * movement is pixel perfect because the player's position will //
			 * always be at a whole number. Whole number positions prevents
			 * problems when adding // new elements like jump through blocks or
			 * slopes.
			 */
			xRep = 0; // should be zero because the for loops count it down but
						// just as a safety
			yRep = 0;
			xRep += floor(abs(xSpeed));
			yRep += floor(abs(ySpeed));
			xSave += abs(xSpeed) - floor(abs(xSpeed));
			ySave += abs(ySpeed) - floor(abs(ySpeed));
			int signX = (xSpeed < 0) ? -1 : 1;
			int signY = (ySpeed < 0) ? -1 : 1;
			// when the player is moving a direction collision is tested for
			// only in that direction
			// the offset variables are used for this in the for loops below
			int offsetX = (xSpeed < 0) ? 0 : 15;
			int offsetY = (ySpeed < 0) ? 0 : 15;

			if (xSave >= 1) {
				xSave -= 1;
				xRep++;
			}
			if (ySave >= 1) {
				ySave -= 1;
				yRep++;
			}

			for (; yRep > 0; yRep--) {
				if (place_free(x, y + offsetY + signY)
						&& place_free(x + 15, y + offsetY + signY)) {
					y += signY;
				} else {
					ySpeed = 0;
				}
			}
			for (; xRep > 0; xRep--) {
				if (place_free(x + offsetX + signX, y)
						&& place_free(x + offsetX + signX, y + 15)) {
					x += signX;
				} else {
					xSpeed = 0;
				}
			}

		}

		public void show() {
			pushMatrix();
			fill(255, 0, 0);
			noStroke();
			rect(x, y, 16, 16);
			popMatrix();
		}
	}

	static public void main(String args[]) {
		PApplet.main(new String[] { "--bgcolor=#F0F0F0",
				madparker.gametools.PlatformerExample.class.getName() });
	}
}

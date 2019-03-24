package test;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class PowerUp extends Entity {

	private int health = 100;

	public PowerUp() throws SlickException {
		setHealth(this.health);
		setImage(new Image("res/powerUp.png"));
	}

	public void moveUp() {
		setyPos(getyPos() - 0.3f);
	}

	public void moveDown() {
		setyPos(getyPos() + 0.2f);
	}

	public void moveLeft() {
		setxPos(getxPos() - 0.3f);
	}

	public void moveRight() {
		setxPos(getxPos() + 0.3f);
	}

}
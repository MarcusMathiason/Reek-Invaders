package test;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Bullet extends Entity {

	private int damage = 1;

	public Bullet(float xPos, float yPos) throws SlickException {
		setDamage(this.damage);
		setxPos(xPos);
		setyPos(yPos);
		setImage(new Image("res/Bullet.png"));
	}

	public void moveUp() {
		setyPos(getyPos() - 10);
	}

	public void moveDown() {
		setyPos(getyPos() + 10);
	}

	public void moveLeft() {
		setxPos(getxPos() - 1);
	}

	public void moveRight() {
		setxPos(getxPos() + 1);
	}
}
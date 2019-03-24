package test;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Enemy extends Entity {

	private int health = 100;
	private int damage = 100;

	public Enemy(float xPos, float yPos) throws SlickException {
		setHealth(this.health);
		setDamage(this.damage);
		setxPos(xPos);
		setyPos(yPos);
		setImage(new Image("res/enemyPH.png"));
	}

	public void moveUp() {
		setyPos(getyPos() - 0.3f);
	}

	public void moveDown() {
		setyPos(getyPos() + 0.3f);
	}

	public void moveLeft() {
		setxPos(getxPos() - 0.3f);
	}

	public void moveRight() {
		setxPos(getxPos() + 0.3f);
	}

}
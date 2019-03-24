package test;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player extends Entity {
	private int health = 500;
	private int damage = 100;
	private int score = 0;
	private float xPos = 400;
	private float yPos = 300;

	public Player() throws SlickException {
		setHealth(this.health);
		setDamage(this.damage);
		setScore(this.score);
		setxPos(this.xPos);
		setyPos(this.yPos);
		setImage(new Image("res/Player.png"));
	}

	public void moveUp() {
		setyPos(getyPos() - 0.8f);
	}

	public void moveDown() {
		setyPos(getyPos() + 0.8f);
	}

	public void moveLeft() {
		setxPos(getxPos() - 0.8f);
	}

	public void moveRight() {
		setxPos(getxPos() + 0.8f);
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
}
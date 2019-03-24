package test;

import java.util.Observable;

import org.newdawn.slick.Image;

public class Entity {

	private int health;
	private int damage;
	private float xPos;
	private float yPos;
	private Image img;

	public Entity() {
	}

	public void setHealth(int newHealth) {
		this.health = newHealth;
	}

	public int getHealth() {
		return this.health;
	}

	public void setDamage(int newDamage) {
		this.damage = newDamage;
	}

	public int getDamage() {
		return this.damage;
	}

	public void setxPos(float xPos) {
		this.xPos = xPos;
	}

	public float getxPos() {
		return this.xPos;
	}

	public void setyPos(float yPos) {
		this.yPos = yPos;
	}

	public float getyPos() {
		return this.yPos;
	}

	public Image getImage() {
		return this.img;
	}

	public void setImage(Image img) {
		this.img = img;
	}
}
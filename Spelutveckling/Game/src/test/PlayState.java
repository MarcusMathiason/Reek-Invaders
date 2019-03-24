package test;

import java.util.Observable;
import java.util.Observer;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

@SuppressWarnings("unused")
public class PlayState extends BasicGameState {

	private float pxPos, pyPox, pauseX, pauseY, pauseW, pauseH;
	private float pyPos;
	public static int score;
	private Player p;
	private Bullet pBullet;
	private Bullet eBullet;
	private PowerUp powerUp;
	private Enemy e[] = new Enemy[10];
	private Image img;
	private boolean shooting = false;
	private boolean eShooting = false;
	private boolean pUpSpawned;
	private Sound shoot;
	private Sound powerUpSound;
	private Sound hits;
	private Sound destroyed;
	public static float mPos;
	private final int spriteOffset = 18;

	public PlayState(int state) {

	}

	/*
	 * Handles spawned powerups
	 */
	public void movePowerUp() {

		// If player has picked up powerup, 'despawn' it
		if (p.getxPos() < powerUp.getxPos() + 20 && p.getxPos() > powerUp.getxPos() - 20
				&& p.getyPos() < powerUp.getyPos() + 20 && p.getyPos() > powerUp.getyPos() - 20) {
			if(!powerUpSound.playing()) {
			powerUpSound.play(1.5f, 0.5f);
			}
			p.setHealth(p.getHealth() + powerUp.getHealth());
			powerUp.setxPos(-10);
			pUpSpawned = false;
		}

		// If powerup has left screen, 'despawn' it
		if (powerUp.getyPos() >= 600) {
			powerUp.setxPos(-10);
			pUpSpawned = false;
		}

		powerUp.moveDown();

	}

	/**
	 * Handles bullets
	 * 
	 * @throws SlickException
	 */
	public void shoot() throws SlickException {

		// Plays sound
		if (!shoot.playing()) {
			shoot.play(1.0f, 0.2f);
		}

		// Enemy hit
		for(int i = 0 ; i < 10; i++) {
		if (pBullet.getxPos() - spriteOffset < e[i].getxPos() + spriteOffset
				&& pBullet.getxPos() + spriteOffset > e[i].getxPos() - spriteOffset
				&& pBullet.getyPos() - spriteOffset < e[i].getyPos() + spriteOffset
				&& pBullet.getyPos() + spriteOffset > e[i].getyPos() - spriteOffset) {
			if(!hits.playing()) {
				hits.play();
			}
			e[i].setHealth(e[i].getHealth() - pBullet.getDamage());
		}
		}
		// Player hit
		if (eBullet.getxPos() - spriteOffset < p.getxPos() + spriteOffset
				&& eBullet.getxPos() + spriteOffset > p.getxPos() - spriteOffset
				&& eBullet.getyPos() - spriteOffset < p.getyPos() + spriteOffset
				&& eBullet.getyPos() + spriteOffset > p.getyPos() - spriteOffset) {
			if(!hits.playing()) {
				hits.play();
			}
			p.setHealth(p.getHealth() - eBullet.getDamage());
		}

		// If player is currently shooting, move bullets
		if (shooting == true) {
			pBullet.moveUp();
		}

		// If enemy is currently shooting, move bullets
		if (eShooting == true) {
			eBullet.moveDown();
		}

	}

	/**
	 * State initiation handler
	 */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

		new Image("res/Bullet.png");
		pxPos = 400;
		pyPos = 300;
		score = 0;
		pUpSpawned = false;
		p = new Player();
		for(int i = 0 ; i < 10 ; i++) {
		e[i] = new Enemy((float) (Math.random() * (800)), (float) (Math.random() * (600)));
		}
		pBullet = new Bullet(-10, -10);
		eBullet = new Bullet(-10, -10);
		powerUp = new PowerUp();
		shoot = new Sound("res/shoot.wav");
		powerUpSound = new Sound("res/powerUp.wav");
		hits = new Sound("res/hits.wav");
		destroyed = new Sound("res/destroyed.wav");

	}

	/**
	 * Render handler
	 */
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		

		g.drawImage(new Image("res/Background.png"), 0, 0);
		p.getImage().drawCentered(p.getxPos(), p.getyPos());

		
		// If enemy is not dead, render it
for(int i = 0 ; i < 10 ; i++) {
			
		
		if (e[i].getHealth() > 0) {
			e[i].getImage().drawCentered(e[i].getxPos(), e[i].getyPos());
		}
}
		// UI rendering
		g.drawString("Press [P | ESCAPE] to pause", 0, 50);
		g.drawString("Health: " + p.getHealth(), 0, 100);
		//g.drawString("xPos: " + p.getxPos(), 0, 120);
		//g.drawString("yPos: " + p.getyPos(), 0, 140);
		g.drawString("Score: " + p.getScore(), 0, 160);
		//g.drawString("Enemy health: " + e.getHealth(), 0, 220);
		//g.drawString("Enemy xPos: " + e.getxPos(), 0, 240);
		//g.drawString("Enemy yPos: " + e.getyPos(), 0, 260);

		// If player is shooting, render bullets
		if (shooting == true) {

			//g.drawString("Currently Shooting!", 0, 160);

			pBullet.getImage().draw(pBullet.getxPos() - 25, pBullet.getyPos(), 50.0f, 100.0f);
		}

		// If enemy is shooting, render bullets
		if (eShooting == true) {
			eBullet.getImage().draw(eBullet.getxPos() - 25, eBullet.getyPos(), 50.0f, 100.0f);
		}

		// If powerup has spawned, render it
		if (pUpSpawned) {

			powerUp.getImage().drawCentered(powerUp.getxPos(), powerUp.getyPos());
		}
	}

	/**
	 * Update handler
	 */
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

		// Spawns powerups with a chance of 1 in 1000
		if ((int) ((Math.random()) * (1000)) == 1) {
			if (!pUpSpawned) {
				powerUp = new PowerUp();
				powerUp.setyPos(1);
				powerUp.setxPos((float) (Math.random() * (gc.getWidth())));
				pUpSpawned = true;
			}
		}

		// If powerup is spawned, move it downwards
		if (pUpSpawned) {
			movePowerUp();
		}

		// If enemy is not currently shooting, move its bullets off the screen
		if (!eShooting) {
			eBullet.setyPos(-10);
			eBullet.setxPos(-10);
		}

		// If enemy is dead, stop shooting and spawn a new enemy at a random location
for(int i = 0 ; i < 10 ; i++) {
			
		
		if (e[i].getHealth() <= 0) {
			if(!destroyed.playing()) {
				destroyed.play();
			}
			eShooting = false;
			e[i] = new Enemy((float) (Math.random() * (800)), (float) (Math.random() * (600)));
			p.setScore(p.getScore() + 100);
			score = p.getScore();

		}
	}

for(int i = 0 ; i < 10 ; i++) {
	

		if (e[i].getHealth() > 0) {

			// If enemy is not dead and player is not in front, stop shooting
			if ((p.getxPos() < e[i].getxPos() || p.getxPos() > e[i].getxPos() || e[i].getyPos() > p.getyPos() - 100)) {
				eShooting = false;

				// Move enemy towards player's x position
				if (e[i].getxPos() < p.getxPos() && e[i].getxPos() < 800) {
					e[i].moveRight();
				}

				// Move enemy towards player's x position
				if (e[i].getxPos() > p.getxPos() && e[i].getxPos() > 0) {
					e[i].moveLeft();
				}

				// Move enemy towards player's x position
				if (e[i].getyPos() > p.getyPos() - 100 && e[i].getyPos() >= 0) {
					e[i].moveUp();
				}

				// Move enemy towards player's x position
				if (e[i].getyPos() < p.getyPos() - 100 && e[i].getyPos() <= 600) {
					e[i].moveDown();
				}

			}

			// Move enemy away from player bullets
			/*
			if ((p.getxPos() > e[i].getxPos() - spriteOffset - 50 && p.getxPos() < e[i].getxPos() + spriteOffset + 50
					&& p.getxPos() >= e[i].getxPos() && e[i].getxPos() > 1) && (e[i].getxPos() > 0 && shooting)) {
				e[i].moveLeft();
			}

			// Move enemy away from player bullets
			if ((p.getxPos() > e[i].getxPos() - spriteOffset - 50 && p.getxPos() < e[i].getxPos() + spriteOffset + 50
					&& p.getxPos() < e[i].getxPos() && e[i].getxPos() < 799) && e[i].getxPos() < 800 && shooting) {
				e[i].moveRight();
			} */
		}

			// If player is in front of enemy, shoot
			if (p.getxPos() >= e[i].getxPos() - spriteOffset && p.getxPos() <= e[i].getxPos() + spriteOffset
					&& p.getyPos() > e[i].getyPos()) {
				eShooting = true;

				// If bullet is off the screen, spawn a new bullet
				if (eBullet.getyPos() >= 600) {
					eBullet = new Bullet(e[i].getxPos(), e[i].getyPos() + 100);
				}
				shoot();
			}
		
		
}

		// If player is dead, end game
		if (p.getHealth() <= 0) {
			StartMenu.soundtrack.stop();
			sbg.enterState(2);
		}

		// If player collides with enemy, decrement player health
		for(int i = 0 ; i < 10 ; i++) {
		if (p.getxPos() - spriteOffset < e[i].getxPos() + spriteOffset
				&& p.getxPos() + spriteOffset > e[i].getxPos() - spriteOffset
				&& p.getyPos() - spriteOffset < e[i].getyPos() + spriteOffset
				&& p.getyPos() + spriteOffset > e[i].getyPos() - spriteOffset) {
			p.setHealth(p.getHealth() - 1);
		}
		}

		// Player shoot
		if (gc.getInput().isKeyDown(Input.KEY_SPACE)) {
			shooting = true;
			if (pBullet.getyPos() <= 0) {
				pBullet = new Bullet(p.getxPos(), p.getyPos() - 50);
			}
			shoot();

		}

		// Stop shooting by player
		if (!gc.getInput().isKeyDown(Input.KEY_SPACE)) {
			shooting = false;
			pBullet.setyPos(-10);
			pBullet.setxPos(-10);
		}

		// Move player upwards
		if (gc.getInput().isKeyDown(Input.KEY_UP) && p.getyPos() > 0) {
			p.moveUp();
		}

		// Move player downwards
		if (gc.getInput().isKeyDown(Input.KEY_DOWN) && p.getyPos() < 600) {
			p.moveDown();
		}

		// Move player to the right
		if (gc.getInput().isKeyDown(Input.KEY_RIGHT) && p.getxPos() < 800) {
			p.moveRight();
		}

		// Move player to the left
		if (gc.getInput().isKeyDown(Input.KEY_LEFT) && p.getxPos() > 0) {
			p.moveLeft();
		}

		// Pause game
		if (gc.getInput().isKeyPressed(Input.KEY_P) || gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			mPos = StartMenu.soundtrack.getPosition();
			StartMenu.soundtrack.pause();
			sbg.enterState(3);
		}

	}

	public int getID() {
		return 1;
	}

}
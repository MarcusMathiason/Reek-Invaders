package test;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameOverState extends BasicGameState {
	private float gOverX, gOverY, gOverW, gOverH, mainX, mainY, mainW, mainH, exitX, exitY, exitW, exitH;

	public GameOverState(int gameState) {

	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		gOverX = gc.getWidth()/2 - g.getFont().getWidth("Game over! Your score: " + PlayState.score)/2;
		gOverY = gc.getHeight()/2 - g.getFont().getHeight("Game over! Your score: " + PlayState.score)/2;
		gOverW = g.getFont().getWidth("Game over! Your score: " + PlayState.score);
		gOverH = g.getFont().getHeight("Game over! Your score: " + PlayState.score);
		mainX = gc.getWidth()/2 - g.getFont().getWidth("Main Menu")/2;
		mainY = gc.getHeight()/2 - g.getFont().getHeight("Main Menu")/2 - 40;
		mainW = g.getFont().getWidth("Main Menu");
		mainH = g.getFont().getHeight("Main Menu");
		exitX = gc.getWidth()/2 - g.getFont().getWidth("Exit")/2;
		exitY = gc.getHeight()/2 - g.getFont().getHeight("Exit")/2 - 80;
		exitW = g.getFont().getWidth("Exit");
		exitH = g.getFont().getHeight("Exit");
		
		g.drawString("Game over! Your score: " + PlayState.score, gOverX, gOverY);
		g.drawString("Main Menu [M]", mainX, mainY + 80);
		g.drawString("Exit [END]", gc.getWidth() / 2, exitY + 160);
		// System.out.println("x: " + Mouse.getX() + " y: " + Mouse.getY());

	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

		//Main Menu
		if ((Mouse.getX() < mainX + mainW && Mouse.getX() > mainX - mainW && Mouse.getY() < mainY + mainH && Mouse.getY() > mainY - mainH)
				&& gc.getInput().isMousePressed(0) || gc.getInput().isKeyPressed(Input.KEY_M)) {
			sbg.getState(0).init(gc, sbg);
			sbg.enterState(0);
		}

		//Exit
		if ((Mouse.getX() < exitX + exitW && Mouse.getX() > exitX - exitW && Mouse.getY() < exitY + exitH && Mouse.getY() > exitY - exitH)
				&& gc.getInput().isMousePressed(0) || gc.getInput().isKeyPressed(Input.KEY_END)) {
			gc.exit();
		}
	}

	public int getID() {
		return 2;
	}

}
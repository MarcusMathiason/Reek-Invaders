package test;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PauseState extends BasicGameState {
	
	private float contX, contY, contW, contH, mainX, mainY, mainW, mainH, exitX, exitY, exitW, exitH;

	public PauseState(int state) {
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		contX = gc.getWidth()/2 - g.getFont().getWidth("Continue")/2;
		contY = gc.getHeight()/2 - g.getFont().getHeight("Continue")/2;
		contW = g.getFont().getWidth("Continue");
		contH = g.getFont().getHeight("Continue");
		mainX = gc.getWidth()/2 - g.getFont().getWidth("Main Menu")/2;
		mainY = gc.getHeight()/2 - g.getFont().getHeight("Main Menu")/2 - 40;
		mainW = g.getFont().getWidth("Main Menu");
		mainH = g.getFont().getHeight("Main Menu");
		exitX = gc.getWidth()/2 - g.getFont().getWidth("Exit")/2;
		exitY = gc.getHeight()/2 - g.getFont().getHeight("Exit")/2 - 80;
		exitW = g.getFont().getWidth("Exit");
		exitH = g.getFont().getHeight("Exit");

		g.drawString("Continue [ESCAPE | P]", contX,
				contY);
		g.drawString("Main Menu [M]", mainX,
				mainY + 80);
		g.drawString("Exit [END]", exitX,
				exitY + 160);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		// Continue
		if ((Mouse.getX() < contX + contW && Mouse.getX() > contX - contW && Mouse.getY() < contY + contH && Mouse.getY() > contY - contH)
				&& gc.getInput().isMousePressed(0) || gc.getInput().isKeyPressed(Input.KEY_ESCAPE) || gc.getInput().isKeyPressed(Input.KEY_P)) {
			StartMenu.soundtrack.setPosition(PlayState.mPos);
			StartMenu.soundtrack.play();
			sbg.enterState(1);
		}

		// Main menu
		if ((Mouse.getX() < mainX + mainW && Mouse.getX() > mainX - mainW && Mouse.getY() < mainY + mainH && Mouse.getY() > mainY - mainH)
				&& gc.getInput().isMousePressed(0) || gc.getInput().isKeyPressed(Input.KEY_M)) {
			sbg.getState(0).init(gc, sbg);
			sbg.enterState(0);
		}

		// Exit
		if ((Mouse.getX() < exitX + exitW && Mouse.getX() > exitX - exitW && Mouse.getY() < exitY + exitH && Mouse.getY() > exitY - exitH)
				&& gc.getInput().isMousePressed(0) || gc.getInput().isKeyPressed(Input.KEY_END)) {
			gc.exit();
		}
	}

	public int getID() {
		return 3;
	}

}
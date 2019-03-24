package test;

import java.io.IOException;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

public class StartMenu extends BasicGameState {

	static Music soundtrack;
	static boolean sound = true;
	private float xPos, yPos;
	
	private float playX, playY, playW, playH, optionsX, optionsY, optionsW, optionsH, exitX, exitY, exitW, exitH;

	public StartMenu(int state) {

	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

		soundtrack = new Music("res/untitled.wav");

	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		xPos = Mouse.getX();
		yPos = Mouse.getY();
		//System.out.println("X: " + xPos + " Y: " + yPos);
		
		playX = gc.getWidth()/2 - g.getFont().getWidth("Play")/2;
		playY = gc.getHeight()/2 - g.getFont().getHeight("Play")/2;
		playW = g.getFont().getWidth("Play");
		playH = g.getFont().getHeight("Play");
		optionsX = gc.getWidth()/2 - g.getFont().getWidth("Options")/2;
		optionsY = gc.getHeight()/2 - g.getFont().getHeight("Options")/2 - 40;
		optionsW = g.getFont().getWidth("Options");
		optionsH = g.getFont().getHeight("Options");
		exitX = gc.getWidth()/2 - g.getFont().getWidth("Exit")/2;
		exitY = gc.getHeight()/2 - g.getFont().getHeight("Exit")/2 - 80;
		exitW = g.getFont().getWidth("Exit");
		exitH = g.getFont().getHeight("Exit");
		/*System.out.println("PlayW: " + playW);
		System.out.println("PlayH: " + playH);
		System.out.println("optionsW: " + optionsW);
		System.out.println("optionsH: " + optionsH);
		System.out.println("exitW: " + exitW);
		System.out.println("exitH: " + exitH);*/
		g.drawString("Play [ENTER]", playX, playY);
		//drawString's y-axis is inverted compared to Mouse.getY(), therefore make up for it visually
		//drawString's y-axis starts in top left corner, Mouse.getY() starts in bottom left
		g.drawString("Options [O]", optionsX, optionsY + 80);
		g.drawString("Exit [END]", exitX, exitY + 160);

	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		//Play
		if ((Mouse.getX() < playX + playW && Mouse.getX() > playX - playW && Mouse.getY() < playY + playH && Mouse.getY() > playY - playH)
				&& gc.getInput().isMousePressed(0) || gc.getInput().isKeyPressed(Input.KEY_ENTER)) {
			if(sound) {
			soundtrack.play();
			}
			sbg.getState(1).init(gc, sbg);
			sbg.enterState(1);
		}
		//Options
		if ((Mouse.getX() < optionsX + optionsW && Mouse.getX() > optionsX - optionsW && Mouse.getY() < optionsY + optionsH && Mouse.getY() > optionsY - optionsH)
				&& gc.getInput().isMousePressed(0) || gc.getInput().isKeyPressed(Input.KEY_O)) {
			sbg.getState(4).init(gc, sbg);
			sbg.enterState(4);
		}
		//Exit
		if ((Mouse.getX() < exitX + exitW && Mouse.getX() > exitX - exitW && Mouse.getY() < exitY + exitH && Mouse.getY() > exitY - exitH)
				&& gc.getInput().isMousePressed(0) || gc.getInput().isKeyPressed(Input.KEY_END)) {
			gc.exit();
		}

	}

	public int getID() {
		return 0;
	}

}
package test;

import java.awt.Menu;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Test extends StateBasedGame{
	
	public static final String gameName = "Reek Invaders";
	public static final int menu = 0;
	public static final int play = 1;
	public static final int gameOver = 2;
	public static final int pause = 3;
	public static final int options = 4;
	public static final int windowX = 800;
	public static final int windowY = 600;
	
	public Test(String gameName) throws SlickException {
		super(gameName);
		this.addState(new StartMenu(menu));
		this.addState(new PlayState(play));
		this.addState(new GameOverState(gameOver));
		this.addState(new PauseState(pause));
		this.addState(new OptionState(options));
		
		
		
	}
	
	public static int getWindowX() {
		return windowX;
	}
	
	public static int getWindowY() {
		return windowY;
	}

	

	
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(menu).init(gc, this);
		this.getState(play).init(gc, this);
		this.getState(gameOver).init(gc, this);
		this.getState(pause).init(gc, this);
		this.getState(options).init(gc, this);
		
	}
	
	public static void main(String[] args) {
		AppGameContainer agc;
		
		try {
			agc = new AppGameContainer(new Test(gameName));
			agc.setDisplayMode(windowX, windowY, false);
			agc.start();
		} catch(SlickException e) {
			e.printStackTrace();
		}

	}

}
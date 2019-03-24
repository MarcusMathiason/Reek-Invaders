package test;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class OptionState extends BasicGameState {
	private float musicW, musicH, musicX, musicY, menuW, menuH, menuX, menuY;

	public OptionState(int state) {

	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		musicX = gc.getWidth()/2 - g.getFont().getWidth("Music: ")/2;
		musicY = gc.getHeight()/2 - g.getFont().getHeight("Music: ")/2;
		musicH = g.getFont().getHeight("Music: ");
		musicW = g.getFont().getWidth("Music: ");
		menuX = gc.getWidth()/2 - g.getFont().getWidth("Back to main menu")/2;
		menuY = gc.getHeight()/2 - g.getFont().getHeight("Back to main menu")/2 - 40;
		menuH = g.getFont().getHeight("Back to main menu");
		menuW = g.getFont().getWidth("Back to main menu");
		g.drawString("Sound: [S] ", musicX, musicY);
			if(StartMenu.sound) {
				g.drawString("[On]", musicX + musicW * 2 , musicY);
			} else {
				g.drawString("[Off]", musicX + musicW * 2, musicY);
			}
		g.drawString("Back to main menu [M]", menuX,
				menuY + 80);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		// Switch music on/off
		if((Mouse.getX() < musicX + musicW * 2 && Mouse.getX() > musicX - musicW && Mouse.getY() < musicY + musicH && Mouse.getY() > musicY - musicH)
				&& gc.getInput().isMousePressed(0) || gc.getInput().isKeyPressed(Input.KEY_S)) {
			if(StartMenu.sound) {
				StartMenu.sound = false;
			} else {
				StartMenu.sound = true;
			}
		}
		// Main menu
		if(((Mouse.getX() < menuX + menuW && Mouse.getX() > menuX - menuW && Mouse.getY() < menuY + menuH && Mouse.getY() > menuY - menuH)
				&& gc.getInput().isMousePressed(0)) || gc.getInput().isKeyPressed(Input.KEY_M)) {
			sbg.enterState(0);
		}
	}

	public int getID() {
		return 4;
	}

}

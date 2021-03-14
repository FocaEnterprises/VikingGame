package net.dinastiafoca.vikinggame;

import com.badlogic.gdx.Game;
import net.dinastiafoca.vikinggame.screens.VillageScreen;

public class VikingGame extends Game {
	public static final String SCREEN_TITLE = "Viking Game (dev)";
	public static final int SCREEN_WIDTH = 640;
	public static final int SCREEN_HEIGHT = 480;

	@Override
	public void create() {
		setScreen(new VillageScreen());
	}
}

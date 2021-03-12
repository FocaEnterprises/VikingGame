package net.dinastiafoca.vikinggame.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import net.dinastiafoca.vikinggame.VikingGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.useVsync(true);
		config.setResizable(false);
		config.setWindowedMode(VikingGame.SCREEN_WIDTH, VikingGame.SCREEN_HEIGHT);
		config.setTitle(VikingGame.SCREEN_TITLE);

		new Lwjgl3Application(new VikingGame(), config);
	}
}

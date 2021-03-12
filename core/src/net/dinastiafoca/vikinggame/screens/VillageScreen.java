package net.dinastiafoca.vikinggame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import net.dinastiafoca.vikinggame.world.Village;

import static net.dinastiafoca.vikinggame.VikingGame.SCREEN_HEIGHT;
import static net.dinastiafoca.vikinggame.VikingGame.SCREEN_WIDTH;

public class VillageScreen extends ScreenAdapter  {

    private OrthographicCamera camera;
    private Village village;

    @Override
    public void show () {
        camera = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
        village = new Village(camera, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        village.update(delta);
    }

    @Override
    public void dispose () {
        village.dispose();
    }
}

package net.dinastiafoca.vikinggame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import net.dinastiafoca.vikinggame.world.Village;

import static net.dinastiafoca.vikinggame.VikingGame.SCREEN_HEIGHT;
import static net.dinastiafoca.vikinggame.VikingGame.SCREEN_WIDTH;

public class VillageScreen extends ScreenAdapter  {

    private final Vector3 screenCoordinates = new Vector3();

    private OrthographicCamera camera;
    private Village village;

    @Override
    public void show () {
        camera = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
        village = new Village(camera, 8, 6);
    }

    @Override
    public void render (float delta) {

        processInput();

        Gdx.gl.glClearColor(0.8f, 1, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        village.update(delta);
    }

    private void processInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            camera.position.y++;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            camera.position.y--;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            camera.position.x--;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            camera.position.x++;
        }

        camera.update();

        if(Gdx.input.isTouched()) {
            screenCoordinates.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(screenCoordinates);
            village.insertBuild(screenCoordinates.x, screenCoordinates.y);
        }
    }

    @Override
    public void dispose () {
        village.dispose();
    }
}

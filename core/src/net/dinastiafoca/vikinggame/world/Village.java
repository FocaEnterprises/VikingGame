package net.dinastiafoca.vikinggame.world;

import com.artemis.World;
import com.artemis.WorldConfigurationBuilder;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.IntMap;
import net.dinastiafoca.vikinggame.entity.EntityFactory;
import net.dinastiafoca.vikinggame.entity.system.SpriteRenderSystem;

import java.util.Arrays;

public class Village implements Disposable {

    public static final int GRID_SIZE = 32;

    private final IntMap<BuildData> builds = new IntMap<>();
    private final int[] grid;

    private final OrthographicCamera camera;
    private final EntityFactory entityFactory;
    private final World world;

    public final int width;
    public final int height;

    public Village(OrthographicCamera camera, int width, int height) {
        this.camera = camera;
        this.width = width;
        this.height = height;
        this.entityFactory = new EntityFactory();

        this.grid = new int[width * height];

        Arrays.fill(grid, -1);

        WorldConfigurationBuilder worldConfig = new WorldConfigurationBuilder()
                .with(new SpriteRenderSystem(camera));

        world = new World(worldConfig.build());
        world.inject(entityFactory);
    }

    public void update(float delta) {
        debug(delta);
        world.delta = delta;
        world.process();
    }

    public int getEntityAt(int x, int y) {
        if(!isInBounds(x, y, 0, 0)) {
            return -1;
        }

        return grid[x + y * width];
    }

    public boolean insertBuild(float x, float y) {
        BuildData data = entityFactory.createMinhoca(world, (int) x, (int) y);

        return insertBuild(data);
    }

    public boolean insertBuild(BuildData data) {
        if(!isInBounds(data.getX(), data.getY(), data.getWidth(), data.getHeight())) {
            return false;
        }

        if(!isFree(data.getX(), data.getY(), data.getWidth(), data.getHeight())) {
            return false;
        }

        for (int xx = data.getX(); xx < data.getX() + data.getWidth(); xx++) {
            for (int yy = data.getY(); yy < data.getY() + data.getHeight(); yy++) {
                setEntityInGrid(xx, yy, data.getEntityId());
            }
        }

        builds.put(data.getEntityId(), data);
        return true;
    }

    private void setEntityInGrid(int x, int y, int entityId) {
        grid[x + y * width] = entityId;
    }

    private boolean isFree(int x, int y, int width, int height) {
        for(int xx = x; xx < x + width -1; xx++) {
            for(int yy = y; yy < y + height -1; yy++) {
                if(getEntityAt(xx, yy) != -1) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isInBounds(int x, int y, int width, int height) {
        return x >= 0 && x + width -1 < this.width && y >= 0 && y + height -1 < this.height;
    }

    @Override
    public void dispose() {
        world.dispose();
        shape.dispose();
    }

    public World getArtmisWorld() {
        return world;
    }

    public EntityFactory getEntityFactory() {
        return entityFactory;
    }

    // ---- Debug ----

    private final ShapeRenderer shape = new ShapeRenderer();

    private void debug(float delta) {
        shape.setProjectionMatrix(camera.combined);
        shape.setColor(Color.RED);

        for(int xx = 0; xx < width * GRID_SIZE; xx += GRID_SIZE) {
            for(int yy = 0; yy < height * GRID_SIZE; yy += GRID_SIZE) {
                drawCell(xx, yy);
            }
        }
    }

    private void drawCell(int x, int y) {
        if(grid[(x / GRID_SIZE) + (y / GRID_SIZE) * width] == -1) {
            shape.begin(ShapeRenderer.ShapeType.Line);
            shape.line(x, y, x + GRID_SIZE, y);
            shape.line(x, y + GRID_SIZE, x + GRID_SIZE, y + GRID_SIZE);
            shape.line(x, y, x, y + GRID_SIZE);
            shape.line(x + GRID_SIZE, y, x + GRID_SIZE, y + GRID_SIZE);
        }
        else {
            shape.begin(ShapeRenderer.ShapeType.Filled);
            shape.rect(x, y, GRID_SIZE, GRID_SIZE);
        }

        shape.end();
    }
}

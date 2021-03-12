package net.dinastiafoca.vikinggame.world;

import com.artemis.World;
import com.artemis.WorldConfigurationBuilder;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Disposable;
import net.dinastiafoca.vikinggame.entity.EntityFactory;
import net.dinastiafoca.vikinggame.entity.system.SpriteRenderSystem;

public class Village implements Disposable {

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

        WorldConfigurationBuilder worldConfig = new WorldConfigurationBuilder()
                .with(new SpriteRenderSystem(camera));

        world = new World(worldConfig.build());
        world.inject(entityFactory);

        entityFactory.createMinhoca(world, 0,0);
    }

    public void update(float delta) {
        world.delta = delta;
        world.process();
    }

    public World getArtmisWorld() {
        return world;
    }

    public EntityFactory getEntityFactory() {
        return entityFactory;
    }

    @Override
    public void dispose() {
        world.dispose();
    }
}

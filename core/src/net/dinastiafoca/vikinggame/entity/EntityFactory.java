package net.dinastiafoca.vikinggame.entity;

import com.artemis.ComponentMapper;
import com.artemis.World;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import net.dinastiafoca.vikinggame.entity.component.SpriteComponent;
import net.dinastiafoca.vikinggame.entity.component.TransformComponent;
import net.dinastiafoca.vikinggame.world.BuildData;

import static net.dinastiafoca.vikinggame.world.Village.GRID_SIZE;

public class EntityFactory {

    private ComponentMapper<TransformComponent> transformComponentMapper;
    private ComponentMapper<SpriteComponent> spriteComponentMapper;

    public BuildData createMinhoca(World world, int x, int y) {
        int entityId = world.create();

        x = (x / GRID_SIZE) * GRID_SIZE;
        y = (y / GRID_SIZE) * GRID_SIZE;

        x /= GRID_SIZE;
        y /= GRID_SIZE;

        Texture texture = new Texture("sprites/minhoca.png");

        TransformComponent transformComponent = transformComponentMapper.create(entityId);
        transformComponent.position.set(x * GRID_SIZE, y * GRID_SIZE);
        transformComponent.scaleX = 3;
        transformComponent.scaleY = 2;

        SpriteComponent spriteComponent = spriteComponentMapper.create(entityId);
        spriteComponent.sprite = new Sprite(texture);

        System.out.println(x + " " + y);

        return new BuildData(entityId, x, y, 3, 2);
    }
}

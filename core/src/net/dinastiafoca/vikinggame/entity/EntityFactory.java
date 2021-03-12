package net.dinastiafoca.vikinggame.entity;

import com.artemis.ComponentMapper;
import com.artemis.World;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import net.dinastiafoca.vikinggame.entity.component.SpriteComponent;
import net.dinastiafoca.vikinggame.entity.component.TransformComponent;

public class EntityFactory {

    private ComponentMapper<TransformComponent> transformComponentMapper;
    private ComponentMapper<SpriteComponent> spriteComponentMapper;

    public int createMinhoca(World world, float x, float y) {
        int entityId = world.create();

        Texture texture = new Texture("snow.png");

        TransformComponent transformComponent = transformComponentMapper.create(entityId);
        transformComponent.position.set(x, y);

        SpriteComponent spriteComponent = spriteComponentMapper.create(entityId);
        spriteComponent.sprite = new Sprite(texture);

        return entityId;
    }
}

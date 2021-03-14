package net.dinastiafoca.vikinggame.world;

public class BuildData {

    private final int entityId;
    private final int width;
    private final int height;

    private int x;
    private int y;

    public BuildData(int entityId, int x, int y, int width, int height)
    {
        this.entityId = entityId;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public int getEntityId() {
        return entityId;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

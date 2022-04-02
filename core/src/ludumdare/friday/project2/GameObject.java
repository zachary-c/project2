package ludumdare.friday.project2;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class GameObject {

    private int posX;
    private int posY;
    protected Handler handler;
    protected float camera_ratio;

    public GameObject(int posX, int posY, Handler handler){
        this.posX = posX;
        this.posY = posY;
        this.handler = handler;
        camera_ratio = handler.game.gameScreen.camera_ratio;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void render(SpriteBatch batch){
        // does stuff in subclasses
    }
    public Rectangle getRectangle() {
        return null;
    }
}

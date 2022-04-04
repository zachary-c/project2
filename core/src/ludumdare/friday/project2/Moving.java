package ludumdare.friday.project2;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public abstract class Moving extends Animated {

    private float velX = 0, velY = 0;
    protected int speed;

    private boolean moveX = true;
    private boolean moveY = true;
    private ArrayList<GameObject> nearby;

   // private final float speedScale = Project2.SPRITE_SPEED_SCALE;

    public Moving(int posX, int posY, Handler handler, int speed) {
        super(posX,posY,handler);
        this.speed = speed;
        nearby = new ArrayList<>();
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
        setPosX((int) (getPosX()+(velX*Project2.SPRITE_SPEED_SCALE)));
        setPosY((int) (getPosY()+(velY*Project2.SPRITE_SPEED_SCALE)));
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public float getVelX() {
        return velX;
    }
    public float getVelY() {
        return velY;
    }
}

package ludumdare.friday.project2;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Moving extends Animated {

    private int velX = 0, velY = 0;
    protected int speed;

   // private final float speedScale = Project2.SPRITE_SPEED_SCALE;

    public Moving(int posX, int posY, Handler handler, int speed) {
        super(posX,posY,handler);
        this.speed = speed;
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
        setPosX((int) (getPosX()+(velX*Project2.SPRITE_SPEED_SCALE)));
        setPosY((int) (getPosY()+(velY*Project2.SPRITE_SPEED_SCALE)));
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public int getVelX() {
        return velX;
    }
    public int getVelY() {
        return velY;
    }
}

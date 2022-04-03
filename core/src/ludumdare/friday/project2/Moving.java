package ludumdare.friday.project2;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public abstract class Moving extends Animated {

    private int velX = 0, velY = 0;
    protected int speed;

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

        int newX = (int) (getPosX()+(velX*Project2.SPRITE_SPEED_SCALE));
        int newY = (int) (getPosY()+(velY*Project2.SPRITE_SPEED_SCALE));
        for (GameObject g : handler.getWorld().getCurrentLevel().getAutoWalls()) {
            Rectangle nearbyRect = g.getRectangle();
            if (g.getID() != getID() && nearbyRect != null) {
                if (new Rectangle(newX, getPosY(), animator.getWidth(), animator.getHeight()).overlaps(nearbyRect) || new Rectangle(getPosX(), newY, animator.getWidth(), animator.getHeight()).overlaps(nearbyRect)) {
                    return;
                }
            }
        }
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

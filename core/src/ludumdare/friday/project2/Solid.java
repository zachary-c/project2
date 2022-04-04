package ludumdare.friday.project2;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Solid extends Moving {
    public Solid(int posX, int posY, Handler handler, int speed) {
        super(posX, posY, handler, speed);
    }

    @Override
    public void render(SpriteBatch batch) {
        int newX = (int) (getPosX()+(getVelX()*Project2.SPRITE_SPEED_SCALE));
        int newY = (int) (getPosY()+(getVelY()*Project2.SPRITE_SPEED_SCALE));


        for (GameObject g : handler.getWorld().getCurrentLevel().getAutoWalls()) {
            Rectangle nearbyRect = g.getRectangle();
            if (g.getID() != getID() && nearbyRect != null) {
                if (new Rectangle(newX, getPosY(), animator.getWidth(), animator.getHeight()).overlaps(nearbyRect)) {
                    setVelX(0);
                }
                if (new Rectangle(getPosX(), newY, animator.getWidth(), animator.getHeight()).overlaps(nearbyRect)) {
                    setVelY(0);
                }
            }
        }
        super.render(batch);
    }

}

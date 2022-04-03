package ludumdare.friday.project2;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Doodad extends GameObject {

    public Doodad(int posX, int posY, Handler handler) {
        super(posX, posY, handler);
    }

    @Override
    public void render(SpriteBatch batch) {

    }

    @Override
    public Rectangle getRectangle() {
        // default width-height is 128-128, size of a tile
        return new Rectangle(getPosX(), getPosY(), 128,128);
    }


}

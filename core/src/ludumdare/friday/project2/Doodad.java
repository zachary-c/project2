package ludumdare.friday.project2;

import com.badlogic.gdx.graphics.Texture;

public abstract class Doodad extends GameObject {

    private Texture texture;

    public Doodad(int posX, int posY, Handler handler, Texture texture) {
        super(posX, posY, handler);
        this.texture = texture;
    }

    public void render() {

    }


}

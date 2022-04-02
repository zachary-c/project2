package ludumdare.friday.project2;

import com.badlogic.gdx.graphics.Texture;

public abstract class Doodad extends GameObject {

    private Texture texture;

    public Doodad(int posX, int posY, Texture texture) {
        super(posX, posY);
        this.texture = texture;
    }

    public void render() {

    }


}

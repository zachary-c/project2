package ludumdare.friday.project2;

import com.badlogic.gdx.graphics.Texture;

public class GameObject {

    private int posX;
    private int posY;

    private Texture ObjectTexture;

    public GameObject(int posX, int posY, Texture ObjectTexture){
        this.posX = posX;
        this.posY = posY;
        this.ObjectTexture = ObjectTexture;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public Texture getObjectTexture() {
        return ObjectTexture;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setObjectTexture(Texture objectTexture) {
        ObjectTexture = objectTexture;
    }

}

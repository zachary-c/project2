package ludumdare.friday.project2;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Player extends GameObject{

    private int health;
    private int speed;
    private ArrayList<GameObject> inventory;

    public Player(int posX, int posY, Texture objectTexture, int health, int speed)
    {
        super(posX, posY, objectTexture);
        this.health = health;
        this.speed = speed;
        inventory = new ArrayList<GameObject>();
    }


}

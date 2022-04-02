package ludumdare.friday.project2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.ArrayList;

public class Player extends Animated {

    private int health;
    private final int speed;
    private ArrayList<GameObject> inventory;

    // these are the animations that need loaded into the animator
    String[] walkAnimations = {"anna_front", "anna_right", "anna_left", "anna_back"};

    private TextureAtlas atlas;

    private int velX, velY;

    public Player(int posX, int posY, int health, int speed)
    {
        super(posX, posY);
        this.health = health;
        this.speed = speed;
        inventory = new ArrayList<GameObject>();

        atlas = new TextureAtlas(Gdx.files.internal("./walkin/walkin.atlas"));

        // load each of the animations provided by the walkAnimations array into the animator
        for (String s : walkAnimations) {
            animator.addAnimationByRegion(atlas, s);
        }
    }

    public void render() {
        // encapsulation babyyyy
        setPosX(getPosX()+velX);
        setPosY(getPosY()+velY);
        animator.render();

        keyHandler();
    }

    private void keyHandler() {

        // keep left right and up down separate
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            velX = -speed;
            animator.setCurrentAnim("anna_left");
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            velX = speed;
            animator.setCurrentAnim("anna_right");
        } else {
            velX = 0;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            velY = -speed;
            animator.setCurrentAnim("anna_front");
        } else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            velY = speed;
            animator.setCurrentAnim("anna_back");
        } else {
            velY = 0;
        }
    }


}

package ludumdare.friday.project2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Player extends Moving {

    private int health;
    private ArrayList<GameObject> inventory;

    // these are the animations that need loaded into the animator
    String[] walkAnimations = {"anna_front", "anna_right", "anna_left", "anna_back"};

    private TextureAtlas atlas;

    public Player(int posX, int posY, Handler handler, int health, int speed)
    {
        super(posX, posY, handler, speed);
        this.health = health;
        inventory = new ArrayList<>();

        atlas = new TextureAtlas(Gdx.files.internal("./walkin/walkin.atlas"));

        // load each of the animations provided by the walkAnimations array into the animator
        for (String s : walkAnimations) {
            animator.addAnimationByRegion(atlas, s);
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        // encapsulation babyyyy
        super.render(batch);
        keyHandler();
    }

    private void keyHandler() {

        // keep left right and up down separate
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            setVelX(-speed);
            animator.setCurrentAnim("anna_left");
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            setVelX(speed);
            animator.setCurrentAnim("anna_right");
        } else {
            setVelX(0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            setVelY(-speed);
            animator.setCurrentAnim("anna_front");
        } else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            setVelY(speed);
            animator.setCurrentAnim("anna_back");
        } else {
            setVelY(0);
        }
    }


}

package ludumdare.friday.project2;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.*;

// HashMap yay
import java.util.HashMap;

public class Animator {

    private SpriteBatch spriteBatch;
    private TextureRegion currentFrame;
    // maps names of animations ("anna_left", "anna_right") to the appropriate Animation Object (a sequence of TextureRegions, really)
    private HashMap<String,Animation<TextureRegion>> animations;
    // keeps track of the current animation that should be playing; the string is a key in the above hashmap
    private String currentAnim = "anna_right";;
    private float stateTime;

    private GameObject owner;

    private float scale = 1f;


    public Animator(GameObject owner) {
        // initalize so the player's x and y match the animation's
        this.owner = owner;

        // instantiate the ArrayList and SpriteBatch
        animations = new HashMap<>();
        spriteBatch = new SpriteBatch();

        // initialize for below
        stateTime = 0.0f;
    }

    public void addAnimationByRegion(TextureAtlas atlas, String name) {
        // This line takes the given atlas (large sprite file) and loads the animation with the give name ("anna_left_0", "anna_left_1), etc
        Animation<TextureRegion> temp = new Animation<TextureRegion>(0.09f, atlas.findRegions(name), Animation.PlayMode.LOOP);
        // then we put it into the animation hashmap paired with its name

        animations.put(name, temp);
    }

    public void render(SpriteBatch batch) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen
        stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time

        // Get current frame of animation for the current stateTime
        currentFrame = animations.get(currentAnim).getKeyFrame(stateTime, true);
        batch.draw(currentFrame, owner.getPosX(), owner.getPosY(), currentFrame.getRegionWidth()*scale, currentFrame.getRegionHeight()*scale); // Draw current frame at the owner's x and y coords

    }

    public void setScaling(float scale) {
        this.scale = scale;
    }

    public void setCurrentAnim(String currentAnim) {
        // for changing what animation is playing
        this.currentAnim = currentAnim;
    }
}

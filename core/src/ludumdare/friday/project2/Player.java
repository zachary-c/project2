package ludumdare.friday.project2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Player extends Solid {

    private int health;
    private ArrayList<GameObject> inventory;

    private Sound damageSound;

    // these are the animations that need loaded into the animator
    String[] walkAnimations = {"anna_front", "anna_right", "anna_left", "anna_back", "anna_ka"};

    private TextureAtlas atlas;
    private float iframes;

    private boolean onExit;

    public Player(int posX, int posY, Handler handler, int health, int speed)
    {
        super(posX, posY, handler, speed);
        this.health = health;
        iframes = 0f;
        inventory = new ArrayList<>();

        atlas = new TextureAtlas(Gdx.files.internal("./walkin/walkin.atlas"));
        onExit = false;

        // load each of the animations provided by the walkAnimations array into the animator
        for (String s : walkAnimations) {
            animator.addAnimationByRegion(atlas, s);
        }
        animator.setAnimationSpeed("anna_ka", .9f);

        // damageSound
        damageSound = Gdx.audio.newSound(Gdx.files.internal("./audio/anna_hurt.mp3"));

    }

    @Override
    public void render(SpriteBatch batch) {
        // encapsulation babyyyy
        super.render(batch);
        updateIFrames(Gdx.graphics.getDeltaTime());
        checkExit();
        keyHandler();
   //     System.out.println(getRectangle().overlaps(handler.game.gameScreen.getCameraRect()));
    }

    private void checkExit() {
        if (handler.getWorld().getCurrentLevel().getExitSquare().overlaps(getRectangle())) {
            onExit = true;
        } else {
            onExit = false;
        }
    }

    private void updateIFrames(float delta) {
        iframes = Math.max(iframes - delta, 0);
    }

    @Override
    public boolean hasIFrames() {
        return iframes > 0;
    }

    public float getIFrames() {
        return iframes;
    }

    private void keyHandler() {
        // keep left right and up down separate
        if (!(Gdx.input.isKeyPressed(Input.Keys.W)
                && Gdx.input.isKeyPressed(Input.Keys.A)
                && Gdx.input.isKeyPressed(Input.Keys.S)
                && Gdx.input.isKeyPressed(Input.Keys.D))) {
            animator.setCurrentAnim("anna_ka");
        }

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

        if (onExit && Gdx.input.isKeyPressed(Input.Keys.E)) {
            handler.loadNextLevel();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.H)) {
        }

    }

    public void takeDamage(int dmg) {
       // System.out.println("taking damage");
        if (!(iframes > 0)) {
            health-=dmg;
            iframes+=.75f;
            damageSound.play(.75f * handler.game.getVolume());
        }
    }

    public int getHealth() { return health; }

    @Override
    public void dispose() {
        damageSound.dispose();
    }
}

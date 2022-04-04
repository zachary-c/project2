package ludumdare.friday.project2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Align;

import java.util.Random;

public class Enemy extends Solid {

    // nate's department
    String[] walkAnimations = {"pendalton_front", "pendalton_right", "pendalton_left", "pendalton_back"};

    private boolean active;

    private TextureAtlas atlas;
    private int damage;

    private Sound discountCat;
    private float soundTimer;
    private Random random = new Random();

    public Enemy(int posX, int posY, Handler handler, int speed, int damage){
        super(posX, posY, handler, speed);

        this.damage = damage;

        active = false;
        atlas = new TextureAtlas(Gdx.files.internal("./penta_walkin/penta_walk_cycle.atlas"));

        // load each of the animations provided by the walkAnimations array into the animator
        for (String s : walkAnimations) {
            animator.addAnimationByRegion(atlas, s);
        }

        // Sound Initialization
        discountCat = Gdx.audio.newSound(Gdx.files.internal("./audio/penta_noise.mp3"));
        soundTimer = 5f;
    }

    public void render(SpriteBatch batch) {
        // encapsulation babyyyy
        if (!active && handler.game.gameScreen.getCameraRect() != null && getRectangle().overlaps(handler.game.gameScreen.getCameraRect())) {
            active = true;
        }
        if (active) {
            super.render(batch);
            detectDamage();
            makeNoise();
            search();
        }
    }

    public void makeNoise() {
        soundTimer -= Gdx.graphics.getDeltaTime();
        if (soundTimer <= 0) {
            soundTimer = random.nextInt(3)+5;

            discountCat.play(handler.game.getVolume()*getSpatialLevel());
        //    System.out.println(distScalar);
        //    System.out.println(handler.game.getVolume()*distScalar);
        }
    }

    public float getSpatialLevel() {
        float distScalar = (float) Math.sqrt(Math.pow(handler.getPlayer().getPosX()-getPosX(), 2) + Math.pow(handler.getPlayer().getPosY()-getPosY(), 2));

        float cameraWidth = handler.game.gameScreen.getCameraRect().width;
        //    System.out.println("cameraWidth: " + cameraWidth);
        //    System.out.println("distScalar: " + distScalar);
        if (distScalar > cameraWidth) {
            distScalar = .25f;
        } else {
            distScalar = (cameraWidth - distScalar) / cameraWidth + .1f;
        }
        return distScalar;
    }

    public void detectDamage() {
        if (handler.getPlayer().getRectangle().overlaps(getRectangle())) {
            handler.getPlayer().takeDamage(damage);
        }
    }

    public void search(){
        int playerPosX = handler.getPlayer().getPosX();
        int playerPosY = handler.getPlayer().getPosY();

        if (playerPosX < getPosX()){
            setVelX(-speed);
            animator.setCurrentAnim("pendalton_left");
        }
        if (playerPosX > getPosX()){
            setVelX(speed);
            animator.setCurrentAnim("pendalton_right");
        }
        if (playerPosY < getPosY()){
            setVelY(-speed);
            animator.setCurrentAnim("pendalton_front");
        }
        if (playerPosY > getPosY()){
            setVelY(speed);
            animator.setCurrentAnim("pendalton_back");
        }

        if (Math.abs(playerPosX - getPosX()) < 4){
            setVelX(0);
        }
        if (Math.abs(playerPosY - getPosY()) < 4){
            setVelY(0);
        }
        if (Math.abs(playerPosY - getPosY()) < Math.abs(playerPosX - getPosX())) {
            if (playerPosX < getPosX()) {
                animator.setCurrentAnim("pendalton_left");
            } else {
                animator.setCurrentAnim("pendalton_right");
            }
        }


    }

    @Override
    public void dispose() {
        discountCat.dispose();
    }


}

package ludumdare.friday.project2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Align;

public class Enemy extends Moving{

    // nate's department
    String[] walkAnimations = {"pendalton_front", "pendalton_right", "pendalton_left", "pendalton_back"};

    private boolean active;

    private TextureAtlas atlas;
    private int damage;

    public Enemy(int posX, int posY, Handler handler, int speed, int damage){
        super(posX, posY, handler, speed);

        this.damage = damage;

        active = false;
        atlas = new TextureAtlas(Gdx.files.internal("./penta_walkin/penta_walk_cycle.atlas"));

        // load each of the animations provided by the walkAnimations array into the animator
        for (String s : walkAnimations) {
            animator.addAnimationByRegion(atlas, s);
        }
    }

    public void render(SpriteBatch batch) {
        // encapsulation babyyyy
     //   Rectangle rect = getRectangle();
     //   System.out.println(rect.x + ", " + (rect.y) + "\n" + (rect.x+ rect.width) + ", " + (rect.y + rect.height));
        if (!active && handler.game.gameScreen.getCameraRect() != null && getRectangle().overlaps(handler.game.gameScreen.getCameraRect())) {
            active = true;
         //   System.out.println("inside");
        }
        if (active) {
            super.render(batch);
            detectDamage();
            search();
        }
    }

    public void activate() {
        active = true;

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


}

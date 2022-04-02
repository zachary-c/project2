package ludumdare.friday.project2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Enemy extends Moving{

    // nate's department
    String[] walkAnimations = {"pendalton_front", "pendalton_right", "pendalton_left", "pendalton_back"};

    private TextureAtlas atlas;

    public Enemy(int posX, int posY, Handler handler, int speed){
        super(posX, posY, handler, speed);

        atlas = new TextureAtlas(Gdx.files.internal("./penta_walkin/penta_walk_cycle.atlas"));

        // load each of the animations provided by the walkAnimations array into the animator
        for (String s : walkAnimations) {
            animator.addAnimationByRegion(atlas, s);
        }
    }

    public void render(SpriteBatch batch) {
        // encapsulation babyyyy
        super.render(batch);
        search();
    }

    public void search(){
        int playerPosX = handler.getPlayer().getPosX();
        int playerPosY = handler.getPlayer().getPosY();
        if (playerPosX < getPosX()){
            setVelX(-speed);
            animator.setCurrentAnim("pendalton_right");
        }
        if (playerPosX > getPosX()){
            setVelX(speed);
            animator.setCurrentAnim("pendalton_left");
        }
        if (playerPosY < getPosY()){
            setVelY(-speed);
            animator.setCurrentAnim("pendalton_back");
        }
        if (playerPosY > getPosY()){
            setVelY(speed);
            animator.setCurrentAnim("pendalton_front");
        }


    }


}

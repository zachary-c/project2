package ludumdare.friday.project2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Enemy extends Animated{

    // nate's department
    String[] walkAnimations = {"anna_front", "anna_right", "anna_left", "anna_back"};

    private TextureAtlas atlas;

    private int velX, velY;


    public Enemy(int posX, int posY, Handler handler){
        super(posX, posY, handler);

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


    }


}

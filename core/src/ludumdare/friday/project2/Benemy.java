package ludumdare.friday.project2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Benemy extends Moving{

    private String[] walkAnimations = {"ben_left", "ben_right", "ben_front", "ben_back"};

    private TextureAtlas atlas;
    private int rof;

    public Benemy(int posX, int posY, Handler handler, int speed, int rateOfFire) {
        super(posX,posY,handler,speed);

        rof = rateOfFire;

        atlas = new TextureAtlas(Gdx.files.internal("./ben_walkin/ben_walk_cycle.atlas"));

        for (String s : walkAnimations) {
            animator.addAnimationByRegion(atlas, s);
        //    System.out.println();
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
            animator.setCurrentAnim("ben_left");
        }
        if (playerPosX > getPosX()){
            setVelX(speed);
            animator.setCurrentAnim("ben_right");
        }
        if (playerPosY < getPosY()){
            setVelY(-speed);
            animator.setCurrentAnim("ben_front");
        }
        if (playerPosY > getPosY()){
            setVelY(speed);
            animator.setCurrentAnim("ben_back");
        }


    }



}

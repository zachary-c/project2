package ludumdare.friday.project2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Benemy extends Solid{

    private String[] walkAnimations = {"ben_left", "ben_right", "ben_front", "ben_back"};

    private TextureAtlas atlas;
    private int rof;

    private boolean active;
    private float timeToFire;
    private int leftBound;
    private int rightBound;

    public Benemy(int posX, int posY, Handler handler, int speed, int rateOfFire) {
        super(posX,posY,handler,speed);

        rof = rateOfFire;
        timeToFire = 2f;
        setVelX(-handler.getEnemySpeed()/2f);

        leftBound = posX-128;
        rightBound = posX+128;

        active = false;
        atlas = new TextureAtlas(Gdx.files.internal("./ben_walkin/ben_walk_cycle.atlas"));

        for (String s : walkAnimations) {
            animator.addAnimationByRegion(atlas, s);
            //    System.out.println();
        }

    }

    public void render(SpriteBatch batch) {
        // encapsulation babyyyy
        super.render(batch);
        if (!active && handler.game.gameScreen.getCameraRect() != null && getRectangle().overlaps(handler.game.gameScreen.getCameraRect())) {
            active = true;
        }
        if (active) {
            super.render(batch);
            // this is to check if we should fire a projectile, and if we shouldn't update the timer
            if (timeToFire > 0) {
                timeToFire-=Gdx.graphics.getDeltaTime();
            } else {
                // otherwise do, reset timer
                fireProjectile();
                timeToFire=2f;
            }

            if (getVelX() > 0 && getPosX() > rightBound) {
                setVelX(-getVelX());
                animator.setCurrentAnim("ben_left");
            } else if (getVelX() < 0 && getPosX() < leftBound) {
                setVelX(-getVelX());
                animator.setCurrentAnim("ben_right");
            }
        }
    }

    public void fireProjectile(){
        int ppX = (int) (handler.getPlayer().getPosX()+handler.getPlayer().getRectangle().width/2);
        int ppY = (int) (handler.getPlayer().getPosY()+handler.getPlayer().getRectangle().height/2);
        int triangleHeight = ppY - getPosY();
        int triangleWidth = ppX - getPosX();

        float dist = (float) Math.sqrt(Math.pow(triangleHeight, 2) + Math.pow(triangleWidth, 2));

    //    System.out.println("spawning a new projectile!");
        handler.addProjectile(new BaseProjectile(getPosX(), (int) (getPosY() + animator.getHeight()/2f), handler, dist, triangleWidth, triangleHeight, getID()));
    //    double angle = Math.tan(triangleHeight/triangleWidth);
    }
}

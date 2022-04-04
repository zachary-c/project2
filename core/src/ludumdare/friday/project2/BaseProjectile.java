package ludumdare.friday.project2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;

public class BaseProjectile extends Moving {

    public static final int BASE_PROJ_SPEED = 3;

    private TextureAtlas atlas;

    // intangible
    private float it;
    private int creatorID;

    public BaseProjectile(int startX, int startY, Handler handler, float dist, int baseDist, int heightDist, int creatorID) {
        // initialize stuff, set the speed to the ratio given by the Benemy
        super(startX, startY, handler, BASE_PROJ_SPEED);
        setVelX((speed/dist) * baseDist);
        setVelY((speed/dist) * heightDist);

        double angle = Math.toDegrees(Math.atan2(heightDist,baseDist))+180;
        animator.setRotation((float) angle);

        // initialize the atlas and add the animation for it
        atlas = new TextureAtlas(Gdx.files.internal("./particles/base_projectile.atlas"));
        animator.addAnimationByRegion(atlas, "proj");

        // initialize creatorID so we don't break on the spawner
        this.creatorID = creatorID;
    }

    public int getCreatorID() {
        return creatorID;
    }

    /*
    @Override
    public void render(SpriteBatch batch) {
        // takes care of animation & adding velocity, leaving us to make the collision check
        super.render(batch);
    } */

    public void explode() {
        // sound stuff, probably
        System.out.println("exploding");
    }

    @Override
    public Rectangle getRectangle() {
        Rectangle temp = super.getRectangle();
        return new Rectangle(temp.x, temp.y, temp.width*Project2.PROJECTILE_SCALE, temp.height* Project2.PROJECTILE_SCALE);
    }

}

package ludumdare.friday.project2;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Animated extends GameObject {

    protected Animator animator;

    //protected float scale = Project2.SPRITE_SCALE;

    public Animated(int posX, int posY, Handler handler) {
        super(posX, posY, handler);

        animator = new Animator(this);
    //    animator.setScaling(scale);
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
        animator.render(batch);
    }

    @Override
    public Rectangle getRectangle() {
        return animator.getRectangle();
    }
}
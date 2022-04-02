package ludumdare.friday.project2;


public abstract class Animated extends GameObject {

    protected Animator animator;

    public Animated(int posX, int posY, Handler handler) {
        super(posX, posY, handler);

        animator = new Animator(this);
    }


}

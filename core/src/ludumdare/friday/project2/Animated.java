package ludumdare.friday.project2;


public abstract class Animated extends GameObject {

    protected Animator animator;

    public Animated(int posX, int posY) {
        super(posX, posY);

        animator = new Animator(this);
    }


}

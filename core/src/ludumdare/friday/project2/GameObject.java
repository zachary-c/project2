package ludumdare.friday.project2;

public abstract class GameObject {

    private int posX;
    private int posY;
    protected Handler handler;

    public GameObject(int posX, int posY, Handler handler){
        this.posX = posX;
        this.posY = posY;
        this.handler = handler;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void render(){

    }
}

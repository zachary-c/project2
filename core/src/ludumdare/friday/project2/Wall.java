package ludumdare.friday.project2;

public class Wall extends Doodad{
    // this class might be extraneous
    public Wall(int x, int y, Handler handler) {
        super(x,y,handler);
    }

    public String toString() {
        return "Wall:\n tileCoords: (" + getPosX()/128 + ", " + getPosY()/128 + ")\n realCoords: (" + (getPosX()) + ", " + (getPosY()) + ")";
    }
}

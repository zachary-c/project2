package ludumdare.friday.project2;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Handler {
    final Project2 game;

    private ArrayList<GameObject> objectList;
    private int pleaseTakeANumber = 0;
    private World world;

    public Handler(final Project2 game){
        this.game = game;
        objectList = new ArrayList<>();
        world = new World(this);
        objectList.add(new Player(8*128, 14*128, this, 100, 3));
        objectList.add(new Enemy(1536, 150, this, 2, 10));
        objectList.add(new Enemy(1536, 896, this, 2, 10));
        objectList.add(new Enemy(150, 896, this, 2, 10));

        //    objectList.add(new Benemy(250, 0, this, 1, 2));
    }

    public void render(SpriteBatch batch){
        for (GameObject o : objectList) {
            o.render(batch);
        }
    }

    public ArrayList<GameObject> getObjectList() {
        return objectList;
    }

    public Player getPlayer() {
        return (Player) objectList.get(0);
    }

    public int getNextID() {
        pleaseTakeANumber++;
        return pleaseTakeANumber;
    }

    public World getWorld() {
        return world;
    }
}

package ludumdare.friday.project2;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Handler {
    final Project2 game;

    private ArrayList<GameObject> objectList;
    private int pleaseTakeANumber = 0;
    private World world;

    // nextPlayerSpawnLocation
    private int nextPSLX = 0;
    private int nextPSLY = 0;

    // enemy stats
    private int enemySpeed;
    private int enemyDamage;
    private int enemyFireRate;

    public Handler(final Project2 game){
        this.game = game;
        objectList = new ArrayList<>();
        objectList.add(new Player(0, 0, this, 100, 3));


        // initialize enemy stats
        enemySpeed = 2;
        enemyDamage = 10;
        enemyFireRate = 2;

        world = new World(this);
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

    public void dispose() {
        for (GameObject o : objectList) {
            o.dispose();
        }
    }

    public void setPlayerSpawn(int x, int y) {
        nextPSLX = x;
        nextPSLY = y;
    }
    public int getPlayerSpawnX() { return nextPSLX; }
    public int getPlayerSpawnY() { return nextPSLY; }

    public int getEnemySpeed() {
        return enemySpeed;
    }
    public int getEnemyDamage() {
        return enemyDamage;
    }
    public int getEnemyFireRate() { return enemyFireRate; }
}

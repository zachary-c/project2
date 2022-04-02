package ludumdare.friday.project2;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Handler {
    final Project2 game;


    private ArrayList<GameObject> objectList;
    private World curWorld;

    public Handler(final Project2 game){
        this.game = game;
        objectList = new ArrayList<>();
        curWorld = new World();
        objectList.add(new Player(50, 50, this, 100, 3));
        objectList.add(new Enemy(50, 50, this, 2));

    }

    public void render(SpriteBatch batch){
        objectList.get(0).render(batch);
        objectList.get(1).render(batch);
    }

    public ArrayList<GameObject> getObjectList() {
        return objectList;
    }

    public Player getPlayer() {
        return (Player) objectList.get(0);
    }

}

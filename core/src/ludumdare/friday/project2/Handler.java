package ludumdare.friday.project2;

import java.util.ArrayList;

public class Handler {

    private ArrayList<GameObject> objectList;
    private World curWorld;

    public Handler(){
        objectList = new ArrayList<>();
        curWorld = new World();
        objectList.add(new Player(50, 50, this, 100, 6));

    }

    public void render(){
        objectList.get(0).render();
    }

}

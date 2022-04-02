package ludumdare.friday.project2;

import java.util.ArrayList;

public class Handler {
    final Project2 game;


    private ArrayList<GameObject> objectList;
    private World curWorld;

    public Handler(final Project2 game){
        this.game = game;
        objectList = new ArrayList<>();
        curWorld = new World();
        objectList.add(new Player(50, 50, this, 100, 6));
        objectList.add(new Enemy(50, 50, this));

    }

    public void render(){
        objectList.get(0).render();
        //objectList.get(1).render();
    }

    public ArrayList<GameObject> getObjectList() {
        return objectList;
    }


}

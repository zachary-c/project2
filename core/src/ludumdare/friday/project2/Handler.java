package ludumdare.friday.project2;

import java.util.ArrayList;

public class Handler {

    private ArrayList<GameObject> objectList;
    private World curWorld;

    public Handler(){
        objectList = new ArrayList<GameObject>();
        curWorld = new World();
    }

}

package ludumdare.friday.project2;

public class World {
    private Level currentLevel;

    private String tmxLevelTwo = "./tileLevel/BEEG_ROOM.tmx";
    private String tsxLevel1 = "./tileSet/Level0.tsx";

    private Handler handler;

    public World(Handler handler){
        this.handler = handler;
        currentLevel = new Level(handler, tmxLevelTwo, tsxLevel1);
    }
    public Level getCurrentLevel() {
        return currentLevel;
    }
}

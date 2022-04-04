package ludumdare.friday.project2;

public class World {
    private Level currentLevel;
    private String tsxMain = "./tileSet/Level0.tsx";
  //  private String tsxMichaelWave = "./tileSet/michaelwave.tsx";

    private String tmxLevel0 = "./tileLevel/level_1_0.tmx";
    private String tmxLevel1 = "./tileLevel/level_1_1.tmx";
    private String tmxLevel2 = "./tileLevel/level_1_2.tmx";
    private String tmxLevel3 = "./tileLevel/level_1_3.tmx";
    private String tmxLevel4 = "./tileLevel/level_1_4.tmx";
    private String tmxLevel5 = "./tileLevel/level_1_5.tmx";

    private Level[] world1;

    private Handler handler;

    private int clNum;

    public World(Handler handler){
        this.handler = handler;
        clNum = 0;

        world1 = new Level[]{
            new Level(handler, tmxLevel0),
            new Level(handler, tmxLevel2),
            new Level(handler, tmxLevel1),
            new Level(handler, tmxLevel3),
            new Level(handler, tmxLevel4),
            new Level(handler, tmxLevel5),
        };
        currentLevel = world1[clNum];
    }
    public Level getCurrentLevel() {
        return currentLevel;
    }

    public Level nextLevel() {
        clNum++;
        currentLevel = world1[clNum];
        return currentLevel;
    }
}

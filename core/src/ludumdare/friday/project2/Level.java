package ludumdare.friday.project2;



import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Level {

    private int numHorizontalTiles = 16;
    private int numVerticalTiles = 9;
    private Handler handler;

    private String tmxFile;
    private TiledMap tiledMap;
    private ArrayList<GameObject> levelGameObjects;
    private ArrayList<Wall> autoWalls;
    private int playerSpawnX;
    private int playerSpawnY;
    private Rectangle exitSquare;

    public static final int PLAYER_SPAWN_ID = 39;
    public static final int PENTAGON_START = 38;
    public static final int BEN_START = 37;
    public static final int DOOR = 40;
    public static final int MICHAEL_WAVE = 42;
    public static final int EXIT = 41;

    // initialized in makeWalls below
    // make walls is really just becoming a catchall initialize map huh
    // alas

    private static final String TSX_FILE_PATH = "./tileSet/Level0.tsx";
    private static final int tileSize = 128;

    public Level(Handler handler, String tmxFile){
        this.tmxFile = tmxFile;
        levelGameObjects = new ArrayList<>();
        this.handler = handler;
        tiledMap = new TmxMapLoader().load(tmxFile);
        autoWalls = makeWalls(tmxFile, TSX_FILE_PATH, tiledMap);
    }

    public String getTmxFilePath() { return tmxFile; }

    public TiledMap getTiledMap() { return tiledMap; }

    public Rectangle getExitSquare() { return exitSquare; }

    public int getNumVerticalTiles() { return numVerticalTiles; }
    public int getNumHorizontalTiles() { return numHorizontalTiles; }

    public int getPlayerSpawnX() { return playerSpawnX; }
    public int getPlayerSpawnY() { return playerSpawnY; }

    public ArrayList<GameObject> getLevelGameObjects() {
        return levelGameObjects;
    }

    public ArrayList<Wall> getAutoWalls() {
        return autoWalls;
    }

    public ArrayList<Wall> makeWalls(String tmxFP, String tsxFP, TiledMap map) {
        int[] wallNumbers = loadTileSetFileBackwards(tsxFP);

        TiledMapTileLayer layer = (TiledMapTileLayer) (map.getLayers().get(0));
        TiledMapTileLayer layer2 = (TiledMapTileLayer) (map.getLayers().get(1));



        // list of walls to return
        ArrayList<Wall> walls = new ArrayList<>();
        numHorizontalTiles = layer.getWidth();
        numVerticalTiles = layer.getHeight();


        // adds walls, prints out an ascii depiction of the walls on the map
        for (int i = numVerticalTiles-1; i >= 0; i--) {
            for (int j = 0; j < numHorizontalTiles; j++) {
            //    System.out.println(j + ", " + i);
                boolean addWall = false;
                for (int k = 0; k < wallNumbers.length; k++) {
                    if (layer.getCell(j, i).getTile().getId() == wallNumbers[k]) {
                        addWall = true;
                        System.out.print("X ");
                        break;
                    }
                }

                // while we're iterating through, might as well check layer2 for spawns, doors michaelwaves
                if (layer2.getCell(j,i) != null) {
                    int layerCID = layer2.getCell(j, i).getTile().getId();
                 //   System.out.println("found layer CID: " + layerCID);
           //         System.out.println("found not null layer cell at " + j + ", " + i);
           //         System.out.println("this has layer ID: " + layerCID);
                    if (layerCID == PLAYER_SPAWN_ID) {
           //             System.out.println("spawning player at " + j*tileSize + ", " +i*tileSize);
                        layer2.setCell(j, i, null);
                        playerSpawnX = j*tileSize;
                        playerSpawnY = i*tileSize;
                    } else if (layerCID == PENTAGON_START) {
                        System.out.println("spawning pentagon at " + j*tileSize + ", " +i*tileSize);
                        layer2.setCell(j, i, null);
                        levelGameObjects.add(new Enemy(j*tileSize, i*tileSize, handler, handler.getEnemySpeed(), handler.getEnemyDamage()));
                    } else if (layerCID == BEN_START) {
                        layer2.setCell(j, i, null);
              //          System.out.println("spawning benemy at " + j*tileSize + ", " +i*tileSize);
                        levelGameObjects.add(new Benemy(j*tileSize, i*tileSize, handler, handler.getEnemySpeed(), handler.getEnemyFireRate()));
                    } else if (layerCID == EXIT) {
                        exitSquare = new Rectangle(j*128, i*128, 128,128);
                        addWall = false;
                    }
                    else if (layerCID == DOOR) {
                        if (exitSquare == null) {
                            exitSquare = new Rectangle(j*128, i*128, 128,128);
                        }
                        addWall = false;
                    }  else if (layerCID == MICHAEL_WAVE) {
                        addWall = false;
                    }
                }

                if (addWall) {
                    walls.add(new Wall(j*tileSize, i*tileSize, handler));
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }

        return walls;

    }

    public int[] loadTileSetFileBackwards(String filepath) {
        Scanner scnr;

        int[] wallIDs = new int[14];
        boolean wallFound = false;
        int i = 0;
        try {
            scnr = new Scanner(new File(filepath));
            int ID = -1;
            while (scnr.hasNextLine()) {
                String currLine = scnr.nextLine();
                if (currLine.startsWith("  <image ")) {
                    if (currLine.substring(20,currLine.length()-1).contains("_wall") || currLine.substring(20,currLine.length()-1).contains("_corner") || currLine.substring(20,currLine.length()-1).contains("pillar")) {
                        wallFound = true;
               //         System.out.println("wall found");
                    }

                } else if (currLine.startsWith(" <tile id=") && wallFound) {
                    i++;
                    wallFound = false;
                    try {
                        ID = Integer.parseInt(currLine.substring(11,13));
                    } catch (NumberFormatException e) {
                        ID = Integer.parseInt((String.valueOf(currLine.charAt(11))));
                        //    System.out.println("inside number format exception, may cause problems later");
                    }
                    wallIDs[i] = ID;
                }
            }
        } catch (FileNotFoundException f) {
            f.printStackTrace();
        }
        for (int l = 0; l < wallIDs.length; l++) {
            System.out.print(wallIDs[l] + " ");
        }
        System.out.println();
        return wallIDs;
    }

    public int[] loadTileSetFile(String filepath) {
        Scanner scnr;

        int[] wallIDs = new int[13];
        int i = 0;
        try {
            scnr = new Scanner(new File(filepath));
            int priorID = -1;
            while (scnr.hasNextLine()) {
                String currLine = scnr.nextLine();
                if (currLine.startsWith(" <tile id=")) {
                    try {
                        priorID = Integer.parseInt(currLine.substring(11,13));
                    } catch (NumberFormatException e) {
                        priorID = Integer.parseInt((String.valueOf(currLine.charAt(11))));
                    //    System.out.println("inside number format exception, may cause problems later");
                    }
                } else if (currLine.startsWith("  <image ")) {
                    if (currLine.substring(20,currLine.length()-1).contains("_wall") || currLine.substring(20,currLine.length()-1).contains("_corner") || currLine.substring(20,currLine.length()-1).contains("pillar")) {
                        wallIDs[i] = priorID;
                        i++;
                    }
                }
            }
        } catch (FileNotFoundException f) {
            f.printStackTrace();
        }
        for (int l = 0; l < wallIDs.length; l++) {
            System.out.print(wallIDs[l] + " ");
        }
        System.out.println();
        return wallIDs;
    }
    /*
    public int[][] getLevelCSVData(String filepath) {
        int[][] map = new int[numVerticalTiles][numHorizontalTiles];
        StringBuilder dataset = new StringBuilder();
        try {
            Scanner scnr;
            boolean foundSet = false;
            scnr = new Scanner(new File(filepath));
            while (scnr.hasNextLine()) {
                String nextLn = scnr.nextLine();
                if ((nextLn.startsWith("</data>"))) {
                    break;
                }
                if (nextLn.startsWith("  <data e")) {
                    foundSet = true;
                } else if (foundSet) {
                    dataset.append(nextLn + "\n");
                }
            }

        } catch (FileNotFoundException f) {
            f.printStackTrace();
        }
        String[] csv_data = dataset.toString().split("\n");

        for (int i = 0; i < numVerticalTiles; i++) {
            String[] line = csv_data[i].split(",");
            for (int j = 0; j < numHorizontalTiles; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }
        // prints out the 2-dimensional array that was generated from the csv dataset, to check whether or not we got it correctly
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }


        return map;

        //objectList.add(new Doodad();
    } */

    public int[] getLevelDimensions(String tmxFile) throws FileNotFoundException {
        Scanner scnr = new Scanner(new File(tmxFile));
        if (scnr.nextLine().contains("?xml")) {
            String mapLine = scnr.nextLine();

            // Width Parsing
            int wStart = mapLine.indexOf("width=\"") + 7;
            System.out.println("wStart: " + wStart);
            int wEnd = mapLine.indexOf("\"", wStart);
            System.out.println("wEnd: " + wEnd);
            int width = Integer.parseInt(mapLine.substring(wStart, wEnd));

            // Height Parsing
            int hStart = wEnd + 10;
            System.out.println("hStart: " + hStart);
            int hEnd = mapLine.indexOf("\"", hStart);
            System.out.println("heEnd: " + hEnd);
            int height = Integer.parseInt(mapLine.substring(hStart, hEnd));
            System.out.print(width + ", " + height);
            return new int[]{width, height};
        }
        return null;
    }

    /*
    <?xml version="1.0" encoding="UTF-8"?>
<map version="1.8" tiledversion="1.8.4" orientation="orthogonal" renderorder="right-down" width="16" height="9" tilewidth="128" tileheight="128" infinite="0" nextlayerid="2" nextobjectid="1">
 <tileset firstgid="1" source="../tileSet/Level0.tsx"/>
 <layer id="1" name="Tile Layer 1" width="16" height="9">
  <data encoding="csv">
     */
}

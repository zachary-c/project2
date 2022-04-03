package ludumdare.friday.project2;



import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Level {

    private int numHorizontalTiles = 16;
    private int numVerticalTiles = 9;

    private ArrayList<GameObject> objectList;
    private ArrayList<Wall> autoWalls;
    private Handler handler;

    private String tmxFile;
    private String tsxFile;

    private int tileSize = 128;

    public Level(Handler handler, String tmxFile, String tsxFile){
        this.tmxFile = tmxFile;
        this.tsxFile = tsxFile;
        objectList = new ArrayList<>();
        this.handler = handler;
        autoWalls = makeWalls(tmxFile, tsxFile, (TiledMapTileLayer) new TmxMapLoader().load(tmxFile).getLayers().get(0));
    }

    public String getTmxFilePath() { return tmxFile; }
    public String getTsxFilePath() { return tsxFile; }

    public int getNumVerticalTiles() { return numVerticalTiles; }
    public int getNumHorizontalTiles() { return numHorizontalTiles; }

    public ArrayList<Wall> getAutoWalls() {
        return autoWalls;
    }

    public ArrayList<Wall> makeWalls(String tmxFP, String tsxFP, TiledMapTileLayer map) {
        int[] wallNumbers = loadTileSetFileBackwards(tsxFP);
       // int[][] map = getLevelCSVData(tmxFP);

        try {
            int[] dimensions = getLevelDimensions(tmxFile);
            numHorizontalTiles = dimensions[0];
            numVerticalTiles = dimensions[1];
     //       System.out.println(numHorizontalTiles + ", " + numVerticalTiles);
        } catch (FileNotFoundException f) {
            f.printStackTrace();
        }

        ArrayList<Wall> walls = new ArrayList<>();

        boolean printedX = false;
        for (int i = numVerticalTiles-1; i >= 0; i--) {
            for (int j = 0; j < numHorizontalTiles; j++) {
            //    System.out.println(j + ", " + i);
                for (int k = 0; k < wallNumbers.length; k++) {
                    if (map.getCell(j, i).getTile().getId() == wallNumbers[k]) {
                        walls.add(new Wall(j*tileSize, i*tileSize, handler));
                        System.out.print("X ");
                        printedX = true;
                        break;
                    }
                }
                if (!printedX) {
                    System.out.print("  ");
                }
                printedX = false;
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
                        System.out.println("wall found");
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
    }

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

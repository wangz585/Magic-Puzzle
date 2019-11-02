package com.puzzle.mazing.Models;

import java.util.ArrayList;
import java.util.List;


/**
 * Created different levels of difficulty of the game
 */

public class BusyWorkerGameLevels {
    public static final int DEFAULT_ROW_NUM = 12;
    public static final int DEFAULT_COLUMN_NUM = 12;
    //There is nothing in the cell
    public static final char NOTHING = ' ';

    //The cell is a box
    public static final char BOX = 'B';

    //The destination of the box, where the flag lies
    public static final char FLAG = 'F';

    //The worker
    public static final char MAN = 'M';

    //The wall
    public static final char WALL = 'W';

    //The worker overlaps the flag
    public static final char MAN_FLAG = 'R';

    //The flag overlaps the box
    public static final char BOX_FLAG = 'X';


    public static final String [] LEVEL_1 = {

            "WWWWWWWWWWWW",

            "W         FW",

            "W          W",

            "W          W",

            "W   WWWW   W",

            "W          W",

            "W    B     W",

            "W    M     W",

            "W          W",

            "W          W",

            "W          W",

            "WWWWWWWWWWWW"

    };



    public static final String [] LEVEL_2 = {

            "WWWWWWWWWWWW",

            "W          W",

            "W          W",

            "W   WWFW   W",

            "W   WWWW   W",

            "W          W",

            "W    B     W",

            "W    M     W",

            "W          W",

            "W          W",

            "W          W",

            "WWWWWWWWWWWW"

    };



    public static ArrayList<String[]> OriginalLevels = new ArrayList<>();

    public static void loadGameLevels(){
        if (OriginalLevels.isEmpty()) {
            OriginalLevels.add(LEVEL_1);
            OriginalLevels.add(LEVEL_2);
        }
    }


    public static String [] getLevel(int level){
        loadGameLevels();
        return OriginalLevels.get(level - 1);
    }


    public static List<String> getLevelList(){
        loadGameLevels();
        List<String> levelList = new ArrayList<>();
        int levelNum = OriginalLevels.size();
        for (int i = 1; i <= levelNum; i++){
            levelList.add(new String("LEVEL" + i ));
        }
        return levelList;
    }
}

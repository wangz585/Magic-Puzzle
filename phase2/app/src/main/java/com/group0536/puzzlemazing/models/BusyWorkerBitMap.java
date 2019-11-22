package com.group0536.puzzlemazing.models;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.group0536.puzzlemazing.R;

public class BusyWorkerBitMap {

    private static Bitmap WorkerBitmap;
    private static Bitmap BoxBitmap;
    private static Bitmap WallBitmap;
    private static Bitmap FlagBitmap;

    public static void setWorkerBitmap(Resources res) {
        WorkerBitmap = BitmapFactory.decodeResource(res, R.drawable.worker);
    }

    public static void setBoxBitmap(Resources res) {
        BoxBitmap = BitmapFactory.decodeResource(res, R.drawable.box);
    }

    public static void setWallBitmap(Resources res) {
        WallBitmap = BitmapFactory.decodeResource(res, R.drawable.wall);
    }

    public static void setFlagBitmap(Resources res) {
        FlagBitmap = BitmapFactory.decodeResource(res, R.drawable.flag);
    }

    public static Bitmap getWorkerBitmap() {
        return WorkerBitmap;
    }

    public static Bitmap getBoxBitmap() {
        return BoxBitmap;
    }

    public static Bitmap getWallBitmap() {
        return WallBitmap;
    }

    public static Bitmap getFlagBitmap() {
        return FlagBitmap;
    }
}

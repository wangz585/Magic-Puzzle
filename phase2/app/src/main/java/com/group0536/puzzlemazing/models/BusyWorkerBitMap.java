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

    public static void initBitmaps(Resources res) {
        WorkerBitmap = BitmapFactory.decodeResource(res, R.drawable.worker);
        BoxBitmap = BitmapFactory.decodeResource(res, R.drawable.skull);
        WallBitmap = BitmapFactory.decodeResource(res, R.drawable.wall);
        FlagBitmap = BitmapFactory.decodeResource(res, R.drawable.tomb);
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
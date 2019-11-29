package com.group0536.puzzlemazing.models.busyworker;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.group0536.puzzlemazing.R;

public class BitMap {

    private static Bitmap WorkerBitmap;
    private static Bitmap BoxBitmap;
    private static Bitmap WallBitmap;
    private static Bitmap FlagBitmap;

    public static void initBitmaps(Resources res) {
        WorkerBitmap = BitmapFactory.decodeResource(res, R.drawable.worker_worker);
        BoxBitmap = BitmapFactory.decodeResource(res, R.drawable.worker_skull);
        WallBitmap = BitmapFactory.decodeResource(res, R.drawable.worker_wall);
        FlagBitmap = BitmapFactory.decodeResource(res, R.drawable.worker_tomb);
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
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

    /**
     * Initialize all images that is needed in the game
     * @param res the place where all images are stored
     */
    public static void initBitmaps(Resources res) {
        WorkerBitmap = BitmapFactory.decodeResource(res, R.drawable.worker_worker);
        BoxBitmap = BitmapFactory.decodeResource(res, R.drawable.worker_skull);
        WallBitmap = BitmapFactory.decodeResource(res, R.drawable.worker_wall);
        FlagBitmap = BitmapFactory.decodeResource(res, R.drawable.worker_tomb);
    }

    /**
     * Get the image of the worker
     *
     * @return bitmap of the worker
     */
    public static Bitmap getWorkerBitmap() {
        return WorkerBitmap;
    }

    /**
     * Get the image of the box
     *
     * @return bitmap of the box
     */
    public static Bitmap getBoxBitmap() {
        return BoxBitmap;
    }

    /**
     * Get the image of the wall
     *
     * @return bitmap of the wall
     */
    public static Bitmap getWallBitmap() {
        return WallBitmap;
    }

    /**
     * Get the image of the flag
     *
     * @return bitmap of the flag
     */
    public static Bitmap getFlagBitmap() {
        return FlagBitmap;
    }
}
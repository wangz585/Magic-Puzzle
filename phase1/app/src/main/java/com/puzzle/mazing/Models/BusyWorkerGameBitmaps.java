package com.puzzle.mazing.Models;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.puzzle.mazing.R;


/**
 * Created the map the worker pushing his boxes on.
 */

public class BusyWorkerGameBitmaps {

    public static Bitmap ManBitmap = null;
    public static Bitmap BoxBitmap = null;
    public static Bitmap WallBitmap = null;
    public static Bitmap FlagBitmap = null;


    public static void loadGameBitmaps(Resources res) {
        if (ManBitmap == null)
            ManBitmap = BitmapFactory.decodeResource(res, R.drawable.eggman_48x48);
        if (BoxBitmap == null)
            BoxBitmap = BitmapFactory.decodeResource(res, R.drawable.box_48x48);
        if (WallBitmap == null)
            WallBitmap = BitmapFactory.decodeResource(res, R.drawable.stone_48x48);
        if (FlagBitmap == null)
            FlagBitmap = BitmapFactory.decodeResource(res, R.drawable.flag_48x48);
    }
}

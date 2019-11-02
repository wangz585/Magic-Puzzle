package com.puzzle.mazing.DataAccess;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

import com.puzzle.mazing.Activities.BusyWorkerGameActivity;
import com.puzzle.mazing.Models.BusyWorkerGameBitmaps;
import com.puzzle.mazing.R;


/**

 * Draw the GameView of the Game

 */

public class BusyWorkerGameView extends View{
    private float mCellWidth;
    public static final int CELL_NUM_PER_LINE = 12;
    private BusyWorkerGameActivity mGameActivity;


    public BusyWorkerGameView(Context context) {
        super(context);
        mGameActivity = (BusyWorkerGameActivity) context;

        //load the images
        BusyWorkerGameBitmaps.loadGameBitmaps(getResources());
    }


    //If the size of the game view has changed, then we will implement onSizeChanged
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCellWidth = w / CELL_NUM_PER_LINE;
    }


    @Override

    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        //Background Color
        Paint background = new Paint();
        background.setColor(getResources().getColor(R.color.background));
        canvas.drawRect(0, 0, getWidth(), getHeight(), background);

        //Draw the game surface
        Paint linePaint = new Paint();
        linePaint.setColor(Color.BLACK);
        for (int r = 0; r <= CELL_NUM_PER_LINE; r++)
            canvas.drawLine(0, r * mCellWidth,
                    getWidth(), r * mCellWidth, linePaint);
        for (int c = 0; c <= CELL_NUM_PER_LINE; c++)
            canvas.drawLine(c * mCellWidth, 0,
                    c * mCellWidth, CELL_NUM_PER_LINE * mCellWidth, linePaint);



        //draw the GameView of the current state of the game
        drawGameBoard(canvas);
        if(mGameActivity.getCurrentState().isGameOver()) {
            Paint txtPaint = new Paint();
            txtPaint.setColor(Color.RED);
            txtPaint.setTextSize(100.0f);

            //Report that we pass the game
            canvas.drawText(getContext().getString(R.string.txt_game_over),
                    4 * mCellWidth, 6 * mCellWidth, txtPaint );
        } else if (mGameActivity.getCurrentState().loseGame()) {
            Paint txtPaint = new Paint();
            txtPaint.setColor(Color.RED);
            txtPaint.setTextSize(100.0f);

            //Report that we pass the game
            canvas.drawText("LOSER",
                    4 * mCellWidth, 6 * mCellWidth, txtPaint );
        }
    }


    private void drawGameBoard(Canvas canvas) {
        Rect srcRect;       //represents the interior of the picture
        Rect destRect;      //represent the region where the pic lies on screen

        StringBuffer [] labelInCells = mGameActivity.getCurrentState().getLabelInCells();

        for (int r = 0; r < labelInCells.length; r++)
            for (int c = 0; c < labelInCells[r].length(); c++){
                destRect = getRect(r, c);
                switch (labelInCells[r].charAt(c)){
                    case 'W':
                        //get the complete region of the wall
                        srcRect = new Rect(0, 0,
                                BusyWorkerGameBitmaps.WallBitmap.getWidth(), BusyWorkerGameBitmaps.WallBitmap.getHeight());
                        //draw the wall on the screen
                        canvas.drawBitmap(BusyWorkerGameBitmaps.WallBitmap,
                                srcRect, destRect, null);
                        break;
                    case 'B':
                        srcRect = new Rect(0, 0,
                                BusyWorkerGameBitmaps.BoxBitmap.getWidth(), BusyWorkerGameBitmaps.BoxBitmap.getHeight());
                        canvas.drawBitmap(BusyWorkerGameBitmaps.BoxBitmap, srcRect, destRect, null);
                        break;
                    case 'F':
                        srcRect = new Rect(0, 0,
                                BusyWorkerGameBitmaps.FlagBitmap.getWidth(), BusyWorkerGameBitmaps.FlagBitmap.getHeight());
                        canvas.drawBitmap(BusyWorkerGameBitmaps.FlagBitmap, srcRect, destRect, null);
                        break;
                    case 'M':
                        srcRect = new Rect(0, 0,
                                BusyWorkerGameBitmaps.ManBitmap.getWidth(), BusyWorkerGameBitmaps.ManBitmap.getHeight());
                        canvas.drawBitmap(BusyWorkerGameBitmaps.ManBitmap, srcRect, destRect, null);
                        break;
                }
            }
    }


    @Override

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN)
            return true;

        int touch_x = (int) event.getX();
        int touch_y = (int) event.getY();

        if (touch_blow_to_man(touch_x, touch_y,
                mGameActivity.getCurrentState().getWorkerRow(),
                mGameActivity.getCurrentState().getWorkerCol()))  //touch way below
            mGameActivity.getCurrentState().handleDown();

        if (touch_right_to_man(touch_x, touch_y,
                mGameActivity.getCurrentState().getWorkerRow(),
                mGameActivity.getCurrentState().getWorkerCol()))  //touch way on the right
            mGameActivity.getCurrentState().handleRight();

        if (touch_above_to_man(touch_x, touch_y, mGameActivity.getCurrentState().getWorkerRow(),
                mGameActivity.getCurrentState().getWorkerCol()))
            mGameActivity.getCurrentState().handleAbove();

        if (touch_left_to_man(touch_x, touch_y, mGameActivity.getCurrentState().getWorkerRow(),
                mGameActivity.getCurrentState().getWorkerCol()))
            mGameActivity.getCurrentState().handleLeft();
        postInvalidate();
        return true;
    }


    private boolean touch_blow_to_man(int touch_x, int touch_y, int manRow, int manColumn) {
        int belowRow = manRow + 1;
        Rect belowRect = getRect(belowRow, manColumn);
        return belowRect.contains(touch_x, touch_y);
    }


    private boolean touch_above_to_man(int touch_x, int touch_y, int manRow, int manColumn) {
        int aboveRow = manRow - 1;
        Rect aboveRect = getRect(aboveRow, manColumn);
        return aboveRect.contains(touch_x, touch_y);
    }


    private boolean touch_left_to_man(int touch_x, int touch_y,  int manRow, int manColumn) {
        int leftColumn = manColumn - 1;
        Rect leftRect = getRect(manRow, leftColumn);
        return leftRect.contains(touch_x, touch_y);
    }


    private boolean touch_right_to_man(int touch_x, int touch_y, int manRow, int manColumn) {
        int rightColumn = manColumn + 1;
        Rect rightRect = getRect(manRow, rightColumn);
        return rightRect.contains(touch_x, touch_y);
    }

    @NonNull

    private Rect getRect(int row, int column) {
        int left = (int)(column * mCellWidth);
        int top = (int) (row * mCellWidth);
        int right = (int)((column + 1) * mCellWidth);
        int bottom = (int)((row + 1) * mCellWidth);
        return new Rect(left, top, right, bottom);
    }
}

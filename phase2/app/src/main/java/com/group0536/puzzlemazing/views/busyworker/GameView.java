package com.group0536.puzzlemazing.views.busyworker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.actions.busyworker.BusyWorkerActionCreator;
import com.group0536.puzzlemazing.models.busyworker.BitMap;
import com.group0536.puzzlemazing.models.busyworker.Map;
import com.group0536.puzzlemazing.stores.busyworker.BusyWorkerChangeEvent;
import com.group0536.puzzlemazing.stores.busyworker.BusyWorkerStore;
import com.group0536.puzzlemazing.views.FluxView;
import com.squareup.otto.Subscribe;


public class GameView extends FluxView {

    private BusyWorkerStore store;
    private BusyWorkerActionCreator actionCreator;
    private int CellWidth;

    public GameView(Context context) {
        super(context);
        BitMap.initBitmaps(getResources());
    }

    @Override
    protected void initFluxComponents() {
        store = BusyWorkerStore.getInstance(dispatcher);
        actionCreator = new BusyWorkerActionCreator(dispatcher);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        registerStore(store);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        unregisterStore(store);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);
        drawSurface(canvas);
        drawGameBoard(canvas);
        drawScore(canvas);
        drawTime(canvas);
    }

    private void drawBackground(Canvas canvas) {
        Paint background = new Paint();
        background.setColor(getResources().getColor(R.color.silver));
        canvas.drawRect(0, 0, getWidth(), getHeight(), background);
    }

    private void drawSurface(Canvas canvas) {
        Paint linePaint = new Paint();
        linePaint.setColor(Color.BLACK);
        int maxRow = store.getMap().getWidth();
        for (int row = 0; row <= maxRow; row++)
            canvas.drawLine(0, row * CellWidth,
                    getWidth(), row * CellWidth, linePaint);
        int maxCol = getWidth()/CellWidth;
        for (int column = 0; column <= maxCol; column++)
            canvas.drawLine(column * CellWidth, 0,
                    column * CellWidth,
                    maxRow * CellWidth, linePaint);
    }

    private void drawGameBoard(Canvas canvas) {
        drawWalls(canvas);
        drawBox(canvas);
        drawWorker(canvas);
        drawFlag(canvas);
    }

    private void drawWalls(Canvas canvas) {
        Rect srcRect;
        Rect destRect;
        Map map = store.getMap();
        for (Point wallPosition : map.getWallPositions()) {
            destRect = getRect(wallPosition.x, wallPosition.y);
            srcRect = new Rect(0, 0,
                    BitMap.getWallBitmap().getWidth(), BitMap.getWallBitmap().getHeight());
            canvas.drawBitmap(BitMap.getWallBitmap(),
                    srcRect, destRect, null);
        }
    }

    private void drawBox(Canvas canvas) {
        Point boxPosition = store.getCurrentBoxPosition();
        Rect destRect = getRect(boxPosition.x, boxPosition.y);
        Rect srcRect = new Rect(0, 0,
                BitMap.getBoxBitmap().getWidth(), BitMap.getBoxBitmap().getHeight());
        canvas.drawBitmap(BitMap.getBoxBitmap(),
                srcRect, destRect, null);
    }

    private void drawWorker(Canvas canvas) {
        Point workerPosition = store.getCurrentWorkerPosition();
        Rect destRect = getRect(workerPosition.x, workerPosition.y);
        Rect srcRect = new Rect(0, 0,
                BitMap.getWorkerBitmap().getWidth(), BitMap.getWorkerBitmap().getHeight());
        canvas.drawBitmap(BitMap.getWorkerBitmap(),
                srcRect, destRect, null);
    }

    private void drawFlag(Canvas canvas) {
        Point flagPosition = store.getMap().getFlagPosition();
        Rect destRect = getRect(flagPosition.x, flagPosition.y);
        Rect srcRect = new Rect(0, 0,
                BitMap.getFlagBitmap().getWidth(), BitMap.getFlagBitmap().getHeight());
        canvas.drawBitmap(BitMap.getFlagBitmap(),
                srcRect, destRect, null);
    }

        private void drawTime(Canvas canvas) {
        Paint txtPaint = new Paint();
        txtPaint.setColor(Color.BLACK);
        txtPaint.setTextSize(100.0f);
        canvas.drawText(getContext().getString(R.string.busy_worker_time),
                3 * CellWidth, 15 * CellWidth, txtPaint);
        canvas.drawText(String.valueOf(store.getTimeUsed()),5 * CellWidth,16 * CellWidth,txtPaint);
    }

    private void drawScore(Canvas canvas) {
        Paint txtPaint = new Paint();
        txtPaint.setColor(Color.RED);
        txtPaint.setTextSize(100.0f);
        canvas.drawText(getContext().getString(R.string.busy_worker_score),
                1 * CellWidth, 18 * CellWidth, txtPaint);
        canvas.drawText(String.valueOf(store.getScore()),5 * CellWidth,19 * CellWidth,txtPaint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN) return true;
        Point position = new Point((int) event.getX()/CellWidth, (int) event.getY()/CellWidth);
        actionCreator.move(position);
        return true;
    }

    @Override
    protected void onSizeChanged(int newWidth, int newHeight, int oldWidth, int oldHeight) {
        super.onSizeChanged(newWidth, newHeight, oldWidth, oldHeight);
        CellWidth = newHeight /  store.getMap().getHeight();
    }

    @Subscribe
    public void x(BusyWorkerChangeEvent e) {
        updateUI();
    }

    private void updateUI() {
        postInvalidate();
    }

    private Rect getRect(int x, int y) {
        int left = x * CellWidth;
        int top = y * CellWidth;
        int right = (x + 1) * CellWidth;
        int bottom = (y + 1) * CellWidth;
        return new Rect(left, top, right, bottom);
    }
}

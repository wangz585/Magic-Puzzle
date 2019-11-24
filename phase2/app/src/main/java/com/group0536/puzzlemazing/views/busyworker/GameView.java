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
import com.group0536.puzzlemazing.models.BusyWorkerBitMap;
import com.group0536.puzzlemazing.models.BusyWorkerMap;
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
        BusyWorkerBitMap.initBitmaps(getResources());
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
    }

    private void drawBackground(Canvas canvas) {
        Paint background = new Paint();
        background.setColor(getResources().getColor(R.color.turquoise));
        canvas.drawRect(0, 0, getWidth(), getHeight(), background);
    }

    private void drawSurface(Canvas canvas) {
        Paint linePaint = new Paint();
        linePaint.setColor(Color.BLACK);
        for (int row = 0; row <= store.getMap().getWidth(); row++)
            canvas.drawLine(0, row * CellWidth,
                    getWidth(), row * CellWidth, linePaint);
        for (int column = 0; column <= store.getMap().getWidth(); column++)
            canvas.drawLine(column * CellWidth, 0,
                    column * CellWidth,
                    store.getMap().getWidth() * CellWidth, linePaint);
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
        BusyWorkerMap map = store.getMap();
        for (Point wallPosition : map.getWallPositions()) {
            destRect = getRect(wallPosition.x, wallPosition.y);
            srcRect = new Rect(0, 0,
                    BusyWorkerBitMap.getWallBitmap().getWidth(), BusyWorkerBitMap.getWallBitmap().getHeight());
            canvas.drawBitmap(BusyWorkerBitMap.getWallBitmap(),
                    srcRect, destRect, null);
        }
    }

    private void drawBox(Canvas canvas) {
        Point boxPosition = store.getCurrentBoxPosition();
        Rect destRect = getRect(boxPosition.x, boxPosition.y);
        Rect srcRect = new Rect(0, 0,
                BusyWorkerBitMap.getBoxBitmap().getWidth(), BusyWorkerBitMap.getBoxBitmap().getHeight());
        canvas.drawBitmap(BusyWorkerBitMap.getBoxBitmap(),
                srcRect, destRect, null);
    }

    private void drawWorker(Canvas canvas) {
        Point workerPosition = store.getCurrentWorkerPosition();
        Rect destRect = getRect(workerPosition.x, workerPosition.y);
        Rect srcRect = new Rect(0, 0,
                BusyWorkerBitMap.getWorkerBitmap().getWidth(), BusyWorkerBitMap.getWorkerBitmap().getHeight());
        canvas.drawBitmap(BusyWorkerBitMap.getWorkerBitmap(),
                srcRect, destRect, null);
    }

    private void drawFlag(Canvas canvas) {
        Point flagPosition = store.getMap().getFlagPosition();
        Rect destRect = getRect(flagPosition.x, flagPosition.y);
        Rect srcRect = new Rect(0, 0,
                BusyWorkerBitMap.getFlagBitmap().getWidth(), BusyWorkerBitMap.getFlagBitmap().getHeight());
        canvas.drawBitmap(BusyWorkerBitMap.getFlagBitmap(),
                srcRect, destRect, null);
    }

    private void drawWin(Canvas canvas) {
        Paint txtPaint = new Paint();
        txtPaint.setColor(Color.RED);
        txtPaint.setTextSize(100.0f);
        canvas.drawText(getContext().getString(R.string.busy_worker_win),
                4 * CellWidth, 6 * CellWidth, txtPaint);
    }

    private void drawLose(Canvas canvas) {
        Paint txtPaint = new Paint();
        txtPaint.setColor(Color.RED);
        txtPaint.setTextSize(100.0f);
        canvas.drawText(getContext().getString(R.string.busy_worker_lose),
                4 * CellWidth, 6 * CellWidth, txtPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN) return true;
        Point position = new Point((int) event.getX(), (int) event.getY());
        actionCreator.move(position);
        postInvalidate();  //Update UI
        return true;
    }

    @Override
    protected void onSizeChanged(int newWidth, int newHeighteight, int oldWidth, int oldHeight) {
        super.onSizeChanged(newWidth, newHeighteight, oldWidth, oldHeight);
        CellWidth = newWidth / store.getMap().getWidth();
    }

    @Subscribe
    public void x(BusyWorkerChangeEvent e) {
        updateUI();
    }

    private void updateUI() {

    }

    private Rect getRect(int x, int y) {
        int left = x * CellWidth;
        int top = y * CellWidth;
        int right = (x + 1) * CellWidth;
        int bottom = (y + 1) * CellWidth;
        return new Rect(left, top, right, bottom);
    }
}

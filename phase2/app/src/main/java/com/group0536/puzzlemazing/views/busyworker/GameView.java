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
import com.group0536.puzzlemazing.stores.busyworker.BusyWorkerChangeEvent;
import com.group0536.puzzlemazing.stores.busyworker.BusyWorkerStore;
import com.group0536.puzzlemazing.stores.crazymatch.CrazyMatchChangeEvent;
import com.group0536.puzzlemazing.views.FluxView;
import com.squareup.otto.Subscribe;


public class GameView extends FluxView {

    private BusyWorkerStore store;
    private BusyWorkerActionCreator actionCreator;
    private int CellWidth;

    public GameView(Context context) {
        super(context);
    }

    @Override
    protected void initFluxComponents() {
        store = BusyWorkerStore.getInstance(dispatcher);
        actionCreator = new BusyWorkerActionCreator(dispatcher);
    }

    @Override
    protected void onAttachedToWindow(){
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

    private void drawBackground(Canvas canvas){
        Paint background = new Paint();
        background.setColor(getResources().getColor(R.color.turquoise));
        canvas.drawRect(0, 0, getWidth(), getHeight(), background);
    }

    private void drawSurface(Canvas canvas){
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

    private void drawGameBoard(Canvas canvas){

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (event.getAction() != MotionEvent.ACTION_DOWN) return true;
        Point position = new Point((int) event.getX(),(int) event.getY());
        actionCreator.move(position);
        postInvalidate();
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

    private Rect getRect(int row, int column) {
        int left = column * CellWidth;
        int top =  row * CellWidth;
        int right = (column + 1) * CellWidth;
        int bottom = (row + 1) * CellWidth;
        return new Rect(left, top, right, bottom);
    }


}

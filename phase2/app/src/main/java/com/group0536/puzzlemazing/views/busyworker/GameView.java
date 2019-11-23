package com.group0536.puzzlemazing.views.busyworker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;

import com.group0536.puzzlemazing.actions.busyworker.BusyWorkerActionCreator;
import com.group0536.puzzlemazing.stores.busyworker.BusyWorkerStore;
import com.group0536.puzzlemazing.views.FluxView;


public class GameView extends FluxView {

    private BusyWorkerStore store;
    private BusyWorkerActionCreator actionCreator;

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
    }

}

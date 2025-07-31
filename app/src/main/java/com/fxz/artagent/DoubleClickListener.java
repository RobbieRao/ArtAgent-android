package com.fxz.artagent;

import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;

public class DoubleClickListener implements View.OnTouchListener {
    private static final long DOUBLE_CLICK_TIME_DELTA = 300; // 设置双击的时间间隔，单位毫秒
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable singleClickRunnable = () -> {
        if (waitingForSecondClick && currentView != null) {
            waitingForSecondClick = false;
            onSingleClick(currentView);
            currentView = null;
        }
    };
    private long lastClickTime = 0;
    private boolean waitingForSecondClick = false;
    private View currentView;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            long clickTime = System.currentTimeMillis();
            if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA) {
                handler.removeCallbacks(singleClickRunnable);
                waitingForSecondClick = false;
                currentView = null;
                onDoubleClick(v);
            } else {
                waitingForSecondClick = true;
                currentView = v;
                handler.postDelayed(singleClickRunnable, DOUBLE_CLICK_TIME_DELTA);
            }
            lastClickTime = clickTime;
        }
        return true;
    }

    protected void onSingleClick(View v) {
        // 处理单击事件
    }

    protected void onDoubleClick(View v) {
        // 处理双击事件
    }
}

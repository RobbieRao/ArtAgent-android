package com.fxz.artagent;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class DrawingView extends View {

    private Path drawPath; //绘图的路径
    private Paint drawPaint, canvasPaint; //画笔和画布的颜色
    private Canvas drawCanvas; //画布
    private Bitmap canvasBitmap; //保存最后绘图的位图

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDrawing();
    }

    public void clearCanvas() {  // 清除画板
        if (drawCanvas != null) {
            drawCanvas.drawColor(Color.WHITE);
            drawPath.reset();
            invalidate();
        }
    }

    public void setBrushSize(float newSize) {  // 不同画笔大小
        drawPaint.setStrokeWidth(newSize);
    }

    public float getBrushSize() {
        return drawPaint.getStrokeWidth();
    }

    public void setEraser() {
        drawPaint.setColor(Color.WHITE); //或者其他背景颜色
        drawPaint.setStrokeWidth(40);    //可以设置为较大的值
    }

    public Bitmap getCanvasBitmap() {
        return canvasBitmap;
    }

    private void setupDrawing() {
        drawPath = new Path();
        drawPaint = new Paint();

        drawPaint.setColor(Color.BLACK); //初始颜色
        drawPaint.setAntiAlias(true); //防止边缘锯齿
        drawPaint.setStrokeWidth(20); //画笔宽度
        drawPaint.setStyle(Paint.Style.STROKE); //空心
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);

        canvasPaint = new Paint(Paint.DITHER_FLAG);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                drawCanvas.drawPath(drawPath, drawPaint);
                drawPath.reset();
                break;
            default:
                return false;
        }

        invalidate(); //重绘
        return true;
    }
}

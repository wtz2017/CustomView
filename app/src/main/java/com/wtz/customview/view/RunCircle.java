package com.wtz.customview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wtz.customview.R;

/**
 * https://github.com/GcsSloop
 */
public class RunCircle extends View {

    // 画笔
    private Paint mDeafultPaint = new Paint();
    // 宽高
    private int mViewWidth, mViewHeight;

    private float currentValue = 0;     // 用于纪录当前的位置,取值范围[0,1]映射Path的整个长度

    private float[] pos;                // 当前点的实际位置
    private float[] tan;                // 当前点的tangent值,用于计算图片所需旋转的角度
    private Bitmap mBitmap;             // 箭头图片
    private Matrix mMatrix;             // 矩阵,用于对图片进行一些操作

    public RunCircle(Context context) {
        this(context, null);
    }

    public RunCircle(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RunCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mDeafultPaint.setColor(Color.BLACK);
        mDeafultPaint.setStyle(Paint.Style.STROKE);
        mDeafultPaint.setStrokeWidth(10f);

        pos = new float[2];
        tan = new float[2];
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;       // 缩放图片
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.arrow, options);
        mMatrix = new Matrix();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(mViewWidth / 2, mViewHeight / 2);      // 平移坐标系

        Path path = new Path();                                 // 创建 Path

        path.addCircle(0, 0, 200, Path.Direction.CW);           // 添加一个圆形

        PathMeasure measure = new PathMeasure(path, false);     // 创建 PathMeasure

        currentValue += 0.005;// 计算当前的位置在总长度上的比例[0,1]
        if (currentValue >= 1) {
            currentValue = 0;
        }

        // 获取当前位置的坐标以及趋势
        measure.getPosTan(measure.getLength() * currentValue, pos, tan);

        mMatrix.reset();// 重置Matrix
        float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI);// 计算图片旋转角度

        mMatrix.postRotate(degrees, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);// 旋转图片
        mMatrix.postTranslate(pos[0] - mBitmap.getWidth() / 2, pos[1] - mBitmap.getHeight() / 2);   // 将图片绘制中心调整到与当前点重合

        canvas.drawPath(path, mDeafultPaint);// 绘制 Path
        canvas.drawBitmap(mBitmap, mMatrix, mDeafultPaint);// 绘制箭头

        invalidate();
    }
}

package com.lqh.lichao.paint_gradient;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017-10-09.
 */

public class MyGradientView extends View {
    private Paint mPaint;
    private Bitmap mBitmap = null;

    private int mWidth;
    private int mHeight;
    private int[] mColors = {Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW};

    public MyGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mBitmap = ((BitmapDrawable)getResources().getDrawable(R.drawable.xyjy2)).getBitmap();
        mPaint = new Paint();
        mWidth = mBitmap.getWidth();
        mHeight = mBitmap.getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        /**
         * TileMode.CLAMP 拉伸最后一个像素去铺满剩下的地方
         * TileMode.MIRROR 通过镜像翻转铺满剩下的地方。
         * TileMode.REPEAT 重复图片平铺整个画面（电脑设置壁纸）
         */
        /*BitmapShader bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.MIRROR);
        mPaint.setShader(bitmapShader);
        mPaint.setAntiAlias(true);
        //设置像素矩阵，来调整大小，为了解决宽高不一致的问题。
        float scale = Math.max(mWidth, mHeight) / Math.min(mWidth, mHeight);

        Matrix matrix = new Matrix();
        matrix.setScale(scale, scale);
        bitmapShader.setLocalMatrix(matrix);
        //圆形头像
        //canvas.drawCircle(mHeight / 2,mHeight / 2, mHeight / 2 ,mPaint);
        //椭圆头像
        //canvas.drawOval(new RectF(0 , 0, mWidth, mHeight),mPaint);
        //矩形头像
        //canvas.drawRect(new Rect(0,0 , 1000, 1600),mPaint);

        //通过shapeDrawable实现圆形头像
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.getPaint().setShader(bitmapShader);
        shapeDrawable.setBounds(0, 0, mWidth, mWidth);
        shapeDrawable.draw(canvas);*/

        /**线性渐变
         * x0, y0, 起始点
         *  x1, y1, 结束点
         * int[]  mColors, 中间依次要出现的几个颜色
         * float[] positions,数组大小跟colors数组一样大，中间依次摆放的几个颜色分别放置在那个位置上(参考比例从左往右)
         *    tile
         */
        LinearGradient linearGradient = new LinearGradient(0, 0, 800, 800, mColors, null, Shader.TileMode.CLAMP);
        mPaint.setShader(linearGradient);
        canvas.drawRect(0, 0, 800, 800, mPaint);
    }
}

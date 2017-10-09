package com.lqh.lichao.paint_gradient;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
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
        //LinearGradient--线性渲染  霓虹灯文字，倒影图片
        /*LinearGradient linearGradient = new LinearGradient(0, 0, 800, 800, mColors, null, Shader.TileMode.CLAMP);
        mPaint.setShader(linearGradient);
        canvas.drawRect(0, 0, 800, 800, mPaint);*/

        //RadialGradient----环形渲染 水波纹效果
        /*RadialGradient radialGradient = new RadialGradient(300, 300, 100, mColors, null, Shader.TileMode.REPEAT);
        mPaint.setShader(radialGradient);
        canvas.drawCircle(300, 300, 300, mPaint);*/

        //SweepGradient----渐变渲染/梯度渲染 雷达扫描效果
        /*SweepGradient sweepGradient = new SweepGradient(300, 300, mColors, null);
        mPaint.setShader(sweepGradient);
        canvas.drawCircle(300, 300, 300, mPaint);*/

        //ComposeShader----组合渲染
        /*LinearGradient linearGradient = new LinearGradient(0, 0, 400, 400, mColors, null, Shader.TileMode.REPEAT);
        BitmapShader bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.MIRROR);
        ComposeShader mComposeShader = new ComposeShader(linearGradient, bitmapShader, PorterDuff.Mode.SRC_OVER);
        mPaint.setShader(mComposeShader);
        canvas.drawRect(0, 0, 800, 1000, mPaint);*/

        /***************用ComposeShader即可实现心形图渐变效果*********************************/
        //创建BitmapShader，用以绘制心
        BitmapShader bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        //创建LinearGradient，用以产生从左上角到右下角的颜色渐变效果
        LinearGradient linearGradient = new LinearGradient(0, 0, mWidth, mHeight, Color.GREEN, Color.BLUE, Shader.TileMode.CLAMP);
        //bitmapShader对应目标像素，linearGradient对应源像素，像素颜色混合采用MULTIPLY模式
        ComposeShader composeShader = new ComposeShader(bitmapShader, linearGradient, PorterDuff.Mode.MULTIPLY);
        //将组合的composeShader作为画笔paint绘图所使用的shader
        mPaint.setShader(composeShader);
        //用composeShader绘制矩形区域
        canvas.drawRect(0, 0, mWidth, mHeight, mPaint);
    }
}

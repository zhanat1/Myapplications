package com.androprogrammer.tutorials.customviews;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.androprogrammer.tutorials.R;

/**
 * Created by Wasim on 25-01-2015.
 */
public class CircularImageView extends ImageView {
    public CircularImageView(Context context) {
        super(context);
        //setup();
    }

    public CircularImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //setup();
    }

    public CircularImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //setup();
    }

    @TargetApi(21)
    public CircularImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    /*private Bitmap getCircledBitmap(Bitmap bitmap, int radius) {
        int targetWidth = 50;
        int targetHeight = 50;
        Bitmap result = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(result);

        //final int color = Color.parseColor("#ceceff");
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, result.getWidth(), result.getHeight());

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        //canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(getResources().getColor(R.color.primary_green));
        canvas.drawCircle(result.getWidth() / 2 + 0.5f, result.getHeight() / 2 + 0.1f,
                result.getWidth() / 2 + 0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(result, rect, rect, paint);
       // canvas.drawBitmap(bitmap, new Rect(0, 0, result.getWidth(), result.getHeight()), new Rect(0, 0, targetWidth, targetHeight), null);

        return result;*//*

        Bitmap sbmp;

        if (bitmap.getWidth() != radius || bitmap.getHeight() != radius)
            sbmp = Bitmap.createScaledBitmap(bitmap, radius, radius, true);
        else
            sbmp = bitmap;

        Bitmap output = Bitmap.createBitmap(sbmp.getWidth(),
                sbmp.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());


        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(getResources().getColor(R.color.primary_green));
        //paint.setStrokeWidth(5);

        canvas.drawCircle(sbmp.getWidth() / 2 + 0.1f, sbmp.getHeight() / 2 + 0.1f,
                sbmp.getWidth() / 2 + 0.1f, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(sbmp, rect, rect, paint);

        return output;
    }

    public Bitmap getRoundedCornerBitmap(Context context, Bitmap input,
                                         int pixels, int w, int h, boolean squareTL, boolean squareTR,
                                         boolean squareBL, boolean squareBR) {

        Bitmap output = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final float densityMultiplier = context.getResources()
                .getDisplayMetrics().density;

        final int color = 0xff424242;

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, w, h);
        final RectF rectF = new RectF(rect);

        // make sure that our rounded corner is scaled appropriately
        final float roundPx = pixels * densityMultiplier;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        // draw rectangles over the corners we want to be square
        if (squareTL) {
            canvas.drawRect(0, 0, w / 2, h / 2, paint);
        }
        if (squareTR) {
            canvas.drawRect(w / 2, 0, w, h / 2, paint);
        }
        if (squareBL) {
            canvas.drawRect(0, h / 2, w / 2, h, paint);
        }
        if (squareBR) {
            canvas.drawRect(w / 2, h / 2, w, h, paint);
        }

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(input, 0, 0, paint);

        return output;
    }*/

    private int borderWidth = 5;
    private int viewWidth;
    private int viewHeight;
    private Bitmap image;
    private Paint paint;
    private Paint paintBorder;
    private BitmapShader shader;

    private void setup()
    {
        // init paint
        paint = new Paint();
        paint.setAntiAlias(true);

        paintBorder = new Paint();
        setBorderColor(Color.WHITE);
        paintBorder.setAntiAlias(true);
        this.setLayerType(LAYER_TYPE_SOFTWARE, paintBorder);
        paintBorder.setShadowLayer(4.0f, 0.0f, 2.0f, Color.BLACK);
        //paintBorder.setStrokeWidth(1);
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        this.invalidate();
    }

    public void setBorderColor(int borderColor) {
        if (paintBorder != null)
            paintBorder.setColor(borderColor);

        this.invalidate();
    }

    private void loadBitmap() {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) this.getDrawable();
        if (bitmapDrawable != null)
            image = bitmapDrawable.getBitmap();
    }

    @SuppressLint("DrawAllocation")
    @Override
    public void onDraw(Canvas canvas) {
        //load the bitmap
        /*loadBitmap();

        // init shader
        if (image != null)
        {
            shader = new BitmapShader(Bitmap.createScaledBitmap(image, canvas.getWidth(),
                    canvas.getHeight(), false),
                    Shader.TileMode.CLAMP,
                    Shader.TileMode.CLAMP);

            paint.setShader(shader);
            int circleCenter = viewWidth / 2;

            canvas.drawCircle(circleCenter + borderWidth,
                    circleCenter + borderWidth,
                    circleCenter + borderWidth - 4.0f,
                    paintBorder);

            canvas.drawCircle(circleCenter + borderWidth,
                    circleCenter + borderWidth,
                    circleCenter - 4.0f, paint);
        }*/

        Drawable drawable = getDrawable();

        if (drawable == null) {
            return;
        }

        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }
        Bitmap b = ((BitmapDrawable) drawable).getBitmap();
        Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);

        int w = getWidth(), h = getHeight();
        int radius = w < h ? w : h;

        Bitmap roundBitmap = getCroppedBitmap(bitmap, radius, w, h);
        // roundBitmap= ImageUtils.setCircularInnerGlow(roundBitmap, 0xFFBAB399,
        // 4, 1);
        canvas.drawBitmap(roundBitmap, 0, 0, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec, widthMeasureSpec);
        viewWidth = width - (borderWidth * 2);
        viewHeight = height - (borderWidth * 2);

        setMeasuredDimension(width, height);
    }

    public static Bitmap getCroppedBitmap(Bitmap bmp, int radius, int w, int h) {
        Bitmap sbmp;
        if (bmp.getWidth() != radius || bmp.getHeight() != radius)
        {
            float _w_rate = 1.0f * radius / bmp.getWidth();
            float _h_rate = 1.0f * radius / bmp.getHeight();
            float _rate = _w_rate < _h_rate ? _h_rate : _w_rate;
            sbmp = Bitmap.createScaledBitmap(bmp, (int)(bmp.getWidth() * _rate),
                    (int)(bmp.getHeight() * _rate), false);
        }
        else
            sbmp = bmp;

        Bitmap output = Bitmap.createBitmap(sbmp.getWidth(), sbmp.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xffa19774;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(w / 2, h / 2, (w < h ? w : h) / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(sbmp, rect, rect, paint);

        return output;
    }

    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY)
        {
            // We were told how big to be
            result = specSize;
        }
        else
        {
            // Measure the text
            result = viewWidth;
        }
        return result;
    }

    private int measureHeight(int measureSpecHeight, int measureSpecWidth) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpecHeight);
        int specSize = MeasureSpec.getSize(measureSpecHeight);

        if (specMode == MeasureSpec.EXACTLY)
        {
            // We were told how big to be
            result = specSize;
        }
        else
        {
            // Measure the text (beware: ascent is a negative number)
            result = viewHeight;
        }

        return result;
    }

    public static Bitmap getScaledBitmap(Bitmap originalImage)
    {
        int width = 250, height = 250;
        Bitmap background = Bitmap.createBitmap((int) width, (int) height, Bitmap.Config.ARGB_8888);

        float originalWidth = originalImage.getWidth(), originalHeight = originalImage.getHeight();

        Canvas canvas = new Canvas(background);
        float scale = width/originalWidth;
        float xTranslation = 0.0f, yTranslation = (height - originalHeight * scale)/2.0f;

        Matrix transformation = new Matrix();
        transformation.postTranslate(xTranslation, yTranslation);
        transformation.preScale(scale, scale);

        Paint paint = new Paint();
        paint.setFilterBitmap(true);
        canvas.drawBitmap(originalImage, transformation, paint);

        return background;
    }

}
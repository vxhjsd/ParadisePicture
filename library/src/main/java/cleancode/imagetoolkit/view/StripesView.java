package cleancode.imagetoolkit.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import java.io.IOException;
import java.io.InputStream;

@SuppressWarnings("ALL")

public class StripesView extends View {
    private int effectAlpha;
    private int numberToRepeat;
    private Bitmap originalRes;

    public StripesView(Context context) {
        super(context);
        this.numberToRepeat = 10;
        this.effectAlpha = 90;
        init();
    }

    public StripesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.numberToRepeat = 10;
        this.effectAlpha = 90;
        init();
    }

    void init() {
        invalidate();
    }

    public int getEffectAlpha() {
        return this.effectAlpha;
    }

    public void setEffectAlpha(int effectAlpha) {
        this.effectAlpha = effectAlpha;
        invalidate();
    }

    public int getNumberToRepeat() {
        return this.numberToRepeat;
    }

    public void setNumberToRepeat(int numberToRepeat) {
        this.numberToRepeat = numberToRepeat;
        invalidate();
    }

    public void setOriginalResFromAsset(Context context, String fileName) {
        originalRes = getBitmapFromAsset(context, fileName);
        invalidate();
    }

    public void setOriginalResFromResource(Context context, int resId) {
        originalRes = BitmapFactory.decodeResource(context.getResources(), resId);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (originalRes != null) {
            float[] fArr = new float[9];
            canvas.getMatrix().getValues(fArr);
            Math.max(1.0f, fArr[0]);
            Math.max(1.0f, fArr[4]);
            float width = (getWidth() / this.numberToRepeat) / this.originalRes.getWidth();
            Paint paint = new Paint(2);
            paint.setAlpha(this.effectAlpha);
            BitmapShader bitmapShader = new BitmapShader(this.originalRes, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
            Matrix matrix = new Matrix();
            matrix.setScale(width, width);
            bitmapShader.setLocalMatrix(matrix);
            paint.setShader(bitmapShader);
            canvas.drawRect(0.0f, 0.0f, getWidth(), getHeight(), paint);
        }
    }

    private static Bitmap getBitmapFromAsset(Context context, String strName) {
        try {
            InputStream open = context.getAssets().open(strName);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inDither = true;
            return BitmapFactory.decodeStream(open, null, options);
        }   catch (IOException unused) {
            return null;
        }
    }
}
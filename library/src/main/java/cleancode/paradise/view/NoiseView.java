package cleancode.paradise.view;

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

public class NoiseView extends View {
    private int effectAlpha;
    private int numberToRepeat;
    private Bitmap originalRes;

    public NoiseView(Context context) {
        super(context);
        this.numberToRepeat = 6;
        this.effectAlpha = 80;
        init();
    }

    public NoiseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.numberToRepeat = 6;
        this.effectAlpha = 80;
        init();
    }

    private void init() {
        invalidate();
    }

    public int getEffectAlpha() {
        return this.effectAlpha;
    }

    public void setEffectAlpha(int effectAlpha) {
        this.effectAlpha = effectAlpha;
        init();
    }

    public int getNumberToRepeat() {
        return this.numberToRepeat;
    }

    public void setNumberToRepeat(int numberToRepeat) {
        this.numberToRepeat = numberToRepeat;
        init();
    }

    public void setOriginalResFromAsset(Context context, String fileName) {
        originalRes = getBitmapFromAsset(context, fileName);
        init();
    }

    public void setOriginalResFromResource(Context context, int resId) {
        originalRes = BitmapFactory.decodeResource(context.getResources(), resId);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (originalRes != null) {
            float[] fArr = new float[9];
            canvas.getMatrix().getValues(fArr);
            Math.max(1.0f, fArr[0]);
            Math.max(1.0f, fArr[4]);
            float max = (Math.max(getWidth(), getHeight()) / this.numberToRepeat) / this.originalRes.getWidth();
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setAlpha((int) (this.effectAlpha * 1.5f));
            canvas.drawRect(0.0f, 0.0f, getWidth(), getHeight(), paint);
            BitmapShader bitmapShader = new BitmapShader(this.originalRes, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
            Matrix matrix = new Matrix();
            matrix.setScale(max, max);
            bitmapShader.setLocalMatrix(matrix);
            paint.setShader(bitmapShader);
            paint.setAlpha(this.effectAlpha);
            canvas.drawRect(0.0f, 0.0f, getWidth(), getHeight(), paint);
        }
    }

    private static Bitmap getBitmapFromAsset(Context context, String strName) {
        try {
            InputStream open = context.getAssets().open(strName);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inDither = true;
            return BitmapFactory.decodeStream(open, null, options);
        } catch (IOException unused) {
            return null;
        }
    }
}

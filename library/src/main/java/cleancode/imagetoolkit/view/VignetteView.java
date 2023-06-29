package cleancode.imagetoolkit.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

@SuppressWarnings("ALL")

public class VignetteView extends View {
    private int vignetteColorA = 170;
    private int vignetteColorB = 0;
    private int vignetteColorG = 0;
    private int vignetteColorR = 0;

    public VignetteView(Context context) {
        super(context);
    }

    public VignetteView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        int argb = Color.argb(vignetteColorA, vignetteColorR, vignetteColorG, vignetteColorB);
        int argb2 = Color.argb(0, vignetteColorR, vignetteColorG, vignetteColorB);
        Rect rect = new Rect(0, 0, getWidth(), getHeight());
        if (rect.width() < 1 || rect.height() < 1) {
            return;
        }
        RadialGradient radialGradient = new RadialGradient(rect.centerX(), rect.centerY(), (float) (Math.sqrt(Math.pow(rect.width(), 2.0d) + Math.pow(rect.height(), 2.0d)) / 2.0d * 0.8d), new int[]{argb2, argb2, argb}, new float[]{0.0f, 0.7f, 1.0f}, Shader.TileMode.CLAMP);
        float max = Math.max(getWidth(), getHeight());
        Matrix matrix = new Matrix();
        matrix.setScale(getWidth() / max, getHeight() / max, rect.centerX(), rect.centerY());
        radialGradient.setLocalMatrix(matrix);
        paint.setShader(radialGradient);
        canvas.drawRect(0.0f, 0.0f, getWidth(), getHeight(), paint);
    }

    public void setVignetteColor(int color) {
        this.vignetteColorB = Color.blue(color);
        this.vignetteColorR = Color.red(color);
        this.vignetteColorG = Color.green(color);
        invalidate();
    }

    public void setVignetteAlpha(int alpha) {
        this.vignetteColorA = alpha;
        invalidate();
    }

    public int getVignetteColor() {
        return Color.rgb(vignetteColorR, vignetteColorG, vignetteColorB);
    }

    public int getVignetteAlpha() {
        return vignetteColorA;
    }
}

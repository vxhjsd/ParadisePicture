package cleancode.imagetoolkit.tool;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

@SuppressWarnings("ALL")
public class ImageAdjuster {
    public static Bitmap adjustImage(Bitmap bitmap, float brightness, float contrast, float hue, float saturation) {
        Bitmap adjustedBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Canvas canvas = new Canvas(adjustedBitmap);
        Paint paint = new Paint();

        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(new float[] {
                contrast, 0, 0, 0, brightness,
                0, contrast, 0, 0, brightness,
                0, 0, contrast, 0, brightness,
                0, 0, 0, 1, 0
        });

        ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.setRotate(0, hue);
        hueMatrix.setRotate(1, hue);
        hueMatrix.setRotate(2, hue);

        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);

        colorMatrix.postConcat(hueMatrix);
        colorMatrix.postConcat(saturationMatrix);

        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0, 0, paint);

        return adjustedBitmap;
    }
}

/* example

        ImageView imageView = findViewById(R.id.imageView);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image);

        float brightness = 50f;
        float contrast = 1.5f;
        float hue = 45f;
        float saturation = 1.2f;

        Bitmap adjustedBitmap = ImageAdjuster.adjustImage(bitmap, brightness, contrast, hue, saturation);
        imageView.setImageBitmap(adjustedBitmap);

*/
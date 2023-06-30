package sample.app;

import android.app.Activity;
import android.os.Bundle;
import com.sample.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.main);
        initialize();
    }

    private void initialize() {
        cleancode.imagetoolkit.view.NoiseView noisee = findViewById(R.id.noise);
        noisee.setOriginalResFromAsset(this,"noise.png");
        noisee.update();
    }

}

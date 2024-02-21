package g313.gusev.lab09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import math.Interp;

public class SurfaceActivity extends AppCompatActivity {

    MySurface ms = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface);

        Intent intent = getIntent();
        ms = findViewById(R.id.mySurface);

        float xMin = intent.getFloatExtra("xMin", 0.0f);
        float xMax = intent.getFloatExtra("xMax", 0.0f);
        int points = intent.getIntExtra("points", 0);

        if (points < 1) {
            Toast.makeText(this, "Невозможен ввод стольких точек, минимум 1", Toast.LENGTH_SHORT).show();
            return;
        }

        ms.n = points;

        ms.x = new float[ms.n];
        ms.y = new float[ms.n];

        for (int i = 0; i < ms.n; i++) {
            ms.x[i] = Interp.map(i, 0, ms.n - 1, xMin, xMax);
            ms.y[i] = (float) Math.cos(ms.x[i]);
        }

        ms.update();
        ms.invalidate();
    }

    public void btnBackClick(View v) {
        finish();
    }

    public void btnScalePlus(View v) {
        ms.scaleUp(2.f);
        ms.invalidate();
    }

    public void btnScaleMinus(View v) {
        ms.scaleDown(2.f);
        ms.invalidate();
    }

    public void btnTransLeft(View v) {
        ms.translateLeft(2.f);
        ms.invalidate();
    }

    public void btnTransRight(View v) {
        ms.translateRight(2.f);
        ms.invalidate();
    }
}
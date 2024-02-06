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

        String stringCountPoints, stringMaxX, stringMinX;
        int countPoints, maxX, minX;

        stringCountPoints = intent.getStringExtra("countPoints");
        stringMaxX = intent.getStringExtra("eMaxX");
        stringMinX = intent.getStringExtra("eMinX");

        try {
            countPoints = Integer.parseInt(stringCountPoints);
            maxX = Integer.parseInt(stringMaxX);
            minX = Integer.parseInt(stringMinX);
        } catch (Exception ex) {
            Toast.makeText(this, "Ввод данных был некорректен, график не вычислиться", Toast.LENGTH_SHORT).show();
            return;
        }

        if (countPoints < 1) {
            Toast.makeText(this, "Невозможен ввод стольких точек, минимум 1", Toast.LENGTH_SHORT).show();
            return;
        }

        ms.n = countPoints;

        ms.x = new float[ms.n];
        ms.y = new float[ms.n];

        for (int i = 0; i < ms.n; i++) {
            ms.x[i] = Interp.map(i, 0, ms.n - 1, minX, maxX);
            ms.y[i] = (float) Math.cos(ms.x[i]);
        }

        ms.update();
        ms.invalidate();
    }

    public void btnBackClick(View v) {
        finish();
    }

    public void btnScalePlus(View v) {
        ms.xmin += 2;
        ms.xmax -= 2;
        if (ms.xmin < ms.xmax) {
            ms.invalidate();
            return;
        }
        ms.xmin -= 2;
        ms.xmax += 2;
    }

    public void btnScaleMinus(View v) {
        ms.xmin -= 2;
        ms.xmax += 2;
        if (ms.xmin > ms.XMIN && ms.xmax < ms.XMAX) {
            ms.invalidate();
            return;
        }
        ms.xmin += 2;
        ms.xmax -= 2;
    }
}
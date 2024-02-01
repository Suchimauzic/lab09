package g313.gusev.lab09;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;
import math.arr;
import math.Interp;

public class MySurface extends SurfaceView {

    Paint p;
    float xmin, xmax;
    float ymin, ymax;
    float[] x;
    float[] y;
    int n;

    public void update() {
        xmin = arr.min(x, n);
        xmax = arr.max(x, n);
        ymin = arr.min(y, n);
        ymax = arr.max(y, n);
    }

    public MySurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        p = new Paint();
        p.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);

        int width = canvas.getWidth();
        int height = canvas.getHeight();
        float x0 = 0.0f, y0 = 0.0f;
        float x1, y1;

        for (int i = 0; i < n; i++) {
            x1 = Interp.map(x[i], xmin, xmax, 0, width - 1);
            y1 = Interp.map(y[i], ymin, ymax, height - 1, 0);

            if (i > 0) canvas.drawLine(x0, y0, x1, y1, p);

            x0 = x1;
            y0 = y1;
        }
    }
}

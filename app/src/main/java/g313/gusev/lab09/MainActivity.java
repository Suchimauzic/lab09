package g313.gusev.lab09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import math.Interp;

public class MainActivity extends AppCompatActivity {

    EditText countPoints;
    EditText eMaxX;
    EditText eMinX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countPoints = findViewById(R.id.editCountPoints);
        eMaxX = findViewById(R.id.editMaxX);
        eMinX = findViewById(R.id.editMinX);
    }

    public void onClickSurface(View v) {
        if (countPoints.getText().toString() == "" && eMaxX.getText().toString() == "" && eMinX.getText().toString() == "") {
            Toast.makeText(this, "Нельзя оставлять пустые строки", Toast.LENGTH_SHORT).show();
            return;
        }



        Intent intent = new Intent(this, SurfaceActivity.class);
        intent.putExtra("countPoints", countPoints.getText().toString());
        intent.putExtra("eMaxX", eMaxX.getText().toString());
        intent.putExtra("eMinX", eMinX.getText().toString());

        startActivityForResult(intent, 414);
    }
}
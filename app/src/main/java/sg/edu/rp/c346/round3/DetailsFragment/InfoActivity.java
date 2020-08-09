package sg.edu.rp.c346.round3.DetailsFragment;

import androidx.appcompat.app.AppCompatActivity;

import sg.edu.rp.c346.round3.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {

    WebView wb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        this.setTitle("Information");

        Intent getInfo = getIntent();
        int position = getInfo.getIntExtra("position", 0);

        wb = findViewById(R.id.webView);

        //Blood pressure
        if (position == 0) {
            wb.loadUrl("file:///android_asset/BloodPressure.html");
        }
        //BMI
        else if (position == 1) {
            wb.loadUrl("file:///android_asset/BMI.html");
        }
        //Body Fat
        else if (position == 2) {
            wb.loadUrl("file:///android_asset/BodyFat.html");
        }
        //Quad Power
        else if (position == 3) {
            wb.loadUrl("file:///android_asset/QuadPower.html");
        }
        //Rack Pull
        else if (position == 4) {
            wb.loadUrl("file:///android_asset/RackPull.html");
        }
        //Agility
        else if (position == 5) {
            wb.loadUrl("file:///android_asset/Agility.html");
        }
    }
}

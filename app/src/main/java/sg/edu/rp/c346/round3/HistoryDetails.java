package sg.edu.rp.c346.round3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import sg.edu.rp.c346.round3.DataClasses.DataEntry;

public class HistoryDetails extends AppCompatActivity {
    TextView agilityHistory, bodyFatHistory, quadPowerHistory, rackPullHistory, heightHistory, weightHistory, sbloodPressureHistory,dbloodPressureHistory, dateHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_details);
        agilityHistory = findViewById(R.id.textViewHistoryA);
        bodyFatHistory = findViewById(R.id.textViewHistoryB);
        quadPowerHistory = findViewById(R.id.textViewHistoryQP);
        rackPullHistory = findViewById(R.id.textViewHistoryRP);
        heightHistory = findViewById(R.id.textViewHistoryH);
        weightHistory = findViewById(R.id.textViewHistoryW);
        dateHistory = findViewById(R.id.textViewHistoryD);
        sbloodPressureHistory = findViewById(R.id.textViewBloodPressure);
        dbloodPressureHistory = findViewById(R.id.textViewBloodPressure2);


        Intent i = getIntent();
        DataEntry a = (DataEntry) i.getSerializableExtra("data");

        agilityHistory.setText(a.getAgility() + "");
        bodyFatHistory.setText(a.getBodyFat() + "%");
        quadPowerHistory.setText(a.getQuadPower() + "kg");
        rackPullHistory.setText(a.getRackPull() + "kg");
        heightHistory.setText(a.getHeight() + "cm");
        weightHistory.setText(a.getWeight() + "kg");
        dateHistory.setText(a.getDate() + "");
        sbloodPressureHistory.setText(a.getSystolicPressure() + "mmHg");
        dbloodPressureHistory.setText(a.getDiastolicPressure() + "mmHg");
    }
}

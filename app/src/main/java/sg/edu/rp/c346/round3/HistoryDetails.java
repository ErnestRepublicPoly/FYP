package sg.edu.rp.c346.round3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import sg.edu.rp.c346.round3.DataClasses.DataEntry;

public class HistoryDetails extends AppCompatActivity {
    TextView agilityHistory, bodyFatHistory, quadPowerHistory, rackPullHistory, heightHistory, weightHistory, bloodPressureHistory, dateHistory;

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

        Intent i = getIntent();
        DataEntry a = (DataEntry) i.getSerializableExtra("data");

        agilityHistory.setText(a.getAgility() + "");
        bodyFatHistory.setText(a.getBodyFat() + "");
        quadPowerHistory.setText(a.getQuadPower() + "");
        rackPullHistory.setText(a.getRackPull() + "");
        heightHistory.setText(a.getHeight() + "");
        weightHistory.setText(a.getWeight() + "");
        dateHistory.setText(a.getDate() + "");
    }
}

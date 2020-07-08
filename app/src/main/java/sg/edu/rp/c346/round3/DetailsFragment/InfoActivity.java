package sg.edu.rp.c346.round3.DetailsFragment;

import androidx.appcompat.app.AppCompatActivity;
import sg.edu.rp.c346.round3.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {

    Spinner spnOption;
    TextView tvInfo;
    ImageView ivInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent getInfo = getIntent();
        int position = getInfo.getIntExtra("position",0);

        ArrayList<String> spinnerChoices = new ArrayList<String>();
        spinnerChoices.add("Male");
        spinnerChoices.add("Female");

        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, spinnerChoices);

        spnOption = findViewById(R.id.spnOption);
        tvInfo = findViewById(R.id.tvInfo);
        ivInfo = findViewById(R.id.ivInfo);

        spnOption.setAdapter(aa);

        //Blood pressure
        if (position == 0){
            spnOption.setEnabled(false);
            //Text Information on Systolic and Diastolic
            tvInfo.setText("Systolic \nDiastolic");
            //Picture update
            ivInfo.setImageResource(R.drawable.bpinfo);
        }
        //BMI
        else if (position == 1){
            spnOption.setEnabled(false);
        }
        //Body Fat
        else if (position == 2){
            spnOption.setEnabled(false);
        }
        //Quad Power
        else if (position == 3){
            if (spnOption.getSelectedItem().toString().equalsIgnoreCase("male")){

            }else{

            }
        }
        //Rack Pull
        else if (position == 4){
            spnOption.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (spnOption.getSelectedItem().toString().equalsIgnoreCase("male")){
                        ivInfo.setImageResource(R.drawable.malerackpull);
                    }else{
                        ivInfo.setImageResource(R.drawable.femalerackpull);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        //Agility
        else if (position == 5){

        }
    }
}

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
            tvInfo.setText("BMI weight ranges\n" +
                    "Less than 18.5 = Underweight\n" +
                    "Between 18.5 - 24.9 = Healthy Weight\n" +
                    "Between 25 - 29.9 = Overweight\n" +
                    "Over 30 = Obese");
            ivInfo.setImageResource(R.drawable.bmiinfo);
        }
        //Body Fat
        else if (position == 2){
            spnOption.setEnabled(false);
            tvInfo.setText("Body fat often gets a bad rap, but it serves an important purpose. Your body stores the fat from the foods you eat in deposits that can be used for energy, insulation, and protection. Everyone needs some fat to live and function. When too much body fat accumulates, however, it can lead to obesity and obesity-related diseases, like type 2 diabetes and heart disease.\n" +
                    "\n" +
                    "Figuring out how much body fat you have isn’t necessarily as easy as looking in a mirror or stepping on a scale. A bodybuilder and an obese person may be the same weight, but they have very different body fat percentages. Your weight alone cannot tell you much muscle or fat you have. Instead, you’ll need to determine your body fat percentage.");

            spnOption.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (spnOption.getSelectedItem().toString().equalsIgnoreCase("male")){
                        ivInfo.setImageResource(R.drawable.malebodyfat);
                    }else{
                        ivInfo.setImageResource(R.drawable.femalebodyfat);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        //Quad Power
        else if (position == 3){
            tvInfo.setText("");
            spnOption.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (spnOption.getSelectedItem().toString().equalsIgnoreCase("male")){
                        ivInfo.setImageResource(R.drawable.malequadpower);
                    }else{
                        ivInfo.setImageResource(R.drawable.femalerackpull);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
        //Rack Pull
        else if (position == 4){
            tvInfo.setText("");
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

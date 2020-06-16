package sg.edu.rp.c346.round3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeFragment extends Fragment {


    private HomeViewModel homeViewModel;
    EditText weight, height, sp, dp, bodyFat, quadPower, rackPull, agility;
    Button submit;
    String a = "GtoqSX78uQUAwarX3wpR";

    FirebaseFirestore db;
    CollectionReference dataRef;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, final Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        db = FirebaseFirestore.getInstance();
        dataRef = db.collection("/User/" + a + "/Data");

        weight = root.findViewById(R.id.editTextWeight);
        height = root.findViewById(R.id.editTextHeight);
        sp = root.findViewById(R.id.editTextSP);
        dp = root.findViewById(R.id.editTextDP);
        bodyFat = root.findViewById(R.id.editTextBodyFat);
        quadPower = root.findViewById(R.id.editTextQuadPower);
        rackPull = root.findViewById(R.id.editTextRackPull);
        agility = root.findViewById(R.id.editTextAgility);
        submit = root.findViewById(R.id.buttonSubmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double spDouble = Double.parseDouble(sp.getText().toString());
                    double dpDouble = Double.parseDouble(dp.getText().toString());
                    double bodyFatDouble = Double.parseDouble(bodyFat.getText().toString());
                    double quadPowerDouble = Double.parseDouble(quadPower.getText().toString());
                    double rackPullDouble = Double.parseDouble(rackPull.getText().toString());
                    double agilityDouble = Double.parseDouble(agility.getText().toString());
                    double weightDouble = Double.parseDouble(weight.getText().toString());
                    double heightDouble = Double.parseDouble(height.getText().toString());

                    dataEntry de = new dataEntry(spDouble, dpDouble, bodyFatDouble, quadPowerDouble, rackPullDouble, agilityDouble, weightDouble, heightDouble);
                    dataRef.add(de);
                    
                    Toast.makeText(getContext(), "Entry Added", Toast.LENGTH_LONG).show();
                } catch (NumberFormatException e) {
                    Toast.makeText(getContext(), "All Fields Have To Be Numbers", Toast.LENGTH_LONG).show();
                }
            }
        });
        return root;
    }
}

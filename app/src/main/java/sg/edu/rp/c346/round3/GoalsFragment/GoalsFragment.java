package sg.edu.rp.c346.round3.GoalsFragment;

import androidx.lifecycle.ViewModelProviders;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import sg.edu.rp.c346.round3.DataClasses.GoalEntry;
import sg.edu.rp.c346.round3.R;

public class GoalsFragment extends Fragment {

    private GoalsViewModel goalsViewModel;
    EditText weight, quadPower, rackPull, agility;
    Button submit;
    String a = "GtoqSX78uQUAwarX3wpR";

    FirebaseFirestore db;
    CollectionReference dataRef;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, final Bundle savedInstanceState) {
        goalsViewModel = ViewModelProviders.of(this).get(GoalsViewModel.class);
        View root = inflater.inflate(R.layout.goals_fragment, container, false);
        /*db = FirebaseFirestore.getInstance();

        weight = root.findViewById(R.id.editTextWG);
        quadPower = root.findViewById(R.id.editTextQPG);
        rackPull = root.findViewById(R.id.editTextRPG);
        agility = root.findViewById(R.id.editTextAG);
        submit = root.findViewById(R.id.buttonGoalSubmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Date c = Calendar.getInstance().getTime();

                    double quadPowerDouble = Double.parseDouble(quadPower.getText().toString());
                    double rackPullDouble = Double.parseDouble(rackPull.getText().toString());
                    double agilityDouble = Double.parseDouble(agility.getText().toString());
                    double weightDouble = Double.parseDouble(weight.getText().toString());
                    GoalEntry ge = new GoalEntry(quadPowerDouble, rackPullDouble, agilityDouble, weightDouble, c);


                    Map<String, Object> saveData = new HashMap<String, Object>();
                    saveData.put("", quadPowerDouble);
                    db.collection("Goals").document("Goals").set(saveData, SetOptions.merge());

                    db.collection("/User/" + a + "/Data").document("Entry" + i).set(de).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getContext(), "Goals Set Successfully", Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "Failed to set Goals ", Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (NumberFormatException e) {
                    Toast.makeText(getContext(), "All Fields Have To Be Numbers", Toast.LENGTH_LONG).show();
                }
            }
        });*/
        return root;
    }
}

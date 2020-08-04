package sg.edu.rp.c346.round3.EntryFragment;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;
import java.util.Date;

import sg.edu.rp.c346.round3.R;
import sg.edu.rp.c346.round3.DataClasses.DataEntry;

public class EntryFragment extends Fragment {


    private EntryViewModel entryViewModel;
    EditText weight, height, sp, dp, bodyFat, quadPower, rackPull, agility;
    Button submit;
    String a = "";
    int count = 0;

    FirebaseFirestore db;
    CollectionReference dataRef;
    FirebaseAuth fbAuth;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, final Bundle savedInstanceState) {
        entryViewModel = ViewModelProviders.of(this).get(EntryViewModel.class);
        View root = inflater.inflate(R.layout.entry_fragment, container, false);

        fbAuth = FirebaseAuth.getInstance();
        FirebaseUser user = fbAuth.getCurrentUser();
        a = user.getUid();

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

        db.collection("/User/" + a + "/Data")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot querySnapshot = task.getResult();
                            count = querySnapshot.size();
                            Log.d("count", count + "");
                        } else {
                            Log.d("FireStore Error", "Error getting documents: ", task.getException());
                        }
                    }
                });
        Log.d("count", count + "");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Date c = Calendar.getInstance().getTime();

                    double spDouble = Double.parseDouble(sp.getText().toString());
                    double dpDouble = Double.parseDouble(dp.getText().toString());
                    double bodyFatDouble = Double.parseDouble(bodyFat.getText().toString());
                    double quadPowerDouble = Double.parseDouble(quadPower.getText().toString());
                    double rackPullDouble = Double.parseDouble(rackPull.getText().toString());
                    double agilityDouble = Double.parseDouble(agility.getText().toString());
                    double weightDouble = Double.parseDouble(weight.getText().toString());
                    double heightDouble = Double.parseDouble(height.getText().toString());
                    final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
                    count++;

                    DataEntry de = new DataEntry(spDouble, dpDouble, bodyFatDouble, quadPowerDouble, rackPullDouble, agilityDouble, weightDouble, heightDouble, c);
                    Log.d("count", count + "");
                   db.collection("/User/" + a + "/Data").document("Entry"+count).set(de).addOnSuccessListener(new OnSuccessListener<Void>() {
                       @Override
                       public void onSuccess(Void aVoid) {
                           Toast.makeText(getContext(), "Entry Added Successfully", Toast.LENGTH_LONG).show();
                       }
                   }).addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception e) {
                           Toast.makeText(getContext(), "Failed to add entry ", Toast.LENGTH_LONG).show();
                       }
                   });

                } catch (NumberFormatException e) {
                    Toast.makeText(getContext(), "All Fields Have To Be Numbers", Toast.LENGTH_LONG).show();
                }
            }
        });
        return root;
    }
}

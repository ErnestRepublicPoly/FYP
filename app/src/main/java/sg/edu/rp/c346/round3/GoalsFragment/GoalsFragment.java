package sg.edu.rp.c346.round3.GoalsFragment;

import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.nfc.FormatException;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import sg.edu.rp.c346.round3.DataClasses.GoalEntry;
import sg.edu.rp.c346.round3.R;

public class GoalsFragment extends Fragment {

    private GoalsViewModel goalsViewModel;
    EditText weight, quadPower, rackPull, agility;
    TextView achieveDate;
    Button submit;
    String a = "GtoqSX78uQUAwarX3wpR";

    FirebaseFirestore db;

    Date d;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, final Bundle savedInstanceState) {
        goalsViewModel = ViewModelProviders.of(this).get(GoalsViewModel.class);
        View root = inflater.inflate(R.layout.goals_fragment, container, false);
        db = FirebaseFirestore.getInstance();

        achieveDate = root.findViewById(R.id.textViewDate);
        weight = root.findViewById(R.id.editTextWG);
        quadPower = root.findViewById(R.id.editTextQPG);
        rackPull = root.findViewById(R.id.editTextRPG);
        agility = root.findViewById(R.id.editTextAG);
        submit = root.findViewById(R.id.buttonGoalSubmit);

        achieveDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, monthOfYear, dayOfMonth);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        String formatedDate = sdf.format(calendar.getTime());
                        achieveDate.setText("" + formatedDate);
                        try {
                            d = sdf.parse(formatedDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                };
                Calendar Cal = Calendar.getInstance();
                DatePickerDialog myDateDialog = new DatePickerDialog(getContext(), myDateListener, Cal.get(Calendar.YEAR), Cal.get(Calendar.MONTH), Cal.get(Calendar.DATE));
                myDateDialog.show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    double quadPowerDouble = Double.parseDouble(quadPower.getText().toString());
                    double rackPullDouble = Double.parseDouble(rackPull.getText().toString());
                    double agilityDouble = Double.parseDouble(agility.getText().toString());
                    double weightDouble = Double.parseDouble(weight.getText().toString());
                    GoalEntry ge = new GoalEntry(quadPowerDouble, rackPullDouble, agilityDouble, weightDouble, d);

                    db.collection("/User/" + a + "/Goals").document("Goals").set(ge).addOnSuccessListener(new OnSuccessListener<Void>() {
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
        });
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        db.collection("/User/" + a + "/Goals").document("Goals").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String fillAgility = String.valueOf(documentSnapshot.getDouble("agility"));
                String fillQuadPower = String.valueOf(documentSnapshot.getDouble("quadPower"));
                String fillRackPull = String.valueOf(documentSnapshot.getDouble("rackPull"));
                String fillWeight = String.valueOf(documentSnapshot.getDouble("weight"));
                Date d = documentSnapshot.getDate("date");
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String formatedDate = sdf.format(d);
                achieveDate.setText("" + formatedDate);
                weight.setText("" + fillWeight);
                quadPower.setText("" + fillQuadPower);
                rackPull.setText("" + fillRackPull);
                agility.setText("" + fillAgility);
            }
        });
    }
}

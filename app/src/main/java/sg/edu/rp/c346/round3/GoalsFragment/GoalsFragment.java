package sg.edu.rp.c346.round3.GoalsFragment;

import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.SetOptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import sg.edu.rp.c346.round3.R;

public class GoalsFragment extends Fragment {

    private GoalsViewModel goalsViewModel;
    EditText weight, quadPower, rackPull, agility;
    TextView achieveDate;
    Button submit;
    String a = "";

    FirebaseFirestore db;
    FirebaseAuth fbAuth;

    Date d;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, final Bundle savedInstanceState) {
        goalsViewModel = ViewModelProviders.of(this).get(GoalsViewModel.class);
        View root = inflater.inflate(R.layout.goals_fragment, container, false);
        db = FirebaseFirestore.getInstance();

        fbAuth = FirebaseAuth.getInstance();
        FirebaseUser user = fbAuth.getCurrentUser();
        a = user.getUid();

        achieveDate = root.findViewById(R.id.textViewDate);
        weight = root.findViewById(R.id.editTextWG);
        quadPower = root.findViewById(R.id.editTextQPG);
        rackPull = root.findViewById(R.id.editTextRPG);
        agility = root.findViewById(R.id.editTextAG);
        submit = root.findViewById(R.id.buttonGoalSubmit);

        db.collection("/User/" + a + "/Goals").document("Goals").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
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
            }
        });

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
                    Double updatedAgility = Double.parseDouble(agility.getText().toString());
                    Map<String, Object> saveData = new HashMap<String, Object>();
                    saveData.put("agility", updatedAgility);
                    db.collection("/User/" + a + "/Goals").document("Goals").set(saveData, SetOptions.merge());
                } catch (NumberFormatException e) {
                    agility.setError("Agility needs to be a number.");
                }

                try {
                    Double updatedQuadPower = Double.parseDouble(quadPower.getText().toString());
                    Map<String, Object> saveData = new HashMap<String, Object>();
                    saveData.put("quadPower", updatedQuadPower);
                    db.collection("/User/" + a + "/Goals").document("Goals").set(saveData, SetOptions.merge());
                } catch (NumberFormatException e) {
                    quadPower.setError("Quad Power needs to be a number.");
                }

                try {
                    Double updatedRackPull = Double.parseDouble(rackPull.getText().toString());
                    Map<String, Object> saveData = new HashMap<String, Object>();
                    saveData.put("rackPull", updatedRackPull);
                    db.collection("/User/" + a + "/Goals").document("Goals").set(saveData, SetOptions.merge());
                } catch (NumberFormatException e) {
                    rackPull.setError("Rack Pull needs to be a number.");
                }

                try {
                    Double updatedWeight = Double.parseDouble(weight.getText().toString());
                    Map<String, Object> saveData = new HashMap<String, Object>();
                    saveData.put("weight", updatedWeight);
                    db.collection("/User/" + a + "/Goals").document("Goals").set(saveData, SetOptions.merge());
                } catch (NumberFormatException e) {
                    weight.setError("Weight needs to be a number.");
                }

                try {
                    Map<String, Object> saveData = new HashMap<String, Object>();
                    saveData.put("date", d);
                    db.collection("/User/" + a + "/Goals").document("Goals").set(saveData, SetOptions.merge());
                } catch (NumberFormatException e) {
                    achieveDate.setError("Date is not set.");
                }

                Toast.makeText(getContext(), "Goals Updated", Toast.LENGTH_LONG).show();
            }
        });
        return root;
    }
}

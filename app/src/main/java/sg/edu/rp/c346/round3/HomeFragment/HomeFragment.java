package sg.edu.rp.c346.round3.HomeFragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import sg.edu.rp.c346.round3.DataClasses.DataEntry;
import sg.edu.rp.c346.round3.HistoryDetails;
import sg.edu.rp.c346.round3.R;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    String a = "";
    FirebaseFirestore db;
    TextView dashSystolic, dashDiastolic, dashFat, dashRack, dashQuad, dashAgility, dashWeight, dashHeight, tvVerification;
    TextView tvAgilityGoalCheck,tvQuadGoalCheck,tvRackGoalCheck,tvWeightGoalCheck;
    ArrayList<DataEntry> entries;
    FirebaseAuth fbAuth;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, final Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.home_fragment, container, false);

        db = FirebaseFirestore.getInstance();
        entries = new ArrayList<>();

        dashSystolic = root.findViewById(R.id.tvDashSystolic);
        dashDiastolic = root.findViewById(R.id.tvDashDiastolic);
        dashFat = root.findViewById(R.id.tvDashFat);
        dashRack = root.findViewById(R.id.tvDashRack);
        dashQuad = root.findViewById(R.id.tvDashQuad);
        dashAgility = root.findViewById(R.id.tvDashAgility);
        dashHeight = root.findViewById(R.id.tvDashHeight);
        dashWeight = root.findViewById(R.id.tvDashWeight);
        tvVerification = root.findViewById(R.id.textViewVerification);
        tvAgilityGoalCheck = root.findViewById(R.id.tvAgilityGoalCheck);
        tvQuadGoalCheck = root.findViewById(R.id.tvQuadGoalCheck);
        tvRackGoalCheck = root.findViewById(R.id.tvRackGoalCheck);
        tvWeightGoalCheck = root.findViewById(R.id.tvWeightGoalCheck);


        fbAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = fbAuth.getCurrentUser();
        a = user.getUid();

        if (!user.isEmailVerified()) {
            tvVerification.setVisibility(View.VISIBLE);
        }

        tvVerification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("verification", "Successful");
                        Toast.makeText(getContext(),"Email Sent Successfully",Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("verification", "Unsuccessful: " + e.getMessage());
                    }
                });
            }
        });



        db.collection("/User/" + a + "/Data").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull final Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if (document.exists()){
                            Date dates = document.getTimestamp("date").toDate();

                            DataEntry newObj = new DataEntry(Double.parseDouble(document.get("systolicPressure").toString()),
                                    Double.parseDouble(document.get("diastolicPressure").toString()),
                                    Double.parseDouble(document.get("bodyFat").toString()),
                                    Double.parseDouble(document.get("quadPower").toString()),
                                    Double.parseDouble(document.get("rackPull").toString()),
                                    Double.parseDouble(document.get("agility").toString()),
                                    Double.parseDouble(document.get("weight").toString()),
                                    Double.parseDouble(document.get("height").toString()),
                                    dates
                            );
                            entries.add(newObj);

                            dashSystolic.setText("" + entries.get(entries.size()-1).getSystolicPressure());
                            dashDiastolic.setText("" + entries.get(entries.size()-1).getDiastolicPressure());
                            dashFat.setText("" + entries.get(entries.size()-1).getBodyFat());
                            dashQuad.setText("" + entries.get(entries.size()-1).getQuadPower());
                            dashRack.setText("" + entries.get(entries.size()-1).getRackPull());
                            dashAgility.setText("" + entries.get(entries.size()-1).getAgility());
                            dashHeight.setText("" + entries.get(entries.size()-1).getHeight());
                            dashWeight.setText("" + entries.get(entries.size()-1).getWeight());

                            db.collection("/User/" + a + "/Goals").document("Goals").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if (documentSnapshot.exists()) {
                                        
                                        if (documentSnapshot.getDouble("agility") != null){
                                            Double GoalAgility = documentSnapshot.getDouble("agility");
                                            if (GoalAgility < entries.get(entries.size()-1).getAgility()){
                                                tvAgilityGoalCheck.setText("You are " + (entries.get(entries.size()-1).getAgility() - GoalAgility) + " seconds off your goal!");
                                            } else if (GoalAgility >= entries.get(entries.size()-1).getAgility()){
                                                tvAgilityGoalCheck.setText("Goal Achieved! Set a new goal?");
                                            }
                                            //Check Goal for Agility and display Text Accordingly
                                            if (GoalAgility < entries.get(entries.size()-1).getAgility()){
                                                tvAgilityGoalCheck.setText("You are " + (entries.get(entries.size()-1).getAgility() - GoalAgility) + " seconds off your goal!");
                                            } else if (GoalAgility >= entries.get(entries.size()-1).getAgility()){
                                                tvAgilityGoalCheck.setText("Goal Achieved! Set a new goal?");
                                            }
                                        }

                                        if (documentSnapshot.getDouble("quadPower") != null){
                                            Double GoalQuadPower = documentSnapshot.getDouble("quadPower");
                                            //Check Goal for Quad and display Text Accordingly
                                            if (GoalQuadPower > entries.get(entries.size()-1).getQuadPower()){
                                                tvQuadGoalCheck.setText("You are " + (GoalQuadPower - entries.get(entries.size()-1).getQuadPower()) + " Kg off your goal!");
                                            } else if (GoalQuadPower <= entries.get(entries.size()-1).getQuadPower()){
                                                tvQuadGoalCheck.setText("Goal Achieved!\nSet a new goal?");
                                            }
                                        }

                                        if (documentSnapshot.getDouble("rackPull") != null){
                                            Double GoalRackPull = documentSnapshot.getDouble("rackPull");
                                            //Check Goal for Rack Pull and display Text Accordingly
                                            if (GoalRackPull > entries.get(entries.size()-1).getRackPull()){
                                                tvRackGoalCheck.setText("You are " + (GoalRackPull - entries.get(entries.size()-1).getRackPull()) + " Kg off your goal!");
                                            } else if (GoalRackPull <= entries.get(entries.size()-1).getRackPull()){
                                                tvRackGoalCheck.setText("Goal Achieved!\nSet a new goal?");
                                            }
                                        }

                                        if (documentSnapshot.getDouble("weight") != null){
                                            Double GoalWeight = documentSnapshot.getDouble("weight");
                                            if (GoalWeight > entries.get(entries.size()-1).getWeight()){
                                                tvWeightGoalCheck.setText((GoalWeight - entries.get(entries.size()-1).getWeight()) + " Kg to Goal");
                                            } else if (GoalWeight < entries.get(entries.size()-1).getWeight()){
                                                tvWeightGoalCheck.setText((entries.get(entries.size()-1).getWeight() - GoalWeight) + " Kg to Goal");
                                            } else {
                                                tvWeightGoalCheck.setText("Goal Achieved!");
                                            }
                                        }
                                    }
                                }
                            });

                        } else {
                        }
                    }
                }
            }
        });
        return root;
    }
}

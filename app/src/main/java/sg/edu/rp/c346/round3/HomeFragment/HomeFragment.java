package sg.edu.rp.c346.round3.HomeFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import sg.edu.rp.c346.round3.DataClasses.DataEntry;
import sg.edu.rp.c346.round3.R;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    String a = "GtoqSX78uQUAwarX3wpR";
    FirebaseFirestore db;
    TextView dashSystolic, dashDiastolic, dashFat, dashRack, dashQuad, dashAgility, dashWeight, dashHeight;
    ArrayList<DataEntry> entries;

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

        db.collection("/User/" + a + "/Data").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull final Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {

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
                    }

                    dashSystolic.setText("" + entries.get(entries.size()-1).getSystolicPressure());
                    dashDiastolic.setText("" + entries.get(entries.size()-1).getDiastolicPressure());
                    dashFat.setText("" + entries.get(entries.size()-1).getBodyFat());
                    dashQuad.setText("" + entries.get(entries.size()-1).getQuadPower());
                    dashRack.setText("" + entries.get(entries.size()-1).getRackPull());
                    dashAgility.setText("" + entries.get(entries.size()-1).getAgility());
                    dashHeight.setText("" + entries.get(entries.size()-1).getHeight());
                    dashWeight.setText("" + entries.get(entries.size()-1).getWeight());
                }
            }
        });

        return root;
    }
}

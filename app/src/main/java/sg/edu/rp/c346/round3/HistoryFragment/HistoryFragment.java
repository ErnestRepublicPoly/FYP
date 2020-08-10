package sg.edu.rp.c346.round3.HistoryFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import sg.edu.rp.c346.round3.DataClasses.DataEntry;
import sg.edu.rp.c346.round3.HistoryDetails;
import sg.edu.rp.c346.round3.R;

public class HistoryFragment extends Fragment {

    private HistoryViewModel mViewModel;
    ListView listView;
    String a = "";
    ArrayList<String> values;
    ArrayList<DataEntry> entries;

    FirebaseFirestore db;
    FirebaseAuth fbAuth;


    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.history_fragment, container, false);
        listView = root.findViewById(R.id.lvHistory);

        fbAuth = FirebaseAuth.getInstance();
        FirebaseUser user = fbAuth.getCurrentUser();
        a = user.getUid();

        db = FirebaseFirestore.getInstance();
        values = new ArrayList<>();
        entries = new ArrayList<>();



        db.collection("/User/" + a + "/Data").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull final Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Date dates = document.getTimestamp("date").toDate();
                        SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy");
                        String format = sfd.format(dates);
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

                        values.add(format);
                        entries.add(newObj);
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, values);
                    listView.setAdapter(adapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            DataEntry selectedEntry = entries.get(position);
                            Intent i = new Intent(getContext(), HistoryDetails.class);
                            i.putExtra("data", selectedEntry);
                            startActivity(i);
                        }
                    });
                }
            }
        });
//yes this is me
        return root;
    }

}
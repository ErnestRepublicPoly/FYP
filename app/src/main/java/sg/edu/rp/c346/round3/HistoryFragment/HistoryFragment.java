package sg.edu.rp.c346.round3.HistoryFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import sg.edu.rp.c346.round3.R;

public class HistoryFragment extends Fragment {

    private HistoryViewModel mViewModel;
    ListView listView;
    String a = "GtoqSX78uQUAwarX3wpR";
    ArrayList<String> values;

    FirebaseFirestore db;

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.history_fragment, container, false);
        listView = root.findViewById(R.id.lvHistory);
        db = FirebaseFirestore.getInstance();
        values = new ArrayList<>();

        db.collection("/User/" + a + "/Data").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Date dates = document.getTimestamp("date").toDate();
                        SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy");
                        String format = sfd.format(dates);
                        values.add(format);
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, values);
                    listView.setAdapter(adapter);
                }
            }
        });

        return root;
    }

}
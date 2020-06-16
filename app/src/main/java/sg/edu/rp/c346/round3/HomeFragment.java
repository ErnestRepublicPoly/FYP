package sg.edu.rp.c346.round3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    EditText sp, dp, bodyFat, quadPower, rackPull, agility;
    Button submit;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        sp = root.findViewById(R.id.editTextDP);
        dp = root.findViewById(R.id.editTextDP);
        submit = root.findViewById(R.id.buttonSubmit);

        return root;
    }
}

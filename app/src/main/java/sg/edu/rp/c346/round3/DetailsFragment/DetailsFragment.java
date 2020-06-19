package sg.edu.rp.c346.round3.DetailsFragment;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import sg.edu.rp.c346.round3.InfoClass;
import sg.edu.rp.c346.round3.R;

public class DetailsFragment extends Fragment {

    private DetailsViewModel mViewModel;


    public static DetailsFragment newInstance() {
        return new DetailsFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
        View root = inflater.inflate(R.layout.details_fragment, container, false);
        ListView lv;
        ArrayAdapter<InfoClass> aa;
        lv = root.findViewById(R.id.lvInfo);


        final ArrayList<InfoClass> currentInfo = new ArrayList<InfoClass>();
        currentInfo.add(new InfoClass("Blood Pressure",R.drawable.bloodpressure));
        currentInfo.add(new InfoClass("BMI",R.drawable.bmi));
        currentInfo.add(new InfoClass("Body Fat",R.drawable.fat));
        currentInfo.add(new InfoClass("Quad Power",R.drawable.legcurl));
        currentInfo.add(new InfoClass("Rack Pull",R.drawable.rackpull));
        currentInfo.add(new InfoClass("Agility",R.drawable.agility));

        aa = new DetailsAdapter(getContext(),R.layout.details_info_design,currentInfo);
        lv.setAdapter(aa);

        return root;
    }

}

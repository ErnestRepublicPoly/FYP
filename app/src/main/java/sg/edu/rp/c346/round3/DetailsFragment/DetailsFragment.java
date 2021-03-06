package sg.edu.rp.c346.round3.DetailsFragment;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import sg.edu.rp.c346.round3.DataClasses.InfoClass;
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
        currentInfo.add(new InfoClass("Blood Pressure", R.drawable.dashboardheart));
        currentInfo.add(new InfoClass("BMI", R.drawable.dashboardbmi));
        currentInfo.add(new InfoClass("Body Fat", R.drawable.dashboardfat));
        currentInfo.add(new InfoClass("Quad Power", R.drawable.legcurl));
        currentInfo.add(new InfoClass("Rack Pull", R.drawable.dashboardrack));
        currentInfo.add(new InfoClass("Agility", R.drawable.dashboardagility));

        aa = new DetailsAdapter(getContext(), R.layout.details_info_design, currentInfo);
        lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent toInfo = new Intent(getContext(), InfoActivity.class);
                toInfo.putExtra("position", position);
                startActivity(toInfo);
            }
        });

        return root;
    }

}

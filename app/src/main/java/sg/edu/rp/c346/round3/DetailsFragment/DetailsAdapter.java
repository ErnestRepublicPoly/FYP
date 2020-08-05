package sg.edu.rp.c346.round3.DetailsFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import sg.edu.rp.c346.round3.DataClasses.InfoClass;
import sg.edu.rp.c346.round3.R;

public class DetailsAdapter extends ArrayAdapter<InfoClass> {
    private ArrayList<InfoClass> currInfo;
    private Context context;
    private TextView tvName;
    private ImageView ivImage;
    int resource;

    public DetailsAdapter(Context context, int resource, ArrayList<InfoClass> classes) {
        super(context, resource, classes);
        this.context = context;
        this.currInfo = classes;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.details_info_design, parent, false);

        tvName = (TextView) rowView.findViewById(R.id.tvInfoName);
        ivImage = (ImageView) rowView.findViewById(R.id.ivPic);

        InfoClass currInfoList = currInfo.get(position);

        tvName.setText(currInfoList.getInfoName());
        ivImage.setImageResource(currInfoList.getImage());

        return rowView;
    }
}

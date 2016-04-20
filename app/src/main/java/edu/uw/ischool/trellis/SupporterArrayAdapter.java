package edu.uw.ischool.trellis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by iguest on 4/19/16.
 */
public class SupporterArrayAdapter extends ArrayAdapter<Supporter> {
    private final Context context;
    private final Supporter[] values;

    public SupporterArrayAdapter(Context context, Supporter[] values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.supporter_results_row, parent, false);
        TextView nameTextView = (TextView) rowView.findViewById(R.id.txtSupporterName);
        TextView countTextView = (TextView) rowView.findViewById(R.id.txtSupportCount);
        TextView quoteTextView = (TextView) rowView.findViewById(R.id.txtQuote);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.supportIcon);

        nameTextView.setText(values[position].getName());
        countTextView.setText("" + values[position].getSupporteeCount());
        quoteTextView.setText(values[position].getQuote());
        //TODO: set the image to something other than the default icon

        return rowView;
    }
}

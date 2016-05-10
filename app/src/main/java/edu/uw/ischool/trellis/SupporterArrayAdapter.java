package edu.uw.ischool.trellis;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.MalformedInputException;

/**
 * Created by iguest on 4/19/16.
 */
public class SupporterArrayAdapter extends ArrayAdapter<Supporter> {
    private final Context context;
    private final Supporter[] values;
    private ProgressDialog pDialog;
    private ImageView iv;
    private Bitmap bitmap;


    public SupporterArrayAdapter(Context context, Supporter[] values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.supporter_results_row, parent, false);
        TextView nameTextView = (TextView) rowView.findViewById(R.id.txtSupporterName);
        TextView countTextView = (TextView) rowView.findViewById(R.id.txtSupportCount);
        TextView quoteTextView = (TextView) rowView.findViewById(R.id.txtQuote);
        iv = (ImageView) rowView.findViewById(R.id.supportIcon);

        nameTextView.setText(values[position].getName());
        countTextView.setText("" + values[position].getSupporteeCount());
        quoteTextView.setText(values[position].getQuote());

        // Set user image
        String imgURL = "https://graph.facebook.com/" + values[position].getId() + "/picture?type=large";

        try {
            URL url = new URL(imgURL);
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            iv.setImageBitmap(bmp);
        } catch (IOException e) {
            Log.e("Image Download", e.getMessage());
        }

        return rowView;
    }
}


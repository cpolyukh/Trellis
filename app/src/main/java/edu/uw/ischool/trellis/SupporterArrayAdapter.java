package edu.uw.ischool.trellis;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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

//        String imgURL = "http://graph.facebo" +
//                "ok.com/" + values[position].getId() + "/picture";
//
//        try {
//            URL url = new URL(imgURL);
//            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//            imageView.setImageBitmap(bmp);
//            Log.e("Create Bitmap", "--------------------" + bmp.toString());
//        } catch (IOException e) {
//            Log.e("Image Download", e.getMessage());
//        }



        //new ImageLoadTask(url, imageView).execute();
        //new DownloadImageTask(imageView).execute(url);


        return rowView;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
                in.close();
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

            Log.e("Create Bitmap",  "--------------------" + mIcon11.toString());
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}


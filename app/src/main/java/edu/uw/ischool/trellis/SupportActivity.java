package edu.uw.ischool.trellis;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SupportActivity extends AppCompatActivity {
    MainApp app;
    ListView list;

    List<Supporter> allSupporters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        overridePendingTransition(R.anim.sendbird_slide_in_from_bottom, R.anim.sendbird_slide_out_to_top);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        LinearLayout friendsLayoutIcon = (LinearLayout) findViewById(R.id.friendsLayoutIcon);
        friendsLayoutIcon.setBackgroundColor(getResources().getColor(R.color.light_gray));

        ImageView icon = (ImageView) friendsLayoutIcon.findViewById(R.id.friendsLayoutIcon_img);
        icon.setImageDrawable(getResources().getDrawable(R.drawable.friends01));


        //TODO: Fix this code! Almost working

        app = (MainApp) getApplication();

        //Intent launchingIntent = getIntent();

        list = (ListView) findViewById(R.id.lstSupporters);

        app.changeStatusBarColor(this);

        addSupporters();

        //TODO: make sure that this list only contains supporters that the given
        //user is not already connected to
        allSupporters = Supporter.getAllSupporters();

        final Supporter[] supporterArray = new Supporter[allSupporters.size()];
        for (int i = 0; i < allSupporters.size(); i++) {
            supporterArray[i] = allSupporters.get(i);
        }
        //SupporterArrayAdapter adapter = new SupporterArrayAdapter(this, supporterArray);
        UserAdapter adapter = new UserAdapter(getApplicationContext(), app.getCurrentUser());
        list.setAdapter(adapter);

        final Button mySupportBtn = (Button) findViewById(R.id.mySupportBtn);
        final Button findSupportBtn = (Button) findViewById(R.id.findSupportBtn);
        final ListView lstSupporters = (ListView) findViewById(R.id.lstSupporters);


        findSupportBtn.setBackgroundColor(getResources().getColor(R.color.mini_header));
        mySupportBtn.setBackgroundColor(getResources().getColor(R.color.header));


        findSupportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findSupportBtn.setBackgroundColor(getResources().getColor(R.color.mini_header));
                mySupportBtn.setBackgroundColor(getResources().getColor(R.color.header));
                mySupportBtn.setTextColor(getResources().getColor(R.color.text_light));
                findSupportBtn.setTextColor(getResources().getColor(R.color.text_dark));
                lstSupporters.setVisibility(View.VISIBLE);
            }
        });


        mySupportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySupportBtn.setBackgroundColor(getResources().getColor(R.color.mini_header));
                findSupportBtn.setBackgroundColor(getResources().getColor(R.color.header));
                mySupportBtn.setTextColor(getResources().getColor(R.color.text_dark));
                findSupportBtn.setTextColor(getResources().getColor(R.color.text_light));
                lstSupporters.setVisibility(View.INVISIBLE);
            }
        });

        /********************************************************/
        /********************** NEW TOOLBAR SETUP *******************/
        /********************************************************/
        LinearLayout friendsIcon = (LinearLayout) findViewById(R.id.friendsLayoutIcon);
        LinearLayout messagesIcon = (LinearLayout) findViewById(R.id.messagesLayoutIcon);
        LinearLayout conversationIcon = (LinearLayout) findViewById(R.id.conversationStartersLayoutIcon);
        LinearLayout learnMoreIcon = (LinearLayout) findViewById(R.id.learnMoreLayoutIcon);
        LinearLayout profileIcon = (LinearLayout) findViewById(R.id.profileLayoutIcon);


        friendsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(SupportActivity.this, SupportActivity.class);
                startActivity(next);
            }
        });

        messagesIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(SupportActivity.this, MessagesActivity.class);
                startActivity(next);
            }
        });

        conversationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(SupportActivity.this, ConversationStarterActivity.class);
                startActivity(next);
            }
        });

        learnMoreIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(SupportActivity.this, LearnMoreActivity.class);
                startActivity(next);
            }
        });

        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(SupportActivity.this, EditProfileActivity.class);
                startActivity(next);
            }
        });
        /********************************************************/
        /********************************************************/
        /********************************************************/


    }

    private void addSupporters() {
        Supporter.resetSupporters();

        List<User> userFriends = app.getCurrentUser().getUserFriends();

        for(int i = 0; i < userFriends.size(); i++) {
            User friend = userFriends.get(i);
            Supporter newSupporter = new Supporter(friend.getFirstName(), friend.getLastName(), "WOw let me support u pls", "", friend.getId());
            newSupporter.setSupporteeCount(i * 2);
        }

    }

    //Used for testing since we don't have actual supporters yet. These are simply default supporters
    //used as an example of how supporters would be displayed
    private void addDefaultSupporters() {
        Supporter newSupporter = new Supporter("Jen", "Nguyen", "\"Happy to just talk or grab some coffee! Don&apos;t be afraid to reach out :)\"");
        newSupporter.setSupporteeCount(10);
        newSupporter = new Supporter("Ahn", "Nest", "\"I am always available to talk, message me at anytime!\"");
        newSupporter.setSupporteeCount(2);
        newSupporter = new Supporter("Buttress", "Ofsupport", "\"I will support the life you want to build for yourself.\"");
        newSupporter.setSupporteeCount(0);
    }



    public class UserAdapter extends BaseAdapter {
        private final Context mContext;
        private final LayoutInflater mInflater;
        private final List<edu.uw.ischool.trellis.User> mItemList;


        public UserAdapter(Context context, edu.uw.ischool.trellis.User user) {
            mContext = context;
            mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mItemList = user.getUserFriends();
        }

        @Override
        public int getCount() {
            return mItemList.size();
        }

        @Override
        public edu.uw.ischool.trellis.User getItem(int position) {
            return mItemList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            edu.uw.ischool.trellis.User item = getItem(position);

            View rowView = mInflater.inflate(R.layout.supporter_results_row, parent, false);


            TextView nameTextView = (TextView) rowView.findViewById(R.id.txt_name);
            TextView supportingView = (TextView) rowView.findViewById(R.id.supportingView);
            final TextView quoteView = (TextView) rowView.findViewById(R.id.quoteView);
            ImageView imgThumb = (ImageView) rowView.findViewById(R.id.img_thumbnail);

            Typeface myTypeface = Typeface.createFromAsset(getAssets(), "Futura.ttc");
            Typeface italic = Typeface.createFromAsset(getAssets(), "Futura_Italic.ttf");
            nameTextView.setTypeface(myTypeface);
            supportingView.setTypeface(myTypeface);
            quoteView.setTypeface(italic);

            if (position % 2 == 0) {
                quoteView.setText("Having struggled with depression for over 7 years, I am here to listen and be a friend.");
            } else {
                quoteView.setText("I'm here to help :)");
            }

            nameTextView.setText(item.getName());
            displayUrlImage(imgThumb, item.getImageUrl());

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent next = new Intent(SupportActivity.this, ProfileActivity.class);
                    next.putExtra("firstName", getItem(position).getFirstName());
                    next.putExtra("lastName", getItem(position).getLastName());
                    next.putExtra("id", getItem(position).getId());
                    next.putExtra("quote", quoteView.getText());
                    startActivity(next);
                }
            });
            return rowView;
        }


    }

    private static void displayUrlImage(ImageView imageView, String url) {
        UrlDownloadAsyncTask.display(url, imageView);
    }

    private static class UrlDownloadAsyncTask extends AsyncTask<Void, Void, Object> {
        private static LRUCache cache = new LRUCache((int) (Runtime.getRuntime().maxMemory() / 16)); // 1/16th of the maximum memory.
        private final UrlDownloadAsyncTaskHandler handler;
        private String url;

        public static void download(String url, final File downloadFile, final Context context) {
            UrlDownloadAsyncTask task = new UrlDownloadAsyncTask(url, new UrlDownloadAsyncTaskHandler() {
                @Override
                public void onPreExecute() {
                    Toast.makeText(context, "Start downloading", Toast.LENGTH_SHORT).show();
                }

                @Override
                public Object doInBackground(File file) {
                    if(file == null) {
                        return null;
                    }

                    try {
                        BufferedInputStream in = null;
                        BufferedOutputStream out = null;

                        //create output directory if it doesn't exist
                        File dir = downloadFile.getParentFile();
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }

                        in = new BufferedInputStream(new FileInputStream(file));
                        out = new BufferedOutputStream(new FileOutputStream(downloadFile));

                        byte[] buffer = new byte[1024 * 100];
                        int read;
                        while ((read = in.read(buffer)) != -1) {
                            out.write(buffer, 0, read);
                        }
                        in.close();
                        out.flush();
                        out.close();

                        return downloadFile;
                    } catch(IOException e) {
                        e.printStackTrace();
                    }

                    return null;
                }

                @Override
                public void onPostExecute(Object object, UrlDownloadAsyncTask task) {
                    if(object != null && object instanceof File) {
                        Toast.makeText(context, "Finish downloading: " + ((File)object).getAbsolutePath(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(context, "Error downloading", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else {
                task.execute();
            }
        }

        public static void display(String url, final ImageView imageView) {
            UrlDownloadAsyncTask task = null;

            if(imageView.getTag() != null && imageView.getTag() instanceof UrlDownloadAsyncTask) {
                try {
                    task = (UrlDownloadAsyncTask) imageView.getTag();
                    task.cancel(true);
                } catch(Exception e) {}

                imageView.setTag(null);
            }

            task = new UrlDownloadAsyncTask(url, new UrlDownloadAsyncTaskHandler() {
                @Override
                public void onPreExecute() {
                    imageView.setImageResource(R.drawable.sendbird_img_placeholder);
                }

                @Override
                public Object doInBackground(File file) {
                    if(file == null) {
                        return null;
                    }

                    Bitmap bm = null;
                    try {
                        int targetHeight = 256;
                        int targetWidth = 256;

                        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(file));
                        bin.mark(bin.available());

                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = true;
                        BitmapFactory.decodeStream(bin, null, options);

                        Boolean scaleByHeight = Math.abs(options.outHeight - targetHeight) >= Math.abs(options.outWidth - targetWidth);

                        if(options.outHeight * options.outWidth >= targetHeight * targetWidth) {
                            double sampleSize = scaleByHeight
                                    ? options.outHeight / targetHeight
                                    : options.outWidth / targetWidth;
                            options.inSampleSize = (int)Math.pow(2d, Math.floor(Math.log(sampleSize)/Math.log(2d)));
                        }

                        try {
                            bin.reset();
                        } catch(IOException e) {
                            bin = new BufferedInputStream(new FileInputStream(file));
                        }

                        // Do the actual decoding
                        options.inJustDecodeBounds = false;
                        bm = BitmapFactory.decodeStream(bin, null, options);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    return getCroppedBitmap(bm, 256);
                }

                @Override
                public void onPostExecute(Object object, UrlDownloadAsyncTask task) {
                    if(object != null && object instanceof Bitmap && imageView.getTag() == task) {
                        imageView.setImageBitmap((Bitmap)object);
                    }
                }
            });

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else {
                task.execute();
            }

            imageView.setTag(task);
        }

        public UrlDownloadAsyncTask(String url, UrlDownloadAsyncTaskHandler handler) {
            this.handler = handler;
            this.url = url;
        }

        public interface UrlDownloadAsyncTaskHandler {
            public void onPreExecute();
            public Object doInBackground(File file);
            public void onPostExecute(Object object, UrlDownloadAsyncTask task);
        }

        @Override
        protected void onPreExecute() {
            if(handler != null) {
                handler.onPreExecute();
            }
        }

        public static Bitmap getCroppedBitmap(Bitmap bmp, int radius) {
            Bitmap sbmp;

            if (bmp.getWidth() != radius || bmp.getHeight() != radius) {
                float smallest = Math.min(bmp.getWidth(), bmp.getHeight());
                float factor = smallest / radius;
                sbmp = Bitmap.createScaledBitmap(bmp, (int)(bmp.getWidth() / factor), (int)(bmp.getHeight() / factor), false);
            } else {
                sbmp = bmp;
            }

            Bitmap output = Bitmap.createBitmap(radius, radius,
                    Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(output);

            final int color = 0xffa19774;
            final Paint paint = new Paint();
            final Rect rect = new Rect(0, 0, radius, radius);

            paint.setAntiAlias(true);
            paint.setFilterBitmap(true);
            paint.setDither(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(Color.parseColor("#BAB399"));
            canvas.drawCircle(radius / 2 + 0.7f,
                    radius / 2 + 0.7f, radius / 2 + 0.1f, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(sbmp, rect, rect, paint);

            return output;
        }

        protected Object doInBackground(Void... args) {
            File outFile = null;
            try {
                if(cache.get(url) != null && new File(cache.get(url)).exists()) { // Cache Hit
                    outFile = new File(cache.get(url));
                } else { // Cache Miss, Downloading a file from the url.
                    outFile = File.createTempFile("sendbird-download", ".tmp");
                    OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outFile));

                    InputStream input = new BufferedInputStream(new URL(url).openStream());
                    byte[] buf = new byte[1024 * 100];
                    int read = 0;
                    while ((read = input.read(buf, 0, buf.length)) >= 0) {
                        outputStream.write(buf, 0, read);
                    }

                    outputStream.flush();
                    outputStream.close();
                    cache.put(url, outFile.getAbsolutePath());
                }



            } catch(IOException e) {
                e.printStackTrace();

                if(outFile != null) {
                    outFile.delete();
                }

                outFile = null;
            }


            if(handler != null) {
                return handler.doInBackground(outFile);
            }

            return outFile;
        }

        protected void onPostExecute(Object result) {
            if(handler != null) {
                handler.onPostExecute(result, this);
            }
        }

        private static class LRUCache {
            private final int maxSize;
            private int totalSize;
            private ConcurrentLinkedQueue<String> queue;
            private ConcurrentHashMap<String, String> map;

            public LRUCache(final int maxSize) {
                this.maxSize = maxSize;
                this.queue	= new ConcurrentLinkedQueue<String>();
                this.map	= new ConcurrentHashMap<String, String>();
            }

            public String get(final String key) {
                if (map.containsKey(key)) {
                    queue.remove(key);
                    queue.add(key);
                }

                return map.get(key);
            }

            public synchronized void put(final String key, final String value) {
                if(key == null || value == null) {
                    throw new NullPointerException();
                }

                if (map.containsKey(key)) {
                    queue.remove(key);
                }

                queue.add(key);
                map.put(key, value);
                totalSize = totalSize + getSize(value);

                while (totalSize >= maxSize) {
                    String expiredKey = queue.poll();
                    if (expiredKey != null) {
                        totalSize = totalSize - getSize(map.remove(expiredKey));
                    }
                }
            }

            private int getSize(String value) {
                return value.length();
            }
        }
    }


}

package com.example.myteam;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    public TextView tvname, tvemail;
    public ImageView ivAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvname = findViewById(R.id.name);
        tvemail = findViewById(R.id.email);
        ivAvatar = findViewById(R.id.avatar);

        String url1 = "https://jsonplaceholder.typicode.com/users/1";
        String url2 = "https://robohash.org/1?set=set2";

        RestLoader  rs = new RestLoader();
        String result = rs.doInBackground(url1);
        rs.onPostExecute(result);

        ImageDownloader task = new ImageDownloader();
        Bitmap bitmap = task.doInBackground(url2);
        task.onPostExecute(bitmap);

        //task.execute(url2);


    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream is = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                return bitmap;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            ivAvatar = findViewById(R.id.avatar);
            ivAvatar.setImageBitmap(bitmap);
        }
    }

    public class RestLoader extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            URL url;
            HttpURLConnection urlConnection;

            try {
                url =  new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();

                InputStream is = urlConnection.getInputStream();

                Scanner sc = new Scanner(is);
                StringBuilder result = new StringBuilder();
                String line;

                while (sc.hasNextLine()){
                    line = sc.nextLine();
                    result.append(line);
                }

                return  result.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String result){

            tvname = findViewById(R.id.name);
            tvemail = findViewById(R.id.email);

            ivAvatar = findViewById(R.id.avatar);

            if(result == null){
//                Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_LONG).show();
                return;
            }

            try {
                JSONObject root = new JSONObject(result);

                int  id = root.getInt("id");
                String name = root.getString("name");
                String email = root.getString("email");

                tvname.setText(name);
                tvemail.setText(email);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}
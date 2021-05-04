package com.example.myteam;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.loader.content.AsyncTaskLoader;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.BitSet;
import java.util.Scanner;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener{

    private static TextView tvName, tvEmail;
    private static ImageView ivAvatar;
    private ImageButton btnIncrease, btnDecrease;

    private int id = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        tvName = view.findViewById(R.id.name);
        tvEmail = view.findViewById(R.id.email);

        ivAvatar = view.findViewById(R.id.avatar);

        btnIncrease = view.findViewById(R.id.btnIncrease);
        btnDecrease = view.findViewById(R.id.btnDecrease);

        //
        String urlText = "https://jsonplaceholder.typicode.com/users/1";
        RestLoader restLoader = new RestLoader();
        restLoader.execute(urlText);

        String urlImage = "https://robohash.org/1?set=set4";
        ImageLoader imageLoader = new ImageLoader();
        imageLoader.execute(urlImage);

        // Click event
        btnIncrease.setOnClickListener(this);
        btnDecrease.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        String urlText, urlImage;
        ImageLoader imageLoader;
        RestLoader restLoader;

        switch (v.getId()){
            case R.id.btnIncrease:
                if(id < 10){
                    id++;
                    Log.i("ID-INCREASE", "" + id);

                    urlText = "https://jsonplaceholder.typicode.com/users/" + id;
                    restLoader = new RestLoader();
                    restLoader.execute(urlText);

                    urlImage = "https://robohash.org/"+ id + "?set=set=4";
                    imageLoader = new ImageLoader();
                    imageLoader.execute(urlImage);
                }
                break;

            case R.id.btnDecrease:
                if(id > 0){
                    id--;
                    Log.i("ID-DECREASE", "" + id);

                    urlText = "https://jsonplaceholder.typicode.com/users/" + id;
                    restLoader = new RestLoader();
                    restLoader.execute(urlText);

                    urlImage = "https://robohash.org/"+ id + "?set=set=4";
                    imageLoader = new ImageLoader();
                    imageLoader.execute(urlImage);
                }
                break;

        }

    }

    public static class RestLoader extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            URL url;
            HttpURLConnection urlConnection;

            try {
                url = new URL(strings[0]);
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

                Log.i("RESULT", "" +result);

                return  result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if(result == null){
                return;
            }

            try {
                JSONObject obj = new JSONObject(result);

                String name = obj.getString("name");
                String email = obj.getString("email");

                tvName.setText(name);
                tvEmail.setText(email);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static class ImageLoader extends AsyncTask<String, Void, Bitmap>{
        @Override
        protected Bitmap doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
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
            ivAvatar.setImageBitmap(bitmap);
        }
    }
}
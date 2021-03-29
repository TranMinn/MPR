package com.example.mybookmarks;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the factory method to
 * create an instance of this fragment.
 */
public class BookmarksFragment extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bookmarks, container, false);

        // References
        LinearLayout mp3Btn, bluezoneBtn, baomoiBtn, mediumBtn;
        mp3Btn = view.findViewById(R.id.mp3Btn);
        bluezoneBtn = view.findViewById(R.id.bluezoneBtn);
        baomoiBtn = view.findViewById(R.id.baomoiBtn);
        mediumBtn = view.findViewById(R.id.mediumBtn);

        // Events
        mp3Btn.setOnClickListener(this);
        bluezoneBtn.setOnClickListener(this);
        baomoiBtn.setOnClickListener(this);
        mediumBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getFragmentManager();
        Fragment fragment = new WebViewFragment();

        // Pass args
        Bundle data = new Bundle();

        switch (v.getId()){
            case R.id.mp3Btn:
                data.putString("URL", "https://mp3.zing.vn/");
                fragment.setArguments(data);

                manager.beginTransaction()
                        .replace(R.id.fragmentContainer, fragment)
                        .addToBackStack(null)
                        .commit();
                break;

            case R.id.bluezoneBtn:
                data.putString("URL", "https://bluezone.gov.vn/");
                fragment.setArguments(data);

                manager.beginTransaction()
                        .replace(R.id.fragmentContainer, fragment)
                        .addToBackStack(null)
                        .commit();
                break;

            case R.id.baomoiBtn:
                data.putString("URL", "https://baomoi.com/");
                fragment.setArguments(data);

                manager.beginTransaction()
                        .replace(R.id.fragmentContainer, fragment)
                        .addToBackStack(null)
                        .commit();
                break;

            case R.id.mediumBtn:
                data.putString("URL", "https://medium.com/");
                fragment.setArguments(data);

                manager.beginTransaction()
                        .replace(R.id.fragmentContainer, fragment)
                        .addToBackStack(null)
                        .commit();
                break;


        }

    }
}
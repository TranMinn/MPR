package com.example.mybookmarks;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the factory method to
 * create an instance of this fragment.
 */
public class WebViewFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_web_view, container, false);

        // References
        WebView webView = view.findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());

        // Enable JS
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        // Get passed data
        String url = getArguments().getString("URL");
        webView.loadUrl(url);

        // Back event
        Button backBtn = view.findViewById(R.id.backBtn);
        backBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getFragmentManager();
        manager.popBackStack();
    }
}
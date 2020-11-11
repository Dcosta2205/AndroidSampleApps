package com.xyz.webview;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This main Activity will open a webview that loads google. Make sure your device is connected to internet.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        find the webview defined in the xml using ID
         */
        WebView webView = findViewById(R.id.webView);
        /*
        loadUrl("url") will load the url inside the webview
         */
        webView.loadUrl("https://www.google.com");
    }
}
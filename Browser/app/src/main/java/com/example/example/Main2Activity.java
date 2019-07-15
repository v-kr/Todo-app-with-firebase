package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String name = getIntent().getStringExtra("Vivek");
        Toast.makeText(getApplicationContext(),name,Toast.LENGTH_SHORT).show();
        WebView webView=findViewById(R.id.webview);
        webView.loadUrl("https://www.google.com");
        webView.setWebViewClient(new WebViewClient());

    }
}

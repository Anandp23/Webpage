package com.example.webpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    SearchView searchView;
    String searchedText="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webview);
        searchView = findViewById(R.id.item1);

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.ddmalar.website/");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.example_menu,menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.item1).getActionView();
        searchView.setQueryHint("Search for a Website");



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if (query.endsWith(".com")||query.endsWith(".in")){
                    webView.loadUrl("https://"+query);
                }else {
                    webView.loadUrl("https://www.google.com/search?q="+query);
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                searchedText=newText;
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item2:
                webView.loadUrl("https://www.ddmalar.website/");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }else {
            super.onBackPressed();
        }
    }


}


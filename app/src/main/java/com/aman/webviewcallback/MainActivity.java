package com.aman.webviewcallback;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(this, "MainActivity");
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("file:///android_asset/webpage.html");
    }

    @JavascriptInterface
    public void callToast(String name, String company) {

        Toast.makeText(this, "Hi " + name + " !! Wecome to " + company, Toast.LENGTH_LONG).show();
    }

    public void onBtnClick(View view) {
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        if (message == null || message.isEmpty()) {
//            Toast.makeText(this, "Message is Empty", Toast.LENGTH_SHORT).show();
            webView.loadUrl("javascript:" + "showAlert" + "(\"Message is Empty\")");
        } else {
            webView.loadUrl("javascript:" + "showAlert" + "(\""+message+"\")");
        }
        editText.setText("");
    }
}

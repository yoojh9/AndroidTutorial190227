package com.example.a10_webview;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    Button btnGo;
    EditText editURL;
    WebView webView;

    ProgressDialog dlg;

    class MyWebViewClient extends WebViewClient {
        // 웹뷰가 페이지가 로딩을 시작 할 때 프로그래스 바를 띄우겠다.
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            dlg.show();
        }

        // 웹뷰가 페이지가 로딩을 끝낼 때
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            dlg.dismiss();
        }
    }

    class MyChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);     // 프로그레스 바 만들 때 이런 값을 받아서 처리할 수 있음.
            Log.d("progress : " , "progress " + newProgress);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dlg = new ProgressDialog(this);
        btnGo = findViewById(R.id.btnGo);
        editURL = findViewById(R.id.editURL);
        webView = findViewById(R.id.webView);

        // WebViewClient
         webView.setWebViewClient(new MyWebViewClient());
        // webView.setWebChromeClient(new MyChromeClient());

        // webView 이미지가 제대로 출력이 안되므로 해당 처리를 해주어야 함 (default가 false임)
        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl("https://daum.net");

    }

    // 메인 액티비티의 back키 동작을 override해서 웹뷰에서 화면 뒤로가기로 변경해보자
    // 기존에는 위에 쌓여있는 액티비티 종료 역할.
    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}

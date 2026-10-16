package np.imposter;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class MainActivity extends Activity {
    private WebView web;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setStatusBarColor(Color.parseColor("#0d0f1f"));
        getWindow().setNavigationBarColor(Color.parseColor("#0d0f1f"));
        web = new WebView(this);
        web.setBackgroundColor(Color.parseColor("#0d0f1f"));
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setDomStorageEnabled(true);
        web.getSettings().setMediaPlaybackRequiresUserGesture(false); // intro sound on launch
        web.setWebChromeClient(new WebChromeClient()); // enables confirm()
        web.addJavascriptInterface(new Object() {
            @JavascriptInterface public void theme(boolean light) {
                runOnUiThread(() -> {
                    int c = Color.parseColor(light ? "#f4f5fb" : "#0d0f1f");
                    getWindow().setStatusBarColor(c);
                    getWindow().setNavigationBarColor(c);
                    web.setBackgroundColor(c);
                    web.setSystemUiVisibility(light ? View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR : 0);
                });
            }
        }, "Android");
        web.loadUrl("file:///android_asset/index.html");
        setContentView(web);
    }

    @Override
    public void onBackPressed() {
        web.evaluateJavascript("goBack()", r -> { if (!"true".equals(r)) finish(); });
    }
}

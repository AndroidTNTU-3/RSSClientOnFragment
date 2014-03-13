package android.edu.rss;

import com.example.rssclient.R;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class DetailFragment extends Fragment{
	
	
	WebViewClient webclient;
	Intent intent;
	String curUrl;
	
	public void init(String url) {

		curUrl = url;
		Log.i("Data transaction: ", curUrl);
		
    }
		 	
	@Override
    public void onActivityCreated(Bundle savedInstanceState) {

            super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
         
    }
      
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       
            View view = inflater.inflate(R.layout.detail, container, false);
            if (curUrl != null) {
            WebView web = (WebView) view.findViewById(R.id.webView);
    		web.setWebViewClient(new Webclient());
    		web.getSettings().setJavaScriptEnabled(true);
            web.loadUrl(curUrl);
            }
    		/*TextView text = (TextView) view.findViewById(R.id.textView1);
    		text.setText(curUrl);*/
            return view;
    }


	public void updateLink(String link) {
		
		curUrl = link;
		WebView web = (WebView) getView().findViewById(R.id.webView);
		web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl(curUrl);
		/*TextView text = (TextView) getView().findViewById(R.id.textView1);
		text.setText(link);*/
	}
	

	private class Webclient extends WebViewClient{
			@Override
	        public boolean shouldOverrideUrlLoading(WebView view, String url) {
	            view.loadUrl(url);
	            return false;
	        }
	    }

}

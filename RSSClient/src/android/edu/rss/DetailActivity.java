package android.edu.rss;

import com.example.rssclient.R;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class DetailActivity extends FragmentActivity {
	WebView web;
	WebViewClient webclient;
	String url = "";
	
	 @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*DetailFragment fragment = (DetailFragment)getFragmentManager().findFragmentById(R.id.detailFragment);
        if (fragment == null) fragment = new DetailFragment();

        
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }*/
        
        DetailFragment fragment = new DetailFragment();
        
        //setContentView(R.layout.details_activity_layout);
        Intent i = getIntent();
		url = i.getExtras().getString("link");

        fragment.init(url);
        getFragmentManager().beginTransaction().add(android.R.id.content, fragment).commit();
     }
	 
	 

}

package android.edu.rss;

import com.example.rssclient.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.edu.rss.FragmentList.Callbacks;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity implements Callbacks {
	FragmentList fragmentList;
	FragmentManager fManager;
	FragmentTransaction fTrans;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if (findViewById(R.id.fragMainPage) != null){
			
		fragmentList = new FragmentList();
		fManager = getFragmentManager();
		
		fTrans = fManager.beginTransaction();
		fTrans.add(R.id.fragMainPage, fragmentList);
		fTrans.commit();
		
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onItemSelected(String link) {
        		
		if (findViewById(R.id.fragPage) != null) { 
			
			DetailFragment fragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.fragPage);
			
			if (fragment == null) {  
				
				Log.i("Data transaction", "This is dual fragment");            
				fragment = new DetailFragment();            
				fragment.init(link);            // in dual fragment 
				FragmentManager fm = getFragmentManager();            
				FragmentTransaction ft = fm.beginTransaction();   
				ft.replace(R.id.fragPage, fragment);            
				ft.commit();
				
			} else {         
				Log.i("Data transaction", "Dual Fragment update");         
				fragment.updateLink(link);       
			}    
			
		}    else {        
				/*System.out.println("Start Activity");        
				Intent intent = new Intent(this, DetailActivity.class);        
				intent.putExtra("link", link);        
				startActivity(intent);*/
			
			DetailFragment df = new DetailFragment();

			df.init(link); 
				
			fTrans = fManager.beginTransaction();
			fTrans.replace(R.id.fragMainPage, df);
			fTrans.addToBackStack(null);
			fTrans.commit();
			
			}

	}
}

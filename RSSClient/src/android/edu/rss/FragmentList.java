package android.edu.rss;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import nl.matshofman.saxrssreader.RssFeed;
import nl.matshofman.saxrssreader.RssItem;
import nl.matshofman.saxrssreader.RssReader;

import org.xml.sax.SAXException;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.rssclient.R;

public class FragmentList extends Fragment {

	private ListView feedList;
	private ProgressBar pbLoad;
	RssFeed feed = null;
	private final String url = "http://9gagrss.com/feed/";

	private Callbacks mCallbacks;
	
	public interface Callbacks {
		
		public void onItemSelected(String link);
		
	}
	
	@Override
	  public void onAttach(Activity activity) {
	    super.onAttach(activity);
	        try {
	        	mCallbacks = (Callbacks) activity;
	        } catch (ClassCastException e) {
	            throw new ClassCastException(activity.toString() + " must implement onSomeEventListener");
	        }
	  }
	
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
    	super.onActivityCreated(savedInstanceState);

        feedList = (ListView) getView().findViewById(R.id.feedList);
        feedList.setOnItemClickListener(new ListListener());
        pbLoad = (ProgressBar) getView().findViewById(R.id.pbLoad);
        new GetRSSFeedTask().execute(url);
    }
    
    @Override 
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState) {  
        return inflater.inflate(R.layout.list, container, false);  
    }  
    
    private void updateFeedList(ArrayList<RssItem> items){
    	feedList.setAdapter(new FeedAdapter(items, getActivity()));
    	showList();
    }
    
    private void showList(){
    	pbLoad.setVisibility(View.GONE);
    	feedList.setVisibility(View.VISIBLE);
    }
    
    private void showProgress(){
    	pbLoad.setVisibility(View.VISIBLE);
    	feedList.setVisibility(View.GONE);
    }
    
    class GetRSSFeedTask extends AsyncTask<String, Void, RssFeed>{

    	@Override
    	protected void onPreExecute() {
    		showProgress();
    		super.onPreExecute();
    	}
    	@Override
    	protected RssFeed doInBackground(String... params) {

            try {
            	URL url = new URL(params[0]);
    			feed = RssReader.read(url);
    		} catch (SAXException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
            return feed;
    	}
    	
    	@Override
    	protected void onPostExecute(RssFeed feed) {
    		super.onPostExecute(feed);
    		if(feed == null)
    			return;
    		updateFeedList(feed.getRssItems());
    	}
    }
    
    public class ListListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
						
            RssItem item = feed.getRssItems().get(position);
            mCallbacks.onItemSelected(item.getLink());
            
          //  Log.i("URL:", item.getLink());
            
		}
    	
    }
}


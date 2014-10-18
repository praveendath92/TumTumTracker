package in.co.praveenkumar.tumtumtracker.activity;

import in.co.praveenkumar.tumtumtracker.R;
import in.co.praveenkumar.tumtumtracker.adapter.AppNavigationDrawer;
import in.co.praveenkumar.tumtumtracker.dialog.LoadingMessage;
import in.co.praveenkumar.tumtumtracker.helper.Param;
import in.co.praveenkumar.tumtumtracker.task.MapHandler;
import in.co.praveenkumar.tumtumtracker.task.MarkerSync;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

public class TrackerActivity extends AppNavigationDrawer {
	MapHandler mapHandler;
	LoadingMessage loadMessage;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Loading message setup
		loadMessage = new LoadingMessage(this);
		loadMessage.show();

		setContentView(R.layout.activity_tracker);
		setUpDrawer();

		// MapHandler setup
		mapHandler = new MapHandler(getSupportFragmentManager());
		mapHandler.overlayMarkers();

		new AsyncMarkerSync().execute("");
	}

	/**
	 * Network sync thread
	 * 
	 * @author Praveen Kumar Pendyala (praveen@praveenkumar.co.in)
	 */
	private class AsyncMarkerSync extends AsyncTask<String, Integer, Boolean> {

		@Override
		protected Boolean doInBackground(String... url) {
			MarkerSync ms = new MarkerSync();
			return ms.syncMarkers();
		}

		@Override
		protected void onPostExecute(Boolean syncStatus) {
			if (syncStatus) {
				mapHandler.overlayMarkers();
				loadMessage.dismiss();
			}

			// Start next update after some wait.
			Handler myHandler = new Handler();
			myHandler.postDelayed(syncLooper, Param.frequency);
		}

	}

	/**
	 * A runnable to support looping sync task.
	 */
	private Runnable syncLooper = new Runnable() {
		@Override
		public void run() {
			new AsyncMarkerSync().execute("");
		}
	};

}

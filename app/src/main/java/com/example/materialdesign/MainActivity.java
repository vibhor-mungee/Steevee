package com.example.materialdesign;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static long SLEEP_TIME = 2;
	private TextView logo;
	private Intent i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Set Content view
		setContentView(R.layout.activity_main);

		// Logo textview
		logo = (TextView) findViewById(R.id.logo);

		// typeface for logo text
		Typeface type = Typeface.createFromAsset(getAssets(),
				"fonts/remachinescript.ttf");

		// set typeface on logo text
		logo.setTypeface(type);
	
		// Next activity intent
		i = new Intent(this, LoginActivity.class);

		// start animation after SLEEP_TIME * 1000 seconds interval
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				startActivity(i);
			}
		}, SLEEP_TIME * 1000);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}

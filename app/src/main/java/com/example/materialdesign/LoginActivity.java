package com.example.materialdesign;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webservices.LoginRestWebserviceCI;

public class LoginActivity extends Activity {

	private RelativeLayout loginSection;
	private TextView logo;
	private EditText userNameET;
	private EditText passWordET;
	private Button submit;
	private String editTextUsername;
	private String editTextPassword;
	private boolean loginStatus;
	private final float amountToMoveUp = 400;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        // Set content view
		setContentView(R.layout.activity_login);
		logo = (TextView) findViewById(R.id.logo);
		Typeface type = Typeface.createFromAsset(getAssets(),
				"fonts/remachinescript.ttf");
		logo.setTypeface(type);
		loginSection = (RelativeLayout) findViewById(R.id.login_section);
		TranslateAnimation anim = new TranslateAnimation(0, 0, amountToMoveUp,
				0);
		anim.setDuration(1000);

		anim.setAnimationListener(new TranslateAnimation.AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
			loginSection.setVisibility(View.VISIBLE);
			}
		});

		logo.startAnimation(anim);

		userNameET = (EditText) findViewById(R.id.username);
		passWordET = (EditText) findViewById(R.id.password);
		submit = (Button) findViewById(R.id.login);
		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (userNameET.getText().length() != 0
						&& userNameET.getText().toString() != "") {
					if (passWordET.getText().length() != 0
							&& passWordET.getText().toString() != "") {
						editTextUsername = userNameET.getText().toString();
						editTextPassword = passWordET.getText().toString();
						AsyncCallWS task = new AsyncCallWS();
						task.execute();
					}
					// If Password text control is empty
					else {
						Toast.makeText(getBaseContext(),
								"Please enter Password", Toast.LENGTH_SHORT)
								.show();
					}
					// If Username text control is empty
				} else {
					Toast.makeText(getBaseContext(), "Please enter Username",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

	}

	private class AsyncCallWS extends AsyncTask {

		@Override
		protected Object doInBackground(Object... params) {
			loginStatus = LoginRestWebserviceCI.invokeLoginWS(editTextUsername,
					editTextPassword, "login");
			return null;
		}

		@Override
		protected void onPostExecute(Object result) {
			super.onPostExecute(result);
			if (loginStatus) {
				Toast.makeText(getBaseContext(), "Login Successful",
						Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(getBaseContext(), "Login not Successful",
						Toast.LENGTH_LONG).show();
			}

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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

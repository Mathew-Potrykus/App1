package com.example.talktoanotherapp;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.CornerPathEffect;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
		@Override
		public void onActivityResult(ActivityResult result) {
			if (result.getResultCode() == Activity.RESULT_OK) {
				Intent intent = result.getData();
				if (intent != null) {
					String thing1 = intent.getStringExtra("ID");
					Toast.makeText(MainActivity.this, thing1, Toast.LENGTH_SHORT).show();
				}
			}
		}
	});

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		FloatingActionButton fab = findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
				//		.setAction("Action", null).show();
				CallApp();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public void CallApp() {
		Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.companyname.reciever_app");

		// Check if there is an app that can be opened
		if (launchIntent != null) {
			// Add variables
			launchIntent.putExtra("LocationID", "LocationID");
			launchIntent.putExtra("TaskID", "TaskID");
			launchIntent.putExtra("UserID", "UserID");
			launchIntent.putExtra("DeviceID", "DeviceID");
			launchIntent.setFlags(0);
			launchIntent.setType("text/plain");
			launchIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
			mStartForResult.launch(launchIntent);
		}
	}
}

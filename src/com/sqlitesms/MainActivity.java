package com.sqlitesms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	Button btnClickSMS;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	btnClickSMS = (Button) findViewById(R.id.btnClickSMS);
	}

	public void Click(View view) {
		Intent intent = new Intent(getApplicationContext(), StudentActivity.class);
		startActivity(intent);
	}
}

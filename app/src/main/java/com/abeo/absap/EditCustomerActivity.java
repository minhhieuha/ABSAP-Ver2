package com.abeo.absap;

import com.abeo.absap.model.TodayVisit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class EditCustomerActivity extends Activity {

	private EditText textViewCode;
	private EditText textViewName;
	private EditText textViewAddress;
	private TodayVisit mTodayVisitItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_customer);

		this.textViewCode = (EditText) findViewById(R.id.editTextCode);
		this.textViewName = (EditText) findViewById(R.id.editTextName);
		this.textViewAddress = (EditText) findViewById(R.id.editTextAddress);

		if (getIntent() != null
				&& getIntent().getSerializableExtra("TodayVisitItemObj") != null) {

			this.mTodayVisitItem = (TodayVisit) getIntent()
					.getSerializableExtra("TodayVisitItemObj");

			Typeface tf = Typeface.createFromAsset(this.getAssets(),
					"fonts/myanmar3.ttf");
			textViewName.setTypeface(tf);
			textViewAddress.setTypeface(tf);
			this.textViewCode.setText(mTodayVisitItem.getCode());
			this.textViewName.setText(mTodayVisitItem.getName());
			this.textViewAddress.setText(mTodayVisitItem.getAddress());

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.action, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_accept:
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}

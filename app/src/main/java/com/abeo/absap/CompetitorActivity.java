package com.abeo.absap;

import com.abeo.absap.model.MasterItem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class CompetitorActivity extends Activity {
	private EditText mItemCode;
	private EditText mItemName;
	private EditText mDesc;
	private EditText mWareHouse;
	private int No;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_competitor);
		
		if (getIntent() != null) {
			this.No = (int) getIntent().getIntExtra("No", 0);
		}

		this.mItemCode = (EditText) findViewById(R.id.editTextItemCode);
		this.mItemName = (EditText) findViewById(R.id.editTextItemName);
		this.mDesc = (EditText) findViewById(R.id.editTextDesc);
		this.mWareHouse = (EditText) findViewById(R.id.editTextWareHouse);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.action, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
			return true;
		}
		switch (item.getItemId()) {
		case R.id.action_accept:
			MasterItem result = new MasterItem(""+this.No, this.mItemCode.getText()
					.toString(), this.mItemName.getText().toString(),
					this.mWareHouse.getText().toString());

			Intent returnIntent = new Intent();
			returnIntent.putExtra("result", result);
			setResult(RESULT_OK, returnIntent);
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}

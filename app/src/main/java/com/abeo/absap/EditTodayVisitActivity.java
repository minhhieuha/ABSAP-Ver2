package com.abeo.absap;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class EditTodayVisitActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_today_visit);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
}

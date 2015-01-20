package com.abeo.absap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.abeo.absap.adapter.DisplayExpandableListAdapter;
import com.abeo.absap.model.DisplayKPI;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

public class DisplayActivity extends Activity {

	DisplayExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<DisplayKPI>> listDataChild;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		// get the listview
		expListView = (ExpandableListView) findViewById(R.id.lvExp);

		// preparing list data
		prepareListData();

		listAdapter = new DisplayExpandableListAdapter(this, listDataHeader,
				listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);

		// Listview Group click listener
		expListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				return false;
			}
		});

		// Listview Group expanded listener
		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
			}
		});

		// Listview Group collasped listener
		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {

			}
		});

		// Listview on child click listener
		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				return false;
			}
		});
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
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<DisplayKPI>>();

		// Adding child data
		listDataHeader.add("Head & Shoulder");
		listDataHeader.add("Camay");
		listDataHeader.add("Orion");
		listDataHeader.add("Pan leen");

		// Adding child data
		List<DisplayKPI> note3 = new ArrayList<DisplayKPI>();
		note3.add(new DisplayKPI("1", "1", "", "Shelf", "1", "10", "0"));
		note3.add(new DisplayKPI("2", "1", "", "Position", "1", "10", "0"));
		note3.add(new DisplayKPI("3", "1", "", "Product", "1", "10", "0"));
		note3.add(new DisplayKPI("4", "1", "", "Time", "1", "10", "0"));

		List<DisplayKPI> note4 = new ArrayList<DisplayKPI>();
		note4.add(new DisplayKPI("1", "1", "", "Shelf", "1", "10", "0"));
		note4.add(new DisplayKPI("2", "1", "", "Position", "1", "10", "0"));
		note4.add(new DisplayKPI("3", "1", "", "Product", "1", "10", "0"));
		note4.add(new DisplayKPI("4", "1", "", "Time", "1", "10", "0"));

		List<DisplayKPI> note5 = new ArrayList<DisplayKPI>();
		note5.add(new DisplayKPI("1", "1", "", "Shelf", "1", "10", "0"));
		note5.add(new DisplayKPI("2", "1", "", "Position", "1", "10", "0"));
		note5.add(new DisplayKPI("3", "1", "", "Product", "1", "10", "0"));
		note5.add(new DisplayKPI("4", "1", "", "Time", "1", "10", "0"));
		
		List<DisplayKPI> note6 = new ArrayList<DisplayKPI>();
		note6.add(new DisplayKPI("1", "1", "", "Shelf", "1", "10", "0"));
		note6.add(new DisplayKPI("2", "1", "", "Position", "1", "10", "0"));
		note6.add(new DisplayKPI("3", "1", "", "Product", "1", "10", "0"));
		note6.add(new DisplayKPI("4", "1", "", "Time", "1", "10", "0"));

		listDataChild.put(listDataHeader.get(0), note3); // Header, Child data
		listDataChild.put(listDataHeader.get(1), note4);
		listDataChild.put(listDataHeader.get(2), note5);
		listDataChild.put(listDataHeader.get(3), note6);
	}
}

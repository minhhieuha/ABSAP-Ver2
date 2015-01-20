package com.abeo.absap;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.abeo.absap.R;
import com.abeo.absap.adapter.DisplayExpandableListAdapter;
import com.abeo.absap.adapter.InvoiceAdapter;
import com.abeo.absap.adapter.StockCountExpandableListAdapter;
import com.abeo.absap.model.DisplayKPI;
import com.abeo.absap.model.Invoice;
import com.abeo.absap.model.MasterItem;
import com.abeo.absap.model.TodayVisit;
import com.abeo.absap.utils.DatePickerFragment;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

public class StockCountActivity extends Activity   {
	
	StockCountExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<MasterItem>> listDataChild;
	EditText mDate;
	private TextView mCode;
	private TextView mName;
	private TextView mAddress;
	private TodayVisit mTodayVisitItem;
	private View mFooterView;
	List<MasterItem> note4 ;
	private int No;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stock_count);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		// get the listview
			expListView = (ExpandableListView) findViewById(R.id.lvExp);
		//mDate = (EditText) findViewById(R.id.editTextDateTime);

		this.mCode = (TextView) findViewById(R.id.textViewCode);
		this.mName = (TextView) findViewById(R.id.textViewName);
		this.mAddress = (TextView) findViewById(R.id.textViewAddress);
		this.mTodayVisitItem = (TodayVisit) getIntent()
				.getSerializableExtra("TodayVisitItemObj");
		
		Typeface tf = Typeface.createFromAsset(this.getAssets(),
				"fonts/myanmar3.ttf");
		mName.setTypeface(tf);
		mAddress.setTypeface(tf);
		this.mCode.setText(mTodayVisitItem.getCode());
		this.mName.setText(mTodayVisitItem.getName());
		this.mAddress.setText(mTodayVisitItem.getAddress());
		
		// preparing list data
		prepareListData();
		
		/*Spinner spinner = (Spinner) findViewById(R.id.spinner);
	    List<String> list = new ArrayList<String>();
	    list.add("WHS 01");
	    list.add("WHS 02");
	    list.add("WHS 03");
	    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
	            android.R.layout.simple_spinner_item, list);
	    dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
	    spinner.setAdapter(dataAdapter);*/

		listAdapter = new StockCountExpandableListAdapter(this, listDataHeader,
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
		
		mFooterView = ((LayoutInflater) this
				.getSystemService(this.LAYOUT_INFLATER_SERVICE)).inflate(
				R.layout.list_view_add_item_footer, null, false);
		mFooterView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent competitorIntent =  new Intent(getApplicationContext(), CompetitorActivity.class);
				competitorIntent.putExtra("No", No+1);
				startActivityForResult(competitorIntent, 1);
				
			}
		});
		this.expListView.addFooterView(mFooterView);
		
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	    if (requestCode == 1) {
	        if(resultCode == RESULT_OK){
	            Bundle result=  data.getExtras();
	            MasterItem item = (MasterItem) result.getSerializable("result");
	            note4.add(item);
	            listAdapter.notifyDataSetChanged();
	            No +=1;
	        }
	        if (resultCode == RESULT_CANCELED) {
	            //Write your code if there's no result
	        }
	    }
	}//onActivityResult
	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<MasterItem>>();

		// Adding child data
		listDataHeader.add("Pahatama Product");
		listDataHeader.add("Competitor Product");

		// Adding child data
		List<MasterItem> note3 = new ArrayList<MasterItem>();
		note3.add(new MasterItem("1", "0105114001", "Gillette Daisy Women 3 Razors(1x240pcs)", "A"));
		note3.add(new MasterItem("2", "0105114002", "Gillette MACH 3  Turbo 1 Up(1x30pcs)", "A"));
		note3.add(new MasterItem("3", "0105114003", "Gillette Sensor Excel Razor for woman(1x144pcs)", "A"));
		note3.add(new MasterItem("4",  "0106121003", "Head  Shoulder-70ml (Itchy Scalp Care) (1x48Pcs)", "A"));
		note3.add(new MasterItem("5", "0106121004", "Head  Shoulder-70ml (Men Hair Retain) (1x48Pcs)", "A"));
		note3.add(new MasterItem("6",  "0106121005", "Head  Shoulder-70ml (Menthol) (1x48Pcs)", "A"));
		note3.add(new MasterItem("7",  "0106121006", "Head  Shoulder-70ml (Slky Smooth) (1x48Pcs)", "A"));
		
		note3.add(new MasterItem("1", "0105114001", "Gillette Daisy Women 3 Razors(1x240pcs)", "B"));
		note3.add(new MasterItem("2", "0105114002", "Gillette MACH 3  Turbo 1 Up(1x30pcs)", "B"));
		note3.add(new MasterItem("3", "0105114003", "Gillette Sensor Excel Razor for woman(1x144pcs)", "C"));
		note3.add(new MasterItem("4",  "0106121003", "Head  Shoulder-70ml (Itchy Scalp Care) (1x48Pcs)", "C"));
		note3.add(new MasterItem("5", "0106121004", "Head  Shoulder-70ml (Men Hair Retain) (1x48Pcs)", "C"));
		note3.add(new MasterItem("6",  "0106121005", "Head  Shoulder-70ml (Menthol) (1x48Pcs)", "C"));
		note3.add(new MasterItem("7",  "0106121006", "Head  Shoulder-70ml (Slky Smooth) (1x48Pcs)", "C"));
		

		note4 = new ArrayList<MasterItem>();
		/*note4.add(new MasterItem("1", "0105114001", "Gillette Daisy Women 3 Razors(1x240pcs)", "A"));
		note4.add(new MasterItem("2", "0105114002", "Gillette MACH 3  Turbo 1 Up(1x30pcs)", "A"));
		note4.add(new MasterItem("3", "0105114003", "Gillette Sensor Excel Razor for woman (1x144pcs)", "A"));
		note4.add(new MasterItem("4",  "0106121003", "Head  Shoulder-70ml(Itchy Scalp Care) (1x48Pcs)", "A"));
		note4.add(new MasterItem("5", "0106121004", "Head  Shoulder-70ml (Men Hair Retain) (1x48Pcs)", "A"));
		note4.add(new MasterItem("6",  "0106121005", "Head  Shoulder-70ml (Menthol) (1x48Pcs)", "A"));
		note4.add(new MasterItem("7",  "0106121006", "Head  Shoulder-70ml (Slky Smooth) (1x48Pcs)", "A"));
		
		note4.add(new MasterItem("1", "0105114001", "Gillette Daisy Women 3 Razors (1x240pcs)", "B"));
		note4.add(new MasterItem("2", "0105114002", "Gillette MACH 3  Turbo 1 Up (1x30pcs)", "B"));
		note4.add(new MasterItem("3", "0105114003", "Gillette Sensor Excel Razor for woman (1x144pcs)", "C"));
		note4.add(new MasterItem("4",  "0106121003", "Head  Shoulder-70ml (Itchy Scalp Care) (1x48Pcs)", "C"));
		note4.add(new MasterItem("5", "0106121004", "Head  Shoulder-70ml (Men Hair Retain) (1x48Pcs)", "C"));
		note4.add(new MasterItem("6",  "0106121005", "Head  Shoulder-70ml (Menthol) (1x48Pcs)", "C"));
		note4.add(new MasterItem("7",  "0106121006", "Head  Shoulder-70ml (Slky Smooth) (1x48Pcs)", "C"));*/

		

		listDataChild.put(listDataHeader.get(0), note3); // Header, Child data
		listDataChild.put(listDataHeader.get(1), note4);
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
	public void showDatePickerDialog(View v) {
		android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
		DatePickerFragment newFragment = new DatePickerFragment(mDate);
		newFragment.show(ft, "aa");
	}

}

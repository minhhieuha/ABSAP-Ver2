package com.abeo.absap;

import java.util.ArrayList;

import com.abeo.absap.adapter.SalesOrderItemAdapter;
import com.abeo.absap.model.MasterItem;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class VisitProcessTabSecondActivity extends Activity {
	private ListView mListViewSalesOrder;
	private ArrayList<MasterItem> mListItem;
	private SalesOrderItemAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_visit_process_tab_second);
		mListViewSalesOrder = (ListView) findViewById(R.id.listViewData);
		mListItem = new ArrayList<MasterItem>();
		mListItem.add(new MasterItem("1", "INV001", "0105114001", "Gillette Daisy Women 3 Razors(1x240pcs)",5));
		mListItem.add(new MasterItem("2", "INV001", "0105114002", "Gillette MACH 3  Turbo 1 Up(1x30pcs)",10));
		mListItem.add(new MasterItem("3", "INV001", "0105114003", "Gillette Sensor Excel Razor for woman(1x144pcs)",15));
		mListItem.add(new MasterItem("4", "INV002", "0106121003", "Head  Shoulder-70ml(Itchy Scalp Care) (1x48Pcs)",9));
		mListItem.add(new MasterItem("5", "INV002", "0106121004", "Head  Shoulder-70ml(Men Hair Retain) (1x48Pcs)",12));
		mListItem.add(new MasterItem("6", "INV003", "0106121005", "Head  Shoulder-70ml(Menthol) (1x48Pcs)",17));
		mListItem.add(new MasterItem("7", "INV004", "0106121006", "Head  Shoulder-70ml(Slky Smooth) (1x48Pcs)",20));
		mAdapter = new SalesOrderItemAdapter(this, R.layout.master_item_item,
				mListItem);

		mListViewSalesOrder.setAdapter(mAdapter);
	}
}

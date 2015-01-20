package com.abeo.absap;

import java.io.Serializable;
import java.util.ArrayList;

import com.abeo.absap.adapter.SalesOrderItemAdapter;
import com.abeo.absap.model.MasterItem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class VisitProcessTabFirstActivity extends Activity {

	private ListView mListViewSalesOrder;
	private ArrayList<MasterItem> mListItem;
	private SalesOrderItemAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_visit_process_tab_first);
		mListViewSalesOrder = (ListView) findViewById(R.id.listViewData);
		mListItem = new ArrayList<MasterItem>();
		mListItem.add(new MasterItem("1", "INV001",
				"0105114001", "Gillette Daisy Women 3 Razors(1x240pcs)",2000));
		mListItem.add(new MasterItem("2", "INV002",
				"0105114002", "Gillette MACH 3  Turbo 1 Up(1x30pcs)",20000));
		mListItem.add(new MasterItem("3", "INV003",
				"0105114003", "Gillette Sensor Excel Razor for woman(1x144pcs)",50000));
		mListItem.add(new MasterItem("4", "INV004",
				"0106121003", "Head  Shoulder-70ml(Itchy Scalp Care)(1x48Pcs)",60000));
		mListItem.add(new MasterItem("5", "INV005",
				"0106121004", "Head  Shoulder-70ml(Men Hair Retain)(1x48Pcs)",70000));
		mListItem.add(new MasterItem("6", "INV006",
				"0106121005", "Head  Shoulder-70ml(Menthol)(1x48Pcs)",50000));
		mListItem.add(new MasterItem("7", "INV007",
				"0106121006", "Head  Shoulder-70ml(Slky Smooth)(1x48Pcs)",50000));
		mListItem.add(new MasterItem("8", "INV008",
				"0107112002", "Olay White Radiance UV Whitening Cream  50g (1X24)",30000));
		mListItem.add(new MasterItem("9", "INV009",
				"0107112003", "Olay White Radiance Cellucent Cream SPF-24  50g (1X6)",50000));

		mAdapter = new SalesOrderItemAdapter(this, R.layout.master_item_item,
				mListItem);

		mListViewSalesOrder.setAdapter(mAdapter);

		mListViewSalesOrder.setClickable(true);
		mListViewSalesOrder
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int position, long arg3) {

						MasterItem item = (MasterItem) mListViewSalesOrder
								.getItemAtPosition(position);
						Intent editItemIntent = new Intent(
								getApplicationContext(),
								MasterItemDetailActivity.class);
						editItemIntent.putExtra("MasterItemObj",
								(Serializable) item);
						startActivityForResult(editItemIntent, 1);
					}
				});
	}
}

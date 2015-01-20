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

public class VisitProcessTabThirdActivity extends Activity {
	private ListView mListViewSalesOrder;
	private ArrayList<MasterItem> mListItem;
	private SalesOrderItemAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_visit_process_tab_third);
		mListViewSalesOrder = (ListView) findViewById(R.id.listViewData);
		mListItem = new ArrayList<MasterItem>();
		mListItem.add(new MasterItem("1", "010214-11",
				"Olay50gm-Balancing Buy12 Pcs-Get1", ""));
		mListItem.add(new MasterItem("2", "010214-27",
				"MR_Gillette_3% Discount Promotion", ""));
		mListItem.add(new MasterItem("3", "010314-02",
				"MR H & S Gondolla Promotion 10% Off", ""));

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
						//startActivityForResult(editItemIntent, 1);
					}
				});
	}
}

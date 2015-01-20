package com.abeo.absap;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.abeo.absap.adapter.CustomMasterItemAdaper;
import com.abeo.absap.model.MasterItem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class EditItemActivity extends Activity {

	private ListView mListView;
	private MasterItem mMasterItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_master_item);

		getActionBar().setDisplayHomeAsUpEnabled(true);

		mListView = (ListView) findViewById(R.id.listview);
		Format formatter = new SimpleDateFormat("dd/MM/yyyy");
		if (getIntent() != null
				&& getIntent().getSerializableExtra("MasterItemObj") != null) {

			this.mMasterItem = (MasterItem) getIntent().getSerializableExtra(
					"MasterItemObj");
			
			if (this.mMasterItem != null) {

				mListView
						.setAdapter(new CustomMasterItemAdaper(
								this,R.layout.edit_master_item_item,
								new String[][] {
										{ "ItemCode",
												this.mMasterItem.getItemCode() },
										{ "ItemName",
												this.mMasterItem.getItemName() },
										{ "WareHouse",
												this.mMasterItem.getWareHouse() },
										{
												"Delivery Date",
												formatter
														.format(new Date()) },
										{
												"Quantity",
												Double.toString(this.mMasterItem
														.getQuantity()) },
										{
												"Items per Unit",
												this.mMasterItem
														.getItemsPerUnit() },
										{
												"Unit Price",
												Double.toString(this.mMasterItem
														.getPrice()) },
										{
												"Discount%",
												Double.toString(this.mMasterItem
														.getDiscount()) },
									
										{ "Gross Total", "1000,000.00" },
										{ "Total", "2000,000.00" } }));
			}
		} else {
			mListView.setAdapter(new CustomMasterItemAdaper(this,R.layout.edit_master_item_item,
					new String[][] { { "ItemCode", "" }, { "ItemName", "" },
							{ "WareHouse", "" },
							{ "Delivery Date", formatter.format(new Date()) },
							{ "Quantity", "1" }, { "Items per Unit", "CTN" },
							{ "Unit Price", "0.00" }, { "Discount%", "0.00" },
							 { "Gross Total", "0.00" },
							{ "Total", "0.00" } }));
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.edit_master_item, menu);
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
		case R.id.action_scan_barcode:
			Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
			startActivityForResult(intent, 0);
			return true;
		case R.id.action_add:
			Intent masterItemIntent = new Intent(getApplicationContext(),
					MasterItemsListActivity.class);
			startActivity(masterItemIntent);
			return true;
		
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}

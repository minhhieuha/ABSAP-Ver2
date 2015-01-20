package com.abeo.absap;

import java.io.Serializable;
import java.util.ArrayList;

import com.abeo.absap.adapter.SalesOrderItemAdapter;
import com.abeo.absap.model.MasterItem;
import com.abeo.absap.model.SalesOrder;
import com.abeo.absap.model.TodayVisit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class SalesOrderActivity extends Activity {
	private TextView mCode;
	private TextView mName;
	private TextView mAddress;
	private TodayVisit mTodayVisitItem;
	private SalesOrder mSalesOrder;
	private View mFooterView;
	private ListView mListViewSalesOrder;
	private ArrayList<MasterItem> mListItem;
	private SalesOrderItemAdapter mAdapter;
	private EditText mTotal;
	private EditText mTax;
	private EditText mDocumentTotal;

	private static final int REQUEST_CODE_ADD_NEW_ITEM = 0;
	private static final int REQUEST_CODE_EDIT_ITEM = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sales_oder);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		mListViewSalesOrder = (ListView) findViewById(R.id.listViewSalesOrderItem);
		this.mCode = (TextView) findViewById(R.id.textViewCode);
		this.mName = (TextView) findViewById(R.id.textViewName);
		this.mAddress = (TextView) findViewById(R.id.textViewAddress);
		this.mTotal = (EditText) findViewById(R.id.editTextTotal);
		this.mTax = (EditText) findViewById(R.id.editTextTax);
		this.mDocumentTotal = (EditText) findViewById(R.id.editTextDoccumentTotal);
		if (getIntent() != null
				&& getIntent().getSerializableExtra("TodayVisitItemObj") != null) {
			this.mTodayVisitItem = (TodayVisit) getIntent()
					.getSerializableExtra("TodayVisitItemObj");

			

			Typeface tf = Typeface.createFromAsset(this.getAssets(),
					"fonts/myanmar3.ttf");
			mName.setTypeface(tf);
			mAddress.setTypeface(tf);
			this.mCode.setText(mTodayVisitItem.getCode());
			this.mName.setText(mTodayVisitItem.getName());
			this.mAddress.setText(mTodayVisitItem.getAddress());
		}

		if (getIntent() != null
				&& getIntent().getSerializableExtra("SalesOderItemObj") != null) {
			this.mSalesOrder = (SalesOrder) getIntent()
					.getSerializableExtra("SalesOderItemObj");

			Typeface tf = Typeface.createFromAsset(this.getAssets(),
					"fonts/myanmar3.ttf");
			mName.setTypeface(tf);
			mAddress.setTypeface(tf);
			this.mCode.setText(mSalesOrder.getCustomerCode());
			this.mName.setText(mSalesOrder.getCustomerName());
			this.mAddress.setText("အမှတ်(အက်စ်-၂)၊အခန်း(၀၀၁)၊မင်းရဲကျော်စွာဗီလာ၊တာမွေလေးရပ်ကွက်");
			this.setTitle("Delivery Order");
		}
		mListItem = new ArrayList<MasterItem>();
		mListItem
				.add(new MasterItem("1", "0105114001",
						"Gillette Daisy Women 3 Razors(1x240pcs)", "VC02006",
						2, 20000));
		mListItem.add(new MasterItem("2", "0105114002",
				"Gillette MACH 3  Turbo 1 Up(1x30pcs)", "VC02006", 3, 30000));
		mListItem.add(new MasterItem("3", "0105114003",
				"Gillette Sensor Excel Razor for woman (1x144pcs)", "VC02006",
				4, 40000));
		mListItem.add(new MasterItem("4", "0106121003",
				"Head  Shoulder-70ml(Itchy Scalp Care) (1x48Pcs)", "VC02006",
				1, 10000));
		mListItem.add(new MasterItem("5", "0106121004",
				"Head  Shoulder-70ml(Men Hair Retain) (1x48Pcs)", "VC02006", 7,
				70000));
		mListItem.add(new MasterItem("6", "0106121005",
				"Head  Shoulder-70ml(Menthol) (1x48Pcs)", "VC02006", 3, 30000));
		mListItem.add(new MasterItem("7", "0106121006",
				"Head  Shoulder-70ml(Slky Smooth)(1x48Pcs)", "VC02006", 1,
				10000));
		mListItem.add(new MasterItem("8", "0107112002",
				"Olay White Radiance UV Whitening Cream  50g (1X24)",
				"VC02006", 12, 120000));
		mListItem.add(new MasterItem("9", "0107112003",
				"Olay White Radiance Cellucent Cream SPF-24  50g (1X6)",
				"VC02006", 4, 40000));
		mAdapter = new SalesOrderItemAdapter(this, R.layout.master_item_item,
				mListItem);

		mListViewSalesOrder.setAdapter(mAdapter);

		mFooterView = ((LayoutInflater) this
				.getSystemService(this.LAYOUT_INFLATER_SERVICE)).inflate(
				R.layout.list_view_add_item_footer, null, false);
		this.mListViewSalesOrder.addFooterView(mFooterView);

		mListViewSalesOrder.setClickable(true);
		mListViewSalesOrder
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						if (position + 1 == mListViewSalesOrder.getCount()) {
							Intent addNewItemIntend = new Intent(
									getApplicationContext(),
									EditItemActivity.class);

							startActivityForResult(addNewItemIntend,
									REQUEST_CODE_ADD_NEW_ITEM);
						} else {
							MasterItem item = (MasterItem) mListViewSalesOrder
									.getItemAtPosition(position);
							Intent editItemIntent = new Intent(
									getApplicationContext(),
									EditItemActivity.class);
							editItemIntent.putExtra("MasterItemObj",
									(Serializable) item);
							startActivityForResult(editItemIntent,
									REQUEST_CODE_EDIT_ITEM);
						}
					}
				});

		this.mTotal.setText("370,000.00");
		this.mTax.setText("3700");
		this.mDocumentTotal.setText("407,000.00");

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
}

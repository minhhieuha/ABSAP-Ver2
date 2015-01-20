package com.abeo.absap;

import java.io.Serializable;

import com.abeo.absap.model.SalesOrder;
import com.abeo.absap.model.TodayVisit;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class VisitProcessActivity extends TabActivity {

	private TextView mCode;
	private TextView mName;
	private TextView mAddress;
	private TodayVisit mTodayVisitItem;
	public static final int MEDIA_TYPE_IMAGE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_visit_process);

		TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);

		TabSpec tab1 = tabHost.newTabSpec("First Tab");
		TabSpec tab2 = tabHost.newTabSpec("Second Tab");
		TabSpec tab3 = tabHost.newTabSpec("Third tab");
		tab1.setIndicator("Recommended");
		tab1.setContent(new Intent(this, VisitProcessTabFirstActivity.class));

		tab2.setIndicator("Last Invoice");
		tab2.setContent(new Intent(this, VisitProcessTabSecondActivity.class));

		tab3.setIndicator("Promotion");
		tab3.setContent(new Intent(this, VisitProcessTabThirdActivity.class));

		tabHost.addTab(tab1);
		tabHost.addTab(tab2);
		tabHost.addTab(tab3);

		for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
			TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i)
					.findViewById(android.R.id.title);
			tv.setTextSize(10);
		}

		if (getIntent() != null
				&& getIntent().getSerializableExtra("TodayVisitItemObj") != null) {

			this.mTodayVisitItem = (TodayVisit) getIntent()
					.getSerializableExtra("TodayVisitItemObj");

			this.mCode = (TextView) findViewById(R.id.textViewCode);
			this.mName = (TextView) findViewById(R.id.textViewName);
			this.mAddress = (TextView) findViewById(R.id.textViewAddress);

			Typeface tf = Typeface.createFromAsset(this.getAssets(),
					"fonts/myanmar3.ttf");
			mName.setTypeface(tf);
			mAddress.setTypeface(tf);
			this.mCode.setText(mTodayVisitItem.getCode());
			this.mName.setText(mTodayVisitItem.getName());
			this.mAddress.setText(mTodayVisitItem.getAddress());

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.visit_process_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.itemSalesOrder:
			Intent saleOrderIntent = new Intent(getApplicationContext(),
					SalesOrderActivity.class);
			saleOrderIntent.putExtra("TodayVisitItemObj",
					(Serializable) mTodayVisitItem);
			startActivity(saleOrderIntent);
			return true;
		case R.id.itemCashSales:
			Intent cashSalesIntent = new Intent(getApplicationContext(),
					CashSalesActivity.class);
			cashSalesIntent.putExtra("TodayVisitItemObj",
					(Serializable) mTodayVisitItem);
			startActivity(cashSalesIntent);
			return true;
		case R.id.itemTakePhoto:
			Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
			startActivityForResult(intent, 0);
			return true;
		case R.id.itemDisplay:
			Intent displayIntent = new Intent(getApplicationContext(),
					DisplayActivity.class);
			startActivityForResult(displayIntent, 0);
			return true;
		case R.id.itemPayment:
			Intent paymentntent = new Intent(getApplicationContext(),
					PaymentActivity.class);
			paymentntent.putExtra("TodayVisitItemObj",
					(Serializable) mTodayVisitItem);
			startActivity(paymentntent);
			return true;
		case R.id.itemStockCount:
			Intent stockCountIntent = new Intent(getApplicationContext(),
					StockCountActivity.class);
			stockCountIntent.putExtra("TodayVisitItemObj",
					(Serializable) mTodayVisitItem);
			startActivity(stockCountIntent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}

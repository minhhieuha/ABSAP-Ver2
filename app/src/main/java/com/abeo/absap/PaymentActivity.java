package com.abeo.absap;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import com.abeo.absap.R;
import com.abeo.absap.adapter.InvoiceAdapter;
import com.abeo.absap.model.Invoice;
import com.abeo.absap.model.TodayVisit;

import android.R.bool;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentActivity extends Activity implements
		android.widget.CompoundButton.OnCheckedChangeListener {
	private TextView mCode;
	private TextView mDate;
	private TextView mAddress;
	private TodayVisit mTodayVisitItem;
	private View mFooterView;
	private ListView mListViewSalesOrder;
	private ArrayList<Invoice> mListItem;
	private InvoiceAdapter mAdapter;
	private EditText mDocumentTotal;
	private EditText mTotal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_payment);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		if (getIntent() != null) {
			this.mTodayVisitItem = (TodayVisit) getIntent()
					.getSerializableExtra("TodayVisitItemObj");
			mListViewSalesOrder = (ListView) findViewById(R.id.listViewSalesOrderItem);
			this.mCode = (TextView) findViewById(R.id.textViewCode);
			this.mDate = (TextView) findViewById(R.id.textViewName);
			this.mAddress = (TextView) findViewById(R.id.textViewAddress);

			this.mTotal = (EditText) findViewById(R.id.editTextDoccumentTotal);
			this.mDocumentTotal = (EditText) findViewById(R.id.editTextDoccumentTotal);

			Typeface tf = Typeface.createFromAsset(this.getAssets(),
					"fonts/myanmar3.ttf");
			mDate.setTypeface(tf);
			mAddress.setTypeface(tf);
			this.mCode.setText(mTodayVisitItem.getCode());
			this.mDate.setText(mTodayVisitItem.getName());
			this.mAddress.setText(mTodayVisitItem.getAddress());

			mListItem = new ArrayList<Invoice>();
			mListItem.add(new Invoice("1", "INV0105114001", new Date(), 20000,
					false));
			mListItem.add(new Invoice("2", "INV0105114002", new Date(), 30000,
					false));
			mListItem.add(new Invoice("3", "INV0105114003", new Date(), 40000,
					false));
			mListItem.add(new Invoice("4", "INV0106121003", new Date(), 10000,
					false));
			mListItem.add(new Invoice("5", "INV0106121004", new Date(), 70000,
					false));
			mListItem.add(new Invoice("6", "INV0106121005", new Date(), 30000,
					false));
			mListItem.add(new Invoice("7", "INV0106121006", new Date(), 10000,
					false));
			mListItem
					.add(new Invoice("8", "INV0107112002", new Date(), 120000));
			mListItem.add(new Invoice("9", "INV0107112003", new Date(), 40000));
			mAdapter = new InvoiceAdapter(this, R.layout.invoice_item,
					mListItem);

			mListViewSalesOrder.setAdapter(mAdapter);

			mFooterView = ((LayoutInflater) this
					.getSystemService(this.LAYOUT_INFLATER_SERVICE)).inflate(
					R.layout.list_view_add_item_footer, null, false);
			// this.mListViewSalesOrder.addFooterView(mFooterView);

			mListViewSalesOrder.setClickable(true);
			mListViewSalesOrder
					.setOnItemClickListener(new OnItemClickListener() {
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
						}
					});

			this.mDocumentTotal.setText("0.00");
		}
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

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		Toast.makeText(getApplicationContext(), "Checked ", Toast.LENGTH_LONG)
				.show();

	}

	public void calculateTotal() {
		ArrayList<Invoice> stateList2 = mAdapter.invoiceList;

		double totalAmount = 0;
		DecimalFormat formatterAmount = new DecimalFormat("#,###.00");

		for (int i = 0; i < stateList2.size(); i++) {
			Invoice stateList = stateList2.get(i);
			if (stateList.getIsChecked()) {
				totalAmount += stateList.getAmount();
			}
		}
		this.mTotal.setText(formatterAmount.format(totalAmount));
	}
}

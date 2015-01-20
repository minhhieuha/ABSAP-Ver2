package com.abeo.absap.adapter;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.abeo.absap.PaymentActivity;
import com.abeo.absap.R;
import com.abeo.absap.model.Invoice;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class InvoiceAdapter extends ArrayAdapter<Invoice> {

	private Context mContext;
	private int mLayoutResID;
	private List<Invoice> mListData;
	public ArrayList<Invoice> invoiceList;

	public InvoiceAdapter(Context context, int layoutResourceID,
			List<Invoice> dataList) {
		super(context, layoutResourceID, dataList);

		this.mContext = context;
		this.mLayoutResID = layoutResourceID;
		this.mListData = dataList;

		this.invoiceList = new ArrayList<Invoice>();
		this.invoiceList.addAll(dataList);

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return getCustomView(position, convertView, parent);
	}

	public View getCustomView(int position, View convertView, ViewGroup parent) {
		Format formatter = new SimpleDateFormat("dd/MM/yyyy");
		View row = convertView;
		ControlHolder holder;

		if (row == null) {
			LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();

			row = inflater.inflate(mLayoutResID, parent,false);
			holder = new ControlHolder();
			holder.id = (TextView) row.findViewById(R.id.textViewNo);
			holder.code = (TextView) row.findViewById(R.id.textViewInvoiceNo);
			holder.amount = (TextView) row.findViewById(R.id.textViewAmount);
			holder.deliveryDate = (TextView) row
					.findViewById(R.id.textViewInvoiceDate);
			holder.chkInvoice = (CheckBox) row
					.findViewById(R.id.checkBoxInvoice);

			row.setTag(holder);

			holder.chkInvoice.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {

					CheckBox cb = (CheckBox) v;
					Invoice in = (Invoice) cb.getTag();

					if (in != null) {
						in.setIsChecked(cb.isChecked());

						PaymentActivity pm = (PaymentActivity) mContext;
						pm.calculateTotal();
					}

				}
			});

		} else {
			holder = (ControlHolder) row.getTag();

		}

		DecimalFormat formatterAmount = new DecimalFormat("#,###.00");

		final Invoice invoice = mListData.get(position);

		holder.code.setText(invoice.getInvoiceNo());

		holder.deliveryDate.setText(formatter.format(invoice
				.getInvoiceDate()));

		if (invoice.getStatus() == "C") {
			holder.chkInvoice.setVisibility(View.GONE);
		}
		holder.id.setText(invoice.getId());
		holder.amount.setText(formatterAmount.format(invoice.getAmount()));
		holder.chkInvoice.setChecked(invoice.getIsChecked());
		holder.chkInvoice.setTag(invoice);
		return row;

	}

	private class ControlHolder {
		TextView id, code, status, deliveryDate, amount;
		CheckBox chkInvoice;
	}

}

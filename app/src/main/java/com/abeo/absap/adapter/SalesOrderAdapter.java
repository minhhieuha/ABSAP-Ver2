package com.abeo.absap.adapter;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

import com.abeo.absap.R;
import com.abeo.absap.model.SalesOrder;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SalesOrderAdapter extends ArrayAdapter<SalesOrder> {

	private Context mContext;
	private int mLayoutResID;
	private List<SalesOrder> mListData;

	public SalesOrderAdapter(Context context, int layoutResourceID,
			List<SalesOrder> dataList) {
		super(context, layoutResourceID, dataList);

		this.mContext = context;
		this.mLayoutResID = layoutResourceID;
		this.mListData = dataList;

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

			row = inflater.inflate(mLayoutResID, parent, false);
			holder = new ControlHolder();
			holder.id = (TextView) row.findViewById(R.id.textViewVisitNo);
			holder.code = (TextView) row.findViewById(R.id.textViewCode);
			holder.name = (TextView) row.findViewById(R.id.textViewName);
			holder.status = (TextView) row.findViewById(R.id.textViewStatus);
			holder.deliveryDate = (TextView) row
					.findViewById(R.id.textViewDeliveryDate);

			Typeface tf = Typeface.createFromAsset(mContext.getAssets(),
					"fonts/myanmar3.ttf");
			holder.name.setTypeface(tf);

			row.setTag(holder);
		} else {
			holder = (ControlHolder) row.getTag();

		}

		SalesOrder salesOrder = mListData.get(position);

		holder.code.setText(salesOrder.getCustomerCode());
		holder.name.setText(salesOrder.getCustomerName());
		holder.status.setText(salesOrder.getStatus());
		holder.deliveryDate.setText(formatter.format(salesOrder
				.getDeliveryDate()));
		holder.id.setText(salesOrder.getId());
		

		return row;

	}

	private static class ControlHolder {
		TextView id, code, name, status, deliveryDate;
	}

}

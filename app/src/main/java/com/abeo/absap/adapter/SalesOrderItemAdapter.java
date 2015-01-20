package com.abeo.absap.adapter;

import java.text.DecimalFormat;
import java.util.List;

import com.abeo.absap.R;
import com.abeo.absap.model.MasterItem;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SalesOrderItemAdapter extends ArrayAdapter<MasterItem> {

	private Context mContext;
	private int mLayoutResID;
	private List<MasterItem> mListData;

	public SalesOrderItemAdapter(Context context, int layoutResourceID,
			List<MasterItem> dataList) {
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

		View row = convertView;
		ControlHolder holder;

		if (row == null) {
			LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();

			row = inflater.inflate(mLayoutResID, parent, false);
			holder = new ControlHolder();
			holder.id = (TextView) row.findViewById(R.id.textViewNo);
			holder.itemCode = (TextView) row
					.findViewById(R.id.textViewItemCode);
			holder.itemName = (TextView) row
					.findViewById(R.id.textViewItemName);
			holder.wareHouse = (TextView) row
					.findViewById(R.id.textViewWareHouse);
			holder.quantity = (TextView) row
					.findViewById(R.id.textViewQuantity);
			holder.price = (TextView) row.findViewById(R.id.textViewPrice);

			Typeface tf = Typeface.createFromAsset(mContext.getAssets(),
					"fonts/myanmar3.ttf");
			// holder.itemCode.setTypeface(tf);
			// holder.itemName.setTypeface(tf);
			// holder.wareHouse.setTypeface(tf);

			row.setTag(holder);
		} else {
			holder = (ControlHolder) row.getTag();

		}

		MasterItem item = mListData.get(position);
		holder.id.setText(item.getId());
		
		holder.itemName.setText(item.getItemName());
		holder.wareHouse.setText(item.getWareHouse());
		DecimalFormat formatter = new DecimalFormat("#,###.00");
		
		if(item.getQuantity()!=0){
			holder.quantity.setText(formatter.format(item.getQuantity()));
		}
		else
		{
			if(item.getStockCount()!=0 )
			{
				holder.quantity.setText(formatter.format(item.getStockCount()));
			}
			else
			{
				holder.quantity.setVisibility(View.GONE);
			}
		}
		if(item.getPrice()!=0){
		holder.price.setText(formatter.format(item.getPrice()));
		}
		else
		{
			holder.price.setVisibility(View.GONE);
		}
		holder.itemCode.setText(item.getItemCode());
		
		return row;

	}

	private static class ControlHolder {
		TextView id, itemCode, itemName, wareHouse, quantity, price;
	}

}

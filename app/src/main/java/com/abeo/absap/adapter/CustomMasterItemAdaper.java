package com.abeo.absap.adapter;

import com.abeo.absap.R;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class CustomMasterItemAdaper extends BaseAdapter {
	private Context mContext;
	private String[][] data;
	private int layoutResID;
	private static LayoutInflater inflater = null;

	public CustomMasterItemAdaper(Context context, int layoutResID,
			String[][] data) {
		// TODO Auto-generated constructor stub
		this.mContext = context;
		this.data = data;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.layoutResID = layoutResID;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View vi = convertView;
		if (layoutResID == R.layout.edit_master_item_item) {
			if (vi == null)
				vi = inflater.inflate(R.layout.edit_master_item_item, null);
			TextView key = (TextView) vi.findViewById(R.id.textViewKey);
			EditText value = (EditText) vi.findViewById(R.id.editTextValue);
			key.setText(data[position][0]);
			value.setText(data[position][1]);
		} else {
			
			if (vi == null)
				vi = inflater.inflate(R.layout.message_list_item, null);
			TextView key = (TextView) vi.findViewById(R.id.textViewNo);
			TextView value = (TextView) vi.findViewById(R.id.textViewName);
			
			Typeface tf = Typeface.createFromAsset(mContext.getAssets(),"fonts/myanmar3.ttf");
			key.setTypeface(tf);
			value.setTypeface(tf);
			
			key.setText(data[position][0]);
			value.setText(data[position][1]);

		}
		return vi;
	}
}

package com.abeo.absap.adapter;

import java.util.List;

import com.abeo.absap.R;
import com.abeo.absap.model.TodayVisit;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TodayVisitAdapter extends ArrayAdapter<TodayVisit> {

	private Context mContext;
	private int mLayoutResID;
	private List<TodayVisit> mListData;

	public TodayVisitAdapter(Context context, int layoutResourceID,
			List<TodayVisit> dataList) {
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
			holder.id = (TextView) row.findViewById(R.id.textViewVisitNo);
			holder.code = (TextView) row.findViewById(R.id.textViewVisitCode);
			holder.name = (TextView) row.findViewById(R.id.textViewVisitName);
			holder.address = (TextView) row.findViewById(R.id.textViewVisitAddress);
			holder.status = (TextView) row.findViewById(R.id.textViewVisitStatus);
			
			

			row.setTag(holder);
		} else {
			holder = (ControlHolder) row.getTag();

		}

		TodayVisit todayVitisItem = mListData.get(position);

		holder.code.setText(todayVitisItem.getCode());
		holder.name.setText(todayVitisItem.getName());
		holder.address.setText(todayVitisItem.getAddress());
		holder.id.setText(todayVitisItem.getId());
		if(todayVitisItem.getStatus()=="C")
		{
			holder.status.setBackgroundColor(Color.DKGRAY);
		}
		else 
		{
			holder.status.setBackgroundColor(Color.BLUE);
		}
		Typeface tf = Typeface.createFromAsset(mContext.getAssets(),"fonts/mm3.ttf");
		holder.name.setTypeface(tf);
		holder.address.setTypeface(tf);
		return row;

	}

	private static class ControlHolder {
		TextView id,code, name, address,status;
	}

}

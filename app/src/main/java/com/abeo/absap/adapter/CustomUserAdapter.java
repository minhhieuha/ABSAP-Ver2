package com.abeo.absap.adapter;

import java.util.List;

import com.abeo.absap.R;
import com.abeo.absap.model.User;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomUserAdapter extends ArrayAdapter<User> {

	public CustomUserAdapter(Context context, int layoutResourceID,
			int textViewResourceId, List<User> dataList) {
		super(context, layoutResourceID, textViewResourceId, dataList);

		this.context = context;
		this.layoutResID = layoutResourceID;
		this.userData = dataList;

	}

	Context context;
	int layoutResID;
	List<User> userData;

	public CustomUserAdapter(Context context, int layoutResourceID,
			List<User> dataList) {
		super(context, layoutResourceID, dataList);

		this.context = context;
		this.layoutResID = layoutResourceID;
		this.userData = dataList;

	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return getCustomView(position, convertView, parent);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return getCustomView(position, convertView, parent);
	}

	public View getCustomView(int position, View convertView, ViewGroup parent) {

		View row = convertView;
		ControlHolder holder;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();

			row = inflater.inflate(layoutResID, parent, false);
			holder = new ControlHolder();

			holder.userImage = (ImageView) row.findViewById(R.id.left_pic);
			holder.name = (TextView) row.findViewById(R.id.text_main_name);
			holder.email = (TextView) row.findViewById(R.id.sub_text_email);

			row.setTag(holder);
		} else {
			holder = (ControlHolder) row.getTag();

		}

		User userItem = userData.get(position);

		holder.userImage.setImageDrawable(row.getResources().getDrawable(
				userItem.getDrawableResID()));
		holder.name.setText(userItem.getName());
		holder.email.setText(userItem.getUserName());

		return row;

	}

	private static class ControlHolder {
		ImageView userImage;
		TextView name, email;
	}
}

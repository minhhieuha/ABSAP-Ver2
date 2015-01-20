package com.abeo.absap.adapter;

import java.util.ArrayList;
import java.util.List;

import com.abeo.absap.R;
import com.abeo.absap.model.DrawerItem;
import com.abeo.absap.model.User;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CustomDrawerAdapter extends ArrayAdapter<DrawerItem> {

	Context context;
	List<DrawerItem> drawerItemList;
	int layoutResID;

	public CustomDrawerAdapter(Context context, int layoutResourceID,
			List<DrawerItem> listItems) {
		super(context, layoutResourceID, listItems);
		this.context = context;
		this.drawerItemList = listItems;
		this.layoutResID = layoutResourceID;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		DrawerItemHolder drawerHolder;
		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			drawerHolder = new DrawerItemHolder();
			view = inflater.inflate(layoutResID, parent, false);
			drawerHolder.itemName = (TextView) view.findViewById(R.id.title);
			drawerHolder.icon = (ImageView) view.findViewById(R.id.icon);
			drawerHolder.title = (TextView) view.findViewById(R.id.drawerTitle);
			drawerHolder.itemLayout = (RelativeLayout) view
					.findViewById(R.id.itemLayout);
			drawerHolder.headerLayout = (LinearLayout) view
					.findViewById(R.id.headerLayout);
			drawerHolder.count = (TextView) view.findViewById(R.id.counter);
			drawerHolder.toplayout = (LinearLayout) view
					.findViewById(R.id.toplayout);
			view.setTag(drawerHolder);

		} else {
			drawerHolder = (DrawerItemHolder) view.getTag();

		}

		DrawerItem dItem = (DrawerItem) this.drawerItemList.get(position);

		if (dItem.isTop()) {
			drawerHolder.headerLayout.setVisibility(LinearLayout.INVISIBLE);
			drawerHolder.itemLayout.setVisibility(LinearLayout.INVISIBLE);
			drawerHolder.toplayout.setVisibility(LinearLayout.VISIBLE);
			List<User> userList = new ArrayList<User>();

			SharedPreferences prfs = this.getContext().getSharedPreferences("AUTHENTICATION_FILE_NAME", Context.MODE_PRIVATE);
		     String userName = prfs.getString("Authentication_Id", "");
		     String fullName = prfs.getString("Authentication_FullName", "");
		     
			userList.add(new User(R.drawable.ic_abeo_logo, userName,
					fullName));

		TextView textViewFullName =  (TextView)	drawerHolder.toplayout.findViewById(R.id.textViewFullName);
		TextView textViewUserName =  (TextView)	drawerHolder.toplayout.findViewById(R.id.textViewUserName);
		
		textViewFullName.setText(userList.get(0).getName());
		textViewUserName.setText(userList.get(0).getUserName());

		} else if (dItem.getTitle() != null) {
			drawerHolder.headerLayout.setVisibility(LinearLayout.VISIBLE);
			drawerHolder.itemLayout.setVisibility(LinearLayout.INVISIBLE);
			drawerHolder.toplayout.setVisibility(LinearLayout.INVISIBLE);
			drawerHolder.title.setText(dItem.getTitle());
			if (drawerItemList.get(position).getCounterVisibility()) {
				drawerHolder.count.setText(drawerItemList.get(position)
						.getCount());
			} else {
				drawerHolder.count.setVisibility(View.GONE);
			}
			Log.d("Getview", "Passed4");
		} else {
			drawerHolder.headerLayout.setVisibility(LinearLayout.INVISIBLE);
			drawerHolder.itemLayout.setVisibility(LinearLayout.VISIBLE);
			drawerHolder.toplayout.setVisibility(LinearLayout.INVISIBLE);

			drawerHolder.icon.setImageDrawable(view.getResources().getDrawable(
					dItem.getImgResID()));
			drawerHolder.itemName.setText(dItem.getItemName());

			if (drawerItemList.get(position).getCounterVisibility()) {
				drawerHolder.count.setText(drawerItemList.get(position)
						.getCount());
			} else {
				drawerHolder.count.setVisibility(View.GONE);
			}
			Log.d("Getview", "Passed5");
		}
		return view;
	}

	private static class DrawerItemHolder {
		TextView itemName, title, count;
		ImageView icon;
		LinearLayout headerLayout, toplayout;
		RelativeLayout itemLayout;
	}
}
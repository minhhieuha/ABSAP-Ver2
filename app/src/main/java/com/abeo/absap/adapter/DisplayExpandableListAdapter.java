package com.abeo.absap.adapter;

import java.util.HashMap;
import java.util.List;

import com.abeo.absap.R;
import com.abeo.absap.model.DisplayKPI;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class DisplayExpandableListAdapter extends BaseExpandableListAdapter {

	private Context _context;
	private List<String> _listDataHeader; // header titles
	// child data in format of header title, child title
	private HashMap<String, List<DisplayKPI>> _listDataChild;

	public DisplayExpandableListAdapter(Context context, List<String> listDataHeader,
			HashMap<String, List<DisplayKPI>> listChildData) {
		this._context = context;
		this._listDataHeader = listDataHeader;
		this._listDataChild = listChildData;
	}

	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		return this._listDataChild.get(this._listDataHeader.get(groupPosition))
				.get(childPosititon);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		final DisplayKPI childText = (DisplayKPI) getChild(groupPosition, childPosition);

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.display_list_item, null);
		}

		TextView textViewNo = (TextView) convertView
				.findViewById(R.id.textViewNo);
		TextView textViewName = (TextView) convertView
				.findViewById(R.id.textViewName);
		TextView textViewFromPoint = (TextView) convertView
				.findViewById(R.id.textViewFromPoint);
		TextView textViewToPoint = (TextView) convertView
				.findViewById(R.id.textViewToPoint);
		EditText editTextPoint = (EditText) convertView
				.findViewById(R.id.editTextPoint);
		
		textViewNo.setText(childText.getId());
		textViewName.setText(childText.getItemName());
		textViewFromPoint.setText(childText.getFromPoint());
		textViewToPoint.setText(childText.getToPoint());
		editTextPoint.setText(childText.getPoint());
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this._listDataChild.get(this._listDataHeader.get(groupPosition))
				.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this._listDataHeader.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this._listDataHeader.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String headerTitle = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.display_list_group, null);
		}

		TextView lblListHeader = (TextView) convertView
				.findViewById(R.id.lblListHeader);
		lblListHeader.setTypeface(null, Typeface.BOLD);
		lblListHeader.setText(headerTitle);

		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}

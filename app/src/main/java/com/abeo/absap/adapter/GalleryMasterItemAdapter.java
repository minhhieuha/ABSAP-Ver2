package com.abeo.absap.adapter;

import java.util.ArrayList;

import com.abeo.absap.MasterItemDetailActivity.ImageItem;
import com.abeo.absap.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;

@SuppressWarnings("deprecation")
public class GalleryMasterItemAdapter extends BaseAdapter {
	Context context;
	ArrayList<ImageItem> _arrayImageItem;
	int mGalleryItemBackground;

	public GalleryMasterItemAdapter(Context c) {
		context = c;
		_arrayImageItem = new ArrayList<ImageItem>();
		TypedArray a = c.obtainStyledAttributes(R.styleable.GalleryTheme);
		mGalleryItemBackground = a.getResourceId(
				R.styleable.GalleryTheme_android_galleryItemBackground, 0);
		a.recycle();
	}

	public void addImageItem(ImageItem item) {
		_arrayImageItem.add(item);
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return _arrayImageItem.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return _arrayImageItem.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView imageView;
		imageView = new ImageView(context);

		LinearLayout layout = new LinearLayout(context);
		layout.setLayoutParams(new Gallery.LayoutParams(410, 410));
		layout.setGravity(Gravity.CENTER);

		imageView.setLayoutParams(new Gallery.LayoutParams(400, 400));
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		imageView.setImageBitmap(_arrayImageItem.get(position).getImage());
		imageView.setBackgroundResource(mGalleryItemBackground);
		layout.addView(imageView);
		return layout;
	}

}
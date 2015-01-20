package com.abeo.absap;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.abeo.absap.adapter.GalleryMasterItemAdapter;
import com.abeo.absap.model.MasterItem;
import com.abeo.absap.model.TodayVisit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MasterItemDetailActivity extends Activity {

	@SuppressWarnings("deprecation")
	private Gallery mGallery;
	ArrayList<ImageItem> arrayImageItem;
	GalleryMasterItemAdapter myAdapter;
	private EditText mItemCode;
	private EditText mItemName;
	private EditText mItemPrice;
	private EditText mItemPrincipal;
	private EditText mItemCategory;
	private ImageView[] imageView;
	private Button mBtnPlayVideo;

	public class ImageItem {

		Bitmap bitmapImage;

		ImageItem(int imageId) {
			// To simplify, we use a default image here
			bitmapImage = BitmapFactory.decodeResource(
					MasterItemDetailActivity.this.getResources(), imageId);
		}

		public Bitmap getImage() {
			return bitmapImage;
		}

		public Bitmap setImage(int imageId) {
			this.bitmapImage = BitmapFactory.decodeResource(
					MasterItemDetailActivity.this.getResources(), imageId);
			return this.bitmapImage;
		}

	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_master_item_detail);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		this.mGallery = (Gallery) findViewById(R.id.gallery_master_item);
		this.mItemCode = (EditText) findViewById(R.id.editTextItemCode);
		this.mItemName = (EditText) findViewById(R.id.editTextItemName);
		this.mItemPrice = (EditText) findViewById(R.id.editTextPrice);
		this.mItemPrincipal = (EditText) findViewById(R.id.editTextPricipal);
		this.mItemCategory = (EditText) findViewById(R.id.editTextCategory);

		this.mBtnPlayVideo = (Button) findViewById(R.id.btnplayvideo);

		mBtnPlayVideo.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {

				

				Intent intent = new Intent(getApplicationContext(), VideoPlayerActivity.class);
				startActivity(intent);

			}
		});

		DecimalFormat formatter = new DecimalFormat("#,###.00");
		if (getIntent() != null) {
			MasterItem item = (MasterItem) getIntent().getSerializableExtra(
					"MasterItemObj");
			this.mItemCode.setText(item.getItemName());
			this.mItemName.setText(item.getWareHouse());
			if(item.getPrice()>0){
			this.mItemPrice.setText(formatter.format(item.getPrice()));
			}else
			{
				this.mItemPrice.setText(formatter.format(item.getStockCount()));
			}
			this.mItemPrincipal.setText("P&G");
			this.mItemCategory.setText("Head & Shoulders");
		}

		this.myAdapter = new GalleryMasterItemAdapter(this);

		this.myAdapter.addImageItem(new ImageItem(R.drawable.head1shoulders));
		this.myAdapter.addImageItem(new ImageItem(R.drawable.head_shoulders));
		this.mGallery.setAdapter(myAdapter);
		this.myAdapter.addImageItem(new ImageItem(R.drawable.headandshoulders));
		this.myAdapter.addImageItem(new ImageItem(R.drawable.headandshoulders));
		this.myAdapter.addImageItem(new ImageItem(R.drawable.headshoulders));
		this.myAdapter.addImageItem(new ImageItem(R.drawable.hs));
		this.myAdapter.addImageItem(new ImageItem(R.drawable.hs1));
		this.myAdapter.notifyDataSetChanged();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

			}

		});
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
}

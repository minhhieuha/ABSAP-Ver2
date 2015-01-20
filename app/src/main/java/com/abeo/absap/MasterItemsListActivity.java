package com.abeo.absap;

import java.util.ArrayList;
import java.util.List;

import com.abeo.absap.adapter.SalesOrderItemAdapter;
import com.abeo.absap.model.MasterItem;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AbsListView.OnScrollListener;

public class MasterItemsListActivity extends Activity {
	private List<MasterItem> mDataList;
	private SalesOrderItemAdapter mAdapter;
	private ListView mMasterItemList;
	private LinearLayout mLayoutLoading;
	private Boolean mLoadingMore = false;
	private View mFooterView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_master_items_list);
		
		mLayoutLoading = (LinearLayout) 
				findViewById(R.id.progressbar_view);

		mMasterItemList = (ListView) 
				findViewById(R.id.listViewItemData);
		mDataList = new ArrayList<MasterItem>();
		mAdapter = new SalesOrderItemAdapter(this,
				R.layout.master_item_item, mDataList);

		mMasterItemList.setAdapter(mAdapter);

		mFooterView = ((LayoutInflater) this.getSystemService(
				this.LAYOUT_INFLATER_SERVICE)).inflate(
				R.layout.list_view_footer, null, false);
		this.mMasterItemList.addFooterView(mFooterView);
		this.mMasterItemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) {
				mMasterItemList.setItemChecked(position, true);
				
			}
		});

		mMasterItemList.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {

				// what is the bottom iten that is visible
				int lastInScreen = firstVisibleItem + visibleItemCount;
				// is the bottom item visible & not loading more already ? Load
				// more !
				if ((lastInScreen == totalItemCount) && !(mLoadingMore)) {
					Thread thread = new Thread(null, loadMoreListItems);
					thread.start();
				}
			}
		});
		mMasterItemList.setClickable(true);
		new Task().execute();
	}
	private Runnable loadMoreListItems = new Runnable() {
		@Override
		public void run() {
			mLoadingMore = true;
			mDataList = new ArrayList<MasterItem>();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			runOnUiThread(returnRes);
		}
	};
	private Runnable returnRes = new Runnable() {
		@Override
		public void run() {
			if (mDataList != null && mDataList.size() > 0) {

				for (int i = 0; i < mDataList.size(); i++)
					mAdapter.add(mDataList.get(i));
			}
			mAdapter.notifyDataSetChanged();
			mLoadingMore = false;
		}
	};

	public class Task extends AsyncTask<String, Integer, Boolean> {
		@Override
		protected void onPreExecute() {
			mLayoutLoading.setVisibility(View.VISIBLE);
			mMasterItemList.setVisibility(View.GONE);

			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Boolean result) {
			mLayoutLoading.setVisibility(View.GONE);
			mMasterItemList.setVisibility(View.VISIBLE);
			mAdapter.notifyDataSetChanged();

			super.onPostExecute(result);
		}

		@Override
		protected Boolean doInBackground(String... params) {
			try {
				Thread.sleep(1000);
				mDataList.add(new MasterItem("1", "INV001", "0105114001", "Gillette Daisy Women 3 Razors(1x240pcs)",5));
				mDataList.add(new MasterItem("2", "INV001", "0105114002", "Gillette MACH 3  Turbo 1 Up(1x30pcs)",10));
				mDataList.add(new MasterItem("3", "INV001", "0105114003", "Gillette Sensor Excel Razor for woman(1x144pcs)",15));
				mDataList.add(new MasterItem("4", "INV002", "0106121003", "Head  Shoulder-70ml(Itchy Scalp Care) (1x48Pcs)",9));
				mDataList.add(new MasterItem("5", "INV002", "0106121004", "Head  Shoulder-70ml(Men Hair Retain) (1x48Pcs)",12));
				mDataList.add(new MasterItem("6", "INV003", "0106121005", "Head  Shoulder-70ml(Menthol) (1x48Pcs)",17));
				mDataList.add(new MasterItem("7", "INV004", "0106121006", "Head  Shoulder-70ml(Slky Smooth) (1x48Pcs)",20));
				
				mDataList.add(new MasterItem("8", "INV001", "0105114001", "Gillette Daisy Women 3 Razors(1x240pcs)",5));
				mDataList.add(new MasterItem("9", "INV001", "0105114002", "Gillette MACH 3  Turbo 1 Up(1x30pcs)",10));
				mDataList.add(new MasterItem("10", "INV001", "0105114003", "Gillette Sensor Excel Razor for woman(1x144pcs)",15));
				mDataList.add(new MasterItem("11", "INV002", "0106121003", "Head  Shoulder-70ml(Itchy Scalp Care) (1x48Pcs)",9));
				mDataList.add(new MasterItem("12", "INV002", "0106121004", "Head  Shoulder-70ml(Men Hair Retain) (1x48Pcs)",12));
				mDataList.add(new MasterItem("13", "INV003", "0106121005", "Head  Shoulder-70ml(Menthol) (1x48Pcs)",17));
				mDataList.add(new MasterItem("14", "INV004", "0106121006", "Head  Shoulder-70ml(Slky Smooth) (1x48Pcs)",20));
				
				mDataList.add(new MasterItem("15", "INV001", "0105114001", "Gillette Daisy Women 3 Razors(1x240pcs)",5));
				mDataList.add(new MasterItem("16", "INV001", "0105114002", "Gillette MACH 3  Turbo 1 Up(1x30pcs)",10));
				mDataList.add(new MasterItem("17", "INV001", "0105114003", "Gillette Sensor Excel Razor for woman(1x144pcs)",15));
				mDataList.add(new MasterItem("18", "INV002", "0106121003", "Head  Shoulder-70ml(Itchy Scalp Care) (1x48Pcs)",9));
				mDataList.add(new MasterItem("19", "INV002", "0106121004", "Head  Shoulder-70ml(Men Hair Retain) (1x48Pcs)",12));
				mDataList.add(new MasterItem("20", "INV003", "0106121005", "Head  Shoulder-70ml(Menthol) (1x48Pcs)",17));
				mDataList.add(new MasterItem("21", "INV004", "0106121006", "Head  Shoulder-70ml(Slky Smooth) (1x48Pcs)",20));
				
				mDataList.add(new MasterItem("22", "INV001", "0105114001", "Gillette Daisy Women 3 Razors(1x240pcs)",5));
				mDataList.add(new MasterItem("23", "INV001", "0105114002", "Gillette MACH 3  Turbo 1 Up(1x30pcs)",10));
				mDataList.add(new MasterItem("24", "INV001", "0105114003", "Gillette Sensor Excel Razor for woman(1x144pcs)",15));
				mDataList.add(new MasterItem("25", "INV002", "0106121003", "Head  Shoulder-70ml(Itchy Scalp Care) (1x48Pcs)",9));
				mDataList.add(new MasterItem("26", "INV002", "0106121004", "Head  Shoulder-70ml(Men Hair Retain) (1x48Pcs)",12));
				mDataList.add(new MasterItem("27", "INV003", "0106121005", "Head  Shoulder-70ml(Menthol) (1x48Pcs)",17));
				mDataList.add(new MasterItem("28", "INV004", "0106121006", "Head  Shoulder-70ml(Slky Smooth) (1x48Pcs)",20));
				
				

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.item_list_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.itemAccept:
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}

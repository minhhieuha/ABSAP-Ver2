package com.abeo.absap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.abeo.absap.adapter.SalesOrderItemAdapter;
import com.abeo.absap.model.MasterItem;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AbsListView.OnScrollListener;

public class MasterItemFragment extends Fragment {
	private List<MasterItem> mDataList;
	private SalesOrderItemAdapter mAdapter;
	private ListView mTodayVisitList;
	private LinearLayout mLayoutLoading;
	private Boolean mLoadingMore = false;
	private View mFooterView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_master_item,
				container, false);
		mLayoutLoading = (LinearLayout) rootView
				.findViewById(R.id.progressbar_view);

		mTodayVisitList = (ListView) rootView
				.findViewById(R.id.listViewItemData);
		mDataList = new ArrayList<MasterItem>();
		mAdapter = new SalesOrderItemAdapter(getActivity(),
				R.layout.master_item_item, mDataList);

		mTodayVisitList.setAdapter(mAdapter);

		mFooterView = ((LayoutInflater) this.getActivity().getSystemService(
				getActivity().LAYOUT_INFLATER_SERVICE)).inflate(
				R.layout.list_view_footer, null, false);
		this.mTodayVisitList.addFooterView(mFooterView);

		mTodayVisitList.setOnScrollListener(new OnScrollListener() {

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
		mTodayVisitList.setClickable(true);
		mTodayVisitList
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int position, long arg3) {

						MasterItem item = (MasterItem) mTodayVisitList
								.getItemAtPosition(position);
						Intent editItemIntent = new Intent(getActivity(),
								MasterItemDetailActivity.class);
						editItemIntent.putExtra("MasterItemObj",
								(Serializable) item);
						startActivityForResult(editItemIntent, 1);
					}
				});
		new Task().execute();
		return rootView;
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
			// Done! now continue on the UI thread
			getActivity().runOnUiThread(returnRes);
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
			mTodayVisitList.setVisibility(View.GONE);

			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Boolean result) {
			mLayoutLoading.setVisibility(View.GONE);
			mTodayVisitList.setVisibility(View.VISIBLE);
			mAdapter.notifyDataSetChanged();

			super.onPostExecute(result);
		}

		@Override
		protected Boolean doInBackground(String... params) {
			try {
				Thread.sleep(1000);
				mDataList.add(new MasterItem("1", "INV001", "0105114001",
						"Gillette Daisy Women 3 Razors(1x240pcs)", 5,20000));
				mDataList.add(new MasterItem("2", "INV001", "0105114002",
						"Gillette MACH 3  Turbo 1 Up(1x30pcs)", 10,30000));
				mDataList.add(new MasterItem("3", "INV001", "0105114003",
						"Gillette Sensor Excel Razor for woman(1x144pcs)", 15,4000));
				mDataList.add(new MasterItem("4", "INV002", "0106121003",
						"Head  Shoulder-70ml(Itchy Scalp Care) (1x48Pcs)", 9,50000));
				mDataList.add(new MasterItem("5", "INV002", "0106121004",
						"Head  Shoulder-70ml(Men Hair Retain) (1x48Pcs)", 12,120000));
				mDataList.add(new MasterItem("6", "INV003", "0106121005",
						"Head  Shoulder-70ml(Menthol) (1x48Pcs)", 17,30000));
				mDataList.add(new MasterItem("7", "INV004", "0106121006",
						"Head  Shoulder-70ml(Slky Smooth) (1x48Pcs)", 20,4000));

				mDataList.add(new MasterItem("8", "INV001", "0105114001",
						"Gillette Daisy Women 3 Razors(1x240pcs)", 5,30000));
				mDataList.add(new MasterItem("9", "INV001", "0105114002",
						"Gillette MACH 3  Turbo 1 Up(1x30pcs)", 10,20000));
				mDataList.add(new MasterItem("10", "INV001", "0105114003",
						"Gillette Sensor Excel Razor for woman(1x144pcs)", 15,40000));
				mDataList.add(new MasterItem("11", "INV002", "0106121003",
						"Head  Shoulder-70ml(Itchy Scalp Care) (1x48Pcs)", 9,50000));
				mDataList.add(new MasterItem("12", "INV002", "0106121004",
						"Head  Shoulder-70ml(Men Hair Retain) (1x48Pcs)", 12,60000));
				mDataList.add(new MasterItem("13", "INV003", "0106121005",
						"Head  Shoulder-70ml(Menthol) (1x48Pcs)", 17,10000));
				mDataList.add(new MasterItem("14", "INV004", "0106121006",
						"Head  Shoulder-70ml(Slky Smooth) (1x48Pcs)", 20,9000));

				mDataList.add(new MasterItem("15", "INV001", "0105114001",
						"Gillette Daisy Women 3 Razors(1x240pcs)", 5,540000));
				mDataList.add(new MasterItem("16", "INV001", "0105114002",
						"Gillette MACH 3  Turbo 1 Up(1x30pcs)", 10,340000));
				mDataList.add(new MasterItem("17", "INV001", "0105114003",
						"Gillette Sensor Excel Razor for woman(1x144pcs)", 15,120000));
				mDataList.add(new MasterItem("18", "INV002", "0106121003",
						"Head  Shoulder-70ml(Itchy Scalp Care) (1x48Pcs)", 9,320000));
				mDataList.add(new MasterItem("19", "INV002", "0106121004",
						"Head  Shoulder-70ml(Men Hair Retain) (1x48Pcs)", 12,12000));
				mDataList.add(new MasterItem("20", "INV003", "0106121005",
						"Head  Shoulder-70ml(Menthol) (1x48Pcs)", 17,340000));
				mDataList.add(new MasterItem("21", "INV004", "0106121006",
						"Head  Shoulder-70ml(Slky Smooth) (1x48Pcs)", 20,340000));

				mDataList.add(new MasterItem("22", "INV001", "0105114001",
						"Gillette Daisy Women 3 Razors(1x240pcs)", 5,340000));
				mDataList.add(new MasterItem("23", "INV001", "0105114002",
						"Gillette MACH 3  Turbo 1 Up(1x30pcs)", 10,540000));
				mDataList.add(new MasterItem("24", "INV001", "0105114003",
						"Gillette Sensor Excel Razor for woman(1x144pcs)", 15,345000));
				mDataList.add(new MasterItem("25", "INV002", "0106121003",
						"Head  Shoulder-70ml(Itchy Scalp Care) (1x48Pcs)", 9,34000));
				mDataList.add(new MasterItem("26", "INV002", "0106121004",
						"Head  Shoulder-70ml(Men Hair Retain) (1x48Pcs)", 12,340000));
				mDataList.add(new MasterItem("27", "INV003", "0106121005",
						"Head  Shoulder-70ml(Menthol) (1x48Pcs)", 17,120000));
				mDataList.add(new MasterItem("28", "INV004", "0106121006",
						"Head  Shoulder-70ml(Slky Smooth) (1x48Pcs)", 20,430000));

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}
}

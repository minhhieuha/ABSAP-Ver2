package com.abeo.absap;



import java.util.ArrayList;
import java.util.List;

import com.abeo.absap.adapter.SalesOrderItemAdapter;
import com.abeo.absap.model.MasterItem;

import android.app.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AbsListView.OnScrollListener;


public class InventoryFragment extends Fragment {

	private List<MasterItem> mDataList;
	private SalesOrderItemAdapter mAdapter;
	private ListView mTodayVisitList;
	private LinearLayout mLayoutLoading;
	private Boolean mLoadingMore = false;
	private View mFooterView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_inventory,
				container, false);
		mLayoutLoading = (LinearLayout) rootView
				.findViewById(R.id.progressbar_view);

		mTodayVisitList = (ListView) rootView
				.findViewById(R.id.lstInventory);
		mDataList = new ArrayList<MasterItem>();
		mAdapter = new SalesOrderItemAdapter(getActivity(),
				R.layout.inventory_report_item, mDataList);

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
				mDataList.add(new MasterItem("1", "", "0105114001",
						"Gillette Daisy Women 3 Razors(1x240pcs)", 5));
				mDataList.add(new MasterItem("2", "", "0105114002",
						"Gillette MACH 3  Turbo 1 Up(1x30pcs)", 10));
				mDataList.add(new MasterItem("3", "", "0105114003",
						"Gillette Sensor Excel Razor for woman(1x144pcs)", 15));
				mDataList.add(new MasterItem("4", "", "0106121003",
						"Head  Shoulder-70ml(Itchy Scalp Care) (1x48Pcs)", 9));
				mDataList.add(new MasterItem("5", "", "0106121004",
						"Head  Shoulder-70ml(Men Hair Retain) (1x48Pcs)", 12));
				mDataList.add(new MasterItem("6", "", "0106121005",
						"Head  Shoulder-70ml(Menthol) (1x48Pcs)", 17));
				mDataList.add(new MasterItem("7", "", "0106121006",
						"Head  Shoulder-70ml(Slky Smooth) (1x48Pcs)", 20));

				mDataList.add(new MasterItem("8", "", "0105114001",
						"Gillette Daisy Women 3 Razors(1x240pcs)", 5));
				mDataList.add(new MasterItem("9", "", "0105114002",
						"Gillette MACH 3  Turbo 1 Up(1x30pcs)", 10));
				mDataList.add(new MasterItem("10", "", "0105114003",
						"Gillette Sensor Excel Razor for woman(1x144pcs)", 15));
				mDataList.add(new MasterItem("11", "", "0106121003",
						"Head  Shoulder-70ml(Itchy Scalp Care) (1x48Pcs)", 9));
				mDataList.add(new MasterItem("12", "", "0106121004",
						"Head  Shoulder-70ml(Men Hair Retain) (1x48Pcs)", 12));
				mDataList.add(new MasterItem("13", "", "0106121005",
						"Head  Shoulder-70ml(Menthol) (1x48Pcs)", 17));
				mDataList.add(new MasterItem("14", "", "0106121006",
						"Head  Shoulder-70ml(Slky Smooth) (1x48Pcs)", 20));

				mDataList.add(new MasterItem("15", "", "0105114001",
						"Gillette Daisy Women 3 Razors(1x240pcs)", 5));
				mDataList.add(new MasterItem("16", "", "0105114002",
						"Gillette MACH 3  Turbo 1 Up(1x30pcs)", 10));
				mDataList.add(new MasterItem("17", "", "0105114003",
						"Gillette Sensor Excel Razor for woman(1x144pcs)", 15));
				mDataList.add(new MasterItem("18", "", "0106121003",
						"Head  Shoulder-70ml(Itchy Scalp Care) (1x48Pcs)", 9));
				mDataList.add(new MasterItem("19", "", "0106121004",
						"Head  Shoulder-70ml(Men Hair Retain) (1x48Pcs)", 12));
				mDataList.add(new MasterItem("20", "", "0106121005",
						"Head  Shoulder-70ml(Menthol) (1x48Pcs)", 17));
				mDataList.add(new MasterItem("21", "", "0106121006",
						"Head  Shoulder-70ml(Slky Smooth) (1x48Pcs)", 20));

				mDataList.add(new MasterItem("22", "", "0105114001",
						"Gillette Daisy Women 3 Razors(1x240pcs)", 5));
				mDataList.add(new MasterItem("23", "", "0105114002",
						"Gillette MACH 3  Turbo 1 Up(1x30pcs)", 10));
				mDataList.add(new MasterItem("24", "", "0105114003",
						"Gillette Sensor Excel Razor for woman(1x144pcs)", 15));
				mDataList.add(new MasterItem("25", "", "0106121003",
						"Head  Shoulder-70ml(Itchy Scalp Care) (1x48Pcs)", 9));
				mDataList.add(new MasterItem("26", "", "0106121004",
						"Head  Shoulder-70ml(Men Hair Retain) (1x48Pcs)", 12));
				mDataList.add(new MasterItem("27", "", "0106121005",
						"Head  Shoulder-70ml(Menthol) (1x48Pcs)", 17));
				mDataList.add(new MasterItem("28", "", "0106121006",
						"Head  Shoulder-70ml(Slky Smooth) (1x48Pcs)", 20));

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}
}
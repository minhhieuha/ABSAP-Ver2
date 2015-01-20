package com.abeo.absap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.abeo.absap.CustomerFragment.Task;
import com.abeo.absap.adapter.SalesOrderAdapter;
import com.abeo.absap.adapter.TodayVisitAdapter;
import com.abeo.absap.model.SalesOrder;
import com.abeo.absap.model.TodayVisit;

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

public class SalesOrderFragment extends Fragment {

	private List<SalesOrder> mDataList;
	private SalesOrderAdapter mAdapter;
	private ListView mTodayVisitList;
	private LinearLayout mLayoutLoading;
	private Boolean mLoadingMore = false;
	private View mFooterView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_sales_order,
				container, false);

		mLayoutLoading = (LinearLayout) rootView
				.findViewById(R.id.progressbar_view);

		mTodayVisitList = (ListView) rootView
				.findViewById(R.id.listViewTodayVisit);
		mDataList = new ArrayList<SalesOrder>();
		mAdapter = new SalesOrderAdapter(this.getActivity(),
				R.layout.sales_order_item, mDataList);

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

				SalesOrder salesOrder = (SalesOrder) mTodayVisitList
						.getItemAtPosition(position);

				Intent todayVisitProcessIntent = new Intent(
						getActivity().getApplicationContext(),
						SalesOrderActivity.class);
				todayVisitProcessIntent.putExtra("SalesOderItemObj",
						(Serializable) salesOrder);
				startActivity(todayVisitProcessIntent);
			}
		});
		new Task().execute();
		return rootView;
	}

	private Runnable loadMoreListItems = new Runnable() {
		@Override
		public void run() {
			mLoadingMore = true;
			mDataList = new ArrayList<SalesOrder>();
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
				mDataList.add(new SalesOrder("1", "C00102635", "​မေဧကရီ",
						"Open", new Date()));
				mDataList.add(new SalesOrder("2", "C00102656", "ဆန်းသစ်လွင်",
						"Open", new Date()));
				mDataList.add(new SalesOrder("3", "C00104722", "စိုးစံ",
						"Open", new Date()));
				mDataList.add(new SalesOrder("4", "C00104650", "လင်း", "Open",
						new Date()));
				mDataList.add(new SalesOrder("5", "C00111382", "Green Luck",
						"Open", new Date()));
				mDataList.add(new SalesOrder("6", "C00107286", "ကိုကျော်ထွန်း",
						"Open", new Date()));
				mDataList.add(new SalesOrder("7", "C00102155", "မယုစတိုး",
						"Open", new Date()));
				mDataList.add(new SalesOrder("8", "C00103261", "​ဒေါ်ရင်အုန်း",
						"Open", new Date()));
				mDataList.add(new SalesOrder("9", "C00103260", "ဦးစိန်လင်း",
						"Open", new Date()));
				mDataList.add(new SalesOrder("10", "C00103612", "မနီ", "Open",
						new Date()));
				mDataList.add(new SalesOrder("11", "C00102854", "ဖိုးဝ",
						"Open", new Date()));
				mDataList.add(new SalesOrder("12", "C00103347", "ပြည့်စုံ",
						"Open", new Date()));
				mDataList.add(new SalesOrder("13", "C00106617", "Shwe Dollar",
						"Open", new Date()));
				mDataList.add(new SalesOrder("14", "C00105197", "Saw Store",
						"Open", new Date()));
				mDataList.add(new SalesOrder("15", "C00106619", "​နေလ", "Open",
						new Date()));
				mDataList.add(new SalesOrder("16", "C00103259", "ကိုဇေယျာ",
						"Open", new Date()));
				mDataList.add(new SalesOrder("17", "C00102181",
						"​ရွှေထိပ်တန်း", "Open", new Date()));
				mDataList.add(new SalesOrder("18", "C00102852",
						"နန့်ရှိုင်းစတိုး", "Open", new Date()));
				mDataList.add(new SalesOrder("19", "C00103137", "ဒေါ်သန်းသန်း",
						"Open", new Date()));
				mDataList.add(new SalesOrder("20", "C00103581", "7-Days",
						"Open", new Date()));

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}
}

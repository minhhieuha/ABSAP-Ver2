package com.abeo.absap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.abeo.absap.adapter.InvoiceAdapter;
import com.abeo.absap.model.Invoice;

import android.app.Fragment;
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

public class PaymentFragment extends Fragment {

	private List<Invoice> mDataList;
	private InvoiceAdapter mAdapter;
	private ListView mMasterItemList;
	private LinearLayout mLayoutLoading;
	private Boolean mLoadingMore = false;
	private View mFooterView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_payment, container,
				false);
		
		mLayoutLoading = (LinearLayout) 
				rootView.findViewById(R.id.progressbar_view);

		mMasterItemList = (ListView) 
				rootView.findViewById(R.id.listViewItemDataInvoice);
		mDataList = new ArrayList<Invoice>();
		mAdapter = new InvoiceAdapter(getActivity(),
				R.layout.invoice_item, mDataList);

		mMasterItemList.setAdapter(mAdapter);

		mFooterView = ((LayoutInflater) getActivity().getSystemService(
				getActivity().LAYOUT_INFLATER_SERVICE)).inflate(
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
		return rootView;
	}
	private Runnable loadMoreListItems = new Runnable() {
		@Override
		public void run() {
			mLoadingMore = true;
			mDataList = new ArrayList<Invoice>();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
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
				mDataList.add(new Invoice("1", "INV0105114001", new Date(),20000,"C"));
				mDataList.add(new Invoice("2", "INV0105114002", new Date(),30000,"C"));
				mDataList.add(new Invoice("3", "INV0105114003", new Date(),40000,"C"));
				mDataList.add(new Invoice("4", "INV0106121003", new Date(),10000,"C"));
				mDataList.add(new Invoice("5", "INV0106121004",new Date(),70000,"C"));
				mDataList.add(new Invoice("6", "INV0106121005", new Date(),30000,"C"));
				mDataList.add(new Invoice("7", "INV0106121006",  new Date(),10000,"C"));
				mDataList.add(new Invoice("8", "INV0107112002",   new Date(),120000,"C"));
				mDataList.add(new Invoice("9", "INV0107112003",  new Date(),40000,"C"));
				
				mDataList.add(new Invoice("10", "INV0105114001", new Date(),20000,"C"));
				mDataList.add(new Invoice("11", "INV0105114002", new Date(),30000,"C"));
				mDataList.add(new Invoice("12", "INV0105114003", new Date(),40000,"C"));
				mDataList.add(new Invoice("13", "INV0106121003", new Date(),10000,"C"));
				mDataList.add(new Invoice("14", "INV0106121004",new Date(),70000,"C"));
				mDataList.add(new Invoice("15", "INV0106121005", new Date(),30000,"C"));
				mDataList.add(new Invoice("16", "INV0106121006",  new Date(),10000,"C"));
				mDataList.add(new Invoice("17", "INV0107112002",   new Date(),120000,"C"));
				mDataList.add(new Invoice("18", "INV0107112003",  new Date(),40000,"C"));
				
				mDataList.add(new Invoice("19", "INV0105114001", new Date(),20000,"C"));
				mDataList.add(new Invoice("20", "INV0105114002", new Date(),30000,"C"));
				mDataList.add(new Invoice("21", "INV0105114003", new Date(),40000,"C"));
				mDataList.add(new Invoice("22", "INV0106121003", new Date(),10000,"C"));
				mDataList.add(new Invoice("23", "INV0106121004",new Date(),70000,"C"));
				mDataList.add(new Invoice("24", "INV0106121005", new Date(),30000,"C"));
				mDataList.add(new Invoice("25", "INV0106121006",  new Date(),10000,"C"));
				mDataList.add(new Invoice("26", "INV0107112002",   new Date(),120000,"C"));
				mDataList.add(new Invoice("27", "INV0107112003",  new Date(),40000,"C"));

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}
}
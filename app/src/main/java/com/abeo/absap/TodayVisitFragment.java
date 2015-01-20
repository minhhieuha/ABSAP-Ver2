package com.abeo.absap;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.abeo.absap.adapter.TodayVisitAdapter;
import com.abeo.absap.model.TodayVisit;

import android.app.Fragment;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class TodayVisitFragment extends Fragment {

	private List<TodayVisit> mDataList;
	private TodayVisitAdapter mAdapter;
	private ListView mTodayVisitList;
	private LinearLayout mLayoutLoading;
	private Boolean mLoadingMore = false;
	private View mFooterView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		         
		View rootView = inflater.inflate(R.layout.fragment_today_visit,
				container, false);
		mLayoutLoading = (LinearLayout) rootView
				.findViewById(R.id.progressbar_view);

		mTodayVisitList = (ListView) rootView
				.findViewById(R.id.listViewTodayVisit);
		mDataList = new ArrayList<TodayVisit>();
		mAdapter = new TodayVisitAdapter(this.getActivity(),
				R.layout.today_visit_item, mDataList);

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

						TodayVisit todayVisitItem = (TodayVisit) mTodayVisitList
								.getItemAtPosition(position);

						Intent todayVisitProcessIntent = new Intent(
								getActivity().getApplicationContext(),
								VisitProcessActivity.class);
						todayVisitProcessIntent.putExtra("TodayVisitItemObj",
								(Serializable) todayVisitItem);
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
			mDataList = new ArrayList<TodayVisit>();
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
				mDataList.add(new TodayVisit("1", "C00102635",
						"​အမှတ် (၁၀၀) ၊ ငုဝါလမ်း ၊ ဌာနရပ်ကွက်",
						"အမှတ် (၁၀၀) ၊ ငုဝါလမ်း ၊ ဌာနရပ်ကွက်", "C"));
				mDataList
						.add(new TodayVisit(
								"1",
								"C00102635",
								"​အမှတ်(၇၉)၊ဆင်မင်းလမ်း၊ဆင်မင်းရပ်ကွက်",
								"အမှတ် - ၅၉ ၊ မာလာမြိုင်(၂)လမ်းထိပ် ၊ (၁၆)ရပ်ကွက် ၊ လှိုင်",
								"C"));
				mDataList
						.add(new TodayVisit(
								"2",
								"C00102656",
								"ဆန်းသစ်လွင်",
								"အမှတ်(အက်စ်-၂)၊အခန်း(၀၀၂)၊မင်းရဲကျော်စွာဗီလာ၊တာမွေလေးရပ်ကွက်",
								"C"));
				mDataList.add(new TodayVisit("3", "C00104722", "စိုးစံ",
						"အမှတ်(၂၀၄)၊ကျိုက္ကဆံလမ်း၊ကျောက်မြောင်း", "C"));
				mDataList
						.add(new TodayVisit(
								"4",
								"C00104650",
								"လင်း",
								"အမှတ်(၁/၀၀၂)၊ကျိုက်က္ကဆံအိမ်ယာ၊ကျိုက်က္ကဆံလမ်း၊တာမွေကြီး(ခ)ရပ်ကွက်",
								"C"));
				mDataList.add(new TodayVisit("5", "C00111382", "Green Luck",
						"အမှတ်(၁၀၁)၊မြင်းပြိုင်ကွင်းလမ်း၊(အရှေ့)"));
				mDataList.add(new TodayVisit("6", "C00107286", "ကိုကျော်ထွန်း",
						"အမှတ် (၂၆) ၊ (၁၆၇) ၊တာမွေ ၊ ရန်ကုန်၊ မြန်မာ"));
				mDataList.add(new TodayVisit("7", "C00102155", "မယုစတိုး",
						"အမှတ်(၄၁)၊(၁၆၇)လမ်း၊တာမွေကြီး(ခ)ရပ်ကွက်"));
				mDataList.add(new TodayVisit("8", "C00103261", "​ဒေါ်ရင်အုန်း",
						"အမှတ်(၂၃)၊(၁၆၄)လမ်း၊တာမွေကြီး(ခ)ရပ်ကွက်"));
				mDataList.add(new TodayVisit("9", "C00103260", "ဦးစိန်လင်း",
						"အမှတ်(၂၉/အေ)၊(၁၆၄)လမ်း၊တာမွေကြီး(ခ)ရပ်ကွက်"));
				mDataList.add(new TodayVisit("10", "C00103612", "မနီ",
						"အမှတ် (၁၂၂) ၊ ဗညားဒလလမ်း ၊ ရန်ကုန်၊ မြန်မာ"));
				mDataList.add(new TodayVisit("11", "C00102854", "ဖိုးဝ",
						"အမှတ် (၃၁၁) ၊ဗညားဒလလမ်း"));
				mDataList.add(new TodayVisit("12", "C00103347", "ပြည့်စုံ",
						"အမှတ်(၄၅၀)၊ဗညားဒလလမ်း၊ဗျိုင်းရေအိုးစဉ်ရပ်ကွက်"));
				mDataList.add(new TodayVisit("13", "C00106617", "Shwe Dollar",
						"အမှတ်(၄၅၂)၊ဗညားဒလလမ်း၊ဗျိုင်းရေအိုးစဉ်ရပ်ကွက်"));
				mDataList.add(new TodayVisit("14", "C00105197", "Saw Store",
						"​အောင်တေဇလမ်း၊ တာမွေ ၊ ရန်ကုန်၊ မြန်မာ"));
				mDataList
						.add(new TodayVisit("15", "C00106619", "​နေလ",
								"အမှတ် (၅၂) ၊ဗျိုင်းရေအိုးစဉ်လမ်း ၊ တာမွေ ၊ ရန်ကုန်၊ မြန်မာ"));
				mDataList.add(new TodayVisit("16", "C00103259", "ကိုဇေယျာ",
						"အမှတ် (၁၇) ၊(၁၅၅) လမ်း ၊ တာမွေ ၊ ရန်ကုန်၊ မြန်မာ"));
				mDataList.add(new TodayVisit("17", "C00102181",
						"​ရွှေထိပ်တန်း",
						"အမှတ်(၂၈၂)၊ကျိုက္ကဆံလမ်း၊ကျားကွက်သစ်ရပ်ကွက်"));
				mDataList
						.add(new TodayVisit("18", "C00102852",
								"နန့်ရှိုင်းစတိုး",
								"အမှတ်(၂၀၇)၊ကျိုက္ကဆံလမ်း၊ဗျိုင်းရေအိုးစဉ်ရွာမရပ်ကွက်"));
				mDataList.add(new TodayVisit("19", "C00103137", "ဒေါ်သန်းသန်း",
						"အမှတ်-၁၆၁၊ကျိုက္ကဆံလမ်း၊တာမွေ"));
				mDataList.add(new TodayVisit("20", "C00103581", "7-Days",
						"အမှတ်(၇၂)၊ကျိုက္ကဆံလမ်း၊အိုးကုန်းရပ်ကွက်"));
				mDataList.add(new TodayVisit("21", "C00102191", "နွယ်",
						"အရိုးကုန်းလမ်း ၊ တာမွေ ၊ ရန်ကုန်၊ မြန်မာ"));
				mDataList.add(new TodayVisit("22", "C00106298", "ဇာဇာစတိုး",
						"အမှတ်(၂၃/၂၅)၊မအူကုန်းလမ်း၊မအူကုန်းရပ်ကွက်"));
				mDataList.add(new TodayVisit("23", "C00100039", "​အောင်စေ",
						"အမှတ် (၄၇) ၊(၁၃၅) လမ်း"));
				mDataList
						.add(new TodayVisit("24", "C00102855", "ဦးစု",
								"အမှတ် (၃) ၊(၁၄၅) ၊ အရိုးကုန်းလမ်း ၊ တာမွေ ၊ ရန်ကုန်၊ မြန်မာ"));
				mDataList.add(new TodayVisit("25", "C00102178",
						"စိန်အောင်မင်း",
						"(၁၄၅) ၊ အရိုးကုန်းလမ်း ၊ တာမွေ ၊ ရန်ကုန်၊ မြန်မာ"));
				mDataList.add(new TodayVisit("26", "C00103584",
						"မသန်းသန်း၀င်း",
						"အမှတ်(၁၁)၊(၁၄၆)လမ်း၊အရိုးကုန်းရပ်ကွက်"));
				mDataList.add(new TodayVisit("27", "C00106620", "မမျိုး",
						"အမှတ် ၁၆) ၊(၁၄၅) လမ်း ၊ တာမွေ ၊ ရန်ကုန်၊ မြန်မာ"));
				mDataList.add(new TodayVisit("28", "C00104711", "အိတုန်",
						"အမှတ် (၆၈) ၊စက်မှုရတနာလမ်းမ"));
				mDataList
						.add(new TodayVisit("29", "C00108427", "ဖြိုး",
								"အမှတ်(၁၁၈၅)၊လက်ဝဲမင်းထင်လမ်း၊မေတ္တာညွန့်ရပ်ကွက်၊တာမွေ"));
				mDataList.add(new TodayVisit("30", "C00105196", "​မဖြူဖြူ",
						"အမှတ်(၄၆၄/၃)အခန်း(၀၀၁)၊လယ်ယာရွှေမြေလမ်း"));
				mDataList.add(new TodayVisit("31", "C00103249", "​ရွှေဘုန်း",
						"အမှတ် (၅၃/၅၄) ၊ လယ်ယာရွှေမြေလမ်း"));
				mDataList.add(new TodayVisit("32", "C00104974", "စုစုစတိုး",
						"အမှတ် ( ၅၈၈) ၊ အနန္တဂုဏ်ရည်လမ်း"));
				mDataList
						.add(new TodayVisit("33", "C00104975",
								"ညွန့်ညွန့်စတိုး",
								"အမှတ် (၆၂၂) ၊ အနန္တမေတ္တာညွန့်လမ်း"));
				mDataList.add(new TodayVisit("34", "C00108005", "​ရွှေသဇင်",
						"သမိုင်း ဗဟန်းလမ်း ၊ အသောကကားမှတ်တိုင်"));
				mDataList.add(new TodayVisit("35", "C00102123", "34 Store",
						"အမှတ်(၃၄)၊သမိန်ဘရမ်းလမ်း၊မလွှကုန်း၊ဌေးကြွယ်ရပ်ကွက်"));
				mDataList.add(new TodayVisit("36", "C00103263", "​ဒေါ်ရင်",
						"အမှတ် ( ၃၅) ၊ သမိန်ဗရမ်းလမ်း"));
				mDataList.add(new TodayVisit("37", "C00107985", "Queen",
						"အမှတ် ( ၂၀) ၊ အသောကလမ်း"));
				mDataList.add(new TodayVisit("38", "C00102205", "မမေသွင်",
						"အမှတ် ( ၁၈) ၊ အသောကလမ်း"));
				mDataList.add(new TodayVisit("39", "C00104153", "ခိုင်စိုးဟန်",
						"အမှတ် ( ၁၂) ၊ အသောကလမ်း"));
				mDataList.add(new TodayVisit("40", "C00104709", "အာသောက",
						"အမှတ် ( ၁၂၄) ၊ အသောကလမ်း"));
				mDataList.add(new TodayVisit("41", "C00105886",
						"ပစ်တိုင်းထောင်",
						"အမှတ်(၃၅)(ဘီ)၊သတိပဌာန်လမ်း၊ကျောက်မြောင်း"));
				mDataList.add(new TodayVisit("42", "C00107160",
						"​ဌေးကြွယ်စတိုး",
						"အမှတ်(၄၇)၊သမာသိဒ္ဓိလမ်း၊မလွှကုန်း ဌေးကြွယ်ရပ်ကွက်"));
				mDataList.add(new TodayVisit("43", "C00106429", "ထိုက်သန်ဝင်း",
						"ကျားကွက်သစ်လမ်း"));
				mDataList.add(new TodayVisit("44", "C00108632", "Space",
						"အမှတ်(၁၂)၊မြေညီထပ်၊ကျားကွက်သစ်လမ်း၊တာမွေ"));
				mDataList.add(new TodayVisit("45", "C00102624", "စန္ဒာပြည့်",
						"အမှတ် ( ၇) ၊ ကျားကွက်သစ်လမ်း"));
				mDataList.add(new TodayVisit("46", "C00103233", "မိသားစု",
						"အမှတ်(၃၀)၊ကျောက်ရေတွင်းလမ်း၊ကျောက်မြောင်း"));
				mDataList.add(new TodayVisit("47", "C00103243", "Zin & Phyu",
						"အမှတ်(၈၅)၊ကျိုက္ကဆံလမ်း၊ကျောက်မြောင်းကြီးရပ်ကွက်"));
				mDataList.add(new TodayVisit("48", "C00102164", "New House",
						"အမှတ် ( ၂၅) ၊ ဓမ္မဝိဟာရလမ်း"));
				mDataList.add(new TodayVisit("49", "C00111073", "Heaven",
						"အမှတ် (၈၀) ၊ ကျောက်မြောင်းလမ်း"));
				mDataList
						.add(new TodayVisit("50", "C00102171", "ပြုံးပန်းတရာ",
								"အမှတ်(၄၁)၊ကျောက်မြောင်းလမ်း၊ကျောက်မြောင်းကြီးရပ်ကွက်"));
				mDataList.add(new TodayVisit("51", "C00102142",
						"Japan Ma (Mart)", "ကျောက်မြောင်းလမ်း"));
				mDataList.add(new TodayVisit("52", "C00103222", "​ဒေါ်အေးရီ",
						"အမှတ်(၂၉/အေ)သီတာလမ်း၊ကျောက်မြောင်းကြီးရပ်ကွက်"));
				mDataList.add(new TodayVisit("53", "C00102126",
						"​အောင်မင်္ဂလာ", "အမှတ် (၉၁) ၊ အောင်မင်္ဂလာလမ်း"));
				mDataList.add(new TodayVisit("54", "C00103225", "ဖြိုးမင်းအေး",
						"အမှတ် (၇၃) ၊ အောင်မင်္ဂလာလမ်း"));
				mDataList.add(new TodayVisit("55", "C00103224", "ဦးတင်ဦး",
						"အမှတ် (၆၂) ၊ အောင်မင်္ဂလာလမ်း"));
				mDataList.add(new TodayVisit("56", "C00103246", "သီတာစိန်",
						"အမှတ်(၄)သီတာလမ်း၊ကျောက်မြောင်းရပ်ကွက်"));
				mDataList.add(new TodayVisit("57", "C00107486", "Fresh & Fair",
						"အမှတ် (၆၅/၇၀) ၊ မြန်မာ့ဂုဏ်ရောင်လမ်း"));
				mDataList.add(new TodayVisit("58", "C00110274",
						"24 Seven Mart",
						"အမှတ်(၅၈)ပုလဲလမ်း၊မြန့်မာဂုဏ်ရောင်အိမ်ရာ၊တာမွေ"));
				mDataList.add(new TodayVisit("59", "C00104745", "လင်းမင်း",
						"အမှတ်(၁၃၆)၊ပန်းဆိုးတန်းအပေါ်လမ်း၊(၇)ရပ်ကွက်"));
				mDataList.add(new TodayVisit("60", "C00101760", "ဦးတင်ဖေ",
						"အမှတ် (၄၆) ၊ ရေကျော်လမ်း"));
				mDataList.add(new TodayVisit("61", "C00106149", "Aquarise",
						"အမှတ်(၇၀)(အေ)၊ရေကျော်လမ်း၊"));
				mDataList.add(new TodayVisit("62", "C00103165", "နန်းထိုက်စံ",
						"အမှတ်(၅၉)(အေ)၊ရေကျော်လမ်း၊"));

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}
}

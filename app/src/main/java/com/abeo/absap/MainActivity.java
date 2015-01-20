package com.abeo.absap;

import java.util.ArrayList;
import java.util.List;

//Abeo
import com.abeo.absap.adapter.CustomDrawerAdapter;
import com.abeo.absap.model.DrawerItem;

//Android
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mTitle;

	private String[] navMenuTitles;

	private List<DrawerItem> mDataList;
	private CustomDrawerAdapter mDrawerAdapter;

	private long mLastPressedTime;
	private static final int PERIOD = 2000;

	private static final int HOME_FRAGMENT = 0;
	private static final int TODAY_VISIT_FRAGMENT = 1;
	private static final int NEW_CUSTOMER_FRAGMENT = 2;
	private static final int SALES_ORDER_FRAGMENT = 3;

	private int mFragmentPossition = -1;
	private int mPreviousFragment = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		// Get title
		mTitle = getTitle();

		// Get menu list
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		// Get drawer layout
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

		mDataList = new ArrayList<DrawerItem>();
		// Set top item
		mDataList.add(new DrawerItem(true));
		// Set menu item
		mDataList.add(new DrawerItem(navMenuTitles[0], R.drawable.ic_home));
		mDataList.add(new DrawerItem(navMenuTitles[1],
				R.drawable.ic_communities));
		mDataList.add(new DrawerItem(navMenuTitles[2], R.drawable.ic_pages));
		mDataList.add(new DrawerItem(navMenuTitles[3], R.drawable.ic_people));
		mDataList.add(new DrawerItem(navMenuTitles[4], R.drawable.ic_photos));
		// mDataList
		// .add(new DrawerItem(navMenuTitles[5], R.drawable.ic_whats_hot));
		mDataList.add(new DrawerItem(navMenuTitles[6],
				android.R.drawable.ic_lock_power_off));
		// Set group item
		mDataList.add(new DrawerItem("REPORTING"));
		mDataList.add(new DrawerItem(navMenuTitles[7],
				android.R.drawable.ic_dialog_info));
		mDataList.add(new DrawerItem(navMenuTitles[8],
				android.R.drawable.ic_menu_agenda));
		mDataList.add(new DrawerItem(navMenuTitles[9],
				android.R.drawable.ic_dialog_alert));
		mDataList.add(new DrawerItem(navMenuTitles[10],
				android.R.drawable.ic_lock_lock));

		// Set adapter for listview
		mDrawerAdapter = new CustomDrawerAdapter(this,
				R.layout.drawer_list_item, mDataList);

		mDrawerList.setAdapter(mDrawerAdapter);

		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.app_name, R.string.app_name) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(getTitle());
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			displayView(0);
		}
	}

	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			displayView(position);
		}
	}

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void displayView(int position) {
		Fragment fragment = null;
		mFragmentPossition = position;
		switch (position) {
		case 0:
			fragment = new HomeFragment();
			break;
		case 1:
			fragment = new TodayVisitFragment();
			break;
		case 2:
			fragment = new CustomerFragment();
			break;
		case 3:
			fragment = new SalesOrderFragment();
			break;
		case 4:
			fragment = new MasterItemFragment();
			break;
		case 5:
			fragment = new PaymentFragment();
			break;
		case 6:
			finish();
			Intent intentLogin = new Intent(this.getApplicationContext(),
					LoginActivity.class);
			startActivity(intentLogin);
			break;
		case 7:
			Toast.makeText(this, "You don't have permisson", Toast.LENGTH_LONG)
			.show();
			break;
		case 8:
			Toast.makeText(this, "You don't have permisson", Toast.LENGTH_LONG)
			.show();
			break;
		case 9:
			
			fragment = new TodaySalesFragment();
			break;
		case 10:
			Toast.makeText(this, "You don't have permisson", Toast.LENGTH_LONG)
					.show();
			break;
		case 11:
			fragment = new InventoryFragment();
			break;
		default:
			break;
		}

		if (fragment != null && mPreviousFragment != mFragmentPossition) {

			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();
			fragmentManager.executePendingTransactions();

			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			if (position > 0) {
				setTitle(navMenuTitles[position - 1]);
			} else {
				setTitle("Main");
			}
			mDrawerLayout.closeDrawer(mDrawerList);
			mPreviousFragment = mFragmentPossition;
		} else {
			if (mPreviousFragment == mFragmentPossition) {
				mDrawerLayout.closeDrawer(mDrawerList);
			}
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		// Create the search view
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.options_menu, menu);
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.search)
				.getActionView();
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		switch (item.getItemId()) {
		case R.id.action_settings:
			Toast.makeText(this.getApplicationContext(), "Clicked",
					Toast.LENGTH_LONG).show();
			return true;
		case R.id.action_add:
			if (mFragmentPossition == NEW_CUSTOMER_FRAGMENT) {
				Intent addCustomerIntent = new Intent(getApplicationContext(),
						EditCustomerActivity.class);
				startActivityForResult(addCustomerIntent, 1);
			} else if (mFragmentPossition == SALES_ORDER_FRAGMENT) {
				Toast.makeText(this.getApplicationContext(), "Add Sales",
						Toast.LENGTH_LONG).show();
			} else if (mFragmentPossition == TODAY_VISIT_FRAGMENT) {
				Toast.makeText(this.getApplicationContext(), "Add Today",
						Toast.LENGTH_LONG).show();
			} else if (mFragmentPossition == HOME_FRAGMENT) {
				Toast.makeText(this.getApplicationContext(), "Add Home",
						Toast.LENGTH_LONG).show();
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * Set title
	 */
	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate()
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onConfigurationChanged()
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	/**
	 * Press again to exit.
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			switch (event.getAction()) {
			case KeyEvent.ACTION_DOWN:

				if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
					mDrawerLayout.closeDrawer(mDrawerList);
				} else {
					if (event.getDownTime() - mLastPressedTime < PERIOD) {
						finish();
					} else {
						Toast.makeText(getApplicationContext(),
								"Press again to exit.", Toast.LENGTH_SHORT)
								.show();
						mLastPressedTime = event.getEventTime();
					}
				}
				return true;
			}
		}
		return false;
	}
}

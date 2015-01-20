package com.abeo.absap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import com.abeo.absap.adapter.CustomMasterItemAdaper;
import com.abeo.absap.utils.DirectionsJSONParser;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeFragment extends Fragment {

	private ListView mListView;
	private TextView mtextViewRouteCode;
	private TextView mtextViewRouteName;
	private TextView mtextViewVisited;
	private TextView mtextViewNotVisited;

	LocationManager mLocationManager;

	static final LatLng HAMBURG = new LatLng(53.558, 9.927);
	static final LatLng KIEL = new LatLng(53.551, 9.993);
	private GoogleMap map;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setRetainInstance(true);
		Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),
				"fonts/myanmar3.ttf");
		View rootView = inflater.inflate(R.layout.fragment_home, container,
				false);
		this.mListView = (ListView) rootView.findViewById(R.id.listViewMessage);
		this.mtextViewRouteCode = (TextView) rootView
				.findViewById(R.id.textViewRouteCode);
		this.mtextViewRouteName = (TextView) rootView
				.findViewById(R.id.textViewRouteName);
		this.mtextViewVisited = (TextView) rootView
				.findViewById(R.id.textViewVisited);
		this.mtextViewNotVisited = (TextView) rootView
				.findViewById(R.id.textViewNotVisited);

		mtextViewRouteName.setTypeface(tf);

		this.mtextViewRouteCode.setText("R0000124");
		this.mtextViewRouteName
				.setText("အမှတ်(အက်စ်-၂)၊အခန်း(၀၀၁)၊မင်းရဲကျော်စွာဗီ");
		this.mtextViewVisited.setText("30");
		this.mtextViewNotVisited.setText("5");

		this.mListView
				.setAdapter(new CustomMasterItemAdaper(
						getActivity(),
						R.layout.message_list_item,
						new String[][] {
								{ "1",
										"အမှတ်(အက်စ်-၂)၊အခန်း(၀၀၁)၊မင်းရဲကျော်စွာဗီလာ၊တာမွေလေးရပ်ကွက" },
								{ "2",
										"အမှတ် (၃) ၊(၁၄၅) ၊ အရိုးကုန်းလမ်း ၊ တာမွေ ၊ ရန်ကုန်၊ မြန်မာ" },
								{ "3",
										"အမှတ်(အက်စ်-၂)၊အခန်း(၀၀၁)၊မင်းရဲကျော်စွာဗီလာ၊တာမွေလေးရပ်ကွက" },
								{ "4",
										"အမှတ်(အက်စ်-၂)၊အခန်း(၀၀၁)၊မင်းရဲကျော်စွာဗီလာ၊တာမွေလေးရပ်ကွက" },
								{ "5",
										"အမှတ်(၁/၀၀၂)၊ကျိုက်က္ကဆံအိမ်ယာ၊ကျိုက်က္ကဆံလမ်း၊တာမွေကြီး(ခ)ရပ်ကွက" },
								{ "6",
										"အမှတ်(၁/၀၀၂)၊ကျိုက်က္ကဆံအိမ်ယာ၊ကျိုက်က္ကဆံလမ်း၊တာမွေကြီး(ခ)ရပ်ကွက" },
								{ "7",
										"အမှတ် (၅၂) ၊ဗျိုင်းရေအိုးစဉ်လမ်း ၊ တာမွေ ၊ ရန်ကုန်၊ မြန်မာ" },
								{ "8",
										"အမှတ် (၃) ၊(၁၄၅) ၊ အရိုးကုန်းလမ်း ၊ တာမွေ ၊ ရန်ကုန်၊ မြန်မာ" },
								{ "9",
										"အမှတ် (၃) ၊(၁၄၅) ၊ အရိုးကုန်းလမ်း ၊ တာမွေ ၊ ရန်ကုန်၊ မြန်မာ" },
								{ "10",
										"အမှတ် (၅၂) ၊ဗျိုင်းရေအိုးစဉ်လမ်း ၊ တာမွေ ၊ ရန်ကုန်၊ မြန်မာ" } }));

		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();

		mLocationManager = (LocationManager) getActivity().getSystemService(
				getActivity().LOCATION_SERVICE);

		focusMapOnCurrentLocation();
		// Getting URL to the Google Directions API
		String url = getDirectionsUrl(new LatLng(10.7891304, 106.700646),
				new LatLng(10.7822061, 106.6965691));
		if (isOnline()) {
			DownloadTask downloadTask = new DownloadTask();

			// Start downloading json data from Google Directions API
			downloadTask.execute(url);
			try {
				map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
						10.7891304, 106.700646), 15));
			} catch (Exception ex) {
				Toast.makeText(getActivity(), ex.getMessage(),
						Toast.LENGTH_SHORT).show();
			}
		}

		return rootView;
	}

	private Location getCurrentLocation() {
		Location location = null;
		String[] providers = new String[3];
		providers[0] = LocationManager.GPS_PROVIDER;
		providers[1] = LocationManager.NETWORK_PROVIDER;
		providers[2] = LocationManager.PASSIVE_PROVIDER;

		for (String provider : providers) {
			if (!mLocationManager.isProviderEnabled(provider))
				continue;
			if (mLocationManager != null) {
				location = mLocationManager.getLastKnownLocation(provider);
				if (location != null) {
					break;
				}
			}
		}
		return location;
	}

	private void focusMapOnCurrentLocation() {
		final int DURATIONMS_CONST = 2000;
		final int ZOOM_ANIM_CAMERA_CONST = 15;
		final int ZOOM_MOVE_CAMERA_CONST = 20;

		// Set focus on current location on google map;

		Location location = getCurrentLocation();
		if (location != null) {
			double lat = location.getLatitude();
			double log = location.getLongitude();
			LatLng latLng = new LatLng(lat, log);
			Marker myL = map.addMarker(new MarkerOptions().position(latLng)
					.title("Your Location"));
			try {
				map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,
						ZOOM_MOVE_CAMERA_CONST));
				map.animateCamera(
						CameraUpdateFactory.zoomTo(ZOOM_ANIM_CAMERA_CONST),
						DURATIONMS_CONST, null);
			} catch (Exception ex) {
				Toast.makeText(getActivity(), ex.getMessage(),
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		try {
			MapFragment fragment = (MapFragment) getActivity()
					.getFragmentManager().findFragmentById(R.id.map);
			if (fragment != null)
				getFragmentManager().beginTransaction().remove(fragment)
						.commit();

		} catch (IllegalStateException e) {
		}
	}

	private String getDirectionsUrl(LatLng origin, LatLng dest) {

		// Origin of route
		String str_origin = "origin=" + origin.latitude + ","
				+ origin.longitude;

		// Destination of route
		String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

		// Sensor enabled
		String sensor = "sensor=false";

		// Building the parameters to the web service
		String parameters = str_origin + "&" + str_dest + "&" + sensor;

		// Output format
		String output = "json";

		// Building the url to the web service
		String url = "https://maps.googleapis.com/maps/api/directions/"
				+ output + "?" + parameters;

		return url;
	}

	/** A method to download json data from url */
	private String downloadUrl(String strUrl) throws IOException {
		String data = "";
		InputStream iStream = null;
		HttpURLConnection urlConnection = null;
		try {
			URL url = new URL(strUrl);

			// Creating an http connection to communicate with url
			urlConnection = (HttpURLConnection) url.openConnection();

			// Connecting to url
			urlConnection.connect();

			// Reading data from url
			iStream = urlConnection.getInputStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(
					iStream));

			StringBuffer sb = new StringBuffer();

			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			data = sb.toString();

			br.close();

		} catch (Exception e) {
			Log.d("Exception while downloading url", e.toString());
		} finally {
			iStream.close();
			urlConnection.disconnect();
		}
		return data;
	}

	// Fetches data from url passed
	private class DownloadTask extends AsyncTask<String, Void, String> {

		// Downloading data in non-ui thread
		@Override
		protected String doInBackground(String... url) {

			// For storing data from web service
			String data = "";

			try {
				// Fetching the data from web service
				data = downloadUrl(url[0]);
			} catch (Exception e) {
				Log.d("Background Task", e.toString());
			}
			return data;
		}

		// Executes in UI thread, after the execution of
		// doInBackground()
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			ParserTask parserTask = new ParserTask();

			// Invokes the thread for parsing the JSON data
			parserTask.execute(result);
		}
	}

	/** A class to parse the Google Places in JSON format */
	private class ParserTask extends
			AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

		// Parsing the data in non-ui thread
		@Override
		protected List<List<HashMap<String, String>>> doInBackground(
				String... jsonData) {

			JSONObject jObject;
			List<List<HashMap<String, String>>> routes = null;

			try {
				jObject = new JSONObject(jsonData[0]);
				DirectionsJSONParser parser = new DirectionsJSONParser();
				// Starts parsing data
				routes = parser.parse(jObject);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return routes;
		}

		// Executes in UI thread, after the parsing process
		@Override
		protected void onPostExecute(List<List<HashMap<String, String>>> result) {
			try {
				ArrayList<LatLng> points = null;
				PolylineOptions lineOptions = null;
				MarkerOptions markerOptions1 = new MarkerOptions();
				MarkerOptions markerOptions2 = new MarkerOptions();
				MarkerOptions markerOptions3 = new MarkerOptions();
				MarkerOptions markerOptions4 = new MarkerOptions();
				// Traversing through all the routes
				for (int i = 0; i < result.size(); i++) {
					points = new ArrayList<LatLng>();
					lineOptions = new PolylineOptions();

					List<HashMap<String, String>> path = result.get(i);
					
					for (int j = 0; j < path.size(); j++) {
						HashMap<String, String> point = path.get(j);

						double lat = Double.parseDouble(point.get("lat"));
						double lng = Double.parseDouble(point.get("lng"));
						LatLng position = new LatLng(lat, lng);

						points.add(position);
					}

					// Adding all the points in the route to LineOptions
					lineOptions.addAll(points);
					lineOptions.width(2);
					lineOptions.color(Color.RED);
				}
				Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),
						"fonts/myanmar3.ttf");
				
				// Setting latitude and longitude of the marker position
				
				markerOptions1.position(new LatLng(10.7891304, 106.700646));
				markerOptions1.title("မေဧကရီ");
				markerOptions1.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
				markerOptions2.position(new LatLng(10.7822061, 106.6965691));
				markerOptions2.title("ဆန်းသစ်လွင်");
				markerOptions2.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
				
				markerOptions3.position(new LatLng(10.788539, 106.703569));
				markerOptions3.title("ိုးစံ");
				//markerOptions3.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
				
				markerOptions4.position(new LatLng(10.787864, 106.695061));
				markerOptions4.title("လင်း");
				//markerOptions4.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
				//markerOptions4.icon(BitmapDescriptorFactory
					//	.fromResource(R.drawable.dd_start));
				Marker m1 =  map.addMarker(markerOptions1);
				Marker m2 = map.addMarker(markerOptions2);
				Marker m3 = map.addMarker(markerOptions3);
				Marker m4 = map.addMarker(markerOptions4);
				m1.showInfoWindow();
				//m2.showInfoWindow();
				//m3.showInfoWindow();
				//m4.showInfoWindow();
				map.addPolyline(lineOptions);
			} catch (Exception ex) {
				Toast.makeText(getActivity(), ex.getMessage(),
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getActivity()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}
}

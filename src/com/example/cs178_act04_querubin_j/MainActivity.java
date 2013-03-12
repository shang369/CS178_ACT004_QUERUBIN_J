package com.example.cs178_act04_querubin_j;

import com.example.cs178_act04_querubin_j.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends Activity {

	//GRID POINTS
	static final LatLng MAIN_CAMPUS = new LatLng(10.30046, 123.88822);
	static final LatLng SM = new LatLng(10.31234, 123.92002);
	static final LatLng TALAMBAN_CAMPUS = new LatLng(10.35410, 123.91145);
	
	//GOOGLE MAP
	private GoogleMap gmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		System.out.println(GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext()));
		
		gmap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		final Marker tc = gmap.addMarker(new MarkerOptions().position(TALAMBAN_CAMPUS).title("talamban campus"));
		final Marker main = gmap.addMarker(new MarkerOptions().position(MAIN_CAMPUS).title("main campus"));
		final Marker smcebu = gmap.addMarker(new MarkerOptions().position(SM).title("sm"));
			
		gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(TALAMBAN_CAMPUS, gmap.getMaxZoomLevel()));

		 
		gmap.setOnMarkerClickListener(new OnMarkerClickListener(){
			    	
		    	AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
		    	
			@Override
				public boolean onMarkerClick(Marker mk) {
					// TODO Auto-generated method stub
					alert.setTitle("travel");
					
					if(mk.equals(tc)){
						alert.setMessage("to main campus! continue?")
						.setPositiveButton("YES!", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(MAIN_CAMPUS, gmap.getMaxZoomLevel()));
							}
						}).setNegativeButton("NOPE.", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {}
						});
						
					} else if(mk.equals(main)){
						alert.setMessage("to sm! continue?")
						.setPositiveButton("YES!", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(SM, gmap.getMaxZoomLevel()));
							}
						}).setNegativeButton("NOPE.", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {}
						});
						
					} else if(mk.equals(smcebu)){
						alert.setMessage("to talamban campus! Are you sure you want to continue?")
						.setPositiveButton("YES!", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(TALAMBAN_CAMPUS, gmap.getMaxZoomLevel()));
							}
						}).setNegativeButton("NOPE.", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {}
						});
						
					}
					
					AlertDialog alertDialog = alert.create();
					alertDialog.show();
				
					return false;
				}
		    	
		    	
		    });

		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}

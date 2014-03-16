package de.gerlich.fitnesscoachapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ProfileActivity extends Activity{
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.coach, menu);
		return true;
	}
	
	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {

	    case R.id.itemProfile:
	    	Intent i1 = new Intent(this, ProfileActivity.class);
	    	startActivity(i1);
	    	break;

	    case R.id.itemReport:
	    	Intent i2 = new Intent(this, ReportActivity.class);
	    	startActivity(i2);
	    	break;
	      
	    case R.id.itemWeightEntry:
	    	Intent i3 = new Intent(this, WeightEntryActivity.class);
	    	startActivity(i3);
	    	break;
	    	
	    case R.id.itemWorkout:
	    	Intent i4 = new Intent(this, WorkoutActicity.class);
	    	startActivity(i4);
	    	break;
		      
	    default:
	    	break;
	    }

	    return true;
	  } 

}

package de.gerlich.fitnesscoachapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CoachActivity extends Activity {
	
	Button login;
	Button newaccount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_coach);
		
		login = (Button) findViewById(R.id.buttonLogin);
		newaccount = (Button) findViewById(R.id.buttonNewAccount);
		
		LoginListener ll = new LoginListener();
		login.setOnClickListener(ll);
		
		NewAccountListener nal = new NewAccountListener();
		newaccount.setOnClickListener(nal);
		
		
	}
	
	
	
	
	public class LoginListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	public class NewAccountListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(CoachActivity.this, NewAccountActivity.class);
			startActivity(intent);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.coach, menu);
		return true;
	}

}

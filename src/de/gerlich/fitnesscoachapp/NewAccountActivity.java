package de.gerlich.fitnesscoachapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class NewAccountActivity extends Activity{
	
	EditText editFirstname;
	EditText editLastname;
	EditText editUsername;
	EditText editPassword;
	EditText editEmail;
	EditText editHeight;
	RadioButton radioFemale;
	RadioButton radioMale;
	RadioGroup radioSex;
	Spinner spinYearbirth;
	
	String firstname, lastname, username, password, email, heightt;
	double height;
	int yearbirth;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		editFirstname = (EditText) findViewById(R.id.editFirstname);
		editLastname = (EditText) findViewById(R.id.editLastname);
		editUsername = (EditText) findViewById(R.id.editUsername);
		editPassword = (EditText) findViewById(R.id.editPassword);
		editEmail = (EditText) findViewById(R.id.editEmail);
		editHeight = (EditText) findViewById(R.id.editHeight);
		radioSex = (RadioGroup) findViewById(R.id.radioGroupSex);
		spinYearbirth = (Spinner) findViewById(R.id.spinnerYearbirth);
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.birthyears, android.R.layout.simple_spinner_dropdown_item);
		spinYearbirth.setAdapter(adapter);

		yearbirth = Integer.parseInt(spinYearbirth.getSelectedItem().toString());
	}
	
	public class SaveListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public class SpinListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
			//Object item = parent.getItemAtPosition(pos);
			
		}
		
	}

}

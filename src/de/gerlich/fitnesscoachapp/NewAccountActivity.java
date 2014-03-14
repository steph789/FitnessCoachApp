package de.gerlich.fitnesscoachapp;

import android.app.Activity;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import de.gerlich.fitnesscoachapp.database.TableUser;
import de.gerlich.fitnesscoachapp.database.UserProvider;

public class NewAccountActivity extends Activity {

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
	Button saveAccount;

	String firstname, lastname, username, password, email;
	double height;
	int sex, yearbirth;

	Uri saveUri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newaccount);
		
		Bundle extras = getIntent().getExtras();
	    saveUri = (savedInstanceState == null) ? null : (Uri) savedInstanceState
	        .getParcelable(UserProvider.CONTENT_ITEM_TYPE);
	    if (extras != null) {
	      saveUri = extras
	          .getParcelable(UserProvider.CONTENT_ITEM_TYPE);
	    }

		editFirstname = (EditText) findViewById(R.id.editFirstname);
		editLastname = (EditText) findViewById(R.id.editLastname);
		editUsername = (EditText) findViewById(R.id.editUsername);
		editPassword = (EditText) findViewById(R.id.editPassword);
		editEmail = (EditText) findViewById(R.id.editEmail);
		editHeight = (EditText) findViewById(R.id.editHeight);
		radioSex = (RadioGroup) findViewById(R.id.radioGroupSex);
		spinYearbirth = (Spinner) findViewById(R.id.spinnerYearbirth);
		saveAccount = (Button) findViewById(R.id.buttonSaveAccount);

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.birthyears,
				android.R.layout.simple_spinner_item);
		Log.d("Adapter", String.valueOf(adapter.getCount()));
		
		spinYearbirth.setAdapter(adapter);

		saveAccount.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				firstname = editFirstname.getText().toString();
				lastname = editLastname.getText().toString();
				username = editUsername.getText().toString();
				password = editPassword.getText().toString();
				email = editEmail.getText().toString();
				yearbirth = Integer.parseInt(spinYearbirth.getSelectedItem().toString());
				height =Double.parseDouble(editHeight.getText().toString());
				sex = radioSex.getCheckedRadioButtonId();
				Log.d("onClick", String.valueOf(radioSex.getCheckedRadioButtonId()));
			}
		});
	}


	private void saveState() {

		ContentValues values = new ContentValues();
		values.put(TableUser.COLUMN_FIRSTNAME, firstname);
		values.put(TableUser.COLUMN_LASTNAME, lastname);
		values.put(TableUser.COLUMN_USERNAME, username);
		values.put(TableUser.COLUMN_PASSWORD, password);
		values.put(TableUser.COLUMN_EMAIL, email);
		values.put(TableUser.COLUMN_HEIGHT, height);
		values.put(TableUser.COLUMN_YEARBIRTH, yearbirth);
		values.put(TableUser.COLUMN_SEX, sex);

		if (saveUri == null) {
			saveUri = getContentResolver().insert(UserProvider.CONTENT_URI,
					values);

		}

		class SpinListener implements OnItemClickListener {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos,
					long id) {
				// Object item = parent.getItemAtPosition(pos);

			}

		}

	}
}

package com.jentsch.deviceid;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.provider.Settings.Secure;
import android.content.*;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		String android_id = Secure.getString(getContentResolver(), Secure.ANDROID_ID); 
		TextView devid = (TextView)findViewById(R.id.deviceid);
		devid.setText(android_id);
    }
	
	public void sendDeviceId(View view)
	{
		String android_id = Secure.getString(getContentResolver(), Secure.ANDROID_ID); 
		
		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("message/rfc822");
		// i.putExtra(Intent.EXTRA_EMAIL  , new String[]{""});
		i.putExtra(Intent.EXTRA_SUBJECT, "My Device Id");
		i.putExtra(Intent.EXTRA_TEXT   , "My Device Id is " + android_id);
		try {
			startActivity(Intent.createChooser(i, "Send mail..."));
		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
		}
	}
}

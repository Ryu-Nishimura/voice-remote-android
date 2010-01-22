package com.korzinek.speech;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Settings extends Activity {

	String server;
	int port;
	
	private OnClickListener restore_listener = new OnClickListener() {
	    public void onClick(View v) {
	    	SharedPreferences settings = getSharedPreferences("speech_remote.prefs", 0);
	  	    server=settings.getString("server", "192.168.0.13");
	  		port=settings.getInt("port", 5555);
	  		
	  		EditText edit_server=(EditText)findViewById(R.id.Server);
	  		edit_server.setText(server);
	  		
	  		EditText edit_port=(EditText)findViewById(R.id.Port);
	  		edit_port.setText(""+port);
	    }
	};
	
	private OnClickListener save_listener = new OnClickListener() {
	    public void onClick(View v) {	    	
	  		EditText edit_server=(EditText)findViewById(R.id.Server);
	  		server=edit_server.getText().toString();
	  		
	  		EditText edit_port=(EditText)findViewById(R.id.Port);
	  		try{
	  			port=Integer.parseInt(edit_port.getText().toString());
	  		}catch(NumberFormatException e)
	  		{
	  			edit_port.setText("error");
	  			return;
	  		}
	  		
	  		SharedPreferences settings = getSharedPreferences("speech_remote.prefs", 0);
			SharedPreferences.Editor editor=settings.edit();
			editor.putString("server", server);
			editor.putInt("port", port);
			editor.commit();
			finish();
	    }
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
	    setContentView(R.layout.settings);
	    
	    SharedPreferences settings = getSharedPreferences("speech_remote.prefs", 0);
	    server=settings.getString("server", "192.168.0.13");
		port=settings.getInt("port", 5555);
		
		EditText edit_server=(EditText)findViewById(R.id.Server);
  		edit_server.setText(server);
  		
  		EditText edit_port=(EditText)findViewById(R.id.Port);
  		edit_port.setText(""+port);
		
		Button restore = (Button)findViewById(R.id.Button01);
        restore.setOnClickListener(restore_listener);
	    
        Button save = (Button)findViewById(R.id.Button02);
        save.setOnClickListener(save_listener);
	        
	}

	
	
}

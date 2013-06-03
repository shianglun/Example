package com.example.progressexample;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	ProgressDialog mdialog;
	Activity viewactivity;
	Button btn;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewactivity=MainActivity.this;
        btn=(Button)findViewById(R.id.btn);
        btn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				new DownloadThread(viewactivity).execute(M_StatusType.Downlad);
			}
		});
    }
    
}

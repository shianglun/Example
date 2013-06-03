package com.example.progressexample;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.Toast;

public class DownloadProgress {
	public Activity viewActivity;
	public ProgressDialog mdialog;
	
	public DownloadProgress(Activity vact){
		viewActivity=vact;
		mHandler = new RefreshHandler();
		mdialog=new ProgressDialog(viewActivity);
	}
	
	public void setProgressTitle(String title){
		mdialog.setTitle(title);
	}
	public void setProgressMessage(String message){
		mdialog.setMessage(message);
	}
	public void runProg(){
		mdialog.setCancelable(false);
		mdialog.setOnKeyListener(new OnKeyListener(){
		public boolean onKey(DialogInterface dialog, int code, KeyEvent event){
			  if(code==KeyEvent.KEYCODE_SEARCH) //如果按下Search鈕
				return true;
			  else
				return false;
			  }
		});
		mdialog.show();
	}
	public void stopProg(){
		mdialog.dismiss();
	}
	
	public  RefreshHandler mHandler ;
	public class RefreshHandler extends Handler {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case M_StatusType.StatusDownload:
				setProgressTitle("下載中...");
				setProgressMessage("請稍候!");
				runProg();
				break;
			case M_StatusType.StatusDownloadFail:
				stopProg();
				TLog("下載失敗!");
				break;
			case M_StatusType.StatusDownloadSuccess:
				stopProg();
				TLog("下載成功!");
				break;
			}
		}
	};

    public void TLog(String s){
    	Toast.makeText(viewActivity, s, Toast.LENGTH_SHORT).show();
    }


}

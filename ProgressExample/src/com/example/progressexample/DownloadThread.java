package com.example.progressexample;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Message;
import android.util.Log;

public class DownloadThread extends AsyncTask<Integer, Integer, Integer> {
	private Context context;
	private DownloadProgress progress;
	private UpdateThread mUpdateThread;
	private Thread mTH;

	public DownloadThread(Context c) {
		Log.d("Download", "New DownloadThread");
		context = c;
		progress = new DownloadProgress((Activity) context);
		mUpdateThread = new UpdateThread();
		mTH = new Thread(mUpdateThread);
	}

	protected Integer doInBackground(Integer... arg0) {
		if (arg0[0]==M_StatusType.Downlad){
			Log.d("Download", ">>>>");
			Message mess = new Message();
			mess.what = M_StatusType.StatusDownload;
			progress.mHandler.sendMessage(mess);
			/*下載資料*/
			mTH.start();
			/*下載結束*/
		}else{
			Log.d("Other", ">>>>");
			//other: upload、delete...
		}
		return null;
	}

	class UpdateThread implements Runnable {
		public void run() {
			try {
				// 用sleep來代替你跟Server登入資料時的Delay時間
				Thread.sleep(5000);
				
				boolean flag=false;
				Message mess = new Message();
				if (flag==true){
					mess.what = M_StatusType.StatusDownloadSuccess;
				}else{
					mess.what = M_StatusType.StatusDownloadFail;
				}
				progress.mHandler.sendMessage(mess);
			} catch (Exception e) {

			}
		}
	}

	protected void onPostExecute(Integer result) {
		super.onPostExecute(result);
	}

	protected void onPreExecute() {
		super.onPreExecute();
	}

	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
	}

	protected void onCancelled() {
		super.onCancelled();
	}

}

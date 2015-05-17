package com.taobao.android.game2.main;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.taobao.android.game2.GameReceiver;
import com.taobao.android.game2.download.GcAppDownLoadService;
import com.taobao.android.game20x7a.R;

public class GcContainerActivity extends Activity {
	private ServiceConnection e;
	GameReceiver mGameReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btnButton = (Button) findViewById(R.id.btnButton);
		Button btnRegReceiver = (Button) findViewById(R.id.btnRegReceiver);
		Button btnUnRegReceiver = (Button) findViewById(R.id.btnUnRegReceiver);
		Button btnSendDY = (Button) findViewById(R.id.btnSendDY);
		Button btnSendSTATIC = (Button) findViewById(R.id.btnSendSTATIC);
		Button btnStartAct = (Button) findViewById(R.id.btnStartAct);
		mGameReceiver = new GameReceiver();

		btnButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {


				try {
					Intent intent = new Intent(GcContainerActivity.this, GcAppDownLoadService.class);
					startService(intent);
					Toast.makeText(getApplication(), "GcAppDownLoadService is booting ", Toast.LENGTH_SHORT).show();
				} catch (Exception e) {
					e.printStackTrace();
				}


			}
		});

		btnRegReceiver.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				IntentFilter mFilter = new IntentFilter("com.tmp.msg");

				registerReceiver(mGameReceiver, mFilter);

			}
		});
		btnUnRegReceiver.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				unregisterReceiver(mGameReceiver);

			}
		});

		btnSendDY.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent mIntent = new Intent("com.tmp.msg");
				mIntent.putExtra("msg", "btnSendDY msg" + System.currentTimeMillis());
				sendBroadcast(mIntent);

			}
		});
		btnSendSTATIC.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent mIntent = new Intent("com.taobao.android.game2.GameStaticReceiver");
				mIntent.putExtra("msg", "btnSendSTATIC msg" + System.currentTimeMillis());
				sendBroadcast(mIntent);

			}
		});
		btnStartAct.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(GcContainerActivity.this, ActivityInternalActivity.class));

			}
		});
	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
		}
		return super.onTouchEvent(event);
	}
}



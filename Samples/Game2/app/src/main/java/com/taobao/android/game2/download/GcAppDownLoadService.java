package com.taobao.android.game2.download;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class GcAppDownLoadService extends Service {
	class  BindImpl extends Binder{
		
	}
	@Override
	public void onCreate() {
		System.out.println("GcAppDownLoadService.onCreate()");
		super.onCreate();
	}

	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("GcAppDownLoadService.onBind()");
		return new BindImpl();
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		System.out.println("GcAppDownLoadService.onStartCommand()");
		return super.onStartCommand(intent, flags, startId);
	}
}

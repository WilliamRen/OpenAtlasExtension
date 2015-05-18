package com.taobao.android.game2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class GameStaticReceiver extends BroadcastReceiver {
	public GameStaticReceiver() {
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO: This method is called when the BroadcastReceiver is receiving
		// an Intent broadcast.
		System.out.println("GameStaticReceiver.onReceive()"+intent.getStringExtra("msg"));
	}
}

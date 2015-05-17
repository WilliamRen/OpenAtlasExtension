package com.taobao.android.game2.main;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.taobao.android.game2.AppProvider;
import com.taobao.android.game20x7a.R;


public class ActivityInternalActivity extends Activity {
Button btnInsert;
Button btnQuery;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_internal);
		btnInsert=(Button) findViewById(R.id.btnInsert);
		btnQuery=(Button) findViewById(R.id.btnQuery);
		btnInsert.setOnClickListener(new  OnClickListener() {
			
			@Override
			public void onClick(View v) {
				insertRecord("uname"+System.currentTimeMillis());
				
			}
		});
		btnQuery.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				displayRecords();
				
			}
		});
	}


	private void insertRecord(String userName) {
		ContentValues values = new ContentValues();
		values.put("USER_NAME", userName);
		getContentResolver().insert(AppProvider.AppProviderURI, values);
	}
	private void displayRecords() {
		String columns[] = new String[] { "_id","USER_NAME" };
		Uri myUri = AppProvider.AppProviderURI;
		Cursor cur = managedQuery(myUri, columns,null, null, null );
		if (cur.moveToFirst()) {
			String id = null;
			String userName = null;
			do {
				id = cur.getString(cur.getColumnIndex("_id"));
				userName = cur.getString(cur.getColumnIndex("USER_NAME"));
				System.out.println("ActivityInternalActivity.displayRecords() id "+id+" uname "+userName);
			} while (cur.moveToNext());
		}
	}
}

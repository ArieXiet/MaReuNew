package com.ariexiet.maru;

import android.app.Application;

import com.ariexiet.maru.ui.list.ListMeetingRecyclerViewAdapter;

public class MyApplication extends Application {

	private ListMeetingRecyclerViewAdapter mAdapter;

	public void setRefToAdapter(ListMeetingRecyclerViewAdapter adapter) {
		mAdapter = adapter;
	}

	public ListMeetingRecyclerViewAdapter getRefToAdapter () {
		return mAdapter;
	}
}

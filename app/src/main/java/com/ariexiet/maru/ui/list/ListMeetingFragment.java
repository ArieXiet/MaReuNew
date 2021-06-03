package com.ariexiet.maru.ui.list;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ariexiet.maru.MyApplication;
import com.ariexiet.maru.R;
import com.ariexiet.maru.di.DI;
import com.ariexiet.maru.model.Meeting;
import com.ariexiet.maru.service.MeetingApiService;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class ListMeetingFragment extends Fragment {
	private MeetingApiService mApiService;
	private RecyclerView mRecyclerView;
	private ListMeetingRecyclerViewAdapter mAdapter;

	public static ListMeetingFragment newInstance() {
		return new ListMeetingFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mApiService = DI.getMeetingApiService();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_meeting_list, container, false);
		Context context = view.getContext();
		mRecyclerView = (RecyclerView) view;
		mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
		mRecyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL));
		initList();
		return view;
	}

	private void initList() {
		((AppCompatActivity) getActivity()).getSupportActionBar().show();
		List<Meeting> mMeetings = mApiService.getMeetings();
		mAdapter = new ListMeetingRecyclerViewAdapter(mMeetings, getContext());
		((MyApplication)getActivity().getApplication()).setRefToAdapter(mAdapter);
		mRecyclerView.setAdapter(mAdapter);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getActivity().getMenuInflater().inflate(R.menu.menu_de_filtrage, menu);
		return true;
	}
}

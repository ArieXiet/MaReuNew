package com.ariexiet.maru.ui.details;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ariexiet.maru.R;
import com.ariexiet.maru.model.Employee;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Objects;

public class ListAttendeesFragment extends Fragment {
	private static final String ARG_COLOR = "color";
	private RecyclerView mRecyclerView;
	private ArrayList<Employee> mAttendees;
	private int mColor;

	public static final String ARG_ATTENDEES = "argAttendees";

	public static ListAttendeesFragment newInstance(ArrayList<Employee> attendees, int color) {
		ListAttendeesFragment fragment = new ListAttendeesFragment();
		Bundle args = new Bundle();
		args.putParcelableArrayList(ARG_ATTENDEES, attendees);
		args.putInt(ARG_COLOR, color);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_attendees_list, container, false);

		if(getArguments() != null) {
			mAttendees = (ArrayList<Employee>) getArguments().getSerializable(ARG_ATTENDEES);
			mColor = getArguments().getInt(ARG_COLOR);
		}
		Context context = v.getContext();
		mRecyclerView = (RecyclerView) v;
		mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
		mRecyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL));
		initList();
		return v;
	}

	@Override
	public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
	}

	private void initList() {
		mRecyclerView.setAdapter(new AttendeesRecyclerViewAdapter(mAttendees, mColor, getContext()));
	}

	@Override
	public void onResume() {
		super.onResume();
		initList();
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onStop() {
		super.onStop();
		EventBus.getDefault().unregister(this);
	}
}

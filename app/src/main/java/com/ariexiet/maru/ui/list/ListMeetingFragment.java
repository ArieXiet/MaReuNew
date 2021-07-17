package com.ariexiet.maru.ui.list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
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

import java.util.List;
import java.util.Objects;

/**
 * Displays the list of meetings
 */
public class ListMeetingFragment extends Fragment {
	private MeetingApiService mApiService;
	private RecyclerView mRecyclerView;
	private static List<Meeting> mMeetings;

	public static ListMeetingFragment newInstance(List<Meeting> meetings) {
		mMeetings = meetings;
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
		mRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
		initList();
		return view;
	}

	private void initList() {
		Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).show();

		ListMeetingRecyclerViewAdapter adapter = new ListMeetingRecyclerViewAdapter(mMeetings, getContext());
		((MyApplication) requireActivity().getApplication()).setRefToAdapter(adapter);
		mRecyclerView.setAdapter(adapter);
	}


	@Override
	public void onResume() {
		super.onResume();
		Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).show();
	}
}

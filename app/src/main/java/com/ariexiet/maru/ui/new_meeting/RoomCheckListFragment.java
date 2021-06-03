package com.ariexiet.maru.ui.new_meeting;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ariexiet.maru.MainActivity;
import com.ariexiet.maru.R;
import com.ariexiet.maru.di.DI;
import com.ariexiet.maru.model.MeetingRoom;
import com.ariexiet.maru.service.MeetingApiService;

import java.util.List;
import java.util.Objects;

import static android.content.ContentValues.TAG;

public class RoomCheckListFragment extends Fragment {
	private MeetingApiService mApiService;
	private RecyclerView mRecyclerView;
	private RoomCheckListRecyclerViewAdapter mAdapter;

	public interface ISelectedRoomListener {
		public void getSelectedRoom(int roomNumber);
	}

	public static RoomCheckListFragment newInstance() {
		RoomCheckListFragment fragment = new RoomCheckListFragment();
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mApiService = DI.getMeetingApiService();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_rooms_check_list, container, false);
		Context context = view.getContext();
		mRecyclerView = view.findViewById(R.id.list_room_check);
		Button mRoomButton = view.findViewById(R.id.room_button);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
		mRecyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL));
		initList();
		mRoomButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//TODO NewMeeting ou MainActivity ((MainActivity)getActivity()).getSelectedRoom
				mApiService.getServiceMeeting().setRoom(mAdapter.mCheckedRoom);
				NewMeetingFragment fragment = new NewMeetingFragment();
				((MainActivity)getActivity()).replaceFragment(fragment, "frags");

			}
		});
		return view;
	}

	@Override
	public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
	}


	private void initList() {
		Log.d(TAG, "DEBUG: initList: ");
		List<MeetingRoom> mRoom = mApiService.getMeetingRooms();
		mAdapter = new RoomCheckListRecyclerViewAdapter(mRoom, getContext());
		mRecyclerView.setAdapter(mAdapter);
	}

	@Override
	public void onResume() {
		super.onResume();
		initList();
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.d(TAG, "AttendeesCheckListFrag onStop: DEBUG:");
		//NewMeetingFragment fragment = new NewMeetingFragment();
		//((MainActivity)getActivity()).replaceFragment(fragment, "frags");
	}
}

package com.ariexiet.maru.ui.new_meeting;

import android.content.Context;
import android.os.Bundle;
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
import com.ariexiet.maru.model.Meeting;
import com.ariexiet.maru.model.MeetingRoom;
import com.ariexiet.maru.service.MeetingApiService;
import com.ariexiet.maru.ui.list.ListMeetingContainerFragment;

import java.util.List;
import java.util.Objects;

/**
 * Displays the list of rooms and save the one checked by user
 */
public class RoomCheckListFragment extends Fragment {
	private MeetingApiService mApiService;
	private RecyclerView mRecyclerView;
	private RoomCheckListRecyclerViewAdapter mAdapter;
	private static String mCall;

	public static RoomCheckListFragment newInstance(String call) {
		mCall = call;
		return new RoomCheckListFragment();
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
		mRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
		initList();
		mRoomButton.setOnClickListener(v -> {
			if (Objects.equals(mCall, "new")) {
				mApiService.getServiceMeeting().setRoom(mAdapter.mCheckedRoom);
				NewMeetingFragment fragment = new NewMeetingFragment();
				((MainActivity) requireActivity()).replaceFragment(fragment, "frags");
			} else if (Objects.equals(mCall, "list")) {
				List<Meeting> mMeetingByRoom;
				mMeetingByRoom = DI.getMeetingApiService().getMeetingsByRoom(mAdapter.mCheckedRoom.getRoomNumber());
				ListMeetingContainerFragment fragment = ListMeetingContainerFragment.newInstance(requireActivity().getApplicationContext(), mMeetingByRoom);
				((MainActivity) requireActivity()).replaceFragment(fragment, "frags");
			}
		});
		return view;
	}

	@Override
	public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
	}


	private void initList() {
		List<MeetingRoom> mRoom = mApiService.getMeetingRooms();
		mAdapter = new RoomCheckListRecyclerViewAdapter(mRoom, getContext());
		mRecyclerView.setAdapter(mAdapter);
	}

	@Override
	public void onResume() {
		super.onResume();
		initList();
	}
}

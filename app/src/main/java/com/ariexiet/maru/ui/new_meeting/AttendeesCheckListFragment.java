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
import com.ariexiet.maru.model.Employee;
import com.ariexiet.maru.service.MeetingApiService;

import java.util.List;

/**
 *  Displays a list of employee with a checkbox and collect the checked ones to create a list
 */
public class AttendeesCheckListFragment extends Fragment {
	private MeetingApiService mApiService;
	private RecyclerView mRecyclerView;
	private AttendeesCheckListRecyclerViewAdapter mAdapter;

	public static AttendeesCheckListFragment newInstance() {
		return new AttendeesCheckListFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mApiService = DI.getMeetingApiService();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_attendees_check_list, container, false);
		Context context = view.getContext();
		mRecyclerView = view.findViewById(R.id.list_attendees_check);
		Button mAttendeeButton = view.findViewById(R.id.attendees_button);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
		mRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
		initList();
		mAttendeeButton.setOnClickListener(v -> {
			mApiService.getServiceMeeting().setAttendees(mAdapter.mCheckedEmployees);
			NewMeetingFragment fragment = new NewMeetingFragment();
			((MainActivity) requireActivity()).replaceFragment(fragment, "frags");
		});
		return view;
	}

	@Override
	public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
	}


	private void initList() {
		List<Employee> mEmployees = mApiService.getEmployees();
		mAdapter = new AttendeesCheckListRecyclerViewAdapter(mEmployees, getContext());
		mRecyclerView.setAdapter(mAdapter);
	}

	@Override
	public void onResume() {
		super.onResume();
		initList();
	}
}

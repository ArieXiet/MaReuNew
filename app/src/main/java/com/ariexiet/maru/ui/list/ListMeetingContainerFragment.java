package com.ariexiet.maru.ui.list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ariexiet.maru.MainActivity;
import com.ariexiet.maru.R;
import com.ariexiet.maru.model.Meeting;
import com.ariexiet.maru.ui.new_meeting.NewMeetingFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Used to display floating action button and ListMeetingFragment on the same screen
 */
public class ListMeetingContainerFragment extends Fragment {

	private static Context mContext;
	private static List<Meeting> mMeetings;

	@BindView(R.id.fab)
	public FloatingActionButton mFab;

	public static ListMeetingContainerFragment newInstance(Context context, List<Meeting> meetings) {
		mContext = context;
		mMeetings = meetings;
		return new ListMeetingContainerFragment();
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_meeting_list_container, container, false);
		ButterKnife.bind(this, view);

		mFab.setOnClickListener(v -> ((MainActivity) requireActivity())
				.replaceFragment(NewMeetingFragment.newInstance(), "frags"));

		ListMeetingFragment fragment = ListMeetingFragment.newInstance(mMeetings);
		requireActivity().getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.meetings_container, fragment)
				.commit();
		return view;
	}
}

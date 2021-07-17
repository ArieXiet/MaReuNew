package com.ariexiet.maru.ui.details;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.ariexiet.maru.R;
import com.ariexiet.maru.model.Meeting;

import java.util.Calendar;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *  Displays all details of a specific meeting (clicked in the listMeetingFragment)
 */
public class DetailsMeetingFragment extends Fragment {

	@BindView(R.id.logo)
	public ImageView mLogo;
	@BindView(R.id.subject)
	public TextView mSubject;
	@BindView(R.id.starting_time)
	public TextView mStartingTime;
	@BindView(R.id.length)
	public TextView mLength;
	@BindView(R.id.room)
	public TextView mRoomName;
	@BindView(R.id.calendar)
	public ImageView mCalendar;
	@BindView(R.id.clock)
	public ImageView mClock;
	@BindView(R.id.place)
	public ImageView mPlace;

	public static final String ARG_MEETING = "argMeeting";

	private static Context mContext;

	public static DetailsMeetingFragment newInstance(Meeting meeting, Context context) {
		DetailsMeetingFragment fragment = new DetailsMeetingFragment();
		Bundle args = new Bundle();
		args.putSerializable(ARG_MEETING, meeting);
		fragment.setArguments(args);
		mContext = context;
		return fragment;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_details, container, false);

		Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).hide();
		Meeting meeting = (Meeting) requireArguments().getSerializable(ARG_MEETING);
		ButterKnife.bind(this, view);
		int color = Objects.requireNonNull(meeting).getRoom().getRoomColor();
		mLogo.setImageResource(meeting.getRoom().getRoomLogo());
		StringBuilder mConstruction = new StringBuilder();
		String mLengthWord = getString(R.string.length) + " ";
		mConstruction.append(mLengthWord).append(meeting.getLength()).append("mn");
		mLength.setText(mConstruction);
		mConstruction.delete(0, mConstruction.length()).append("Le ")
				.append(String.format("%02d", meeting.getDate().get(Calendar.DAY_OF_MONTH)))
				.append("/").append(String.format("%02d", meeting.getDate().get(Calendar.MONTH)))
				.append(" à ").append(String.format("%02d", meeting.getDate().get(Calendar.HOUR_OF_DAY)))
				.append("h").append(String.format("%02d", meeting.getDate().get(Calendar.MINUTE)));
		mStartingTime.setText(mConstruction);
		mSubject.setText(getString(R.string.subject_1) + " " + meeting.getSubject());
		mSubject.setTextColor(color);
		mRoomName.setText(getString(R.string.room) + " " + meeting.getRoom().getRoomNumber());
		mCalendar.setColorFilter(color);
		mClock.setColorFilter(color);
		mPlace.setColorFilter(color);

		ListAttendeesFragment fragment = ListAttendeesFragment.newInstance(meeting.getAttendees(), color);
		requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.attendees_container, fragment).commit();
		return view;
	}

}

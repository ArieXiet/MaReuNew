package com.ariexiet.maru.ui.details;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.ariexiet.maru.MainActivity;
import com.ariexiet.maru.R;
import com.ariexiet.maru.model.Meeting;
import com.ariexiet.maru.ui.list.ListMeetingFragment;

import java.util.Calendar;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

public class DetailsMeetingFragment extends Fragment {

	@BindView(R.id.logo)
	public ImageView mLogo;
	@BindView(R.id.subject)
	public TextView mSubject;
	@BindView(R.id.starting_time)
	public TextView mStartingTime;
	@BindView(R.id.length)
	public TextView mLength;
	@BindView(R.id.salle)
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

		Meeting meeting = (Meeting) Objects.requireNonNull(getArguments()).getSerializable(ARG_MEETING);
		ButterKnife.bind(this, view);
		int color = Objects.requireNonNull(meeting).getRoom().getRoomColor();
		mLogo.setImageResource(meeting.getRoom().getRoomLogo());
		StringBuilder mConstruction = new StringBuilder();
		mConstruction.append("Durée: ").append(meeting.getLength()).append("mn");
		mLength.setText(mConstruction);
		String mDay = String.format("%02d", meeting.getDate().get(Calendar.DAY_OF_MONTH));
		mConstruction.delete(0, mConstruction.length()).append("Le ")
				.append(String.format("%02d", meeting.getDate().get(Calendar.DAY_OF_MONTH)))
				.append("/").append(String.format("%02d", meeting.getDate().get(Calendar.MONTH)))
				.append(" à ").append(String.format("%02d", meeting.getDate().get(Calendar.HOUR_OF_DAY)))
				.append("h").append(String.format("%02d", meeting.getDate().get(Calendar.MINUTE)));
		mStartingTime.setText(mConstruction);
		mSubject.setText("Sujet: " + meeting.getSubject());
		mSubject.setTextColor(color);
		mRoomName.setText("Salle " + meeting.getRoom().getRoomNumber());
		mCalendar.setColorFilter(color);
		mClock.setColorFilter(color);
		mPlace.setColorFilter(color);

		ListAttendeesFragment fragment = ListAttendeesFragment.newInstance(meeting.getAttendees(), color);
		((FragmentActivity)mContext).getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.attendees_container, fragment)
				.commit();
		return view;
	}

	@Override
	public void onStop() {
		super.onStop();
		ListMeetingFragment fragment = new ListMeetingFragment();
		((MainActivity)getActivity()).replaceFragment(fragment, "frags");
	}
}

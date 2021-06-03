package com.ariexiet.maru.ui.new_meeting;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.ariexiet.maru.R;
import com.ariexiet.maru.di.DI;
import com.ariexiet.maru.model.Employee;
import com.ariexiet.maru.model.Meeting;
import com.ariexiet.maru.model.MeetingRoom;
import com.ariexiet.maru.model.ServiceMeeting;
import com.ariexiet.maru.service.MeetingApiService;
import com.ariexiet.maru.MainActivity;
import com.ariexiet.maru.ui.list.ListMeetingFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewMeetingFragment extends Fragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

	public int mYear;
	public int mMonth;
	public int mDayOfMonth;
	public int mStartHour;
	public int mEndHour;
	public int mStartMinute;
	public int mEndMinute;
	public ServiceMeeting mPreparedMeeting;
	public Calendar mStartDate = Calendar.getInstance();
	public Calendar mEndDate = Calendar.getInstance();
	public Calendar mC = Calendar.getInstance();
	public boolean mCheckStop;
	public boolean mDateOn;
	public boolean mStartOn;
	public boolean mEndOn;
	private static final String TAG = "NewMeetingFragment";

	private static NewMeetingFragment mInstance;
	@BindView(R.id.editText_sujet)
	public TextInputLayout mEditSujet;
	@BindView(R.id.date)
	public TextView mDate;
	@BindView(R.id.color)
	public ImageView mColor;
	@BindView(R.id.image_subject)
	public ImageView mColorSubject;
	@BindView(R.id.image_date)
	public ImageView mColorDate;
	@BindView(R.id.image_time)
	public ImageView mColorTime;
	@BindView(R.id.image_room)
	public ImageView mColorRoom;
	@BindView(R.id.image_attendees)
	public ImageView mColorAtten;
	@BindView(R.id.date_big)
	public TextView mDateBig;
	@BindView(R.id.time)
	public TextView mTime;
	@BindView(R.id.time_big)
	public TextView mTimeBig;
	@BindView(R.id.attendees)
	public TextView mAttendees;
	@BindView(R.id.checked_room)
	public TextView mCheckedRoom;
	@BindView(R.id.button2)
	public Button mValidButton;

	private MeetingApiService mApiService;

	public static NewMeetingFragment newInstance() {
		mInstance = new NewMeetingFragment();
		return mInstance;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_new_meeting, container, false);
		ButterKnife.bind(this, view);

		mDateOn = false;
		mStartOn = false;
		mEndOn = false;
		mApiService = DI.getMeetingApiService();
		mPreparedMeeting = mApiService.getServiceMeeting();
		mCheckStop = false;

		mDateBig.setOnClickListener(v -> {
			DialogFragment datePicker = new DatePickerFragment(mInstance);
			datePicker.show(Objects.requireNonNull(NewMeetingFragment.this.getFragmentManager()), "date picker");
		});
		mTimeBig.setOnClickListener(v -> {
			DialogFragment timePicker = new TimePickerFragment(mInstance);
			timePicker.show(Objects.requireNonNull(NewMeetingFragment.this.getFragmentManager()), "time picker");
		});
		mAttendees.setOnClickListener(v -> {
			mPreparedMeeting.setSubject(Objects.requireNonNull(mEditSujet.getEditText()).getText().toString());
			mPreparedMeeting.setInProgress(true);
			mCheckStop = true;
			AttendeesCheckListFragment fragment = new AttendeesCheckListFragment();
			((MainActivity) Objects.requireNonNull(getActivity())) .replaceFragment(fragment, "frags");
		});
		mCheckedRoom.setOnClickListener(v -> {
			mPreparedMeeting.setSubject(Objects.requireNonNull(mEditSujet.getEditText()).getText().toString());
			mPreparedMeeting.setInProgress(true);
			mCheckStop = true;
			RoomCheckListFragment fragment = new RoomCheckListFragment();
			((MainActivity) Objects.requireNonNull(getActivity())) .replaceFragment(fragment, "frags");
		});
		//initList();
		return view;
	}

	@Override
	public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
	}

	private void enableButton() {
		if(mPreparedMeeting.getDate()!=null && mPreparedMeeting.getSubject()!=null && mPreparedMeeting.getStartDate()!=null
		&& mPreparedMeeting.getAttendees()!=null && mPreparedMeeting.getRoom()!=null) {
			mValidButton.setEnabled(true);
		}
	}

	private void initList() {
		mValidButton.setEnabled(false);
		enableButton();
		if(mPreparedMeeting.isInProgress()) {
			if(mPreparedMeeting.getDate() != null) {
				String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(mPreparedMeeting.getDate().getTime());
				mDateBig.setText(currentDateString);
				mDate.setText("Date");
				mYear = mPreparedMeeting.getDate().YEAR;
				mMonth = mPreparedMeeting.getDate().MONTH;
				mDayOfMonth = mPreparedMeeting.getDate().DAY_OF_MONTH;
			}
			if(mPreparedMeeting.getStartDate() != null){
				int hourOfDay = mPreparedMeeting.getStartDate().HOUR_OF_DAY;
				int minute = mPreparedMeeting.getStartDate().MINUTE;
				Log.d(TAG, "initList: DEBUG:" + String.format("%02d", hourOfDay) + " : " + String.format("%02d", minute));
				mTimeBig.setText(String.format("%02d", hourOfDay) + " : " + String.format("%02d", minute));
				mTime.setText("Time");
			}

			if(mPreparedMeeting.getSubject() != null)
				Objects.requireNonNull(mEditSujet.getEditText()).setText(mPreparedMeeting.getSubject());
			String mAttendeesToText = "";
			ArrayList<Employee> mEmployeeToText = mPreparedMeeting.getAttendees();
			if(mEmployeeToText != null) {
				for(Employee in : mEmployeeToText) {
					mAttendeesToText += in.getName();
					if(in != (mEmployeeToText.get(mEmployeeToText.size() - 1))) {
						mAttendeesToText += ", ";
					}
					mAttendees.setText(mAttendeesToText);
				}
			}
			if(mPreparedMeeting.getRoom() != null) {
				mColor.setColorFilter(mPreparedMeeting.getRoom().getRoomColor());
				mColorAtten.setColorFilter(mPreparedMeeting.getRoom().getRoomColor());
				mColorDate.setColorFilter(mPreparedMeeting.getRoom().getRoomColor());
				mColorRoom.setColorFilter(mPreparedMeeting.getRoom().getRoomColor());
				mColorSubject.setColorFilter(mPreparedMeeting.getRoom().getRoomColor());
				mColorTime.setColorFilter(mPreparedMeeting.getRoom().getRoomColor());
				String mRoomToText = "";
				int mRoomNumber = mPreparedMeeting.getRoom().getRoomNumber();
				switch(mRoomNumber) {
					case 1:
						mRoomToText = "Salle 1";
						break;
					case 2:
						mRoomToText = "Salle 2";
						break;
					case 3:
						mRoomToText = "Salle 3";
						break;
					case 4:
						mRoomToText = "Salle 4";
						break;
					case 5:
						mRoomToText = "Salle 5";
						break;
					case 6:
						mRoomToText = "Salle 6";
						break;
					case 7:
						mRoomToText = "Salle 7";
						break;
					case 8:
						mRoomToText = "Salle 8";
						break;
					case 9:
						mRoomToText = "Salle 9";
						break;
					case 10:
						mRoomToText = "Salle 10";
						break;
				}
				mCheckedRoom.setText(mRoomToText);
			}
		}
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
		if (!mCheckStop) {
			Fragment fragment = new ListMeetingFragment();
			((MainActivity) Objects.requireNonNull(getActivity())).replaceFragment(fragment, "frags");
		}
	}


	@Override
	public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
		mYear = year;
		mMonth = month;
		mDayOfMonth = dayOfMonth;
		mC.set(Calendar.YEAR, year);
		mC.set(Calendar.MONTH, month);
		mC.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(mC.getTime());
		mDate.setText("Date");
		Log.d(TAG, "onDateSet: DEBUG:" + currentDateString);
		mDateBig.setText(currentDateString);
		mPreparedMeeting.setDate(mC);
		Log.d(TAG, "onDateSet: DEBUG: mC" + mC.getTime());
		mPreparedMeeting.setInProgress(true);
		mDateOn = true;
		if (mStartOn && mEndOn) {
			enableButton();
		}
	}

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

			mStartHour = hourOfDay;
			mStartMinute = minute;
			mC.set(Calendar.HOUR_OF_DAY, hourOfDay);
			mC.set(Calendar.MINUTE, minute);
			Calendar mTest = Calendar.getInstance();
			mTest.set(2000,01,01,hourOfDay,minute);
			mPreparedMeeting.setStartDate(mC);
			mPreparedMeeting.setInProgress(true);
			mTimeBig.setText(String.format("%02d", hourOfDay) + " : " + String.format("%02d", minute));
			mTime.setText("Time");
			mStartOn = true;
			mStartDate.set(Calendar.HOUR_OF_DAY, mStartHour);
			mStartDate.set(Calendar.MINUTE, mStartMinute);
			Log.d(TAG, "onTimeSet: DEBUG" + mStartDate.getTime());
			//createStartDate();
			if (mDateOn && mEndOn) {
				enableButton();
			}
	}


	/*private void createStartDate() {
		mStartDate.set(Calendar.HOUR, mStartHour);
		mStartDate.set(Calendar.MINUTE, mStartMinute);
	}*/

	private void createEndDate() {
		mEndDate.set(Calendar.HOUR, mEndHour);
		mEndDate.set(Calendar.MINUTE, mEndMinute);
	}

	@OnClick(R.id.button2)
	void addMeeting() {
		mPreparedMeeting.setSubject(Objects.requireNonNull(mEditSujet.getEditText()).getText().toString());
		Calendar mFinal;
		mPreparedMeeting.setRoom(mPreparedMeeting.getRoom());
		mFinal = mPreparedMeeting.getDate();
		Meeting mMeeting = new Meeting(mFinal, 45, mPreparedMeeting.getRoom(),
				mPreparedMeeting.getSubject(), mPreparedMeeting.getAttendees());
		mApiService.createMeeting(mMeeting);
		mPreparedMeeting.setInProgress(false);
		mPreparedMeeting.setStartDate(null);
		mPreparedMeeting.setRoom(null);
		mPreparedMeeting.setAttendees(null);
		mPreparedMeeting.setSubject(null);
		mPreparedMeeting.setDate(null);
		Fragment fragment = new ListMeetingFragment();
		((MainActivity) Objects.requireNonNull(getActivity())).replaceFragment(fragment, "frags");
	}
}

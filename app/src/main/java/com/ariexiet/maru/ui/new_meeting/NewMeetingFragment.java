package com.ariexiet.maru.ui.new_meeting;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.ariexiet.maru.R;
import com.ariexiet.maru.di.DI;
import com.ariexiet.maru.model.Employee;
import com.ariexiet.maru.model.ServiceMeeting;
import com.ariexiet.maru.service.MeetingApiService;
import com.ariexiet.maru.MainActivity;
import com.ariexiet.maru.ui.list.ListMeetingContainerFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *  Used to create a new meeting
 */
public class NewMeetingFragment extends Fragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
	public int mYear;
	public int mMonth;
	public int mDayOfMonth;
	public int mStartHour;
	public int mStartMinute;
	public ServiceMeeting mPreparedMeeting;
	public Calendar mStartDate = Calendar.getInstance();
	public Calendar mC = Calendar.getInstance();
	private MeetingApiService mApiService;
	private static NewMeetingFragment mInstance;

	@BindView(R.id.editText_subject)
	public TextInputLayout mEditSubject;
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
	public ImageView mColorAttendee;
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
	@BindView(R.id.error)
	public TextView mError;
	@BindView(R.id.button_create)
	public Button mValidButton;

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
		Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).hide();

		mApiService = DI.getMeetingApiService();
		mPreparedMeeting = mApiService.getServiceMeeting();

		mDateBig.setOnClickListener(v -> {
			DialogFragment datePicker = new DatePickerFragment(mInstance);
			datePicker.show(NewMeetingFragment.this.getParentFragmentManager(), "date picker");
		});
		mTimeBig.setOnClickListener(v -> {
			DialogFragment timePicker = new TimePickerFragment(mInstance);
			timePicker.show(NewMeetingFragment.this.getParentFragmentManager(), "time picker");
		});
		mAttendees.setOnClickListener(v -> {
			mPreparedMeeting.setSubject(Objects.requireNonNull(mEditSubject.getEditText()).getText().toString());
			mPreparedMeeting.setInProgress(true);
			AttendeesCheckListFragment fragment = AttendeesCheckListFragment.newInstance();
			((MainActivity) requireActivity()) .replaceFragment(fragment, "frags");
		});
		mCheckedRoom.setOnClickListener(v -> {
			mPreparedMeeting.setSubject(Objects.requireNonNull(mEditSubject.getEditText()).getText().toString());
			mPreparedMeeting.setInProgress(true);
			((MainActivity) requireActivity()) .replaceFragment(RoomCheckListFragment.newInstance("new"), "frags");
		});
		return view;
	}

	@Override
	public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
	}

	/**
	 *  Enable the button to create meeting when every data is acquired
	 *  and if the meeting room is available at required time
	 */
	private void enableButton() {
		boolean isAvailable = false;
		if (mPreparedMeeting.getRoom() != null) {
			for(int i = 0; i < mApiService.getMeetings().size(); i++) {
				long meetingInListTime = mApiService.getMeetings().get(i).getDate().getTimeInMillis();
				int meetingInListRoom = mApiService.getMeetings().get(i).getRoom().getRoomNumber();
				long meetingToTestTime = mPreparedMeeting.getDate().getTimeInMillis();
				if(meetingInListRoom == mPreparedMeeting.getRoom().getRoomNumber() &&
						meetingInListTime < meetingToTestTime && meetingToTestTime < (meetingInListTime + 2700000) ||
						(meetingToTestTime + 2700000) > meetingInListTime && (meetingToTestTime + 2700000) < (meetingInListTime + 2700000)) {
					mError.setText(R.string.not_available);
					mError.setTextColor(0xFFFF0000);
					isAvailable = false;
					break;
				}else {
					isAvailable = true;
				}
			}
			if(mPreparedMeeting.getDate() != null && mPreparedMeeting.getSubject() != null && mPreparedMeeting.getStartDate() != null
					&& mPreparedMeeting.getAttendees() != null && mPreparedMeeting.getRoom() != null && isAvailable) {
				mValidButton.setEnabled(true);
				mError.setText("");
			}
		}
	}

	private void initPrepMeet() {
		mValidButton.setEnabled(false);
		Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).hide();
		enableButton();
		if(mPreparedMeeting.isInProgress()) {
			if(mPreparedMeeting.getDate() != null) {
				String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(mPreparedMeeting.getDate().getTime());
				mDateBig.setText(currentDateString);
				mDate.setText("Date");
				mYear = mPreparedMeeting.getDate().get(Calendar.YEAR);
				mMonth = mPreparedMeeting.getDate().get(Calendar.MONTH);
				mDayOfMonth = mPreparedMeeting.getDate().get(Calendar.DAY_OF_MONTH);
			}
			if(mPreparedMeeting.getStartDate() != null){
				int hourOfDay = mPreparedMeeting.getDate().get(Calendar.HOUR);
				int minute = mPreparedMeeting.getDate().get(Calendar.MINUTE);
				mTimeBig.setText(String.format("%02d", hourOfDay) + " : " + String.format("%02d", minute));
				mTime.setText("Time");
			}

			if(mPreparedMeeting.getSubject() != null)
				Objects.requireNonNull(mEditSubject.getEditText()).setText(mPreparedMeeting.getSubject());
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
				mColorAttendee.setColorFilter(mPreparedMeeting.getRoom().getRoomColor());
				mColorDate.setColorFilter(mPreparedMeeting.getRoom().getRoomColor());
				mColorRoom.setColorFilter(mPreparedMeeting.getRoom().getRoomColor());
				mColorSubject.setColorFilter(mPreparedMeeting.getRoom().getRoomColor());
				mColorTime.setColorFilter(mPreparedMeeting.getRoom().getRoomColor());
				String mRoomToText;
				mRoomToText = mPreparedMeeting.getRoom().getRoomName();
				mCheckedRoom.setText(mRoomToText);
			}
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		initPrepMeet();
		enableButton();
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
		mDateBig.setText(currentDateString);
		mPreparedMeeting.setDate(mC);
		mPreparedMeeting.setInProgress(true);
		enableButton();
	}

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			mStartHour = hourOfDay;
			mStartMinute = minute;
			mC.set(Calendar.HOUR_OF_DAY, hourOfDay);
			mC.set(Calendar.MINUTE, minute);
			mPreparedMeeting.setStartDate(mC);
			mPreparedMeeting.setInProgress(true);
			mTimeBig.setText(String.format("%02d", hourOfDay) + " : " + String.format("%02d", minute));
			mTime.setText("Time");
			mStartDate.set(Calendar.HOUR_OF_DAY, mStartHour);
			mStartDate.set(Calendar.MINUTE, mStartMinute);
			enableButton();
	}

	/**
	 *  Used to create a meeting with the information from user
	 */
	@OnClick(R.id.button_create)
	void addMeeting() {
		mPreparedMeeting.setSubject(Objects.requireNonNull(mEditSubject.getEditText()).getText().toString());
		mApiService.createMeeting(mPreparedMeeting.getDate(), mPreparedMeeting.getRoom(),
				mPreparedMeeting.getSubject(), mPreparedMeeting.getAttendees());
		mPreparedMeeting.setInProgress(false);
		mPreparedMeeting.setStartDate(null);
		mPreparedMeeting.setRoom(null);
		mPreparedMeeting.setAttendees(null);
		mPreparedMeeting.setSubject(null);
		mPreparedMeeting.setDate(null);
		Fragment fragment = new ListMeetingContainerFragment();
		((MainActivity) requireActivity()).replaceFragment(fragment, "frags");
	}
}

package com.ariexiet.maru.service;

import android.util.Log;


import com.ariexiet.maru.model.Employee;
import com.ariexiet.maru.model.Meeting;
import com.ariexiet.maru.model.MeetingRoom;
import com.ariexiet.maru.model.ServiceMeeting;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {
	private final List<Meeting> mMeetings = DummyMeetingGenerator.generateMeetings();
	private List<Meeting> mMeetingsByDate = null;
	private List<Meeting> mMeetingsByRoom = null;
	private final List<MeetingRoom> mMeetingRooms = DummyMeetingGenerator.generateMeetingRooms();
	private final List<Employee> mEmployees = DummyMeetingGenerator.generateEmployees();
	private ServiceMeeting mServiceMeeting = DummyMeetingGenerator.serviceMeeting();
	private static final String TAG = "DummyMeetingApiService";

	@Override
	public List<Meeting> getMeetings() {
		return mMeetings;
	}

	@Override
	public List<MeetingRoom> getMeetingRooms() {
		return mMeetingRooms;
	}

	@Override
	public List<Employee> getEmployees() {
		return mEmployees;
	}

	@Override
	public ServiceMeeting getServiceMeeting() { return mServiceMeeting; }

	@Override
	public void deleteMeeting(Meeting meeting) {
		mMeetings.remove(meeting);
	}

	@Override
	public void createMeeting(Meeting meeting) {
		mMeetings.add(meeting);
	}

	@Override
	public void createPreparedMeeting(ServiceMeeting serviceMeeting) {
		mServiceMeeting = serviceMeeting;
	}

	@Override
	public List<Meeting> getMeetingsByDate(Calendar date) {
		Log.d(TAG, "getMeetingsByDate: DEBUG: " + date.getTime());
		Calendar mTestStart = Calendar.getInstance();
		Calendar mTestEnd = Calendar.getInstance();
		mTestStart.set(Calendar.YEAR, date.get(Calendar.YEAR));
		mTestStart.set(Calendar.MONTH, date.get(Calendar.MONTH));
		mTestStart.set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH));
		mTestStart.set(Calendar.HOUR_OF_DAY, 0);
		mTestStart.set(Calendar.MINUTE, 1);
		mTestEnd.set(Calendar.YEAR, date.get(Calendar.YEAR));
		mTestEnd.set(Calendar.MONTH, date.get(Calendar.MONTH));
		mTestEnd.set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH));
		mTestEnd.set(Calendar.HOUR_OF_DAY, 23);
		mTestEnd.set(Calendar.MINUTE, 59);
		Log.d(TAG, "getMeetingsByDate: DEBUG: " + mTestStart.getTime());
		Log.d(TAG, "getMeetingsByDate: DEBUG: " + mTestEnd.getTime());
		mMeetingsByDate = new ArrayList<Meeting>();
		for (Meeting in : mMeetings) {
			if (in.getDate().after(mTestStart) && in.getDate().before(mTestEnd)) {
				Log.d(TAG, "getMeetingsByDate: DEBUG: " + in.getSubject() + " " + in.getDate().getTime());
				mMeetingsByDate.add(in);
			}
		}
		return mMeetingsByDate;
	}

	@Override
	public List<Meeting> getMeetingsByRoom(int roomNumber) {
		mMeetingsByRoom = new ArrayList<Meeting>();
		for (Meeting in : mMeetings) {
			if (in.getRoom().getRoomNumber() == roomNumber) {
				mMeetingsByRoom.add(in);
			}
		}
		return  mMeetingsByRoom;
	}
}

package com.ariexiet.maru.service;

import com.ariexiet.maru.model.Employee;
import com.ariexiet.maru.model.Meeting;
import com.ariexiet.maru.model.MeetingRoom;
import com.ariexiet.maru.model.ServiceMeeting;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {
	private final List<Meeting> mMeetings = DummyMeetingGenerator.generateMeetings();
	private List<Meeting> mMeetingsByDate;
	private List<Meeting> mMeetingsByRoom;
	private final List<MeetingRoom> mMeetingRooms = DummyMeetingGenerator.generateMeetingRooms();
	private final List<Employee> mEmployees = DummyMeetingGenerator.generateEmployees();
	private final ServiceMeeting mServiceMeeting = DummyMeetingGenerator.serviceMeeting();

	public DummyMeetingApiService() {
		mMeetingsByDate = null;
		mMeetingsByRoom = null;
	}

	/**
	 *  Used to collect the meetings' list
	 */
	@Override
	public List<Meeting> getMeetings() {
		return mMeetings;
	}

	/**
	 *  Used to collect all rooms in meetings' list
	 */
	@Override
	public List<MeetingRoom> getMeetingRooms() {
		return mMeetingRooms;
	}

	/**
	 *  Used to collect a list of all employee
	 */
	@Override
	public List<Employee> getEmployees() {
		return mEmployees;
	}

	/**
	 *  Used to collect the service meeting
	 */
	@Override
	public ServiceMeeting getServiceMeeting() { return mServiceMeeting; }

	/**
	 *  Used to delete a meeting in generator
	 * @param meeting meeting to delete
	 */
	@Override
	public void deleteMeeting(Meeting meeting) {
		mMeetings.remove(meeting);
	}

	/**
	 * Used to add a meeting in generator
	 * @param date
	 * @param room
	 * @param subject
	 * @param attendees
	 */
	@Override
	public void createMeeting(Calendar date, MeetingRoom room, String subject
			, ArrayList<Employee> attendees) {
		Meeting meeting = new Meeting(date, 45, room,
				subject, attendees);
		mMeetings.add(meeting);
	}

	/**
	 *  Used to filter generator list with a specific date
	 * @param date the day to filter by
	 */
	@Override
	public List<Meeting> getMeetingsByDate(Calendar date) {
		mMeetingsByDate = new ArrayList<>();
		for (Meeting in : mMeetings) {
			if(in.getDate().get(Calendar.YEAR) == date.get(Calendar.YEAR) &&
					in.getDate().get(Calendar.MONTH) == date.get(Calendar.MONTH) &&
					in.getDate().get(Calendar.DAY_OF_MONTH) == date.get(Calendar.DAY_OF_MONTH)) {
				mMeetingsByDate.add(in);
			}
		}
		return mMeetingsByDate;
	}

	/**
	 *  Used to filter generator list with a specific room number
	 * @param roomNumber the room to filter by
	 */
	@Override
	public List<Meeting> getMeetingsByRoom(int roomNumber) {
		mMeetingsByRoom = new ArrayList<>();
		for (Meeting in : mMeetings) {
			if (in.getRoom().getRoomNumber() == roomNumber) {
				mMeetingsByRoom.add(in);
			}
		}
		return  mMeetingsByRoom;
	}
}

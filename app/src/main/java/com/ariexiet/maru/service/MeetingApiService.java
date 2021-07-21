package com.ariexiet.maru.service;


import com.ariexiet.maru.model.Employee;
import com.ariexiet.maru.model.Meeting;
import com.ariexiet.maru.model.MeetingRoom;
import com.ariexiet.maru.model.ServiceMeeting;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public interface MeetingApiService {
	List<Meeting> getMeetings();

	List<Meeting> getMeetingsByDate(Calendar date);

	List<MeetingRoom> getMeetingRooms();

	List<Meeting> getMeetingsByRoom(int roomNumber);

	List<Employee> getEmployees();

	ServiceMeeting getServiceMeeting();

	void deleteMeeting(Meeting meeting);

	void createMeeting(Calendar date, MeetingRoom room, String subject
			, ArrayList<Employee> attendees);

}

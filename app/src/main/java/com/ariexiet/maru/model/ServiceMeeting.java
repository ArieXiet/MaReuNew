package com.ariexiet.maru.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class ServiceMeeting extends Meeting implements Serializable {

	private Calendar mStartDate;
	private boolean mInProgress;

	/**
	 *  Defines the ServiceMeeting object which will be used to store a meeting while it's created by user
	 * @param date the day it takes place
	 * @param length for how long
	 * @param room in which room
	 * @param subject what it is about
	 * @param attendees who's attending
	 * @param inProgress is it currently being booked
	 */
	public ServiceMeeting(Calendar date, int length, Calendar startDate, MeetingRoom room,
	                      String subject, ArrayList<Employee> attendees, boolean inProgress) {
		super(date, length, room, subject, attendees);
		mStartDate = startDate;
		mInProgress = inProgress;
	}

	public boolean isInProgress() {
		return mInProgress;
	}

	public void setInProgress(boolean inProgress) {
		mInProgress = inProgress;
	}

	public Calendar getStartDate() {
		return mStartDate;
	}

	public void setStartDate(Calendar startDate) {
		mStartDate = startDate;
	}

}

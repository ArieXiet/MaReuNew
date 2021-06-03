package com.ariexiet.maru.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class ServiceMeeting implements Serializable {
	private Calendar mDate;
	private Calendar mStartDate;
	private Calendar mEndDate;

	private MeetingRoom mRoom;
	private String mSubject;
	private ArrayList<Employee> mAttendees;
	private boolean mInProgress;

	public ServiceMeeting(Calendar date, Calendar startDate, Calendar endDate, MeetingRoom room,
	                      String subject, ArrayList<Employee> attendees, boolean inProgress) {
		mDate = date;
		mStartDate = startDate;
		mEndDate = endDate;
		mRoom = room;
		mSubject = subject;
		mAttendees = attendees;
		mInProgress = inProgress;
	}

	public boolean isInProgress() {
		return mInProgress;
	}

	public void setInProgress(boolean inProgress) {
		mInProgress = inProgress;
	}

	public Calendar getDate() {
		return mDate;
	}

	public void setDate(Calendar date) {
		mDate = date;
	}

	public MeetingRoom getRoom() {
		return mRoom;
	}

	public void setRoom(MeetingRoom room) {
		this.mRoom = room;
	}

	public String getSubject() {
		return mSubject;
	}

	public void setSubject(String subject) {
		this.mSubject = subject;
	}

	public ArrayList<Employee> getAttendees() {
		return mAttendees;
	}

	public void setAttendees(ArrayList<Employee> attendees) {
		this.mAttendees = attendees;
	}

	public Calendar getStartDate() {
		return mStartDate;
	}

	public void setStartDate(Calendar startDate) {
		mStartDate = startDate;
	}

	public Calendar getEndDate() {
		return mEndDate;
	}

	public void setEndDate(Calendar endDate) {
		mEndDate = endDate;
	}


}

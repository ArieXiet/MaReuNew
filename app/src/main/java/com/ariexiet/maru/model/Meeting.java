package com.ariexiet.maru.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class Meeting implements Serializable {
	private Calendar mDate;
	private int mLength;
	private MeetingRoom mRoom;
	private String mSubject;
	private ArrayList<Employee> mAttendees;

	/**
	 * Defines the Meeting object
	 * @param date the day the meeting should take place
	 * @param length how long it is supposed to last
	 * @param room in which room
	 * @param subject what it is about
	 * @param attendees who is invited to the meeting
	 */
	public Meeting(Calendar date, int length, MeetingRoom room, String subject, ArrayList<Employee> attendees) {
		mDate = date;
		mLength = length;
		mRoom = room;
		mSubject = subject;
		mAttendees = attendees;
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

	public int getLength() {
		return mLength;
	}

	public void setLength(int length) {
		mLength = length;
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

}

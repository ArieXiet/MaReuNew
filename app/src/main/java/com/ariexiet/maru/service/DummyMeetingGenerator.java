package com.ariexiet.maru.service;


import com.ariexiet.maru.model.Employee;
import com.ariexiet.maru.model.Meeting;
import com.ariexiet.maru.model.MeetingRoom;
import com.ariexiet.maru.model.ServiceMeeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public abstract class DummyMeetingGenerator {

	private static final Employee mJean = new Employee("Jean", "jean@lamzone.com");
	private static final Employee mYann = new Employee("Yann", "yann@lamzone.com");
	private static final Employee mArthur = new Employee("Arthur", "arthur@lamzone.com");
	private static final Employee mPierre = new Employee("Pierre", "pierre@lamzone.com");
	private static final Employee mStephane = new Employee("Stephane", "stephane@lamzone.com");
	private static final Employee mMaude = new Employee("Maude", "maude@lamzone.com");
	private static final Employee mValerie =	new Employee("Valerie", "valerie@lamzone.com");
	private static final Employee mLea = new Employee("Léa", "lea@lamzone.com");
	private static final Employee mChloe = new Employee("Chloe", "chloe@lamzone.com");
	private static final Employee mClara = new Employee("Clara", "clara@lamzone.com");
	private static final Employee mEmma = new Employee("Emma", "emma@lamzone.com");
	private static final Employee mJade = new Employee("Jade", "jade@lamzone.com");
	private static final Employee mLouise = new Employee("Louise", "louise@lamzone.com");
	private static final Employee mAlice = new Employee("Alice", "alise@lamzone.com");
	private static final Employee mJulie = new Employee("Julie", "julie@lamzone.com");
	private static final Employee mLouis = new Employee("Louis", "louis@lamzone.com");
	private static final Employee mSimon = new Employee("Simon", "simon@lamzone.com");
	public static final Employee mSebastien = new Employee("Sebastien", "sebastien@lamzone.com");
	private static final Employee mAntoine = new Employee("Antoine", "antoine@lamzone.com");
	public static final Employee mMarc = new Employee("Marc", "marc@lamzone.com");

	private static final List<Employee> DUMMY_EmployeeS = Arrays.asList(
			mJean, mYann, mArthur, mPierre, mStephane, mMaude, mValerie, mLea, mChloe, mClara,
			mEmma, mJade, mLouise, mAlice, mJulie, mLouis, mSimon, mSebastien, mAntoine, mMarc,
			mJean, mYann, mArthur, mPierre, mStephane, mMaude, mValerie, mLea, mChloe, mClara,
			mEmma, mJade, mLouise, mAlice, mJulie, mLouis, mSimon, mSebastien, mAntoine, mMarc);

	public static final MeetingRoom mMeetingRoom1 = new MeetingRoom(1);
	private static final MeetingRoom mMeetingRoom2 = new MeetingRoom(2);
	private static final MeetingRoom mMeetingRoom3 = new MeetingRoom(3);
	private static final MeetingRoom mMeetingRoom4 = new MeetingRoom(4);
	private static final MeetingRoom mMeetingRoom5 = new MeetingRoom(5);
	private static final MeetingRoom mMeetingRoom6 = new MeetingRoom(6);
	private static final MeetingRoom mMeetingRoom7 = new MeetingRoom(7);
	private static final MeetingRoom mMeetingRoom8 = new MeetingRoom(8);
	private static final MeetingRoom mMeetingRoom9 = new MeetingRoom(9);
	private static final MeetingRoom mMeetingRoom10 = new MeetingRoom(10);

	private static final List<MeetingRoom> DUMMY_MEETING_ROOMS = Arrays.asList(
			mMeetingRoom1, mMeetingRoom2, mMeetingRoom3, mMeetingRoom4, mMeetingRoom5,
			mMeetingRoom6, mMeetingRoom7, mMeetingRoom8, mMeetingRoom9, mMeetingRoom10);

	public static final List<Meeting> DUMMY_MEETINGS = Arrays.asList(
			new Meeting(new GregorianCalendar(2020, 7, 7, 13, 0),
					45, mMeetingRoom1, "Subject 1",
					new ArrayList<>(Arrays.asList(mJade, mAlice, mAntoine))),
			new Meeting(new GregorianCalendar(2020, 7, 7, 14, 0),
					50, mMeetingRoom2, "Subject 2",
					new ArrayList<>(Arrays.asList(mSebastien, mMarc, mMaude))),
			new Meeting(new GregorianCalendar(2020, 7, 7, 9, 0),
					30, mMeetingRoom3, "Subject 3",
					new ArrayList<>(Arrays.asList(mPierre, mLouis, mJulie))),
			new Meeting(new GregorianCalendar(2020, 7, 7, 11, 15),
					45, mMeetingRoom4, "Subject 4",
					new ArrayList<>(Arrays.asList(mSimon, mEmma, mValerie))),
			new Meeting(new GregorianCalendar(2020, 7, 7, 14, 20),
					35, mMeetingRoom5, "Subject 5",
					new ArrayList<>(Arrays.asList(mClara, mLouise, mArthur))),
			new Meeting(new GregorianCalendar(2020, 7, 7, 13, 10),
					35, mMeetingRoom6, "Subject 6",
					new ArrayList<>(Arrays.asList(mYann, mJean, mStephane))),
			new Meeting(new GregorianCalendar(2020, 6, 7, 15, 15),
					45, mMeetingRoom7, "Subject 7",
					new ArrayList<>(Arrays.asList(mLea, mChloe, mAntoine))),
			new Meeting(new GregorianCalendar(2020, 7, 7, 10, 30),
					40, mMeetingRoom8, "Subject 8",
					new ArrayList<>(Arrays.asList(mJade, mSebastien, mJulie))),
			new Meeting(new GregorianCalendar(2020, 7, 7, 13, 0),
					55, mMeetingRoom9, "Subject 9",
					new ArrayList<>(Arrays.asList(mLea, mLouis, mClara))),
			new Meeting(new GregorianCalendar(2020, 7, 10, 14, 45),
					55, mMeetingRoom10, "Subject 10",
					new ArrayList<>(Arrays.asList(mChloe, mSimon, mEmma)))
	);

	static List<Employee> generateEmployees() { return new ArrayList<>(DUMMY_EmployeeS) ; }
	static List<Meeting> generateMeetings() {return new ArrayList<>(DUMMY_MEETINGS); }
	static List<MeetingRoom> generateMeetingRooms() {return new ArrayList<>(DUMMY_MEETING_ROOMS);}
	static ServiceMeeting serviceMeeting() {return new ServiceMeeting(Calendar.getInstance(),45,
			Calendar.getInstance(), null,null,null, false);}
}
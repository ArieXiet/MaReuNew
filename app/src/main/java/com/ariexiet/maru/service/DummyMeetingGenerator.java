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

abstract class DummyMeetingGenerator {

	private static Employee mJean = new Employee("Jean", "jean@lamzone.com",0,false);
	private static Employee mYann = new Employee("Yann", "yann@lamzone.com", 1, false);
	private static Employee mArthur = new Employee("Arthur", "arthur@lamzone.com",2, false);
	private static Employee mPierre = new Employee("Pierre", "pierre@lamzone.com",3,false);
	private static Employee mStephane = new Employee("Stéphane", "stephane@lamzone.com",4,false);
	private static Employee mMaude = new Employee("Maude", "maude@lamzone.com",5,false);
	private static Employee mValerie =	new Employee("Valérie", "valerie@lamzone.com",6,false);
	private static Employee mLea = new Employee("Léa", "lea@lamzone.com",7,false);
	private static Employee mChloe = new Employee("Chloé", "chloe@lamzone.com",8,false);
	private static Employee mClara = new Employee("Clara", "clara@lamzone.com",9,false);
	private static Employee mEmma = new Employee("Emma", "emma@lamzone.com",10,false);
	private static Employee mJade = new Employee("Jade", "jade@lamzone.com",11,false);
	private static Employee mLouise = new Employee("Louise", "louise@lamzone.com",12,false);
	private static Employee mAlice = new Employee("Alice", "alise@lamzone.com",13,false);
	private static Employee mJulie = new Employee("Julie", "julie@lamzone.com",14,false);
	private static Employee mLouis = new Employee("Louis", "louis@lamzone.com",15,false);
	private static Employee mSimon = new Employee("Simon", "simon@lamzone.com",16,false);
	private static Employee mSebastien = new Employee("Sébastien", "sebastien@lamzone.com",17,false);
	private static Employee mAntoine = new Employee("Antoine", "antoine@lamzone.com",18,false);
	private static Employee mMarc = new Employee("Marc", "marc@lamzone.com",19,false);

	private static final List<Employee> DUMMY_EmployeeS = Arrays.asList(
			mJean, mYann, mArthur, mPierre, mStephane, mMaude, mValerie, mLea, mChloe, mClara, mEmma, mJade, mLouise, mAlice, mJulie, mLouis, mSimon, mSebastien, mAntoine, mMarc,mJean, mYann, mArthur, mPierre, mStephane, mMaude, mValerie, mLea, mChloe, mClara, mEmma, mJade, mLouise, mAlice, mJulie, mLouis, mSimon, mSebastien, mAntoine, mMarc

	);

	private static MeetingRoom mMeetingRoom1 = new MeetingRoom(1);
	private static MeetingRoom mMeetingRoom2 = new MeetingRoom(2);
	private static MeetingRoom mMeetingRoom3 = new MeetingRoom(3);
	private static MeetingRoom mMeetingRoom4 = new MeetingRoom(4);
	private static MeetingRoom mMeetingRoom5 = new MeetingRoom(5);
	private static MeetingRoom mMeetingRoom6 = new MeetingRoom(6);
	private static MeetingRoom mMeetingRoom7 = new MeetingRoom(7);
	private static MeetingRoom mMeetingRoom8 = new MeetingRoom(8);
	private static MeetingRoom mMeetingRoom9 = new MeetingRoom(9);
	private static MeetingRoom mMeetingRoom10 = new MeetingRoom(10);


	private static final List<MeetingRoom> DUMMY_MEETING_ROOMS = Arrays.asList(
			mMeetingRoom1, mMeetingRoom2, mMeetingRoom3, mMeetingRoom4, mMeetingRoom5, mMeetingRoom6, mMeetingRoom7, mMeetingRoom8, mMeetingRoom9, mMeetingRoom10
	);
	private static final List<Meeting> DUMMY_MEETINGS = Arrays.asList(
			new Meeting(new GregorianCalendar(2020, 7, 7, 13, 0),
					45, mMeetingRoom1, "Blah",
					new ArrayList<>(Arrays.asList(mJade, mAlice, mAntoine))),
			new Meeting(new GregorianCalendar(2020, 7, 7, 14, 0),
					50, mMeetingRoom2, "Blih",
					new ArrayList<>(Arrays.asList(mSebastien, mMarc, mMaude))),
			new Meeting(new GregorianCalendar(2020, 7, 7, 9, 0),
					30, mMeetingRoom3, "Bleh",
					new ArrayList<>(Arrays.asList(mPierre, mLouis, mJulie))),
			new Meeting(new GregorianCalendar(2020, 7, 7, 11, 15),
					45, mMeetingRoom4, "Bloh",
					new ArrayList<>(Arrays.asList(mSimon, mEmma, mValerie))),
			new Meeting(new GregorianCalendar(2020, 7, 7, 14, 20),
					35, mMeetingRoom5, "Bluh",
					new ArrayList<>(Arrays.asList(mClara, mLouise, mArthur))),
			new Meeting(new GregorianCalendar(2020, 7, 7, 13, 10),
					35, mMeetingRoom6, "Blauh",
					new ArrayList<>(Arrays.asList(mYann, mJean, mStephane))),
			new Meeting(new GregorianCalendar(2020, 6, 7, 15, 15),
					45, mMeetingRoom7, "Blouh",
					new ArrayList<>(Arrays.asList(mLea, mChloe, mAntoine))),
			new Meeting(new GregorianCalendar(2020, 7, 7, 10, 30),
					40, mMeetingRoom8, "Bleih",
					new ArrayList<>(Arrays.asList(mJade, mSebastien, mJulie))),
			new Meeting(new GregorianCalendar(2020, 7, 7, 13, 0),
					55, mMeetingRoom9, "Blinh",
					new ArrayList<>(Arrays.asList(mLea, mLouis, mClara))),
			new Meeting(new GregorianCalendar(2020, 7, 10, 14, 45),
					55, mMeetingRoom10, "Blanh",
					new ArrayList<>(Arrays.asList(mChloe, mSimon, mEmma)))
	);

	static List<Employee> generateEmployees() { return new ArrayList<>(DUMMY_EmployeeS) ; }
	static List<Meeting> generateMeetings() {return new ArrayList<>(DUMMY_MEETINGS); }
	static List<MeetingRoom> generateMeetingRooms() {return new ArrayList<>(DUMMY_MEETING_ROOMS);}
	static ServiceMeeting serviceMeeting() {return new ServiceMeeting(Calendar.getInstance(), Calendar.getInstance(),
			Calendar.getInstance(), null,null,null, false);}
}

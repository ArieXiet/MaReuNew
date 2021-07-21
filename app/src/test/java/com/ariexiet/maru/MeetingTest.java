package com.ariexiet.maru;

import com.ariexiet.maru.di.DI;
import com.ariexiet.maru.model.Meeting;
import com.ariexiet.maru.service.DummyMeetingGenerator;
import com.ariexiet.maru.service.MeetingApiService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import static com.ariexiet.maru.service.DummyMeetingGenerator.mMarc;
import static com.ariexiet.maru.service.DummyMeetingGenerator.mSebastien;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class MeetingTest {

	private MeetingApiService mDummyMeetingApiService;
	private final Calendar mDate = Calendar.getInstance();
	private final Random mRandom = new Random();

	@Before
	public void setup() { mDummyMeetingApiService = DI.getMeetingApiService();}

	@Test
	public void createMeetingWithSuccess() {
		int MeetingsBefore = mDummyMeetingApiService.getMeetings().size();
		mDummyMeetingApiService.createMeeting(new GregorianCalendar(2021, 8, 1),
				DummyMeetingGenerator.mMeetingRoom1, "RAS",
				new ArrayList<>(Arrays.asList(mSebastien, mMarc)));
		int MeetingsAfter = mDummyMeetingApiService.getMeetings().size();
		assertEquals(MeetingsAfter, (MeetingsBefore + 1));
	}

	@Test
	public void deleteMeetingWithSuccess() {
		Meeting meetingToDelete = mDummyMeetingApiService.getMeetings().get(0);
		mDummyMeetingApiService.deleteMeeting(meetingToDelete);
		assertFalse(mDummyMeetingApiService.getMeetings().contains(meetingToDelete));
	}

	@Test
	public void dateFilterWithSuccess() {
		mDate.set(2020,7,7);
		List<Meeting> sortedMeetings = mDummyMeetingApiService.getMeetingsByDate(mDate);
		for (Meeting meetingToTest : mDummyMeetingApiService.getMeetings()) {
			if (meetingToTest.getDate().get(Calendar.YEAR) == mDate.get(Calendar.YEAR) &&
					meetingToTest.getDate().get(Calendar.MONTH) == mDate.get(Calendar.MONTH) &&
					meetingToTest.getDate().get(Calendar.DAY_OF_MONTH) == mDate.get(Calendar.DAY_OF_MONTH)){
				assertTrue(sortedMeetings.contains(meetingToTest));
			}else{
				assertFalse(sortedMeetings.contains(meetingToTest));
			}
		}
	}

	@Test
	public void roomFilterWithSuccess() {
		int mRandomRoomNum = mRandom.nextInt(11);
		List<Meeting> sortedMeetings = mDummyMeetingApiService.getMeetingsByRoom(mRandomRoomNum);
		for (Meeting meetingToTest : mDummyMeetingApiService.getMeetings()) {
			if (meetingToTest.getRoom().getRoomNumber() == mRandomRoomNum){
				assertTrue(sortedMeetings.contains(meetingToTest));
			}else{
				assertFalse(sortedMeetings.contains(meetingToTest));
			}
		}
	}
}
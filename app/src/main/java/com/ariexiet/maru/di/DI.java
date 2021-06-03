package com.ariexiet.maru.di;


import com.ariexiet.maru.service.DummyMeetingApiService;
import com.ariexiet.maru.service.MeetingApiService;

public class DI {

	private static final MeetingApiService service = new DummyMeetingApiService();

	public static MeetingApiService getMeetingApiService() {
		return service;
	}

}

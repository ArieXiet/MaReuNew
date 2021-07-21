package com.ariexiet.maru.model;

import android.graphics.Color;
import android.util.Log;

import com.ariexiet.maru.R;

import java.util.Arrays;
import java.util.List;

import static android.content.ContentValues.TAG;


public class MeetingRoom {
	private int mRoomNumber;
	private int mRoomLogo;
	private int mColor;
	private String mName;
	private final List<List<Integer>> mListColor = Arrays.asList(Arrays.asList(255,83,85),Arrays.asList(95,193,204),
			Arrays.asList(140,225,168),Arrays.asList(226,139,218),Arrays.asList(207,181,59),Arrays.asList(9,200,24),
			Arrays.asList(82,75,241),Arrays.asList(176,22,232),Arrays.asList(155,155,155),Arrays.asList(255,255,85));

	/**
	 * Defines the MeetingRoom object
	 * @param roomNumber the assigned number for this room
	 *                   for each number the room will be assigned a logo with a specific color and a label
	 */
	public MeetingRoom(int roomNumber) {
		mRoomNumber = roomNumber;
	}

	public int getRoomNumber() {
		return mRoomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		mRoomNumber = roomNumber;
	}

	public String getRoomName() {
		switch (mRoomNumber) {
			case 1:
				mName = "salle 1";
				break;
			case 2:
				mName = "salle 2";
				break;
			case 3:
				mName = "salle 3";
				break;
			case 4:
				mName = "salle 4";
				break;
			case 5:
				mName = "salle 5";
				break;
			case 6:
				mName = "salle 6";
				break;
			case 7:
				mName = "salle 7";
				break;
			case 8:
				mName = "salle 8";
				break;
			case 9:
				mName = "salle 9";
				break;
			case 10:
				mName = "salle 10";
				break;
		}
		return mName;
	}

	public int getRoomLogo() {
		switch (mRoomNumber) {
			case 1:
				mRoomLogo = R.mipmap.ic_salle1;
				break;
			case 2:
				mRoomLogo = R.mipmap.ic_salle2;
				break;
			case 3:
				mRoomLogo = R.mipmap.ic_salle3;
				break;
			case 4:
				mRoomLogo = R.mipmap.ic_salle4;
				break;
			case 5:
				mRoomLogo = R.mipmap.ic_salle5;
				break;
			case 6:
				mRoomLogo = R.mipmap.ic_salle6;
				break;
			case 7:
				mRoomLogo = R.mipmap.ic_salle7;
				break;
			case 8:
				mRoomLogo = R.mipmap.ic_salle8;
				break;
			case 9:
				mRoomLogo = R.mipmap.ic_salle9;
				break;
			case 10:
				mRoomLogo = R.mipmap.ic_salle10;
				break;
		}
		return mRoomLogo;
	}

	public int getRoomColor() {
		mColor = Color.rgb(mListColor.get(mRoomNumber -1).get(0),
				mListColor.get(mRoomNumber -1).get(1), mListColor.get(mRoomNumber -1).get(2));
		Log.d(TAG, "getRoomColor: DEBUG:" + mColor);
		return mColor;
	}
}

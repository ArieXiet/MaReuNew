package com.ariexiet.maru.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Employee implements Parcelable {

	private String mName;
	private String mEmail;
	private int mPosition;
	private boolean mIsChecked;

	protected Employee(Parcel in) {
		mName = in.readString();
		mEmail = in.readString();
		mPosition = in.readInt();
		mIsChecked = in.readByte() != 0;
	}

	public static final Creator<Employee> CREATOR = new Creator<Employee>() {
		@Override
		public Employee createFromParcel(Parcel in) {
			return new Employee(in);
		}

		@Override
		public Employee[] newArray(int size) {
			return new Employee[size];
		}
	};


	public String getEmail() {
		return mEmail;
	}

	public void setEmail(String email) {
		mEmail = email;
	}

	public Employee(String name, String email, int position, boolean isChecked) {
		mName = name;
		mEmail = email;
		mPosition = position;
		mIsChecked = isChecked;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mName);
		dest.writeString(mEmail);
		dest.writeInt(mPosition);
		dest.writeByte((byte) (mIsChecked ? 1 : 0));
	}
}

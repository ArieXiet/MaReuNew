package com.ariexiet.maru.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Employee implements Parcelable {

	private String mName;
	private String mEmail;

	/**
	 * Defines the Employee object
	 * @param name employee's name
	 * @param email employee's email address
	 */
	public Employee(String name, String email) {
		mName = name;
		mEmail = email;
	}

	protected Employee(Parcel in) {
		mName = in.readString();
		mEmail = in.readString();
	}

	public String getEmail() {
		return mEmail;
	}

	public void setEmail(String email) {
		mEmail = email;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mName);
		dest.writeString(mEmail);
	}
}

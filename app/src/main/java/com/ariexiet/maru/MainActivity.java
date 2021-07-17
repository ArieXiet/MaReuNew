package com.ariexiet.maru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;

import com.ariexiet.maru.di.DI;
import com.ariexiet.maru.model.Meeting;
import com.ariexiet.maru.ui.list.ListMeetingContainerFragment;
import com.ariexiet.maru.ui.list.ListMeetingRecyclerViewAdapter;
import com.ariexiet.maru.ui.new_meeting.DatePickerFragment;
import com.ariexiet.maru.ui.new_meeting.RoomCheckListFragment;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

	private final Context mContext = this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/*
		  if there's no saved instance, launch ListMeetingFragment
		 */
		if (null == savedInstanceState) {
			replaceFragment(ListMeetingContainerFragment.newInstance(mContext, DI.getMeetingApiService().getMeetings()), "frags");
		}
	}

	/**
	 * bound menu with corresponding xml file
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.filter_menu, menu);
		return true;
	}

	/**
	 * used to change fragment
	 */
	public void replaceFragment(Fragment fragment, String tag) {
		Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.container);

		if (currentFragment != null) {
			if(currentFragment.getClass() == fragment.getClass()) {
				return;
			}

			if(getSupportFragmentManager().findFragmentByTag(tag) != null) {
				getSupportFragmentManager()
						.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
			}
		}
		getSupportFragmentManager()
				.beginTransaction()
				.addToBackStack(null)
				.replace(R.id.container, fragment)
				.commit();
	}


	/**
	 * handle action from menu
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		ListMeetingRecyclerViewAdapter mAdapter = ((MyApplication)getApplication()).getRefToAdapter();
		switch(item.getItemId()) {
			case R.id.action_sort:
				DialogFragment datePicker = new DatePickerFragment(this);
				datePicker.show(getSupportFragmentManager(), "date picker");
				return true;
			case R.id.action_sort2:
				replaceFragment(RoomCheckListFragment.newInstance("list"), "frags");
				RoomCheckListFragment fragment = new RoomCheckListFragment();
				replaceFragment(fragment, "frags");
				return true;
			case R.id.action_all:
				List<Meeting> mMeetings = DI.getMeetingApiService().getMeetings();
				mAdapter.sortItems(mMeetings);
				default:
				return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * Used to sort by date
	 */
	@Override
	public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
		Calendar mC = Calendar.getInstance();
		List<Meeting> mMeetingsByDate;
		ListMeetingRecyclerViewAdapter mAdapter = ((MyApplication)getApplication()).getRefToAdapter();
		mC.set(Calendar.YEAR, year);
		mC.set(Calendar.MONTH, month);
		mC.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		mMeetingsByDate = DI.getMeetingApiService().getMeetingsByDate(mC);
		mAdapter.sortItems(mMeetingsByDate);
	}
}
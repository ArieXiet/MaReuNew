package com.ariexiet.maru;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.ariexiet.maru.di.DI;
import com.ariexiet.maru.model.Meeting;
import com.ariexiet.maru.ui.list.ListMeetingFragment;
import com.ariexiet.maru.ui.list.ListMeetingRecyclerViewAdapter;
import com.ariexiet.maru.ui.new_meeting.AttendeesCheckListFragment;
import com.ariexiet.maru.ui.new_meeting.DatePickerFragment;
import com.ariexiet.maru.ui.new_meeting.NewMeetingFragment;
import com.ariexiet.maru.ui.new_meeting.RoomCheckListFragment;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, RoomCheckListFragment.ISelectedRoomListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/*
		  if there's no saved instance, launch ListMeetingFragment
		 */
		if (null == savedInstanceState) {
			replaceFragment(ListMeetingFragment.newInstance(), "frags");
		}
	}

	/**
	 * bound menu with corresponding xml file
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_de_filtrage, menu);
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
	 * open NewMeetingFragment when fab (R.id.floatingActionButton) is clicked
	 */
	public void newMeeting(View view) {
		replaceFragment(NewMeetingFragment.newInstance(), "frags");
	}


	/**
	 * show or hide fab when needed
	 */
	@Override
	public void onAttachFragment(@NonNull Fragment fragment) {
		super.onAttachFragment(fragment);
		if (fragment instanceof ListMeetingFragment) {
			this.findViewById(R.id.floatingActionButton).setVisibility(View.VISIBLE);

		} else if (fragment instanceof AttendeesCheckListFragment) {
			Objects.requireNonNull(this.getSupportActionBar()).hide();
			this.findViewById(R.id.floatingActionButton).setVisibility(View.INVISIBLE);
		} else {
			Objects.requireNonNull(this.getSupportActionBar()).hide();
			this.findViewById(R.id.floatingActionButton).setVisibility(View.INVISIBLE);
		}
	}

	/**
	 * handle action from menu
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		ListMeetingRecyclerViewAdapter mAdapter = ((MyApplication)getApplication()).getRefToAdapter();//TODO a changer
		switch(item.getItemId()) {
			case R.id.action_sort:
				DialogFragment datePicker = new DatePickerFragment(this);
				datePicker.show(getSupportFragmentManager(), "date picker");
				//ListMeetingFragment.newInstance().sortItemsByDate();
				//mAdapter.sortItemsByDate();//TODO a changer
				Toast.makeText(this, "Item 1 selected", Toast.LENGTH_SHORT).show();

				return true;
			case R.id.action_sort2:
				mAdapter.sortItemsByRoom();//TODO a changer
				Toast.makeText(this, "Item 2 selected", Toast.LENGTH_SHORT).show();
				//mMeetingsByDate = DI.getMeetingApiService().getMeetingsByDate(mC);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
		Calendar mC = Calendar.getInstance();
		List<Meeting> mMeetingsByDate;
		ListMeetingRecyclerViewAdapter mAdapter = ((MyApplication)getApplication()).getRefToAdapter();
		mC.set(Calendar.YEAR, year);
		mC.set(Calendar.MONTH, month);
		mC.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		mMeetingsByDate = DI.getMeetingApiService().getMeetingsByDate(mC);
		mAdapter.sortItemsByDate(mMeetingsByDate);
		// TODO a verifier / enlever mAdapter.sortItemsByDate(mMeetingsByDate);
	}

	@Override
	public void getSelectedRoom(int roomNumber) {

	}
}
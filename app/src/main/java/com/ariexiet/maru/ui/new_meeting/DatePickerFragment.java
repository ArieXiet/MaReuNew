package com.ariexiet.maru.ui.new_meeting;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {

	private final DatePickerDialog.OnDateSetListener mListener;

	@NonNull
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		return new DatePickerDialog(getActivity(), mListener, year, month, day);
	}

	public DatePickerFragment(DatePickerDialog.OnDateSetListener listener) {
		mListener = listener;
	}

	@Override
	public void onStop() {
		super.onStop();
		//getActivity().getSupportFragmentManager().popBackStack("frags", FragmentManager.POP_BACK_STACK_INCLUSIVE);
	}
}
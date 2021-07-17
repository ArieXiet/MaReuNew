package com.ariexiet.maru.ui.new_meeting;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.ariexiet.maru.R;
import com.ariexiet.maru.model.Employee;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *  Displays each employees data with a checkbox for the list
 */
public class AttendeesCheckListRecyclerViewAdapter extends RecyclerView.Adapter<AttendeesCheckListRecyclerViewAdapter.ViewHolder> {

	private static List<Employee> mEmployees;
	private static Context mContext;
	public ArrayList<Employee> mCheckedEmployees = new ArrayList<>();
	static SparseBooleanArray itemStateArray = new SparseBooleanArray();

	public AttendeesCheckListRecyclerViewAdapter(List<Employee> employees, Context context) {
		mEmployees = employees;
		mContext = context;
	}

	public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		@BindView(R.id.checkbox)
		public CheckBox mCheckBox;
		@BindView(R.id.attendee_name_check)
		public TextView mAttendeeName;
		@BindView(R.id.attendee_email_check)
		public TextView mAttendeeEmail;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
			itemView.setOnClickListener(this);
		}
		public void bind (int position) {
			int adapterPosition = getBindingAdapterPosition();
			if (!itemStateArray.get(position, false)) {
				mCheckBox.setChecked(false);
				itemStateArray.put(adapterPosition, false);
			}
			else {
				mCheckBox.setChecked(true);
				itemStateArray.put(adapterPosition, true);
			}
		}

		@Override
		public void onClick(View v) {
		}
	}
	@NonNull
	@Override
	public AttendeesCheckListRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_attendees_check,parent,false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull final AttendeesCheckListRecyclerViewAdapter.ViewHolder holder, final int position) {
		holder.bind(position);
		final Employee mEmployee = mEmployees.get(position);
		if (!mCheckedEmployees.contains(mEmployee)) {
			holder.mCheckBox.setChecked(false);
		}
		holder.mCheckBox.setOnClickListener(v -> {
			if(holder.mCheckBox.isChecked()) {
				itemStateArray.put(position, true);
				mCheckedEmployees.add(mEmployee);
			} else {
				itemStateArray.put(position, false);
				mCheckedEmployees.remove(mEmployee);
			}
		});
		holder.mAttendeeName.setText(mEmployee.getName());
		holder.mAttendeeEmail.setText(mEmployee.getEmail());
	}

	@Override
	public int getItemCount() {
		if (mEmployees == null) {
			return 0;
		}
		return mEmployees.size();
	}
}

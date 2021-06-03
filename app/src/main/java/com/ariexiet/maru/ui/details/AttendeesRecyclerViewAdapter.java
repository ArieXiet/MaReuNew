package com.ariexiet.maru.ui.details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ariexiet.maru.R;
import com.ariexiet.maru.model.Employee;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AttendeesRecyclerViewAdapter extends RecyclerView.Adapter<AttendeesRecyclerViewAdapter.ViewHolder> {

	private static List<Employee> mAttendees;
	private static int mColor;
	private static Context mContext;

	public AttendeesRecyclerViewAdapter(List<Employee> attendees, int color, Context context) {
		mAttendees = attendees;
		mContext = context;
		mColor = color;
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		@BindView(R.id.attendee_name)
		public TextView mAttendeeName;
		@BindView(R.id.attendee_email)
		public TextView mAttendeeEmail;
		@BindView(R.id.layout)
		public ConstraintLayout mLayout;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}
	@NonNull
	@Override
	public AttendeesRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_attendees,parent,false);
		return new AttendeesRecyclerViewAdapter.ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull AttendeesRecyclerViewAdapter.ViewHolder holder, int position) {
		final Employee mAttendee = mAttendees.get(position);
		holder.mAttendeeEmail.setText(mAttendee.getEmail());
		holder.mAttendeeName.setText(mAttendee.getName());
		holder.mLayout.setBackgroundColor(mColor);
	}

	@Override
	public int getItemCount() {
		return mAttendees.size();
	}
}

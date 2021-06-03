package com.ariexiet.maru.ui.list;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ariexiet.maru.MainActivity;
import com.ariexiet.maru.R;
import com.ariexiet.maru.di.DI;
import com.ariexiet.maru.model.Employee;
import com.ariexiet.maru.model.Meeting;
import com.ariexiet.maru.ui.details.DetailsMeetingFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListMeetingRecyclerViewAdapter extends RecyclerView.Adapter<ListMeetingRecyclerViewAdapter.ViewHolder> {
	private static List<Meeting> mMeetings;
	private static Context mContext;
	public int mYear;
	public int mMonth;
	public int mDayOfMonth;
	public Calendar mC = Calendar.getInstance();

	public ListMeetingRecyclerViewAdapter(List<Meeting> meetings, Context context) {
		mMeetings = meetings;
		mContext = context;
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		@BindView(R.id.room_logo)
		public ImageView mRoomLogo;
		@BindView(R.id.item_list_name)
		public TextView mMeetingName;
		@BindView(R.id.item_list_participants)
		public TextView mParticipants;
		@BindView(R.id.item_list_delete_button)
		public ImageButton mDeleteButton;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					DetailsMeetingFragment fragment = DetailsMeetingFragment.newInstance(mMeetings.get(ViewHolder.this.getAdapterPosition()), mContext);
					((MainActivity) mContext).replaceFragment(fragment, "frags");
				}
			});
		}
	}

	@NonNull
	@Override
	public ListMeetingRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_meeting,parent,false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ListMeetingRecyclerViewAdapter.ViewHolder holder, final int position) {
		final Meeting mMeeting = mMeetings.get(position);
		String mAttendeesToText = "";
		ArrayList<Employee> mEmployeeToText = mMeeting.getAttendees();
		if (mEmployeeToText != null) {
			for(Employee in : mEmployeeToText) {
				mAttendeesToText += in.getName();
				if(in != (mEmployeeToText.get(mEmployeeToText.size() - 1))) {
					mAttendeesToText += ", ";
				}
			}
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		holder.mMeetingName.setText(mMeeting.getSubject() + ", " + dateFormat.format(mMeeting.getDate().getTime()));
		holder.mParticipants.setText(mAttendeesToText);
		holder.mRoomLogo.setImageResource(mMeeting.getRoom().getRoomLogo());
		//holder.mDeleteButton.setOnClickListener(v -> EventBus.getDefault().post(new DeleteMeetingEvent(mMeeting)));
		holder.mDeleteButton.setOnClickListener(view -> {
			DI.getMeetingApiService().deleteMeeting(mMeeting);
			notifyItemRemoved(position);
			});

	}

	public void sortItemsByDate(List<Meeting> meetingsByDate){
		mMeetings = meetingsByDate;
		this.notifyDataSetChanged();
	}

	public void sortItemsByRoom(){
		Collections.sort(mMeetings, new Comparator<Meeting>() {
			@Override
			public int compare(Meeting o1, Meeting o2) {
				int num1 = o1.getRoom().getRoomNumber();
				int num2 = o2.getRoom().getRoomNumber();
				return Integer.compare(num1, num2);
			}
		});
		this.notifyDataSetChanged();
	}

	@Override
	public int getItemCount() {
		if (mMeetings == null) {
			return 0;
		} else {
			return mMeetings.size();
		}
	}

}

package com.ariexiet.maru.ui.new_meeting;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ariexiet.maru.MainActivity;
import com.ariexiet.maru.R;
import com.ariexiet.maru.model.MeetingRoom;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RoomCheckListRecyclerViewAdapter extends RecyclerView.Adapter<RoomCheckListRecyclerViewAdapter.ViewHolder> {

	private static List<MeetingRoom> mRooms;
	private static Context mContext;
	public MeetingRoom mCheckedRoom;
	static SparseBooleanArray itemStateArray = new SparseBooleanArray();
	private static final String TAG = "RoomCheckListRecyc";
	private RecyclerView mRecyclerView;

	public RoomCheckListRecyclerViewAdapter(List<MeetingRoom> rooms, Context context) {
		mRooms = rooms;
		mContext = context;
	}

	public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		@BindView(R.id.checkbox)
		public CheckBox mCheckBox;
		@BindView(R.id.room_name_check)
		public TextView mRoomName;
		@BindView(R.id.room_logo_check)
		public ImageView mRoomLogo;


		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
			itemView.setOnClickListener(this);
		}
		public void bind (int position) {
			int adapterPosition = getAdapterPosition();
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
			/*int adapterPosition = getAdapterPosition();
			if (!itemStateArray.get(adapterPosition, false)) {
				mCheckBox.setChecked(true);
				itemStateArray.put(adapterPosition, true);
			}
			else  {
				mCheckBox.setChecked(false);
				itemStateArray.put(adapterPosition, false);
			}*/
		}
	}
	@NonNull
	@Override
	public RoomCheckListRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_rooms_check,parent,false);
		return new RoomCheckListRecyclerViewAdapter.ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull final RoomCheckListRecyclerViewAdapter.ViewHolder holder, final int position) {
		holder.bind(position);
		final MeetingRoom mRoom = mRooms.get(position);
		if (!(mCheckedRoom == mRoom)) {
			holder.mCheckBox.setChecked(false);
		}
		holder.mCheckBox.setOnClickListener(v -> {
			if(holder.mCheckBox.isChecked()) {
				//holder.mCheckBox.setChecked(false);
				//TODO : passer tout en faux
				mRecyclerView = ((MainActivity)mContext).findViewById(R.id.list_room_check);
				int mCounter = mRecyclerView.getChildCount();
				//programmer boucle pour parcourir les éléments du recycler view
				itemStateArray.put(position, true);
				mCheckedRoom = mRoom;
			} else {
				//holder.mCheckBox.setChecked(true);
				itemStateArray.put(position, false);
				mCheckedRoom = null;
			}
		});

		holder.mRoomName.setText(mRoom.getRoomName());
		holder.mRoomLogo.setImageResource(mRoom.getRoomLogo());
	}


	@Override
	public int getItemCount() {
		if (mRooms == null) {
			return 0;
		}
		return mRooms.size();
	}



}
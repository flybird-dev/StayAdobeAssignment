/*
 *
 *  * @author ghanshyamnayma on 26/6/19 7:19 AM
 *  * @file TODO: Add a class header comment!
 *  * @copyright Tewton Software Solutions. All Rights Reserved.
 *
 */

package com.shyam.stayadobe.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.shyam.stayadobe.R;
import com.shyam.stayadobe.activities.EditNotesActivity;
import com.shyam.stayadobe.db.entities.NotesE;
import com.shyam.stayadobe.utils.Constants;

import java.util.List;

/**
 * @author Ghanshyam Nayma on 31/08/2019.
 */

public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.ViewHolder> {
    private List<NotesE> listOfPlaces;
    private Context mContext;

    public NotesListAdapter(Context mContext, List<NotesE> listOfPlaces) {
        this.mContext = mContext;
        this.listOfPlaces = listOfPlaces;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_notes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        try {
            final NotesE placeModel = listOfPlaces.get(position);
            if (placeModel != null) {
                String title = placeModel.getTitle() + " " + (position + 1);
                holder.mPlaceName.setText(title);
                holder.mDate.setText(placeModel.getDate());
                holder.mDescription.setText(placeModel.getDescription());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, EditNotesActivity.class);
                        intent.putExtra(Constants.MODEL_DATA, new Gson().toJson(placeModel));
                        intent.putExtra(Constants.IS_NEW_NOTE, false);
                        mContext.startActivity(intent);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return listOfPlaces.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mPlaceName, mDate, mDescription;

        private ViewHolder(View itemView) {
            super(itemView);

            mPlaceName = itemView.findViewById(R.id.tv_note_title);
            mDate = itemView.findViewById(R.id.tv_note_date);
            mDescription = itemView.findViewById(R.id.tv_note_description);
        }
    }

}

package com.example.rishabh.simplenotesapp.recyclerViewHelpers;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rishabh.simplenotesapp.R;
import com.example.rishabh.simplenotesapp.database.NotesColumns;

/**
 * Created by Rishabh on 1/26/17.
 */

public class NotesCursorAdapter extends rvCursorAdapter<NotesCursorAdapter.ViewHolder> {


    public NotesCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        ViewHolder vh = new ViewHolder(itemView);
        return vh;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView titleView;
        public final TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleView = (TextView) itemView.findViewById(R.id.note_title_item);
            textView = (TextView) itemView.findViewById(R.id.note_text_item);
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final Cursor cursor) {


        viewHolder.titleView.setText(cursor.getString(NotesColumns.TITLE_INDEX));
        viewHolder.textView.setText(cursor.getString(NotesColumns.TEXT_INDEX));


    }


}

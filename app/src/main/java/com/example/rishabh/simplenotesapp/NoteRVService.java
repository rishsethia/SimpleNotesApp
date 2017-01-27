package com.example.rishabh.simplenotesapp;

import android.content.Intent;
import android.database.Cursor;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.rishabh.simplenotesapp.database.NotesColumns;
import com.example.rishabh.simplenotesapp.database.NotesProvider;

/**
 * Created by Rishabh on 1/27/17.
 */

public class NoteRVService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewsFactory() {

            Cursor mCursor = null;
            @Override
            public void onCreate() {

            }

            @Override
            public void onDataSetChanged() {
                if (mCursor != null){
                    mCursor.close();
                }

                mCursor = getContentResolver().query(NotesProvider.Notes.CONTENT_URI
                , NotesColumns.ALL_COLUMNS,null,null,null);
            }

            @Override
            public void onDestroy() {
                if(mCursor != null){
                    mCursor.close();
                    mCursor = null;
                }

            }

            @Override
            public int getCount() {
                if(mCursor == null){
                    return 0;
                }
                return mCursor.getCount();
            }

            @Override
            public RemoteViews getViewAt(int position) {
                if (position == AdapterView.INVALID_POSITION ||
                        mCursor == null || !mCursor.moveToPosition(position)) {
                    return null;
                }


                RemoteViews rv = new RemoteViews(getPackageName(), R.layout.recycler_view_item);

                rv.setTextViewText(R.id.note_title_item,mCursor.getString(NotesColumns.TITLE_INDEX));
                rv.setTextViewText(R.id.note_text_item,mCursor.getString(NotesColumns.TEXT_INDEX));

                final Intent intent = new Intent();
                intent.putExtra("notesId", mCursor.getInt(NotesColumns
                        .ID_INDEX));

                intent.putExtra("noteTitle", mCursor.getString(NotesColumns.TITLE_INDEX));
                intent.putExtra("noteText", mCursor.getString(NotesColumns
                        .TEXT_INDEX));


                rv.setOnClickFillInIntent(R.id.note_item_layout,intent);
                return rv;
            }

            @Override
            public RemoteViews getLoadingView() {
                return new RemoteViews(getPackageName(), R.layout.recycler_view_item);
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }
        };
    }
}

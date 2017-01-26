package com.example.rishabh.simplenotesapp;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.rishabh.simplenotesapp.database.NotesColumns;
import com.example.rishabh.simplenotesapp.database.NotesProvider;

/**
 * Created by Rishabh on 1/26/17.
 */
public class SaveNoteTask extends AsyncTask<NoteProperties,Void,NoteProperties> {

    private Context mContext;

    @Override
    protected NoteProperties doInBackground(NoteProperties... params) {
        mContext = params[0].getmContext();
        String noteText = params[0].getNoteText();
        String noteTitle = params[0].getNoteTitle();
        long timeInMillis = params[0].getTimeInMillis();

        ContentValues newContentValues = new ContentValues();
        newContentValues.put(NotesColumns.TEXT,noteText);
        newContentValues.put(NotesColumns.TITLE,noteTitle);
        newContentValues.put(NotesColumns.TIME_IN_MILLIS,timeInMillis);

        ContentResolver cr = mContext.getContentResolver();

        cr.insert(NotesProvider.Notes.CONTENT_URI,newContentValues);

        MainActivity.updateWidgets(mContext);
        return params[0];
    }

    @Override
    protected void onPostExecute(NoteProperties noteProperties) {
        Toast.makeText(mContext, mContext.getString(R.string.note_saved_success), Toast
                .LENGTH_SHORT).show();


    }
}
